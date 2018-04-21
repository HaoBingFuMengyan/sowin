package com.hbf.persistence;

import com.google.common.collect.Lists;
import com.hbf.utils.Collections3;
import com.hbf.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DynamicSpecifications {
    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> clazz , final String ...fetch) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                if (Collections3.isNotEmpty(filters)) {

                    List<Predicate> predicates = Lists.newArrayList();
                    for (SearchFilter filter : filters) {
                        // nested path translate, 如Task的名为"user.name"的filedName,
                        // 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.fieldName, ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {

                            if (Collection.class.isAssignableFrom(expression.getJavaType())) {
                                expression = root.join(names[0]).get(names[i]);
                            } else
                                expression = expression.get(names[i]);
                        }

                        for(int i=0;i<fetch.length;i++){
                            String s=fetch[i];
                            root.fetch(s);
                        }
                        Object val = filter.value;
                        if (expression.getJavaType().equals(Date.class)) {
                            if (val instanceof String)
                                if(((String) val).length() > 10)
                                    val = DateUtils.parseDateByFormat(val.toString().replace("T", " "),"yyyy-MM-dd HH:mm:ss");
                                else
                                    val = DateUtils.parseDate(val.toString());
                        }

                        // logic operator
                        switch (filter.operator) {
                            case EQ:
                                if (val == null)
                                    predicates.add(expression.isNull());
                                else
                                    predicates.add(builder.equal(expression, val));
                                break;
                            case LIKE:
                                predicates.add(builder.like(expression, "%" + val + "%"));
                                break;
                            case BLIKE:
                                predicates.add(builder.like(expression, val + "%"));
                                break;
                            case ELIKE:
                                predicates.add(builder.like(expression, "%" + val));
                                break;
                            case GT:
                                predicates.add(builder.greaterThan(expression, (Comparable) val));
                                break;
                            case LT:
                                predicates.add(builder.lessThan(expression, (Comparable) val));
                                break;
                            case GTE:
                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) val));
                                break;
                            case LTE:
                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) val));
                                break;
                            case NE:
                                predicates.add(builder.notEqual(expression, (Comparable) val));
                                break;
                            case IN:
                                CriteriaBuilder.In in = builder.in(expression);
                                if (val != null) {
                                    if (val.getClass().isArray()) {
                                        Object[] itemsArray = (Object[]) val;
                                        for (int i = 0; i < itemsArray.length; i++) {
                                            String v = itemsArray[i].toString();
                                            if (expression.getJavaType().equals(Long.class)) {
                                                in.value(Long.valueOf(v));
                                            } else if (expression.getJavaType().equals(Integer.class)) {
                                                in.value(Integer.valueOf(v));
                                            } else {
                                                in.value(v);
                                            }
                                        }
                                    } else {
                                        String[] spl = val.toString().split(",");
                                        if (spl != null && spl.length > 0) {
                                            for (String v : spl) {
                                                if (expression.getJavaType().equals(Long.class)) {
                                                    in.value(Long.valueOf(v));
                                                } else if (expression.getJavaType().equals(Integer.class)) {
                                                    in.value(Integer.valueOf(v));
                                                } else {
                                                    in.value(v);
                                                }
                                            }
                                        }
                                    }
                                }

                                predicates.add(in);
                                break;
                            case NI:
                                CriteriaBuilder.In notin = builder.in(expression);
                                if (val != null) {
                                    String[] spl = val.toString().split(",");
                                    if (spl != null && spl.length > 0) {
                                        for (String v : spl) {
                                            if (expression.getJavaType().equals(Long.class)) {
                                                notin.value(Long.valueOf(v));
                                            } else if (expression.getJavaType().equals(Integer.class)) {
                                                notin.value(Integer.valueOf(v));
                                            } else {
                                                notin.value(v);
                                            }
                                        }
                                    }
                                }
                                predicates.add(builder.not(notin));
                                break;

                            case GTETIME:
                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) DateUtils.getDate((Date)val)));
                                break;
                            case LTETIME:
                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) DateUtils.getDayEnd((Date)val)));
                                break;
                        }

                    }

                    // 将所有条件用 and 联合起来
                    if (predicates.size() > 0) {
                        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                }

                return builder.conjunction();
            }
        };
    }
}

package com.hbf.persistence;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hbf.utils.B;
import com.hbf.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class SearchFilter {


    public enum Operator {
        EQ, LIKE, GT, LT, GTE, LTE,NE,IN,NN,NI,BLIKE,ELIKE,GTETIME,LTETIME
    }

    public String fieldName;
    public Object value;
    public Operator operator;

    public SearchFilter(String fieldName, Operator operator, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }
    public static SearchFilter eq(String field,Object value){
        return new SearchFilter(field,Operator.EQ,value);
    }
    public static SearchFilter like(String field,Object value){
        return new SearchFilter(field,Operator.LIKE,value);
    }

    public static SearchFilter gt(String field,Object value){
        return new SearchFilter(field,Operator.GT,value);
    }
    public static SearchFilter lt(String field,Object value){
        return new SearchFilter(field,Operator.LT,value);
    }
    public static SearchFilter gte(String field,Object value){
        return new SearchFilter(field,Operator.GTE,value);
    }
    public static SearchFilter lte(String field,Object value){
        return new SearchFilter(field,Operator.LTE,value);
    }
    public static SearchFilter in(String field,Object value){
        return new SearchFilter(field,Operator.IN,value);
    }
    public static SearchFilter ne(String field,Object value){
        return new SearchFilter(field,Operator.NE,value);
    }
    public static SearchFilter nn(String field,Object value){
        return new SearchFilter(field,Operator.NN,value);
    }
    public static SearchFilter blike(String field,Object value){
        return new SearchFilter(field,Operator.BLIKE,value);
    }
    public static SearchFilter elike(String field,Object value){
        return new SearchFilter(field,Operator.ELIKE,value);
    }
    public static SearchFilter ni(String field,Object value){
        return new SearchFilter(field,Operator.NI,value);
    }
    public static SearchFilter gtetime(String field,Object value){
        return new SearchFilter(field,Operator.GTETIME,value);
    }
    public static SearchFilter ltetime(String field,Object value){
        return new SearchFilter(field,Operator.LTETIME,value);
    }
    //EQ, , , ,
    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME
     */
    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        return SearchFilter.parse(searchParams, "search_");
    }
    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME
     */
    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams,String prefix) {
        Map<String, SearchFilter> filters = Maps.newHashMap();
        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
            if (B.N(prefix) && key.startsWith(prefix))
                key = key.substring(prefix.length());
            if (value == null || StringUtils.isBlank(value.toString())) {
                continue;
            }

            if(value.equals("*"))
                value=null;
            // 拆分operator与filedAttribute
            String[] names = StringUtils.split(key, "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(key + " is not a valid search filter name");
            }
            String filedName = names[1];
            Operator operator = Operator.valueOf(names[0].toUpperCase());

            // 创建searchFilter
            SearchFilter filter = new SearchFilter(filedName, operator, value);
            filters.put(key, filter);

        }

        return filters;
    }

    public static Object[] toWhere(Map<String, Object> searchParams,String v) {
        return SearchFilter.toWhere(searchParams, v, "search_",0,true);
    }

    public static Object[] toSqlWhere(Map<String, Object> searchParams,String v) {
        return SearchFilter.toWhere(searchParams, v, "search_",1,true);
    }

    public static Object[] toLowerSqlWhere(Map<String, Object> searchParams,String v) {
        return SearchFilter.toLowerWhere(searchParams, v, "search_",1,true);
    }

    public static Object[] toWhere(Map<String, Object> searchParams,String v,boolean havewhere) {
        return SearchFilter.toWhere(searchParams, v, "search_",0,havewhere);
    }

    public static Object[] toSqlWhere(Map<String, Object> searchParams,String v,boolean havewhere) {
        return SearchFilter.toWhere(searchParams, v, "search_",1,havewhere);
    }

    public static Object[] toLowerSqlWhere(Map<String, Object> searchParams,String v,boolean havewhere) {
        return SearchFilter.toLowerWhere(searchParams, v, "search_",1,havewhere);
    }


    /**
     * 根据searchParams中的参数组装成SQL的where语句或是HQL语句
     * @param searchParams
     * @param v
     * @param prefix
     * @param type
     * @return
     */
    public static Object[] toWhere(Map<String, Object> searchParams,String v,String prefix,int type,boolean havewhere) {

        Object[] rs=new Object[2];

        StringBuilder sb=new StringBuilder();
        List<Object> vals= Lists.newArrayList();
        int i=1;
        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || StringUtils.isBlank(value.toString())) {
                continue;
            }
            if (B.N(prefix) && key.startsWith(prefix))
                key = key.substring(prefix.length());
            if(value.equals("*"))
                value="";
            // 拆分operator与filedAttribute
            String[] names = StringUtils.split(key, "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(key + " is not a valid search filter name");
            }
            String field = names[1];
            String filedName =field;
            if(StringUtils.isNotBlank(v))
                filedName=v+"."+filedName;
            if(i>1)
                sb.append(" and ");
            Operator operator = Operator.valueOf(names[0].toUpperCase());
            switch (operator) {
                case EQ:
                    sb.append(filedName+" =?"+(type == 0?i:""));

                    //	predicates.add(builder.equal(expression, val));
                    break;
                case LIKE:
                    sb.append(filedName+" like ?"+(type == 0?i:""));
                    //	predicates.add(builder.like(expression, "%" + val + "%"));
                    value="%"+value+"%";
                    break;
                case BLIKE:
                    sb.append(filedName+" like ?"+(type == 0?i:""));
                    //	predicates.add(builder.like(expression, "%" + val + "%"));
                    value=value+"%";
                    break;
                case ELIKE:
                    sb.append(filedName+" like ?"+(type == 0?i:""));
                    //	predicates.add(builder.like(expression, "%" + val + "%"));
                    value="%"+value;
                    break;
                case GT:
                    sb.append(filedName+" >?"+(type == 0?i:""));
                    //	predicates.add(builder.greaterThan(expression, (Comparable) val));
                    break;
                case LT:
                    sb.append(filedName+" <?"+(type == 0?i:""));

                    //	predicates.add(builder.lessThan(expression, (Comparable) val));
                    break;
                case GTE:
                    sb.append(filedName+" >=?"+(type == 0?i:""));
                    //	predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) val));
                    break;
                case LTE:
                    sb.append(filedName+" <=?"+(type == 0?i:""));
                    //	predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) val));
                    break;
                case NE:
                    sb.append(filedName+" <>?"+(type == 0?i:""));
                    //	predicates.add(builder.notEqual(expression, (Comparable) val));
                    break;
                case NN:
                    sb.append(filedName+" is not null");
                    break;
                case IN:
                    String[] spl=value.toString().split(",");
                    if(spl != null && spl.length>0){
                        sb.append(filedName+" in ("+(type == 0?i:""));
                        for(int j=0;j<spl.length;j++){
                            sb.append((j==0?"":",")+" ?"+(type == 0?i:""));
                            i++;
                            if(filedName.startsWith("i"))
                                vals.add(Integer.parseInt(spl[j]));
                            else
                                vals.add(spl[j]);
                        }
                        sb.append(") ");
                    }
                    break;
                case NI:
                    String[] spl1=value.toString().split(",");
                    if(spl1 != null && spl1.length>0){
                        sb.append(filedName+" not in ("+(type == 0?i:""));
                        for(int j=0;j<spl1.length;j++){
                            sb.append((j==0?"":",")+" ?"+(type == 0?i:""));
                            i++;
                            if(filedName.startsWith("i"))
                                vals.add(Integer.parseInt(spl1[j]));
                            else
                                vals.add(spl1[j]);
                        }
                        sb.append(") ");
                    }
                    break;
                case GTETIME:
                    sb.append(filedName+" >=? 00:00:00"+(type == 0?i:""));
                    break;
                case LTETIME:
                    sb.append(filedName+" <=? 23:59:59"+(type == 0?i:""));
                    break;
            }
            if(operator != Operator.IN && operator != Operator.NI){
                if(field.startsWith("d"))
                    value=DateUtils.parseDate(value.toString());
                vals.add(value);
                i++;
            }
        }
        if(sb.length()>0)
            rs[0]= (havewhere?" where ":"")+sb.toString();
        else
            rs[0]= "";
        rs[1]=vals.toArray();
        return rs;

    }

    /**
     * 根据searchParams中的参数组装成SQL的where语句或是HQL语句
     * @param searchParams
     * @param v
     * @param prefix
     * @param type
     * @return
     */
    public static Object[] toLowerWhere(Map<String, Object> searchParams,String v,String prefix,int type,boolean havewhere) {

        Object[] rs=new Object[2];

        StringBuilder sb=new StringBuilder();
        List<Object> vals=Lists.newArrayList();
        int i=1;
        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || StringUtils.isBlank(value.toString())) {
                continue;
            }
            if (B.N(prefix) && key.startsWith(prefix))
                key = key.substring(prefix.length());
            if(value.equals("*"))
                value="";
            // 拆分operator与filedAttribute
            String[] names = StringUtils.split(key, "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(key + " is not a valid search filter name");
            }
            String field = names[1];
            String filedName =field;
            if(StringUtils.isNotBlank(v))
                filedName=v+"."+filedName;
            if(i>1)
                sb.append(" and ");
            Operator operator = Operator.valueOf(names[0].toUpperCase());
            switch (operator) {
                case EQ:
                    sb.append(filedName+" =?"+(type == 0?i:""));

                    //	predicates.add(builder.equal(expression, val));
                    break;
                case LIKE:
                    sb.append("lower("+ filedName +")"+" like ?"+(type == 0?i:""));
                    //	predicates.add(builder.like(expression, "%" + val + "%"));
                    value="%"+((String)value).toLowerCase()+"%";
                    break;
                case BLIKE:
                    sb.append("lower("+ filedName +")"+" like ?"+(type == 0?i:""));
                    //	predicates.add(builder.like(expression, "%" + val + "%"));
                    value=((String)value).toLowerCase()+"%";
                    break;
                case ELIKE:
                    sb.append("lower("+ filedName +")"+" like ?"+(type == 0?i:""));
                    //	predicates.add(builder.like(expression, "%" + val + "%"));
                    value="%"+((String)value).toLowerCase();
                    break;
                case GT:
                    sb.append(filedName+" >?"+(type == 0?i:""));
                    //	predicates.add(builder.greaterThan(expression, (Comparable) val));
                    break;
                case LT:
                    sb.append(filedName+" <?"+(type == 0?i:""));

                    //	predicates.add(builder.lessThan(expression, (Comparable) val));
                    break;
                case GTE:
                    sb.append(filedName+" >=?"+(type == 0?i:""));
                    //	predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) val));
                    break;
                case LTE:
                    sb.append(filedName+" <=?"+(type == 0?i:""));
                    //	predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) val));
                    break;
                case NE:
                    sb.append(filedName+" <>?"+(type == 0?i:""));
                    //	predicates.add(builder.notEqual(expression, (Comparable) val));
                    break;
                case NN:
                    sb.append(filedName+" is not null");
                    break;
                case IN:
                    String[] spl=value.toString().split(",");
                    if(spl != null && spl.length>0){
                        sb.append(filedName+" in ("+(type == 0?i:""));
                        for(int j=0;j<spl.length;j++){
                            sb.append((j==0?"":",")+" ?"+(type == 0?i:""));
                            i++;
                            if(filedName.startsWith("i"))
                                vals.add(Integer.parseInt(spl[j]));
                            else
                                vals.add(spl[j]);
                        }
                        sb.append(") ");
                    }
                    break;
                case NI:
                    String[] spl1=value.toString().split(",");
                    if(spl1 != null && spl1.length>0){
                        sb.append(filedName+" not in ("+(type == 0?i:""));
                        for(int j=0;j<spl1.length;j++){
                            sb.append((j==0?"":",")+" ?"+(type == 0?i:""));
                            i++;
                            if(filedName.startsWith("i"))
                                vals.add(Integer.parseInt(spl1[j]));
                            else
                                vals.add(spl1[j]);
                        }
                        sb.append(") ");
                    }
                    break;

                case GTETIME:
                    sb.append(filedName+" >=? 00:00:00"+(type == 0?i:""));
                    break;
                case LTETIME:
                    sb.append(filedName+" <=? 23:59:59"+(type == 0?i:""));
                    break;
            }
            if(operator != Operator.IN && operator != Operator.NI){
                if(field.startsWith("d"))
                    value= DateUtils.parseDate(value.toString());
                vals.add(value);
                i++;
            }
        }
        if(sb.length()>0)
            rs[0]= (havewhere?" where ":"")+sb.toString();
        else
            rs[0]= "";
        rs[1]=vals.toArray();
        return rs;

    }
}

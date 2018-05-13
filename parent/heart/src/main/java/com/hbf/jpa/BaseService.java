package com.hbf.jpa;


import com.google.common.collect.Maps;
import com.hbf.exception.E;
import com.hbf.exception.ServiceException;
import com.hbf.persistence.DynamicSpecifications;
import com.hbf.persistence.PageUtils;
import com.hbf.persistence.SearchFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T> {
    protected abstract BaseDao<T, String> getBaseDao();

    protected abstract void BaseSaveCheck(T obj);

    public static Logger log = LoggerFactory.getLogger(BaseService.class);

    public Class<T> getDomainClass() {
        return this.getBaseDao().getDomainClass();
    }

    /**
     * 获取所有单据编号信息列表 List<Product>
     */
    @Transactional(readOnly = true)
    public Page<T> listPage(Pageable page, Map<String, Object> searchParams) {

        Specification<T> spec;
        if (searchParams != null && searchParams.size() > 0) {
            Map<String, SearchFilter> ss = SearchFilter.parse(searchParams);
            spec = DynamicSpecifications.bySearchFilter(ss.values(),
                    getBaseDao().getDomainClass());
        } else
            spec = null;
        return getBaseDao().findAll(spec, page);
    }

    @Transactional(readOnly = true)
    public Page<T> listPage(Pageable page, Specification<T> spec) {

        return getBaseDao().findAll(spec, page);
    }

    /**
     * 根据界面条件、组装条件查询
     *
     * @param page
     * @param searchParams
     * @param searchFilters
     * @return
     */
    @Transactional(readOnly = true)
    public Page<T> listsPage(Pageable page, Map<String, Object> searchParams,
                             SearchFilter... searchFilters) {

        Specification<T> spec;
        Map<String, SearchFilter> ss;
        if (searchParams != null)
            ss = SearchFilter.parse(searchParams);
        else
            ss = Maps.newHashMap();

        for (SearchFilter sf : searchFilters) {
            ss.put(sf.fieldName, sf);
        }
        spec = DynamicSpecifications.bySearchFilter(ss.values(), getBaseDao()
                .getDomainClass());

        return getBaseDao().findAll(spec, page);
    }
    /**
     * @param page
     * @param searchParams 页面传过来的查询条件
     * @param diySpec      构造的查询条件 需要fetch 等情况
     * @return
     */
    @Transactional(readOnly = true)
    public Page<T> listPage(Pageable page, Map<String, Object> searchParams,
                            final Specification<T> diySpec) {

        final Specification<T> spec;
        Map<String, SearchFilter> ss;
        if (searchParams != null)
            ss = SearchFilter.parse(searchParams);
        else
            ss = Maps.newHashMap();

        spec = DynamicSpecifications.bySearchFilter(ss.values(), getBaseDao()
                .getDomainClass());

        Specification<T> last = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                if(diySpec == null)
                    return spec.toPredicate(root, query, cb);
                else
                    return cb.and(spec.toPredicate(root, query, cb),
                            diySpec.toPredicate(root, query, cb));
            }
        };
        return getBaseDao().findAll(last, page);
    }

    @Transactional(readOnly = true)
    public List<T> list(Map<String, Object> searchParams, String sort,
                        SearchFilter... searchFilters) {
        return this.list(searchParams, PageUtils.toSort(sort), searchFilters);
    }

    @Transactional(readOnly = true)
    public List<T> list(Map<String, Object> searchParams, Sort sort,
                        SearchFilter... searchFilters) {

        Specification<T> spec;
        Map<String, SearchFilter> ss;
        if (searchParams != null)
            ss = SearchFilter.parse(searchParams);
        else
            ss = Maps.newHashMap();

        for (SearchFilter sf : searchFilters) {
            if (sf.value == null)
                continue;
            ss.put(sf.fieldName, sf);
        }
        spec = DynamicSpecifications.bySearchFilter(ss.values(), getBaseDao()
                .getDomainClass());

        return getBaseDao().findAll(spec, sort);
    }

    /**
     * 保存或修改单据编号 void
     */
    @Transactional
    public T BaseSave(T obj, String id) throws ServiceException {
        BaseSaveCheck(obj);
        obj = getBaseDao().save(obj);
        return obj;
    }

    /**
     * 根据id数组删除单据编号
     *
     * @param checkboxId 单据编号数组
     */
    @Transactional
    public void BaseDelete(String[] checkboxId) throws ServiceException {
        try {
            for (String id : checkboxId) {
                if (StringUtils.isNotBlank(id)) {
                    T obj = getBaseDao().findOne(id);
                    if (obj != null) {
                        BaseDeleteBefore(id, obj);
                        getBaseDao().delete(obj);
                    }
                }
            }
        } catch (ServiceException ex) {
            E.S(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("根据id数组删除单据编号失败");
        }
    }

    @Transactional
    protected void BaseDeleteBefore(String id, T obj) {

    }

    /**
     * 根据id获取单据编号对象 Product
     */
    @Transactional(readOnly = true)
    public T findOne(String id) {
        return getBaseDao().findOne(id);
    }
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getBaseDao().findAll();
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Map<String, Object> searchParams) {
        Specification<T> spec;
        if (searchParams != null) {
            Map<String, SearchFilter> ss = SearchFilter.parse(searchParams);
            spec = DynamicSpecifications.bySearchFilter(ss.values(),
                    getBaseDao().getDomainClass());
        } else
            spec = null;
        return getBaseDao().findAll(spec);
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Map<String, Object> searchParams, Sort sort) {
        Specification<T> spec;
        if (searchParams != null) {
            Map<String, SearchFilter> ss = SearchFilter.parse(searchParams);
            spec = DynamicSpecifications.bySearchFilter(ss.values(),
                    getBaseDao().getDomainClass());
        } else
            spec = null;
        return getBaseDao().findAll(spec, sort);
    }

//    @Transactional(readOnly = true)
//    public List<T> findAllLimit(Sort sort, int limit, final String... fetch) {
//        Specification<T> spec = new Specification<T>() {
//            @Override
//            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
//                                         CriteriaBuilder cb) {
//                for (int i = 0; i < fetch.length; i++) {
//
//                    root.fetch(fetch[i]);
//                }
//
//                return null;
//            }
//        };
//        return getBaseDao().findAllLimit(spec, sort, limit);
//    }

//    public List<T> findFiltersLimit(Sort sort, int limit,
//                                    SearchFilter... searchFilters) {
//        Specification<T> spec;
//        Map<String, SearchFilter> ss = Maps.newHashMap();
//        for (SearchFilter sf : searchFilters) {
//            ss.put(sf.fieldName, sf);
//        }
//        spec = DynamicSpecifications.bySearchFilter(ss.values(), getBaseDao()
//                .getDomainClass());
//        return getBaseDao().findAllLimit(spec, sort, limit);
//    }

//    @Transactional(readOnly = true)
//    public List<T> findSpecLimit(final Specification<T> spec, Sort sort,
//                                 int limit, final String... fetch) {
//        Specification<T> newspec = new Specification<T>() {
//            @Override
//            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
//                                         CriteriaBuilder cb) {
//                for (int i = 0; i < fetch.length; i++) {
//
//                    root.fetch(fetch[i]);
//                }
//
//                return spec.toPredicate(root, query, cb);
//            }
//        };
//        return getBaseDao().findAllLimit(newspec, sort, limit);
//    }

//    @Transactional(readOnly = true)
//    public List<T> findByColumn(String colName, Object colValue) {
//        return getBaseDao().findByPropertyName(colName, colValue);
//    }

    /**
     * 根据列查找唯一对象
     *
     * @param colName
     * @param colValue
     * @return
     */
    @Transactional(readOnly = true)
    public T findOneByProperty(final String colName, final Object colValue) {
        return getBaseDao().findOne(new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get(colName), colValue);
            }
        });
    }


}

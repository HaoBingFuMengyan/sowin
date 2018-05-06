//package com.hbf.jpa;
//
//import static org.springframework.data.jpa.repository.query.QueryUtils.COUNT_QUERY_STRING;
//import static org.springframework.data.jpa.repository.query.QueryUtils.DELETE_ALL_QUERY_STRING;
//import static org.springframework.data.jpa.repository.query.QueryUtils.applyAndBind;
//import static org.springframework.data.jpa.repository.query.QueryUtils.getQueryString;
//import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.LockModeType;
//import javax.persistence.NoResultException;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Expression;
//import javax.persistence.criteria.Path;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.persistence.criteria.Selection;
//
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.query.QueryUtils;
//import org.springframework.data.jpa.repository.support.JpaEntityInformation;
//import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
//import org.springframework.data.jpa.repository.support.LockMetadataProvider;
//import org.springframework.data.jpa.repository.support.PersistenceProvider;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.Assert;
//
//import com.google.common.collect.Lists;
//
///**
// * Default implementation of the {@link org.springframework.data.repository.CrudRepository} interface. This will offer
// * you a more sophisticated interface than the plain {@link EntityManager} .
// *
// * @author Oliver Gierke
// * @author Eberhard Wolff
// * @author Thomas Darimont
// * @param <T> the type of the entity to handle
// * @param <ID> the type of the entity's identifier
// */
//@org.springframework.stereotype.Repository
//@Transactional(readOnly = true)
//public abstract class SimpleJpaRepositoryEx<T, ID extends Serializable> implements JpaRepository<T, ID>,
//        JpaSpecificationExecutor<T> {
//
//    protected final JpaEntityInformation<T, ?> entityInformation;
//    protected final EntityManager em;
//    protected final PersistenceProvider provider;
//
//    protected LockMetadataProvider lockMetadataProvider;
//
//    /**
//     * Creates a new {@link SimpleJpaRepository} to manage objects of the given {@link JpaEntityInformation}.
//     *
//     * @param entityInformation must not be {@literal null}.
//     * @param entityManager must not be {@literal null}.
//     */
//    public SimpleJpaRepositoryEx(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
//
//        Assert.notNull(entityInformation);
//        Assert.notNull(entityManager);
//
//        this.entityInformation = entityInformation;
//        this.em = entityManager;
//        this.provider = PersistenceProvider.fromEntityManager(entityManager);
//    }
//
//    /**
//     * Creates a new {@link SimpleJpaRepository} to manage objects of the given domain type.
//     *
//     * @param domainClass must not be {@literal null}.
//     * @param em must not be {@literal null}.
//     */
//    public SimpleJpaRepositoryEx(Class<T> domainClass, EntityManager em) {
//        this(JpaEntityInformationSupport.getMetadata(domainClass, em), em);
//    }
//
//    /**
//     * Configures a custom {@link LockMetadataProvider} to be used to detect {@link LockModeType}s to be applied to
//     * queries.
//     *
//     * @param lockMetadataProvider
//     */
//    public void setLockMetadataProvider(LockMetadataProvider lockMetadataProvider) {
//        this.lockMetadataProvider = lockMetadataProvider;
//    }
//
//    public Class<T> getDomainClass() {
//        return entityInformation.getJavaType();
//    }
//
//    private String getDeleteAllQueryString() {
//        return getQueryString(DELETE_ALL_QUERY_STRING, entityInformation.getEntityName());
//    }
//
//    private String getCountQueryString() {
//
//        String countQuery = String.format(COUNT_QUERY_STRING, "x", "%s");
//        return getQueryString(countQuery, entityInformation.getEntityName());
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.repository.CrudRepository#delete(java.io.Serializable)
//     */
//    @Transactional
//    public void delete(ID id) {
//
//        Assert.notNull(id, "The given id must not be null!");
//
//        if (!exists(id)) {
//            throw new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!",
//                    entityInformation.getJavaType(), id), 1);
//        }
//
//        delete(findOne(id));
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
//     */
//    @Transactional
//    public void delete(T entity) {
//
//        Assert.notNull(entity, "The entity must not be null!");
//        em.remove(em.contains(entity) ? entity : em.merge(entity));
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Iterable)
//     */
//    @Transactional
//    public void delete(Iterable<? extends T> entities) {
//
//        Assert.notNull(entities, "The given Iterable of entities not be null!");
//
//        for (T entity : entities) {
//            delete(entity);
//        }
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaRepository#deleteInBatch(java.lang.Iterable)
//     */
//    @Transactional
//    public void deleteInBatch(Iterable<T> entities) {
//
//        Assert.notNull(entities, "The given Iterable of entities not be null!");
//
//        if (!entities.iterator().hasNext()) {
//            return;
//        }
//
//        applyAndBind(getQueryString(DELETE_ALL_QUERY_STRING, entityInformation.getEntityName()), entities, em)
//                .executeUpdate();
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.repository.Repository#deleteAll()
//     */
//    @Transactional
//    public void deleteAll() {
//
//        for (T element : findAll()) {
//            delete(element);
//        }
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaRepository#deleteAllInBatch()
//     */
//    @Transactional
//    public void deleteAllInBatch() {
//        em.createQuery(getDeleteAllQueryString()).executeUpdate();
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see
//     * org.springframework.data.repository.Repository#readById(java.io.Serializable
//     * )
//     */
//    public T findOne(ID id) {
//
//        Assert.notNull(id, "The given id must not be null!");
//        return em.find(getDomainClass(), id);
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.repository.CrudRepository#exists(java.io.Serializable)
//     */
//    public boolean exists(ID id) {
//
//        Assert.notNull(id, "The given id must not be null!");
//
//        if (entityInformation.getIdAttribute() != null) {
//
//            String placeholder = "x";
//            String entityName = entityInformation.getEntityName();
//            Iterable<String> idAttributeNames = entityInformation.getIdAttributeNames();
//            String existsQuery = QueryUtils.getExistsQueryString(entityName, placeholder, idAttributeNames);
//
//            TypedQuery<Long> query = em.createQuery(existsQuery, Long.class);
//
//            if (entityInformation.hasCompositeId()) {
//                for (String idAttributeName : idAttributeNames) {
//                    query.setParameter(idAttributeName, entityInformation.getCompositeIdAttributeValue(id, idAttributeName));
//                }
//            } else {
//                query.setParameter(idAttributeNames.iterator().next(), id);
//            }
//
//            return query.getSingleResult() == 1L;
//        } else {
//            return findOne(id) != null;
//        }
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
//     */
//    public List<T> findAll() {
//        return getQuery(null, (Sort) null).getResultList();
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.repository.CrudRepository#findAll(ID[])
//     */
//    public List<T> findAll(Iterable<ID> ids) {
//
//        if (ids == null || !ids.iterator().hasNext()) {
//            return Collections.emptyList();
//        }
//
//        return getQuery(new Specification<T>() {
//            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                Path<?> path = root.get(entityInformation.getIdAttribute());
//                return path.in(cb.parameter(Iterable.class, "ids"));
//            }
//        }, (Sort) null).setParameter("ids", ids).getResultList();
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaRepository#findAll(org.springframework.data.domain.Sort)
//     */
//    public List<T> findAll(Sort sort) {
//        return getQuery(null, sort).getResultList();
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Pageable)
//     */
//    public Page<T> findAll(Pageable pageable) {
//
//        if (null == pageable) {
//            return new PageImpl<T>(findAll());
//        }
//
//        return findAll(null, pageable);
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findOne(org.springframework.data.jpa.domain.Specification)
//     */
//    public T findOne(Specification<T> spec) {
//
//        try {
//            return getQuery(spec, (Sort) null).getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(org.springframework.data.jpa.domain.Specification)
//     */
//    public List<T> findAll(Specification<T> spec) {
//        return getQuery(spec, (Sort) null).getResultList();
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Pageable)
//     */
//    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
//        TypedQuery<T> query = getQuery(spec, pageable);
//        return pageable == null ? new PageImpl<T>(query.getResultList()) : readPage(query, pageable, spec);
//
//
////		//TypedQuery<T> query = getQuery(spec, pageable);
////		Sort sort = pageable == null ? null : pageable.getSort();
////		CriteriaBuilder builder = em.getCriteriaBuilder();
////		CriteriaQuery<T> query = builder.createQuery(getDomainClass());
////
////
////		Assert.notNull(query);
////		Root<T> root = query.from(getDomainClass());
////		Predicate predicate = spec.toPredicate(root, query, builder);
////
////		if (predicate != null) {
////			query.where(predicate);
////		}
////		query.select(root);
////
////		if (sort != null) {
////			query.orderBy(toOrders(sort, root, builder));
////		}
////		TypedQuery<T> tquery= applyLockMode(em.createQuery(query));
////
////		return pageable == null ? new PageImpl<T>(tquery.getResultList()) : readPage(tquery, pageable, predicate);
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Sort)
//     */
//    public List<T> findAll(Specification<T> spec, Sort sort) {
//
//        return getQuery(spec, sort).getResultList();
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.repository.CrudRepository#count()
//     */
//    public long count() {
//        return em.createQuery(getCountQueryString(), Long.class).getSingleResult();
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#count(org.springframework.data.jpa.domain.Specification)
//     */
//    public long count(Specification<T> spec) {
//
//        return getCountQuery(spec).getSingleResult();
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
//     */
//    @Transactional
//    public <S extends T> S save(S entity) {
//
//        if (entityInformation.isNew(entity)) {
//            em.persist(entity);
//            return entity;
//        } else {
//            return em.merge(entity);
//        }
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaRepository#saveAndFlush(java.lang.Object)
//     */
//    @Transactional
//    public T saveAndFlush(T entity) {
//
//        T result = save(entity);
//        flush();
//
//        return result;
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaRepository#save(java.lang.Iterable)
//     */
//    @Transactional
//    public <S extends T> List<S> save(Iterable<S> entities) {
//
//        List<S> result = new ArrayList<S>();
//
//        if (entities == null) {
//            return result;
//        }
//
//        for (S entity : entities) {
//            result.add(save(entity));
//        }
//
//        return result;
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.jpa.repository.JpaRepository#flush()
//     */
//    @Transactional
//    public void flush() {
//
//        em.flush();
//    }
//
//    /**
//     * Reads the given {@link TypedQuery} into a {@link Page} applying the given {@link Pageable} and
//     * {@link Specification}.
//     *
//     * @param query must not be {@literal null}.
//     * spec can be {@literal null}.
//     * @param pageable can be {@literal null}.
//     * @return
//     */
//    private Page<T> readPage(TypedQuery<T> query, Pageable pageable, Predicate predicate) {
//
//        query.setFirstResult(pageable.getOffset());
//        query.setMaxResults(pageable.getPageSize());
//
//        Long total = QueryUtils.executeCountQuery(getCountQuery(predicate));
//        List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T> emptyList();
//
//        return new PageImpl<T>(content, pageable, total);
//    }
//    /**修改,如何请求需要合计,那么统计合计值
//     * Reads the given {@link TypedQuery} into a {@link Page} applying the given {@link Pageable} and
//     * {@link Specification}.
//     *
//     * @param query must not be {@literal null}.
//     * @param spec can be {@literal null}.
//     * @param pageable can be {@literal null}.
//     * @return
//     */
//    private Page<T> readPage(TypedQuery<T> query, Pageable pageable, Specification<T> spec) {
//
//        query.setFirstResult(pageable.getOffset());
//        query.setMaxResults(pageable.getPageSize());
//
//        Long total = QueryUtils.executeCountQuery(getCountQuery(spec));
//        List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T> emptyList();
//        if(pageable instanceof PageSumRequest){
//            PageSumRequest p=(PageSumRequest)pageable;
//            TypedQuery<Number[]> qq = this.getSummQuery(spec,p.getSumcols());
//            List<Number[]> rs = qq.getResultList();
//            if(rs!=null&&rs.size()==1){
//                SumPageImpl<T> page = new SumPageImpl<T>(content, pageable, total);
//                Object o=rs.get(0);
//                Object[] vals;
//                if(o!=null&&o.getClass().isArray()){
//                    vals=(Object[])o;
//                }
//                else
//                    vals=new Object[]{o==null?0:o};
//                if(vals.length==p.getSumcols().length){
//                    for(int i=0;i<vals.length;i++){
//                        page.getSum().put(p.getSumcols()[i], vals[i]);
//                    }
//                    return page;
//                }
//            }
//        }
//
//        return new PageImpl<T>(content, pageable, total);
//
//
//
//    }
//    /**
//     * Creates a new {@link TypedQuery} from the given {@link Specification}.
//     *
//     * @param spec can be {@literal null}.
//     * @param pageable can be {@literal null}.
//     * @return
//     */
//    private TypedQuery<T> getQuery(Specification<T> spec, Pageable pageable) {
//
//        Sort sort = pageable == null ? null : pageable.getSort();
//        return getQuery(spec, sort);
//    }
//
//    /**
//     * Creates a {@link TypedQuery} for the given {@link Specification} and {@link Sort}.
//     *
//     * @param spec can be {@literal null}.
//     * @param sort can be {@literal null}.
//     * @return
//     */
//    private TypedQuery<T> getQuery(Specification<T> spec, Sort sort) {
//
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
//
//        Root<T> root = applySpecificationToCriteria(spec, query);
//        query.select(root);
//
//        if (sort != null) {
//            query.orderBy(toOrders(sort, root, builder));
//        }
//
//        return applyLockMode(em.createQuery(query));
//    }
//
//    /**
//     * Creates a new count query for the given {@link Specification}.
//     *
//     * @param spec can be {@literal null}.
//     * @return
//     */
//    private TypedQuery<Long> getCountQuery(Specification<T> spec) {
//
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Long> query = builder.createQuery(Long.class);
//
//        Root<T> root = applySpecificationToCriteria(spec, query);
//        root.getFetches().clear();//计数的时候删除fetch
//        if (query.isDistinct()) {
//            query.select(builder.countDistinct(root));
//        } else {
//            query.select(builder.count(root));
//        }
//
//        return em.createQuery(query);
//    }
//    /**
//     * 根据spec 合计列值 fyj 2016-5-11
//     * @param spec
//     * @return
//     */
//    private TypedQuery<Number[]> getSummQuery(Specification<T> spec,String []cols) {
//
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Number[]> query = builder.createQuery(Number[].class);
//
//        Root<T> root = applySpecificationToCriteria(spec, query);
//        root.getFetches().clear();//计数的时候删除fetch
//        List<Selection<?>> list=Lists.newArrayList();
//        for(String s:cols){
//            Expression<Number> s1;
//            String[] ss=s.split("\\*");
//            if(ss.length>1)
//                s1=prod(ss,builder,root);
//            else
//                s1=onePath(s,builder,root);
//            //Path<Number> p=root.get(s);
//            //s1 =builder.sum(p);
//            list.add(builder.sum(s1));
//        }
//        query.distinct(true);
//        query.multiselect(list);
//        return em.createQuery(query);
//    }
//    private  Expression<Number> prod(String[] ss, CriteriaBuilder builder, Root<T> root) {
//
//        Path<Number> p1 = onePath(ss[0], builder, root);
//        Path<Number> p2 = onePath(ss[1], builder, root);
//        return builder.prod(p1, p2);
//
//
//    }
//
//    private Path<Number> onePath(String s, CriteriaBuilder builder, Root<T> root) {
//        Path<Number> p;
//        String[] ss=s.split("\\.");
//        if(ss.length==1){
//            p=root.get(s);
//
//        }
//        else{
//            p=root.get(ss[0]).get(ss[1]);
//
//        }
//        return p;
//    }
//
//    private TypedQuery<Long> getCountQuery(Predicate predicate) {
//
//
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Long> query = builder.createQuery(Long.class);
//
//        Root<T> root = query.from(getDomainClass());
//        if(predicate!=null)
//            query.where(predicate);
//        if (query.isDistinct()) {
//            query.select(builder.countDistinct(root));
//        } else {
//            query.select(builder.count(root));
//        }
//
//        return em.createQuery(query);
//    }
//
//
//    /**
//     * Applies the given {@link Specification} to the given {@link CriteriaQuery}.
//     *
//     * @param spec can be {@literal null}.
//     * @param query must not be {@literal null}.
//     * @return
//     */
//    private <S> Root<T> applySpecificationToCriteria(Specification<T> spec, CriteriaQuery<S> query) {
//
//        Assert.notNull(query);
//        Root<T> root = query.from(getDomainClass());
//
//        if (spec == null) {
//            return root;
//        }
//
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        Predicate predicate = spec.toPredicate(root, query, builder);
//
//        if (predicate != null) {
//            query.where(predicate);
//        }
//
//        return root;
//    }
//
//    private TypedQuery<T> applyLockMode(TypedQuery<T> query) {
//
//        LockModeType type = lockMetadataProvider == null ? null : lockMetadataProvider.getLockModeType();
//        return type == null ? query : query.setLockMode(type);
//    }
//
//
//}
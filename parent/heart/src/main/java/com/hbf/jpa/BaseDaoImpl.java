package com.hbf.jpa;

import com.google.common.collect.Lists;
import com.hbf.exception.E;
import com.hbf.utils.StringHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.ast.QueryTranslatorImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.internal.SessionImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.LockMetadataProvider;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoRepositoryBean
public class BaseDaoImpl<T, ID extends Serializable> extends
        SimpleJpaRepositoryEx<T, ID> implements BaseDao<T, ID> {


    // There are two constructors to choose from, either can be used.
    public BaseDaoImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);

    }

    @Override
    public void setLockMetadataProvider(
            LockMetadataProvider lockMetadataProvider) {
        super.setLockMetadataProvider(lockMetadataProvider);
        this.lockMetadataProvider = lockMetadataProvider;

    }

    @Override
    public void detach(T obj) {
        em.detach(obj);
    }

    public String getSeqNo(String name) {

        try {
            String temp = null;
            Connection conn = ((SessionImpl) em.getDelegate()).connection();
            CallableStatement proc = conn
                    .prepareCall("{call pd_GetSeqNo(?,?)}");

            proc.setString(1, name);
            proc.registerOutParameter(2, Types.VARCHAR);
            proc.execute();
            temp = proc.getString(2);
            proc.close();

            return temp;

        } catch (Exception e) {

            e.printStackTrace();
            System.gc();
            E.S("编号生成故障:" + name);
            return null;
        }

    }

    @Override
    public long count(String nameCol, Object nameVal, String id) {

        boolean hasid = StringUtils.isNotBlank(id);
        String sql = "select count(*)  FROM "
                + getDomainClass().getSimpleName() + " t WHERE t." + nameCol
                + "= :" + nameCol;
        if (hasid)
            sql += " and t.id<> :id";
        Query query = em.createQuery(sql);
        query.setParameter(nameCol, nameVal);
        if (hasid)
            query.setParameter("id", id);
        long i = (Long) query.getSingleResult();

        return i;

    }

    @Override
    public long count(String nameCol, Object nameVal) {

        String sql = "select count(*)  FROM "
                + getDomainClass().getSimpleName() + " t WHERE t." + nameCol
                + "= :" + nameCol;
        Query query = em.createQuery(sql);
        query.setParameter(nameCol, nameVal);
        long i = (Long) query.getSingleResult();
        return i;
    }

    @Override
    public long countIgnoreCase(String nameCol, String nameVal, String id) {
        boolean hasid = StringUtils.isNotBlank(id);
        String sql = "select count(*)  FROM "
                + getDomainClass().getSimpleName() + " t WHERE lower(t."
                + nameCol + ") = :" + nameCol;
        if (hasid)
            sql += " and t.id<> :id";
        Query query = em.createQuery(sql);
        query.setParameter(nameCol, nameVal.toLowerCase());
        if (hasid)
            query.setParameter("id", id);
        long i = (Long) query.getSingleResult();

        return i;
    }

    @Override
    public long countIgnoreCase(String nameCol, String nameVal) {
        String sql = "select count(*)  FROM "
                + getDomainClass().getSimpleName() + " t WHERE lower(t."
                + nameCol + ") = :" + nameCol;
        Query query = em.createQuery(sql);
        query.setParameter(nameCol, nameVal.toLowerCase());
        long i = (Long) query.getSingleResult();
        return i;
    }

    @Override
    public List<T> findByPropertyName(String nameCol, Object nameVal) {
        String sql = "FROM " + getDomainClass().getSimpleName() + " t WHERE t."
                + nameCol + "= :" + nameCol;

        Query query = em.createQuery(sql);
        query.setParameter(nameCol, nameVal);

        return query.getResultList();
    }

    @Override
    public T findOneByPropertyName(final String nameCol, final Object nameVal) {
        // TODO Auto-generated method stub
        return findOne(new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get(nameCol), nameVal);
            }
        });
    }

    @Override
    public List<T> findAllLimit(Class<T> objClass, Specification<T> spec,
                                Sort sort, int limit) {
        if (objClass == null)
            objClass = getDomainClass();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(objClass);

        Root<T> root = query.from(objClass);

        if (spec != null) {
            Predicate predicate = spec.toPredicate(root, query, builder);
            if (predicate != null) {
                query.where(predicate);
            }
        }

        if (sort != null) {
            query.orderBy(QueryUtils.toOrders(sort, root, builder));
        }
        query.select(root);
        TypedQuery<T> typeQuery = em.createQuery(query);
        typeQuery.setMaxResults(limit);
        return typeQuery.getResultList();
    }

    @Override
    public List<?> findBySql(String sql, Object... paras) {
        Query query = em.createNativeQuery(sql);
        if (paras != null && paras.length > 0)
            for (int i = 0; i < paras.length; i++)
                if (paras[i] instanceof Date)
                    query.setParameter(i + 1, (Date) paras[i],
                            TemporalType.DATE);
                else
                    query.setParameter(i + 1, paras[i]);

        return query.getResultList();
    }

    @Override
    public List<?> findByJql(String sql, Object... paras) {
        //

        Query query = em.createQuery(sql);
        if (paras != null)
            for (int i = 0; i < paras.length; i++)
                query.setParameter(i + 1, paras[i]);

        return query.getResultList();
    }

    @Override
    public List<T> findAllLimit(Specification<T> spec, Sort sort, int limit) {
        return this.findAllLimit(getDomainClass(), spec, sort, limit);
    }

    @Override
    public String executeProcedure(String sql, Object[] paras) {
        try {
            String temp = null;
            Connection conn = ((SessionImpl) em.getDelegate()).connection();
            CallableStatement proc = conn.prepareCall(sql);
            int out = 1;
            if (paras != null) {
                for (int i = 0; i < paras.length; i++)
                    proc.setObject(i + 1, paras[i]);
                out = paras.length + 1;
            }

            proc.registerOutParameter(out, Types.VARCHAR);
            proc.execute();
            temp = proc.getString(out);
            proc.close();

            return temp;

        } catch (Exception e) {

            e.printStackTrace();
            System.gc();
            E.S("执行存储过程出错");
            return null;
        }

		/*
		 * Query query = em.createNativeQuery(sql);
		 *
		 * if (paras != null) for (int i = 0; i < paras.length; i++)
		 * query.setParameter(i + 1, paras[i]);
		 *
		 * Object result = query.getSingleResult(); if (result == null) return
		 * ""; return result.toString();
		 */
    }

    @Override
    public Object getFieldValue(String sql, Object... paras) {
        Query query = em.createNativeQuery(sql);
        if (paras != null && paras.length > 0)
            for (int i = 0; i < paras.length; i++)
                if (paras[i] instanceof Date)
                    query.setParameter(i + 1, (Date) paras[i],
                            TemporalType.DATE);
                else
                    query.setParameter(i + 1, paras[i]);
        return query.getSingleResult();

    }

    @Override
    public int executeNativeSql(String sql, Object... paras) {
        Query query = em.createNativeQuery(sql);
        if (paras != null && paras.length > 0)
            for (int i = 0; i < paras.length; i++)
                if (paras[i] instanceof Date)
                    query.setParameter(i + 1, (Date) paras[i],
                            TemporalType.DATE);
                else
                    query.setParameter(i + 1, paras[i]);
        return query.executeUpdate();

    }

    @Override
    public <T> List<T> findBySql(Class<T> objClass, String sql, Object... paras) {
        List<T> list = new ArrayList<T>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            pstmt = ((SessionImpl) em.getDelegate()).connection()
                    .prepareStatement(sql);
            if (paras != null)
                for (int i = 0; i < paras.length; i++) {
                    pstmt.setObject(i + 1, paras[i]);
                }
            res = pstmt.executeQuery();
            while (res.next()) {
                T vo = (T) Class.forName(objClass.getName()).newInstance();
                ResultSetMetaData rmd = res.getMetaData();
                int num = rmd.getColumnCount();
                for (int i = 1; i <= num; i++) {
                    if (res.getObject(rmd.getColumnName(i)) != null)
                        BeanUtils.setProperty(vo, rmd.getColumnName(i)
                                .toLowerCase().equals("sguid") ? "id" : rmd
                                .getColumnName(i).toLowerCase(), res
                                .getObject(rmd.getColumnName(i)));
                }
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        } finally {
            try {
                if (res != null)
                    res.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public <T> List<T> findBySql(Class<T> objClass, String sql, int limit,
                                 Object... paras) {
        List<T> list = new ArrayList<T>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            pstmt = ((SessionImpl) em.getDelegate()).connection()
                    .prepareStatement(sql);
            if (paras != null)
                for (int i = 0; i < paras.length; i++) {
                    pstmt.setObject(i + 1, paras[i]);
                }
            pstmt.setMaxRows(limit);
            res = pstmt.executeQuery();
            while (res.next()) {
                T vo = (T) Class.forName(objClass.getName()).newInstance();
                ResultSetMetaData rmd = res.getMetaData();
                int num = rmd.getColumnCount();
                for (int i = 1; i <= num; i++) {
                    if (res.getObject(rmd.getColumnName(i)) != null)
                        BeanUtils.setProperty(vo, rmd.getColumnName(i)
                                .toLowerCase().equals("sguid") ? "id" : rmd
                                .getColumnName(i).toLowerCase(), res
                                .getObject(rmd.getColumnName(i)));
                }
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        } finally {
            try {
                if (res != null)
                    res.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public <V> Page<V> findPageBySql(Class<V> objClass, String sql,
                                     Pageable pageable, Object... paras) {
        PreparedStatement stmt = null;
        ResultSet res = null;
        List<V> vos = Lists.newArrayList();
        long totalCount = 0;
        try {
            ConvertUtils
                    .register(new com.hbf.utils.DateTimeConverter(), java.util.Date.class);
            // 取总记录数
            totalCount = this.getCountQuery(sql, paras);

            stmt = ((SessionImpl) em.getDelegate()).connection()
                    .prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            if (paras != null)
                for (int i = 0; i < paras.length; i++) {
                    stmt.setObject(i + 1, paras[i]);
                }

            int maxNum = pageable.getOffset() + pageable.getPageSize();
            stmt.setMaxRows((maxNum > totalCount) ? (int) totalCount : maxNum);
            res = stmt.executeQuery();
            res.absolute(pageable.getOffset() + 1);
            res.previous();

            ResultSetMetaData rmd = res.getMetaData();
            int num = rmd.getColumnCount();

            V vo = null;
            while (res.next()) {
                vo = (V) Class.forName(objClass.getName()).newInstance();
                for (int i = 1; i <= num; i++) {
                    if (res.getObject(rmd.getColumnName(i)) != null)
                        BeanUtils.setProperty(vo, rmd.getColumnName(i)
                                .toLowerCase().equals("sguid") ? "id" : rmd
                                .getColumnName(i).toLowerCase(), res
                                .getObject(rmd.getColumnName(i)));
                }
                vos.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        } finally {
            try {
                if (res != null)
                    res.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new PageImpl<V>(vos, pageable, totalCount);
    }


    /**
     * 根据Hql  查询指定对象的分布记录，必须标准的Hql
     *
     * @param objClass
     * @param hql
     * @param pageable
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Page<V> findPageByHql(Class<V> objClass, String hql,
                                     Pageable pageable, Object... params) {
        List<V> entities = Lists.newArrayList();
        org.hibernate.Session session = null;
        long totalCount = 0;
        try {
            SessionFactory sessionFactory = ((SessionImpl) em.getDelegate()).getSessionFactory();
            session = sessionFactory.openSession();
            org.hibernate.Query query = session.createQuery(hql);
            // 得到session工厂实现类
            SessionFactoryImpl sfi = (SessionFactoryImpl) sessionFactory;
            // 得到Query转换器实现类
            QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(hql, hql, Collections.EMPTY_MAP, sfi);
            queryTranslator.compile(Collections.EMPTY_MAP, false);
            // 将hql转为sql，便于统计总条数，作用仅仅是统计条数
            String sql = queryTranslator.getSQLString();
            totalCount = getCountQuery(sql, params);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    if (params[i] != null) {
                        StringHelper helper = new StringHelper();
                        if (helper.isNumber(params[i].toString())) {
                            query.setParameter(i, Integer.valueOf(params[i].toString()));
                        } else if (helper.isDouble(params[i].toString())) {
                            query.setParameter(i, Double.parseDouble(params[i].toString()));
                        } else {
                            query.setParameter(i, params[i]);
                        }
                    }

                }
            }
            query.setFirstResult(pageable.getPageNumber()).setMaxResults(pageable.getOffset() + pageable.getPageSize());
            entities = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        }
        return new PageImpl<V>(entities, pageable, totalCount);
    }


    @Override
    public Object findObjectBySql(Class<T> objClass, String sql,
                                  Object... paras) {
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            T object = (T) Class.forName(objClass.getName()).newInstance();
            pstmt = ((SessionImpl) em.getDelegate()).connection()
                    .prepareStatement(sql);
            if (paras != null)
                for (int i = 0; i < paras.length; i++) {
                    pstmt.setObject(i + 1, paras[i]);
                }
            res = pstmt.executeQuery();
            while (res.next()) {
                ResultSetMetaData rmd = res.getMetaData();
                int num = rmd.getColumnCount();
                for (int i = 1; i <= num; i++) {
                    if (res.getObject(rmd.getColumnName(i)) != null)
                        BeanUtils.setProperty(object, rmd.getColumnName(i)
                                .toLowerCase().equals("sguid") ? "id" : rmd
                                .getColumnName(i).toLowerCase(), res
                                .getObject(rmd.getColumnName(i)));
                }
                break;
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        } finally {
            try {
                if (res != null)
                    res.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 统计当前查询语句的总记录数
     *
     * @param sql
     * @param paras
     * @return
     */
    private long getCountQuery(String sql, Object... paras) {
        return ((BigDecimal) this.getFieldValue("select count(1) from(" + sql
                + ")", paras)).longValue();
    }

    @Override
    public T lock(String id, LockModeType... lockmodetype) {
        T entity = em.find(getDomainClass(), id);
        this.lock(entity, lockmodetype);
        return entity;
    }

    @Override
    public void lock(T entity, LockModeType... lockmodetype) {
        if (lockmodetype.length == 0)
            em.refresh(entity, LockModeType.PESSIMISTIC_WRITE);
        else
            em.refresh(entity, lockmodetype[0]);
    }

    @Override
    public long countIgnoreCaseMember(String nameCol, String nameVal) {
        String sql = "select count(*)  FROM "
                + getDomainClass().getSimpleName() + " t WHERE iauthtype > 0  and lower(t."
                + nameCol + ") = :" + nameCol;
        Query query = em.createQuery(sql);
        query.setParameter(nameCol, nameVal.toLowerCase());
        long i = (Long) query.getSingleResult();
        return i;
    }
}
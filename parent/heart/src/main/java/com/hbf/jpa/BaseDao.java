package com.hbf.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.LockModeType;


@NoRepositoryBean
public interface BaseDao<T,ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {

    Class<T> getDomainClass();
//    String getSeqNo(String name);
//    int executeNativeSql(String sql,Object...paras);
//    String executeProcedure(String sql,Object[] paras);
//    long count(String nameCol, Object nameVal, String id);
//    long countIgnoreCase(String nameCol, String nameVal, String id);
//    long count(String nameCol, Object nameVal);
//    long countIgnoreCase(String nameCol, String nameVal);
//    long countIgnoreCaseMember(String nameCol, String nameVal);
//    List<T> findByPropertyName(String nameCol, Object nameVal);
//    T findOneByPropertyName(String nameCol,Object nameVal);
//    List<T> findAllLimit(Specification<T> spec, Sort sort, int limit);
//    List<T> findAllLimit(Class<T> objClass,Specification<T> spec,Sort sort,int limit);
//    /**
//     * 根据sql返回一个数组集合
//     * @param sql
//     * @param paras
//     * @return
//     */
//    List<?> findBySql(String sql,Object...paras);
//
//    /**
//     * 根据SQL语句查询指定对象的列表记录，通过反射进行字段设置，要求查询语句的列名与对象的属性值一致
//     * @param objClass
//     * @param sql
//     * @param paras
//     * @return
//     */
//    <T> List<T> findBySql(Class<T> objClass,String sql,Object...paras);
//
//    /**
//     * 根据SQL语句查询指定对象的列表记录，通过反射进行字段设置，要求查询语句的列名与对象的属性值一致
//     * @param objClass
//     * @param sql
//     * @param paras
//     * @return
//     */
//    <T> List<T> findBySql(Class<T> objClass,String sql,int limit,Object...paras);
//
//    /**
//     * 根据jql返回一个数组集合
//     * @param sql
//     * @param paras
//     * @return
//     */
//    List<?> findByJql(String sql, Object... paras) ;
//    /**
//     * 根据sql返回一列,返回列必须唯一,返回结果必须唯一
//     * @param sql
//     * @param paras
//     * @return
//     */
//    Object  getFieldValue(String sql,Object ...paras);
//
//    /**
//     * 根据SQL语句查询指定对象的分布记录
//     * @param objClass
//     * @param sql
//     * @param pageable
//     * @param paras
//     * @return
//     */
//    <V> Page<V> findPageBySql(Class<V> objClass, String sql, Pageable pageable, Object...paras);
//
//    /**
//     * 根据Hql  查询指定对象的分布记录，必须标准的Hql
//     * @param objClass
//     * @param Hql
//     * @param pageable
//     * @param paras
//     * @return
//     */
//    <V> Page<V> findPageByHql(Class<V> objClass, String Hql, Pageable pageable, Object[] paras);
//    /**
//     * 根据SQL语句返回单个对象
//     * @param objClass
//     * @param sql
//     * @param paras
//     * @return
//     */
//    Object findObjectBySql(Class<T> objClass,String sql,Object...paras);
//
//    public T lock(String id, LockModeType... lockmodetype);
//
//    public void lock(T entity,LockModeType ...lockmodetype);
//
//    void detach(T obj);
}

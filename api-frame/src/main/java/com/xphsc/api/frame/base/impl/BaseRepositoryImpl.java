package com.xphsc.api.frame.base.impl;

import com.github.xphsc.collect.Lists;
import com.github.xphsc.page.Page;
import com.github.xphsc.page.PageImpl;
import com.xphsc.api.frame.base.BaseRepository;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${huipei.x} on 2016/8/8.
 */
@NoRepositoryBean
public abstract class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    protected final EntityManager em;
    protected final JpaEntityInformation entityInformation;
    protected Class<T> clazz;

    public BaseRepositoryImpl(final JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
        this.entityInformation = entityInformation;
        this.clazz = entityInformation.getJavaType();
    }

    @Override
    public <T> Page<T> queryPageBySQL(StringBuffer sql,Object clazz, Pageable pageable, Sort sort,Class<T> resultClass) {
        StringBuffer newSql;
        SQLQuery query;
        List<T> queryList= Lists.newArrayList();
        if(pageable==null){
            if(sort==null){
                query= createSqlQuery(sql.toString(),clazz);
                queryList=setResultClass(query,resultClass);
                return new PageImpl<T>(0,0,queryList.size(),queryList);
            }else{
                query= createSqlQuery(sql.toString()+getSortSql(sort),clazz);
                queryList=setResultClass(query,resultClass);
                return new PageImpl<T>(0,0,queryList.size(),queryList);
            }
        }else{
            newSql=new StringBuffer (sql+ getSortSql(pageable.getSort()) );
            query= createSqlQuery(newSql.toString(),clazz);
        }
        if(pageable.getPageNumber() !=-1&&pageable.getPageSize() !=-1){
            query.setMaxResults(pageable.getPageSize()).setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        }
        queryList=setResultClass(query,resultClass);
        int total= queryDataCountBySQL(createCountsql(sql), clazz);
        return new PageImpl<T>(pageable.getPageNumber(),pageable.getPageSize(),total,queryList);
    }


    public <T> Page<T> queryPageBySQL(StringBuffer sql, Pageable pageable, Map<String, Object> params,Class<T> resultClass) {
        StringBuffer newSql;
        SQLQuery query;
        List<T> queryList= Lists.newArrayList();
        if(pageable==null){
            query= createSqlQuery(sql.toString(),params);
            queryList=setResultClass(query,resultClass);
            return new PageImpl<T>(0,0,queryList.size(),queryList);
        }else{
            newSql=new StringBuffer (sql+ getSortSql(pageable.getSort()) );
            query= createSqlQuery(newSql.toString(),params);
        }
        if(pageable.getPageNumber() !=-1&&pageable.getPageSize() !=-1){
            query.setMaxResults(pageable.getPageSize()).setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        }
        queryList=setResultClass(query,resultClass);
        int total= queryDataCountBySQL(createCountsql(sql), params);
        return new PageImpl<T>(pageable.getPageNumber(),pageable.getPageSize(),total,queryList);
    }

    public  Page<Map<String,Object>> queryPageBySQL(StringBuffer sql ,Map<String, Object> params, Pageable pageable,Map<String, Object> resultMap) {
        StringBuffer newSql;
        SQLQuery query;
        List<Map<String,Object>> queryList= Lists.newArrayList();
        if(pageable==null){
             query= createSqlQuery(sql.toString(),params);
              queryList=setResultMap(query,resultMap);
              return new PageImpl<Map<String,Object>>(0,0,queryList.size(),queryList);
        }else{
            newSql=new StringBuffer (sql+ getSortSql(pageable.getSort()) );
            query= createSqlQuery(newSql.toString(),params);
        }
        if(pageable.getPageNumber() !=-1&&pageable.getPageSize() !=-1){
            query.setMaxResults(pageable.getPageSize()).setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        }
        queryList=setResultMap(query,resultMap);
        int total= queryDataCountBySQL(createCountsql(sql), params);
        return new PageImpl<Map<String,Object>>(pageable.getPageNumber(),pageable.getPageSize(),total,queryList);
    }


    public int queryDataCountBySQL(StringBuffer sql,Object params) {
        SQLQuery query= createSqlQuery(sql.toString(),params);
        return  ((BigInteger)query.uniqueResult()).intValue();
    }


    public <T> T findByJpql(String jpql,Object... objects) {
        Query query = em.createQuery(jpql);
        if (objects != null) {
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    query.setParameter(i + 1, objects[i]);
                }
            }
        }


        return this.getSingleResult(query);
    }





    private   <T> T getSingleResult(Query query) {
        List objects = query.getResultList();
        return (T) (objects.isEmpty()?null:objects.get(0));
    }

    private String getSortSql(Sort sort){
        if(sort == null)
            return "";
        Iterator<Sort.Order> iterator= sort.iterator();
        StringBuffer sb=new StringBuffer();
        while (iterator.hasNext()){
            Sort.Order order=iterator.next();
            sb.append(" ").append(order.getProperty()).append(" ").append(order.getDirection());
            if( iterator.hasNext() ){
                sb.append(",");
            }
        }
        if( sb.length()>0 )
            return sb.insert(0," ORDER BY ").toString();
        return sb.toString();
    }


    private StringBuffer createCountsql(StringBuffer sql){
        int start=0;
        int end=0;
        if(sql.toString().contains("SELECT")){
            start =sql.indexOf("SELECT");
            if(sql.toString().contains("FROM")){
                end =sql.indexOf("FROM");
            }
        }
        if(sql.toString().contains("select")){
            start =sql.indexOf("select");
            if(sql.toString().contains("from")){
                end =sql.indexOf("from");
            }
        }
        StringBuffer buildsql= sql.delete(start, end).insert(0,"SELECT count(1) ");
        return buildsql;
    }


    protected SQLQuery createSqlQuery(String sqlString,Object clazz) {
        Query query=em.createNativeQuery(sqlString);
        SQLQuery sqlQuery=query.unwrap(SQLQuery.class);
        sqlQuery.setProperties(clazz);
        return sqlQuery;
    }



    protected <T> List<T>  setResultClass( SQLQuery query,Class<T> resultClass){
        List<T> queryList=query.setResultTransformer(new AliasToBeanResultTransformer(resultClass)).list();
        return queryList;
    }

    protected List<Map<String,Object>> setResultMap(SQLQuery query, Map<String, Object> params){
        List<Map<String,Object>> queryList=query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return queryList;
    }



    private void setParameter(Query query, Object[] parameter) {
        if (parameter != null && parameter.length > 0) {
            for (int i = 0; i < parameter.length; i++) {
                query.setParameter(i, parameter[i]);
            }
        }
    }

    public  String bulidCountSql(String sql){
        String countSqlString = "select count(*) " + removeSelect(removeOrders(sql));
        return countSqlString;
    }


    private String removeSelect(String hqlString) {
        int beginPos = hqlString.toLowerCase().indexOf("from");
        return hqlString.substring(beginPos);
    }

    private String removeOrders(String hqlString) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hqlString);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private Boolean hasOrders(String hqlString) {
        Boolean flag = false;
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hqlString);
        while (m.find()) {
            flag = true;
        }
        return flag;
    }
}

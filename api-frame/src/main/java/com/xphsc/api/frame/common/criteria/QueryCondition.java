package com.xphsc.api.frame.common.criteria;

import java.util.Collection;


/**
 * 条件构造器
 * Created by ${huipei.x} on 2017-2-25.
 */
public class QueryCondition {


    public static QuerySimpleExpression isNotEmpty(String fieldName) {
        return new QuerySimpleExpression(fieldName, Criterion.Operator.ISNOTEMPTY);
    }


    public static QuerySimpleExpression isEmpty(String fieldName) {
        return new QuerySimpleExpression(fieldName, Criterion.Operator.ISEMPTY);
    }


    public static QuerySimpleExpression isNull(String fieldName) {
        return new QuerySimpleExpression(fieldName, Criterion.Operator.ISNULL);
    }


    public static QuerySimpleExpression isNotNull(String fieldName) {
        return new QuerySimpleExpression(fieldName, Criterion.Operator.ISNOTNULL);
    }


    public static QuerySimpleExpression eq(String fieldName, Object value) {
        if(value.equals(null))return null;  
        return new QuerySimpleExpression(fieldName, value, Criterion.Operator.EQ);
    }  
    

    public static QueryBuilderExpression eq(QueryConstructor queryConstructor, Object value) {
    	if(value.equals(null))return null;
    	return new QueryBuilderExpression(queryConstructor.getField(), value,queryConstructor.getType(), Criterion.Operator.EQ);
    }  
      

    public static QuerySimpleExpression ne(String fieldName, Object value) {
        if(value.equals(null))return null;  
        return new QuerySimpleExpression(fieldName, value, Criterion.Operator.NE);
    }  
    

    public static QueryBuilderExpression ne(QueryConstructor queryConstructor, Object value) {
    	if(value.equals(null))return null;  
    	return new QueryBuilderExpression(queryConstructor.getField(), value,queryConstructor.getType(), Criterion.Operator.NE);
    }  
  

    public static QuerySimpleExpression like(String fieldName, String value) {
        if(value.equals(null))return null;  
        return new QuerySimpleExpression(fieldName, value, Criterion.Operator.LIKE);
    }  
    

    public static QueryBuilderExpression like(QueryConstructor queryConstructor, String value) {
    	if(value.equals(null))return null;  
    	return new QueryBuilderExpression(queryConstructor.getField(), value,queryConstructor.getType(), Criterion.Operator.LIKE);
    }  
  

    public static QuerySimpleExpression like(String fieldName, String value,
            Criterion.MatchMode matchMode) {
        if(value.equals(null))return null;  
        return new QuerySimpleExpression(fieldName, value, matchMode, Criterion.Operator.LIKE);
    }  
    

    public static QueryBuilderExpression like(QueryConstructor queryConstructor, String value,
    		Criterion.MatchMode matchMode) {
    	if(value.equals(null))return null;  
    	return new QueryBuilderExpression(queryConstructor.getField(), value,queryConstructor.getType(), Criterion.Operator.LIKE, matchMode);
    }  
  

    public static QuerySimpleExpression gt(String fieldName, Object value) {
        if(value.equals(null))return null;  
        return new QuerySimpleExpression(fieldName, value, Criterion.Operator.GT);
    }  
    

    public static QueryBuilderExpression gt(QueryConstructor queryConstructor, Object value) {
    	if(value.equals(null))return null;  
    	return new QueryBuilderExpression(queryConstructor.getField(), value,queryConstructor.getType(), Criterion.Operator.GT);
    }  
  

    public static QuerySimpleExpression lt(String fieldName, Object value) {
        if(value.equals(null))return null;  
        return new QuerySimpleExpression(fieldName, value, Criterion.Operator.LT);
    }  
    

    public static QueryBuilderExpression lt(QueryConstructor queryConstructor, Object value) {
    	if(value.equals(null))return null;  
    	return new QueryBuilderExpression(queryConstructor.getField(), value,queryConstructor.getType(), Criterion.Operator.LT);
    }  
  

    public static QuerySimpleExpression lte(String fieldName, Object value) {
        if(value.equals(null))return null;  
        return new QuerySimpleExpression(fieldName, value, Criterion.Operator.LTE);
    }  
    

    public static QueryBuilderExpression lte(QueryConstructor queryConstructor, Object value) {
    	if(value.equals(null))return null;  
    	return new QueryBuilderExpression(queryConstructor.getField(), value,queryConstructor.getType(), Criterion.Operator.LTE);
    }  
  

    public static QuerySimpleExpression gte(String fieldName, Object value) {
        if(value.equals(null))return null;  
        return new QuerySimpleExpression(fieldName, value, Criterion.Operator.GTE);
    }  
    

    public static QueryBuilderExpression gte(QueryConstructor queryConstructor, Object value) {
    	if(value.equals(null))return null;  
    	return new QueryBuilderExpression(queryConstructor.getField(), value,queryConstructor.getType(), Criterion.Operator.GTE);
    }  
  
    

    public static QueryComplexExpression or(Criterion... criterions){
        return new QueryComplexExpression(criterions, Criterion.Operator.OR);
    }  
    
    

    public static QueryComplexExpression between(String column, Object val1, Object val2){
    	return new QueryComplexExpression(column, val1, val2, Criterion.Operator.BETWEEN);
    }
    
    
    
    

    public static QueryComplexExpression in(String fieldName, Collection value) {
        QuerySimpleExpression[] ses = new QuerySimpleExpression[value.size()];
        int i=0;  
        for(Object obj : value){  
            ses[i]=new QuerySimpleExpression(fieldName,obj,Criterion.Operator.EQ);
            i++;  
        }  
        return new QueryComplexExpression(ses,Criterion.Operator.OR);
    }

    public static QueryComplexExpression notIn(String fieldName, Collection value) {
        QuerySimpleExpression[] ses = new QuerySimpleExpression[value.size()];
        int i=0;
        for(Object obj : value){
            ses[i]=new QuerySimpleExpression(fieldName,obj,Criterion.Operator.NE);
            i++;
        }
        return new QueryComplexExpression(ses,Criterion.Operator.AND);
    }

}

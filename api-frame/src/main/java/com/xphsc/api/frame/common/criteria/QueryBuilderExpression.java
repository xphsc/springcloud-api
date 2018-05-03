package com.xphsc.api.frame.common.criteria;

import com.github.xphsc.util.StringUtil;
import lombok.Data;

import javax.persistence.criteria.*;


/**
 * 函数条件表达式
 *  Created by ${huipei.x} on 2016/8/8.
 */
@Data
public class QueryBuilderExpression implements Criterion {
    private String fieldName;       //属性名
    private Object value;           //对应值
    private Projection projection;  //函数条件类型
    private Operator operator;      //基础条件类型
    private MatchMode matchMode;    //like用匹配类型
    
    protected QueryBuilderExpression(String fieldName, Object value, Projection projection, Operator operator) {
        this.fieldName = fieldName;  
        this.value = value;  
        this.projection = projection;
        this.operator = operator;
    }  
    protected QueryBuilderExpression(String fieldName, Object value, Projection projection, Operator operator, MatchMode matchMode) {
    	this.fieldName = fieldName;  
    	this.value = value;  
    	this.projection = projection;
    	this.operator = operator;
    	this.matchMode = matchMode;
    }  
  
	
	@SuppressWarnings({ "rawtypes", "unchecked" })  
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,  
            CriteriaBuilder builder) {  
        Path expression = null;  
        if(fieldName.contains(".")){  
            String[] names = StringUtil.split(fieldName, ".");
            expression = root.get(names[0]);  
            for (int i = 1; i < names.length; i++) {  
                expression = expression.get(names[i]);  
            }  
        }else{  
            expression = root.get(fieldName);  
        }  
        switch (projection) {  
        case LENGTH :
            return getBuilder(builder, builder.length(expression), value, matchMode);
        case MAX :
        	return getBuilder(builder, builder.max(expression), value, matchMode);
        case SUM :
        	return getBuilder(builder, builder.sum(expression), value, matchMode);
        case MIN :
        	return getBuilder(builder, builder.min(expression), value, matchMode);
        case AVG :
        	return getBuilder(builder, builder.avg(expression), value, matchMode);
        case COUNT :
        	return getBuilder(builder, builder.count(expression), value, matchMode);
        default:
            return null;  
        }  
    }  
	
	private Predicate getBuilder(CriteriaBuilder builder, Expression expression, Object obj, MatchMode matchMode){
		switch (operator) {  
        case EQ:  
            return builder.equal(expression, value);  
        case NE:  
            return builder.notEqual(expression, value);  
        case LIKE:  
    		switch(matchMode){
    			case START : 
    				return builder.like((Expression<String>) expression, value + "%"); 
    			case END : 
    				return builder.like((Expression<String>) expression, "%" + value); 
    			case ANYWHERE : 
    				return builder.like((Expression<String>) expression, "%" + value + "%"); 
    			default : 
    				return builder.like((Expression<String>) expression, "%" + value + "%");
    		}
        case LT:  
        	return builder.lessThan(expression, (Comparable) value);  
        case GT:  
        	return builder.greaterThan(expression, (Comparable) value);  
        case LTE:  
        	return builder.lessThanOrEqualTo(expression, (Comparable) value);  
        case GTE:
        	return builder.greaterThanOrEqualTo(expression, (Comparable) value);
        default:
        	return null;
		}
	}
	

}

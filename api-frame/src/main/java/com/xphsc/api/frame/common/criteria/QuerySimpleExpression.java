package com.xphsc.api.frame.common.criteria;

import com.github.xphsc.util.StringUtil;
import lombok.Data;

import javax.persistence.criteria.*;


/**
 * 简单条件表达式
 *  Created by ${huipei.x} on 2016/8/8.
 */
@Data
public class QuerySimpleExpression implements Criterion {
    /**
     * 属性名
     */
    private String fieldName;
    /**
     *对应值
     */
    private Object value;
    /**
     *计算符
     */
    private Operator operator;
    /**
     * like匹配方式
     */
    private MatchMode matchMode;


    protected QuerySimpleExpression(String fieldName, Operator operator) {
        this.fieldName = fieldName;
        this.operator = operator;
    }

    protected QuerySimpleExpression(String fieldName, Object value, Operator operator) {
        this.fieldName = fieldName;  
        this.value = value;  
        this.operator = operator;  
    }  
    
    protected QuerySimpleExpression(String fieldName, Object value, MatchMode matchMode, Operator operator) {
        this.fieldName = fieldName;  
        this.value = value;  
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
        case ISNOTNULL:
            return builder.isNotNull(expression);
        case ISNULL:
            return builder.isNull(expression);
        case ISEMPTY:
            return builder.isEmpty(expression);
        case ISNOTEMPTY:
            return builder.isNotEmpty(expression);
        default:  
            return null;  
        }  
    }  
}

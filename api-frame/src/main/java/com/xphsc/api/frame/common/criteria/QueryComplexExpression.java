package com.xphsc.api.frame.common.criteria;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 逻辑条件表达式 用于复杂条件时使用，如但属性多对应值的OR查询等
 * Created by ${huipei.x} on 2016/8/8.
 */
@Data
public class QueryComplexExpression implements Criterion {
	private Criterion[] criterion;  // 逻辑表达式中包含的表达式
	private Operator operator;      //计算符
	private String field;
	private Object expField;
	private Object expOneField;
	  
    public QueryComplexExpression(Criterion[] criterions, Operator operator) {
        this.criterion = criterions;  
        this.operator = operator;  
    }

	//between用构造方法
    public QueryComplexExpression(String field, Object expField, Object expOneField, Operator operator){
    	this.field = field;
    	this.expField = expField;
    	this.expOneField = expOneField;
    	this.operator = operator;
    	this.criterion = null;
    }
    
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,  
            CriteriaBuilder builder) {  
        List<Predicate> predicates = new ArrayList<Predicate>();  
        if(!StringUtils.isEmpty(criterion))
	        for(int i=0;i<this.criterion.length;i++){  
	            predicates.add(this.criterion[i].toPredicate(root, query, builder));  
	        }  
        switch (operator) {  
        case OR:  
            return builder.or(predicates.toArray(new Predicate[predicates.size()]));  
        case AND:  
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        case BETWEEN:
        	Expression expression = root.get(field);
        	return builder.between(expression, (Comparable) expField, (Comparable) expOneField);
        default:  
            return null;  
        }  
    }


}

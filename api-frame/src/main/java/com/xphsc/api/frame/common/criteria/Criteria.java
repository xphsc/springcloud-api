package com.xphsc.api.frame.common.criteria;


import com.github.xphsc.collect.Lists;
import com.github.xphsc.util.StringUtil;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 定义一个查询条件容器
 *  Created by ${huipei.x} on 2016/8/8.
 */
@Data
public class Criteria<T> implements Specification<T>{

	private List<Criterion> criterions = Lists.newArrayList();
	//查询条件容器
	//倒序查询条件
	private String orderByDESC;
	//升序查询条件
	private String orderByASC;
	//group查询条件
	private String groupBy;
	  
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,  
            CriteriaBuilder builder) {  
    	if(StringUtil.isNotEmpty(orderByASC))
    		query.orderBy(builder.desc(root.get(getOrderByASC())));
    	if(StringUtil.isNotEmpty(orderByDESC))
    		query.orderBy(builder.desc(root.get(getOrderByDESC())));
    	if(StringUtil.isNotEmpty(groupBy))
    		query.groupBy(root.get(getGroupBy()));
        if (Lists.isNotEmpty(criterions)) {
            List<Predicate> predicates =Lists.newArrayList();
            for(Criterion c : criterions){  
                predicates.add(c.toPredicate(root, query,builder));  
            }
			// 将所有条件用 and 联合起来
            if (Lists.isNotEmpty(predicates)) {
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));  
            }  
        }  
        return builder.conjunction();  
    }  


 public void add(Criterion criterion){
        if(criterion!=null){  
            criterions.add(criterion);  
        }  
    }  
    

    
}

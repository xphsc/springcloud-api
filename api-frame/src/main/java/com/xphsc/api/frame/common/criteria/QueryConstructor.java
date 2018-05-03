package com.xphsc.api.frame.common.criteria;

import lombok.Data;

/**
 * 函数构造器
 * Created by ${huipei.x} on 2017-2-25.
 */
@Data
public class QueryConstructor {
    //函数作用字段
    private String field;
    //函数类型
    private Criterion.Projection type;

    public QueryConstructor(String field, Criterion.Projection type){
        this.field = field;
        this.type = type;
    }

    public static QueryConstructor Max(String col){
        return new QueryConstructor(col,  Criterion.Projection.MAX);
    }

    public static QueryConstructor Length(String col){
        return new QueryConstructor(col, Criterion.Projection.LENGTH);
    }

    public static QueryConstructor Min(String col){
        return new QueryConstructor(col, Criterion.Projection.MIN);
    }

    public static QueryConstructor Sum(String col){
        return new QueryConstructor(col, Criterion.Projection.SUM);
    }


}

package com.xphsc.api.frame.base;


import com.github.xphsc.page.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import java.io.Serializable;
import java.util.Map;


/**
 * Created by ${huipei.x} on 2018/4/9.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {

    /**
     *   enfOfficialsListVO 参数对象
     *   StringBuffer sql=new StringBuffer();
     *  sql.append(" select eo.id,eo.name,eo.sex,eo.duty,eo.certificate_number,eo.workunits,eo.idcard,eo.contactphone,r.randomdepartname,eo.departid " +
     *   "  from  (select departid,grade from org_departinformation where provinceid=18");
     *  if(ObjectUtil.isNotEmpty(enfOfficialsListVO.getCityid())){
     *  sql.append(" and cityid =" + enfOfficialsListVO.getCityid());
     *   }
     *
     * @param resultClass 结果�?
     * @param sql sql语句
     * @param pageable 分页对象
     * @param sort 排序
     * @param clazz 查询对象
     * @param <T>
     * @return
     */
    public <T> Page<T> queryPageBySQL(StringBuffer sql, Object clazz, Pageable pageable, Sort sort, Class<T> resultClass);
    public <T> Page<T> queryPageBySQL(StringBuffer sql, Pageable pageable, Map<String, Object> params, Class<T> resultClass);
    public  Page<Map<String,Object>> queryPageBySQL(StringBuffer sql, Map<String, Object> params, Pageable pageable, Map<String, Object> resultMap);
    public int queryDataCountBySQL(StringBuffer sql, Object params);
    public <T> T findByJpql(String jpql, Object... objects);

}

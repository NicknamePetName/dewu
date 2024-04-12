package com.youkeda.dewu.dao;

import com.youkeda.dewu.dataobject.ProductDO;
import com.youkeda.dewu.param.BasePageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDAO {

    /** 根据主键删除数据 */
    int deleteByPrimaryKey(String id);

    /** 新增一条数据 */
    int insert(ProductDO record);

    /** 根据主键查询一条数据 */
    ProductDO selectByPrimaryKey(String id);

    /** 查询所有条数据 */
    List<ProductDO> selectAll();

    /** 根据参数对象中的主键值更新一条数据 */
    int updateByPrimaryKey(ProductDO record);

    /** 查询一共有多少条数据 */
    int selectAllCounts();

    /** 分页查询 */
    List<ProductDO> pageQuery(BasePageParam param);
}
package com.youkeda.dewu.dao;

import com.youkeda.dewu.dataobject.ProductDetailDO;
import com.youkeda.dewu.model.ProductDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDetailDAO {

    /** 根据主键删除数据 */
    int deleteByPrimaryKey(String id);

    /** 新增一条数据 */
    int insert(ProductDetailDO record);

    /** 根据主键查询一条数据 */
    ProductDetailDO selectByPrimaryKey(String id);

    /** 查询所有条数据 */
    List<ProductDetailDO> selectAll();

    /** 根据参数对象中的主键值更新一条数据 */
    int updateByPrimaryKey(ProductDetailDO record);



    List<ProductDetailDO> selectByProductId(String productId);

    List<ProductDetailDO> selectByIds(List<String> ids);
}
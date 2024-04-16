package com.youkeda.dewu.service;

import com.youkeda.dewu.model.ProductDetail;

import java.util.List;

public interface ProductDetailService {

    /**
     * 添加或者修改
     * @param productDetail 产品
     * @return int
     */
    int save(ProductDetail productDetail);

    /**
     * 获取商品id获取所有商品详情
     *
     * @param productId 商品主键
     * @return ProductDetail
     */
    List<ProductDetail> getByProductId(String productId);

    /**
     * 获取商品详情
     *
     * @param id 主键
     * @return ProductDetail
     */
    ProductDetail get(String id);
}
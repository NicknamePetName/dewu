package com.youkeda.dewu.service.impl;

import com.youkeda.dewu.dao.ProductDetailDAO;
import com.youkeda.dewu.dataobject.ProductDetailDO;
import com.youkeda.dewu.model.ProductDetail;
import com.youkeda.dewu.service.ProductDetailService;
import com.youkeda.dewu.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailDAO productDetailDAO;
    @Override
    public int save(ProductDetail productDetail) {
        if (StringUtils.isBlank(productDetail.getId())) {
            productDetail.setId(UUIDUtils.getUUID());
            return productDetailDAO.insert(new ProductDetailDO(productDetail));
        }

        return productDetailDAO.updateByPrimaryKey(new ProductDetailDO(productDetail));
    }

    @Override
    public List<ProductDetail> getByProductId(String productId) {

        if (StringUtils.isBlank(productId)) {
            return new ArrayList<>();
        }
        return productDetailDAO.selectByProductId(productId).stream()
                .map(ProductDetailDO::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetail get(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        ProductDetailDO productDetailDO = productDetailDAO.selectByPrimaryKey(id);
        return productDetailDO != null ? productDetailDO.convertToModel() : new ProductDetail();
    }
}

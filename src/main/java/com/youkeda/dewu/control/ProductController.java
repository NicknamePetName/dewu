package com.youkeda.dewu.control;

import com.youkeda.dewu.dao.ProductDAO;
import com.youkeda.dewu.dataobject.ProductDO;
import com.youkeda.dewu.model.Product;
import com.youkeda.dewu.param.BasePageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/product/page")
    public List<ProductDO> queryPage(@RequestBody(required = false) BasePageParam param) {
        return productDAO.pageQuery(param);
    }
}

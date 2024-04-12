package com.youkeda.dewu.api;

import com.youkeda.dewu.model.Paging;
import com.youkeda.dewu.model.Product;
import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.param.BasePageParam;
import com.youkeda.dewu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/product")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @ResponseBody
    @GetMapping("/page")
    public Result<Paging<Product>> queryPage(@RequestBody BasePageParam param) {
        Result<Paging<Product>> result = new Result<>();
        result.setData(productService.pageQueryProduct(param));
        result.setSuccess(true);
        return result;
    }
}

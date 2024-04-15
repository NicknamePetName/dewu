package com.youkeda.dewu.api;

import com.youkeda.dewu.model.ProductDetail;
import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/productdetail")
public class ProductDetailAPI {

    @Autowired
    private ProductDetailService productDetailService;
    @ResponseBody
    @GetMapping("/productId")
    public Result<List<ProductDetail>> getByProductId(@RequestParam String productId) {
        Result<List<ProductDetail>> result = new Result<>();
        result.setData(productDetailService.getByProductId(productId));
        result.setSuccess(true);
        return result;
    }

}

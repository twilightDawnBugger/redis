package com.msb.controller;

import com.msb.pojo.Product;
import com.msb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by 金喆
 */

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/show")
    public String select(Integer id , Model model)
    {
        Product product = productService.findProductById(id);
        model.addAttribute("product" , product);

        return "show";
    }

}

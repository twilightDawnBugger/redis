package com.msb.service.impl;

import com.msb.mapper.ProductMapper;
import com.msb.pojo.Product;
import com.msb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * Created by 金喆
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Product findProductById(Integer id) {

        String key = "product:" +id;
        //先从redis中获取数据
        if(redisTemplate.hasKey(key))
        {
            System.out.println("执行缓存");
            redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Product>(Product.class));
            Product product = (Product) redisTemplate.opsForValue().get(key);
            return product;
        }
        System.out.println("执行mysql");

        Product product = productMapper.findProductById(id);
        redisTemplate.opsForValue().set(key, product);
        return product;
    }
}

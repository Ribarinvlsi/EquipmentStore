package com.udhtu.controllers;

import com.udhtu.model.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController
        implements ICrudController<ProductDto, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final HashMap<Long, ProductDto> mockDb = new HashMap();
    @Override
    public List<ProductDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всього товару");
        return new ArrayList(this.mockDb.values());
    }

    @Override
    public ProductDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання товара за ідентифікатором {}", id);
        return this.mockDb.get(id);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення товара за ідентифікатором {}", id);
        this.mockDb.remove(id);
    }

    @Override
    public ProductDto saveOrUpdate(ProductDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження/редагування клієнта {} {}", dto.getProductName(), dto.getProductPrice());
        this.mockDb.put(dto.getId(), dto);
        return dto;
    }
}

package com.udhtu.controllers;

import com.udhtu.model.dto.ProductDto;
import com.udhtu.model.entity.ProductEntity;
import com.udhtu.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController
        implements ICrudController<ProductDto, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всього товару");
        List<ProductEntity> entityList = productRepository.findAll();
        List<ProductDto> dtoList = new ArrayList<>(entityList.size());
        for (ProductEntity entity : entityList)
            dtoList.add(buildDto(entity));
        return dtoList;
    }

    @Override
    public ProductDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання товара за ідентифікатором {}", id);
        ProductDto dto = null;
        Optional<ProductEntity> preloadOptional = productRepository.findById(id);
        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення товара за ідентифікатором {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto saveOrUpdate(ProductDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження/редагування клієнта {} {}", dto.getProductName(), dto.getProductPrice());
        ProductEntity preload;
        if (dto.getId() == null) {
            preload = new ProductEntity();
        } else {
            Optional<ProductEntity> preloadOptional = productRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(ProductEntity::new);
        }
        preload.setProductName(dto.getProductName());
        preload.setProductPrice(dto.getProductPrice());
        preload = productRepository.save(preload);
        dto.setId(preload.getId());
        return dto;
    }

    public ProductDto buildDto(ProductEntity entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setProductPrice(entity.getProductPrice());
        return dto;
    }
}

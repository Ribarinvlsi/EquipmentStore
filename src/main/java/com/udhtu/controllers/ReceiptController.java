package com.udhtu.controllers;

import com.udhtu.model.dto.ClientDto;
import com.udhtu.model.dto.ProductDto;
import com.udhtu.model.dto.ReceiptDto;
import com.udhtu.model.entity.ClientEntity;
import com.udhtu.model.entity.ProductEntity;
import com.udhtu.model.entity.ReceiptEntity;
import com.udhtu.repository.ClientRepository;
import com.udhtu.repository.ProductRepository;
import com.udhtu.repository.ReceiptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("receipts")
public class ReceiptController
        implements ICrudController<ReceiptDto, Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptDto.class);
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ReceiptDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх чеків");
        List<ReceiptEntity> entityList = receiptRepository.findAll();
        List<ReceiptDto> dtoList = new ArrayList<>(entityList.size());
        for (ReceiptEntity entity : entityList)
            dtoList.add(buildDto(entity));
        return dtoList;
    }

    @Override
    public ReceiptDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання чека за ідентифікатором {}", id);
        ReceiptDto dto = null;
        Optional<ReceiptEntity> preloadOptional = receiptRepository.findById(id);
        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення чека за ідентифікатором {}", id);
        receiptRepository.deleteById(id);
    }

    @Override
    public ReceiptDto saveOrUpdate(ReceiptDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження/редагування чека {} {} {}", dto.getClient(), dto.getProduct(), dto.getPurchaseDate());
        ReceiptEntity preload;
        if (dto.getId() == null) {
            preload = new ReceiptEntity();
        } else {
            Optional<ReceiptEntity> preloadOptional = receiptRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(ReceiptEntity::new);
        }
        if(dto.getClient() != null && dto.getClient().getId() != null) {
            Optional<ClientEntity> optionalClient = clientRepository.findById(dto.getClient().getId());
            if(optionalClient.isPresent()) {
                preload.setClient(optionalClient.get());
            }
        }
        if(dto.getProduct() != null && dto.getProduct().getId() != null) {
            Optional<ProductEntity> optionalProduct = productRepository.findById(dto.getProduct().getId());
            if(optionalProduct.isPresent()) {
                preload.setProduct(optionalProduct.get());
            }
        }
        preload.setPurchaseDate(dto.getPurchaseDate());
        preload = receiptRepository.save(preload);
        return getById(preload.getId());
    }

    public ReceiptDto buildDto(ReceiptEntity entity) {
        ReceiptDto dto = new ReceiptDto();
        dto.setId(entity.getId());
        ClientDto clientDto = null;
        if(entity.getClient() != null) {
            ClientEntity clientEntity = entity.getClient();
            clientDto = new ClientDto();
            clientDto.setId(clientEntity.getId());
            clientDto.setFirstName(clientEntity.getFirstName());
            clientDto.setLastName(clientEntity.getLastName());
            clientDto.setPhoneNumber(clientEntity.getPhoneNumber());
            dto.setClient(clientDto);
        }
        ProductDto productDto = null;
        if(entity.getProduct() != null) {
            ProductEntity productEntity = entity.getProduct();
            productDto = new ProductDto();
            productDto.setId(productEntity.getId());
            productDto.setProductName(productEntity.getProductName());
            productDto.setProductPrice(productEntity.getProductPrice());
            dto.setProduct(productDto);
        }
        dto.setPurchaseDate(entity.getPurchaseDate());
        return dto;
    }
}

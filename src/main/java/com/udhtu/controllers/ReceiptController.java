package com.udhtu.controllers;

import com.udhtu.model.ReceiptDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("receipts")
public class ReceiptController
        implements ICrudController<ReceiptDto, Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptDto.class);

    @Override
    public List<ReceiptDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх чеків");
        return null;
    }

    @Override
    public ReceiptDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання чека за ідентифікатором {}", id);
        return null;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення чека за ідентифікатором {}", id);
    }

    @Override
    public ReceiptDto saveOrUpdate(ReceiptDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження/редагування чека {} {} {}", dto.getClient(), dto.getProduct(), dto.getPurchaseDate());
        return null;
    }
}

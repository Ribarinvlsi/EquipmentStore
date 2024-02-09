package com.udhtu.repository;

import com.udhtu.model.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository
        extends JpaRepository<ReceiptEntity, Long> {
}

package com.nipat.exportexelsendemailspring.service;

import com.nipat.exportexelsendemailspring.entity.PromotionEntity;
import com.nipat.exportexelsendemailspring.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    public List<PromotionEntity> PromotionAll() {
        return promotionRepository.findAll();
    }
}

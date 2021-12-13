package com.nipat.exportexelsendemailspring.repository;

import com.nipat.exportexelsendemailspring.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository  extends JpaRepository<PromotionEntity, Long> {

}

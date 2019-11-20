package com.wildcodeschool.wizardsnpotions.repository;

import com.wildcodeschool.wizardsnpotions.entity.Potion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotionRepository extends JpaRepository<Potion, Long> {
}

package com.beauty.api.model.shop.persist.repository;

import com.beauty.api.model.shop.persist.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

}

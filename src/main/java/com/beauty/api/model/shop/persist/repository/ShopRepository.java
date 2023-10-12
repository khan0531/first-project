package com.beauty.api.model.shop.persist.repository;

import com.beauty.api.model.shop.dto.ShopDto;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

  @Query(value =
      "select * from"
          + "(select s.id, s.name, s.latitude, s.longitude, s.review_count as reviewCount, s.rating_sum / s.review_count as rating"
          + ", (6371*acos(cos(radians(:latitude))*cos(radians(s.latitude))*cos(radians(s.longitude)-radians(:longitude))+sin(radians(:latitude))*sin(radians(s.latitude)))) as distance"
//          + ", (SELECT JSON_ARRAYAGG(cosmetic_types) FROM shop_cosmetic_types sct WHERE sct.shop_id = s.id GROUP BY shop_id) AS cosmeticTypes "
          + "from shop s "
          + "where s.name LIKE %:name%"
          + ") s"
//      + "having distance <= ?3 "
      ,
      countQuery = "select count(*) "
          + "from shop s "
          + "where s.name LIKE %:name%"
//          + "having distance <= ?3 "
      ,
      nativeQuery = true)
  public Page<ShopDto> findAllBy(String name, Double latitude, Double longitude, Pageable pageable);
}

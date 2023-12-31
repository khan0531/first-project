package com.beauty.api.model.inquiry.persist.repository;

import com.beauty.api.model.inquiry.persist.entity.InquiryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {

  List<InquiryEntity> findByTitleAndContent(String title, String content);
}

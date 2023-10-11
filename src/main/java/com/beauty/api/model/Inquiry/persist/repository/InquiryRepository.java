package com.beauty.api.model.Inquiry.persist.repository;

import com.beauty.api.model.Inquiry.persist.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {

}

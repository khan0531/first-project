package com.beauty.api.model.user.persist.repository;

import com.beauty.api.model.user.persist.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
  boolean existsByEmail(String email);
}

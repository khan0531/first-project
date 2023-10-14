package com.beauty.api.model.user.persist.repository;

import com.beauty.api.model.user.persist.entity.AdminMemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMemberRepository extends JpaRepository<AdminMemberEntity, String> {

  Optional<AdminMemberEntity> findByEmail(String email);

  boolean existsByEmail(String email);
}

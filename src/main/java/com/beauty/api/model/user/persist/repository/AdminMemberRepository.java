package com.beauty.api.model.user.persist.repository;

import com.beauty.api.model.user.persist.entity.AdminMemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMemberRepository extends JpaRepository<AdminMemberEntity, Long> {

  Optional<AdminMemberEntity> findByEmail(String email);

  boolean existsByEmail(String email);

  Optional<AdminMemberEntity> findByNameAndName(String name, String name1);
}

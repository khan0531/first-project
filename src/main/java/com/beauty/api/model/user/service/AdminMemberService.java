package com.beauty.api.model.user.service;

import com.beauty.api.model.user.domain.AdminMember;
import com.beauty.api.model.user.persist.repository.AdminMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminMemberService implements UserDetailsService {

  private final AdminMemberRepository adminMemberRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return this.adminMemberRepository.findByEmail(email)
        .map(AdminMember::fromEntity)
        .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + email));
  }
}

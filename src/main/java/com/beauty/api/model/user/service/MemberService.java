package com.beauty.api.model.user.service;

import com.beauty.api.model.user.dto.Member;
import com.beauty.api.model.user.dto.MemberResponse;
import com.beauty.api.model.user.dto.MemberSigninReQuest;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import com.beauty.api.model.user.persist.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }

  public MemberResponse signUp(Member member) {
    boolean exists = this.memberRepository.existsByEmail(member.getEmail());
    if (exists) {
      throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
    }

    member.setPassword(this.passwordEncoder.encode(member.getPassword()));
    MemberEntity result = this.memberRepository.save(member.toEntity());

    return MemberResponse.fromEntity(result);
  }

  public MemberResponse signIn(MemberSigninReQuest memberSigninReQuest) {
    MemberEntity memberEntity = this.memberRepository.findByEmail(memberSigninReQuest.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디 입니다."));

    if (!this.passwordEncoder.matches(memberSigninReQuest.getPassword(), memberEntity.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    return MemberResponse.fromEntity(memberEntity);
  }
}

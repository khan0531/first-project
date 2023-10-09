package com.beauty.api.model.user.service;

import com.beauty.api.model.user.dto.MemberResponse;
import com.beauty.api.model.user.dto.MemberSignInRequest;
import com.beauty.api.model.user.dto.MemberSignUpRequest;
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
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return this.memberRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + email));
  }

  public MemberResponse signUp(MemberSignUpRequest memberSignUpRequest) {
    boolean exists = this.memberRepository.existsByEmail(memberSignUpRequest.getEmail());
    if (exists) {
      throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
    }

    memberSignUpRequest.setPassword(this.passwordEncoder.encode(memberSignUpRequest.getPassword()));
    MemberEntity result = this.memberRepository.save(memberSignUpRequest.toEntity());

    return MemberResponse.fromEntity(result);
  }

  public MemberEntity signIn(MemberSignInRequest memberSigninReQuest) {
    MemberEntity memberEntity = this.memberRepository.findByEmail(memberSigninReQuest.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디 입니다."));

    if (!this.passwordEncoder.matches(memberSigninReQuest.getPassword(), memberEntity.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    return memberEntity;
  }
}

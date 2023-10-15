package com.beauty.api.model.user.service;

import com.beauty.api.model.user.domain.Member;
import com.beauty.api.model.user.dto.MemberResponse;
import com.beauty.api.model.user.dto.MemberSignInRequest;
import com.beauty.api.model.user.dto.MemberSignUpRequest;
import com.beauty.api.model.user.dto.MemberUpdateRequest;
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
        .map(Member::fromEntity)
        .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + email));
  }

  public MemberResponse signUp(MemberSignUpRequest memberSignUpRequest) {
    boolean exists = this.memberRepository.existsByEmail(memberSignUpRequest.getEmail());
    if (exists) {
      throw new IllegalArgumentException("이미 가입되어 있는 이메일 입니다.");
    }

    memberSignUpRequest.setPassword(this.passwordEncoder.encode(memberSignUpRequest.getPassword()));

    Member member = Member.fromRequest(memberSignUpRequest);

    return MemberResponse.fromEntity(this.memberRepository.save(member.toEntity()));
  }

  public MemberEntity signIn(MemberSignInRequest memberSigninReQuest) {
    MemberEntity memberEntity = this.memberRepository.findByEmail(memberSigninReQuest.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디 입니다."));

    if (!this.passwordEncoder.matches(memberSigninReQuest.getPassword(), memberEntity.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    return memberEntity;
  }

  public MemberResponse updateMember(Member member, MemberUpdateRequest memberUpdateRequest) {
    MemberEntity memberEntity = this.memberRepository.findById(memberUpdateRequest.getId())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디 입니다."));

    if (!memberEntity.getId().equals(member.getId())) {
      throw new IllegalArgumentException("해당 회원의 정보가 아닙니다.");
    }

    return MemberResponse.fromEntity(this.memberRepository.save(member.update(memberUpdateRequest).toEntity()));
  }

  public void deleteMember(Member member, Long id) {
    MemberEntity memberEntity = this.memberRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디 입니다."));

    if (!member.getId().equals(id)) {
      throw new IllegalArgumentException("해당 회원의 정보가 아닙니다.");
    }

    this.memberRepository.delete(memberEntity);
  }

  public MemberResponse getUser(Long id) {
    MemberEntity memberEntity = this.memberRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디 입니다."));

    return MemberResponse.fromEntity(memberEntity);
  }
}

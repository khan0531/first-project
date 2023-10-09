package com.beauty.api.model.user.controller;

import com.beauty.api.model.user.dto.MemberSignInRequest;
import com.beauty.api.model.user.dto.MemberSignUpRequest;
import com.beauty.api.model.user.service.MemberService;
import com.beauty.api.security.TokenProvider;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  private final MemberService memberService;

  private final TokenProvider tokenProvider;

  //회원가입
  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody @Valid MemberSignUpRequest memberSignUpRequest) {
    var result = this.memberService.signUp(memberSignUpRequest);
    return ResponseEntity.ok(result);
  }

  //로그인
  @PostMapping("/signin")
  public ResponseEntity<?> signIn(@RequestBody MemberSignInRequest memberSignInRequest) {
    var member = this.memberService.signIn(memberSignInRequest);
    String token = this.tokenProvider.generateToken(
        member.getUsername(),
        member.getRoles().stream().map(Enum::name).collect(java.util.stream.Collectors.toList())
    );
    log.info("user login -> " + memberSignInRequest.getEmail());
    return ResponseEntity.ok(token);
  }


  //회원 정보 수정
  @PutMapping
  public void updateUser() {

  }

  //회원 탈퇴
  @DeleteMapping
  public void deleteUser() {

  }
}

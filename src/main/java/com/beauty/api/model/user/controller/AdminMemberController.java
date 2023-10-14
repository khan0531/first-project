package com.beauty.api.model.user.controller;

import com.beauty.api.model.user.dto.AdminMemberResponse;
import com.beauty.api.model.user.dto.AdminMemberSignUpRequest;
import com.beauty.api.model.user.service.AdminMemberService;
import com.beauty.api.security.TokenProvider;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-member")
public class AdminMemberController {

  private final AdminMemberService adminMemberService;

  private final TokenProvider tokenProvider;

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody @Valid AdminMemberSignUpRequest adminMemberSignUpRequest) {
    AdminMemberResponse result = this.adminMemberService.signUp(adminMemberSignUpRequest);
    return ResponseEntity.ok(result);
  }

  //로그인
  @PostMapping("/signIn")
  public ResponseEntity<?> signIn() {
    return null;
  }


  //회원 정보 수정
  @PatchMapping
  public ResponseEntity<?> updateUser() {
    return null;
  }

  //회원 탈퇴
  @DeleteMapping
  public ResponseEntity<?> deleteUser() {
    return null;
  }


}

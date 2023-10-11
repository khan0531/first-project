package com.beauty.api.model.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-member")
public class AdminMemberController {

  @PostMapping("/signup")
  public ResponseEntity<?> signUp() {
    return null;
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

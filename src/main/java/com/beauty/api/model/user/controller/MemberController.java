package com.beauty.api.model.user.controller;

import com.beauty.api.model.user.dto.Member;
import com.beauty.api.model.user.dto.MemberSigninReQuest;
import com.beauty.api.model.user.service.MemberService;
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
@RequestMapping("/user")
public class MemberController {

  private final MemberService memberService;

  //회원가입
  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody Member member) {
    var result = this.memberService.signUp(member);
    return ResponseEntity.ok(result);
  }

  //로그인
  @PostMapping("/signin")
  public ResponseEntity<?> signIn(@RequestBody MemberSigninReQuest memberSigninReQuest) {
    var result = this.memberService.signIn(memberSigninReQuest);
    return ResponseEntity.ok(result);
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

package com.beauty.api.model.user.controller;

import com.beauty.api.model.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  //회원가입
  @PostMapping("/signup")
  public void signUp() {

  }

  //로그인
  @PostMapping("/signin")
  public void signIn() {

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

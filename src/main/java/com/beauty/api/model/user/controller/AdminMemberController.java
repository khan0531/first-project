package com.beauty.api.model.user.controller;

import com.beauty.api.model.user.domain.AdminMember;
import com.beauty.api.model.user.dto.AdminMemberFindEmail;
import com.beauty.api.model.user.dto.AdminMemberResponse;
import com.beauty.api.model.user.dto.AdminMemberSignInRequest;
import com.beauty.api.model.user.dto.AdminMemberSignUpRequest;
import com.beauty.api.model.user.dto.AdminMemberUpdatePassword;
import com.beauty.api.model.user.dto.AdminMemberUpdateRequest;
import com.beauty.api.model.user.persist.entity.AdminMemberEntity;
import com.beauty.api.model.user.service.AdminMemberService;
import com.beauty.api.security.TokenProvider;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
  public ResponseEntity<?> signIn(@RequestBody AdminMemberSignInRequest adminMemberSignInRequest) {
    AdminMemberEntity adminMemberEntity = this.adminMemberService.signIn(adminMemberSignInRequest);
    AdminMember adminMember = AdminMember.fromEntity(adminMemberEntity);

    String token = this.tokenProvider.generateToken(
        adminMember.getUsername(),
        adminMember.getRoles().stream().map(Enum::name).collect(java.util.stream.Collectors.toList())
    );

    log.info("user login -> " + adminMemberSignInRequest.getEmail());

    return ResponseEntity.ok(token);
  }


  //회원 정보 수정
  @PutMapping
  public ResponseEntity<?> updateAdminMember(@AuthenticationPrincipal AdminMember adminMember,
      @RequestBody AdminMemberUpdateRequest adminMemberUpdateRequest) {
    AdminMemberResponse result = this.adminMemberService.updateAdminMember(adminMember, adminMemberUpdateRequest);
    return ResponseEntity.ok(result);
  }

  //비밀번호 수정
  @PatchMapping("/{id}/password")
  public ResponseEntity<?> updatePassword(@AuthenticationPrincipal AdminMember adminMember,
      @RequestBody AdminMemberUpdatePassword adminMemberUpdatePassword, @PathVariable Long id) {
    if (!adminMember.getId().equals(id)) {
      throw new IllegalArgumentException("해당 회원의 정보가 아닙니다.");
    }
    AdminMemberResponse result = this.adminMemberService.updatePassword(adminMember, adminMemberUpdatePassword);
    return ResponseEntity.ok(result);
  }

  //아이디(이메일) 찾기
  @GetMapping("/email")
  public ResponseEntity<?> findEmail(@RequestBody AdminMemberFindEmail adminMemberFindEmail) {
    AdminMemberResponse result = this.adminMemberService.findEmail(adminMemberFindEmail);
    return ResponseEntity.ok(result);
  }

  //회원 탈퇴
  @DeleteMapping
  public ResponseEntity<?> deleteUser(@AuthenticationPrincipal AdminMember adminMember, Long id) {
    this.adminMemberService.deleteAdminMember(adminMember, id);
    return ResponseEntity.ok().build();
  }

  //회원 정보 조회
  @GetMapping("/{id}")
  public ResponseEntity<?> getAdminMember(@AuthenticationPrincipal AdminMember adminMember, Long id) {
    AdminMemberResponse result = this.adminMemberService.getAdminMember(adminMember, id);
    return ResponseEntity.ok(result);
  }


}

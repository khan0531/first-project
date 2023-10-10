package com.beauty.api.model.user.controller;

import com.beauty.api.model.user.dto.MemberSignInRequest;
import com.beauty.api.model.user.dto.MemberSignUpRequest;
import com.beauty.api.model.user.dto.MemberUpdateRequest;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import com.beauty.api.model.user.service.MemberService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  @PatchMapping
  public ResponseEntity<?> updateUser(@AuthenticationPrincipal MemberEntity memberEntity,
      @RequestBody @Valid MemberUpdateRequest memberUpdateRequest) {
    var result = this.memberService.updateUser(memberUpdateRequest);
    return ResponseEntity.ok(result);
  }

  //비밀번호 수정
  @PatchMapping
  public ResponseEntity<?> updatePassword(@AuthenticationPrincipal MemberEntity memberEntity) {
    return null;
  }

  //회원 탈퇴
  @DeleteMapping
  public ResponseEntity<?> deleteUser(@AuthenticationPrincipal MemberEntity memberEntity, @RequestParam Long id) {
    if (!memberEntity.getId().equals(id)) {
      return ResponseEntity.badRequest().build();
    }

    this.memberService.deleteUser(id);

    return ResponseEntity.ok().build();
  }

  //회원 정보 조회(내 정보 보기)
  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@AuthenticationPrincipal MemberEntity memberEntity, @RequestParam Long id) {
    return null;
  }

  //아이디(이메일) 찾기
  @GetMapping()
  public ResponseEntity<?> findEmail() {
    return null;
  }

  //비밀번호 찾기(재설정)
  @GetMapping()
  public ResponseEntity<?> findPassword() {
    return null;
  }

  //내 예약 목록 조회
  @GetMapping("/{id}/reservation")
  public ResponseEntity<?> getReservationList(@PathVariable String id) {
    return null;
  }

  //내 예약 리뷰 조회
  @GetMapping("/{id}/review")
  public ResponseEntity<?> getReviewList(@PathVariable String id) {
    return null;
  }

  //내 예약에 대한 리뷰 수정
  @PatchMapping("/{memberId}/review/{reviewId}")
  public ResponseEntity<?> updateReview(@PathVariable String memberId, @PathVariable String reviewId) {
    return null;
  }

  //내 예약 삭제
  @DeleteMapping("/{memberId}/reservation/{reservationId}")
  public ResponseEntity<?> deleteReservation(@PathVariable String memberId,
      @PathVariable String reservationId) {
    return null;
  }

  //내 문의 조회
  @GetMapping("/{id}/inquiry")
  public ResponseEntity<?> getInquiryList(@PathVariable String id) {
    return null;
  }

  //내 문의 수정
  @PatchMapping("/{memberId}/inquiry/{inquiryId}")
  public ResponseEntity<?> updateInquiry(@PathVariable String inquiryId, @PathVariable String memberId) {
    return null;
  }
}

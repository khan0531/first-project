package com.beauty.api.model.user.controller;

import com.beauty.api.model.inquiry.dto.InquiryResponse;
import com.beauty.api.model.inquiry.dto.InquiryUpdateRequest;
import com.beauty.api.model.inquiry.service.InquiryService;
import com.beauty.api.model.reservation.service.ReservationService;
import com.beauty.api.model.review.dto.ReviewResponse;
import com.beauty.api.model.review.dto.ReviewUpdateRequest;
import com.beauty.api.model.review.service.ReviewService;
import com.beauty.api.model.user.dto.Member;
import com.beauty.api.model.user.dto.MemberResponse;
import com.beauty.api.model.user.dto.MemberSignInRequest;
import com.beauty.api.model.user.dto.MemberSignUpRequest;
import com.beauty.api.model.user.dto.MemberUpdateRequest;
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
import org.springframework.web.bind.annotation.PutMapping;
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

  private final ReservationService reservationService;

  private final ReviewService reviewService;

  private final InquiryService inquiryService;

  private final TokenProvider tokenProvider;

  //회원가입
  @PostMapping("/sign-up")
  public ResponseEntity<?> signUp(@RequestBody @Valid MemberSignUpRequest memberSignUpRequest) {
    var result = this.memberService.signUp(memberSignUpRequest);
    return ResponseEntity.ok(result);
  }

  //로그인
  @PostMapping("/sign-in")
  public ResponseEntity<?> signIn(@RequestBody MemberSignInRequest memberSignInRequest) {
    var memberEntity = this.memberService.signIn(memberSignInRequest);
    Member member = Member.fromEntity(memberEntity);

    String token = this.tokenProvider.generateToken(
        member.getUsername(),
        member.getRoles().stream().map(Enum::name).collect(java.util.stream.Collectors.toList())
    );
    log.info("user login -> " + memberSignInRequest.getEmail());
    return ResponseEntity.ok(token);
  }


  //회원 정보 수정
  // TODO
  @PutMapping
  public ResponseEntity<?> updateUser(@AuthenticationPrincipal Member member,
      @RequestBody @Valid MemberUpdateRequest memberUpdateRequest) {
    var result = this.memberService.updateUser(memberUpdateRequest);
    return ResponseEntity.ok(result);
  }

  //비밀번호 수정
  @PatchMapping("/password")
  public ResponseEntity<?> updatePassword(@AuthenticationPrincipal Member member) {
    return null;
  }

  //회원 탈퇴
  @DeleteMapping
  public ResponseEntity<?> deleteUser(@AuthenticationPrincipal Member member, @RequestParam Long id) {

    this.memberService.deleteUser(id);

    return ResponseEntity.ok().build();
  }

  //회원 정보 조회(내 정보 보기)
  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@AuthenticationPrincipal Member member, @RequestParam Long id) {
    MemberResponse memberResponse = this.memberService.getUser(id);
    return ResponseEntity.ok(memberResponse);
  }

  //아이디(이메일) 찾기
  @GetMapping("/email")
  public ResponseEntity<?> findEmail() {
    return null;
  }

  //비밀번호 찾기(재설정)
  @GetMapping("/password")
  public ResponseEntity<?> findPassword() {
    return null;
  }

  //내 예약 목록 조회
  @GetMapping("/{id}/reservation")
  public ResponseEntity<?> getReservationList(@AuthenticationPrincipal Member member, @PathVariable Long id) {
    return null;
  }

  //내 예약 리뷰 조회
  @GetMapping("/{id}/review")
  public ResponseEntity<?> getReviewList(@AuthenticationPrincipal Member member, @PathVariable Long id) {
    return null;
  }

  //내 예약에 대한 리뷰 수정
  @PatchMapping("/{memberId}/review/{reviewId}")
  public ResponseEntity<?> updateReview(@AuthenticationPrincipal Member member, @PathVariable Long memberId,
      @PathVariable Long reviewId, @RequestBody ReviewUpdateRequest reviewUpdateRequest) {
    ReviewResponse reviewResponse = this.reviewService.updateReview(reviewUpdateRequest);
    return ResponseEntity.ok(reviewResponse);
  }

  //내 문의 조회
  @GetMapping("/{id}/inquiry")
  public ResponseEntity<?> getInquiryList(@AuthenticationPrincipal Member member, @PathVariable Long id) {
    return null;
  }

  //내 문의 수정
  @PatchMapping("/{memberId}/inquiry/{inquiryId}")
  public ResponseEntity<?> updateInquiry(@AuthenticationPrincipal Member member, @PathVariable Long inquiryId,
      @PathVariable Long memberId, @RequestBody InquiryUpdateRequest inquiryUpdateRequest) {
    InquiryResponse inquiryResponse = this.inquiryService.updateInquiry(inquiryUpdateRequest);
    return ResponseEntity.ok(inquiryResponse);
  }
}

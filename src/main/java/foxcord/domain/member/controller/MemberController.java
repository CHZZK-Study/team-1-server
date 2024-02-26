package foxcord.domain.member.controller;

import foxcord.domain.member.dto.request.MemberUpdatePasswordRequest;
import foxcord.domain.member.dto.request.MemberUpdateRequest;
import foxcord.domain.member.dto.response.MemberResponse;
import foxcord.domain.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/info")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<MemberResponse> findMemberInfo(
            @CookieValue(value = "JWT", required = false) Cookie cookie) {
        MemberResponse memberResponseDto = memberService.findMemberInfo(cookie);
        return ResponseEntity.ok(memberResponseDto);
    }

    @PatchMapping
    public ResponseEntity<Void> updateMemberInfo(
            @CookieValue(value = "JWT", required = false) Cookie cookie,
            @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMemberInfo(cookie, memberUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updateMemberPassword(
            @CookieValue(value = "JWT", required = false) Cookie cookie,
            @RequestBody MemberUpdatePasswordRequest memberUpdatePasswordRequest) {
        memberService.updatePassword(cookie, memberUpdatePasswordRequest);
        return ResponseEntity.ok().build();
    }
}

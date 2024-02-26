package foxcord.domain.member.service;

import foxcord.domain.member.dto.request.MemberUpdatePasswordRequest;
import foxcord.domain.member.dto.request.MemberUpdateRequest;
import foxcord.domain.member.dto.response.MemberResponse;
import foxcord.domain.member.entity.Member;
import foxcord.domain.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoxcordMemberService implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponse findMemberInfo(Cookie cookie) {
        Member member = getMemberByCookie(cookie);
        return MemberResponse.toDto(member);
    }

    @Override
    @Transactional
    public void updateMemberInfo(Cookie cookie, MemberUpdateRequest memberUpdateRequest) {
        Member member = getMemberByCookie(cookie);
        member.updateMemberInfo(memberUpdateRequest.profileImg(), memberUpdateRequest.password(),
                memberUpdateRequest.nickname(), memberUpdateRequest.introduce());
    }

    @Override
    @Transactional
    public void updatePassword(Cookie cookie,
            MemberUpdatePasswordRequest memberUpdatePasswordRequest) {
        Member member = getMemberByCookie(cookie);
        member.updateMemberPassword(memberUpdatePasswordRequest.newPassword());
    }

    private Member getMemberByCookie(Cookie cookie) {
        Long memberId = Long.parseLong(cookie.getValue());
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("쿠키가 올바르지 않습니다"));
    }
}

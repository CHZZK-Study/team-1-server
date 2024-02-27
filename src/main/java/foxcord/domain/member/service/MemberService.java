package foxcord.domain.member.service;

import foxcord.domain.member.dto.request.MemberUpdatePasswordRequest;
import foxcord.domain.member.dto.request.MemberUpdateRequest;
import foxcord.domain.member.dto.response.MemberResponse;
import jakarta.servlet.http.Cookie;

public interface MemberService {

    void updateMemberInfo(Cookie cookie, MemberUpdateRequest memberUpdateRequest);

    MemberResponse findMemberInfo(Cookie cookie);

    void updatePassword(Cookie cookie, MemberUpdatePasswordRequest memberUpdatePasswordRequest);
}

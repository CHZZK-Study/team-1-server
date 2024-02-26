package foxcord.domain.member.dto.response;

import foxcord.domain.member.entity.Member;

public record MemberResponse(
        String profileImage,
        String nickname,
        String introduce,
        String email

) {

    public static MemberResponse toDto(Member member) {
        return new MemberResponse(member.getProfileImg(), member.getNickname(),
                member.getIntroduce(), member.getEmail());
    }
}

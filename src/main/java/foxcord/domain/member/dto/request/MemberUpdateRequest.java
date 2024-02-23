package foxcord.domain.member.dto.request;

public record MemberUpdateRequest(
        String profileImg,
        String password,
        String nickname,
        String introduce

) {
}

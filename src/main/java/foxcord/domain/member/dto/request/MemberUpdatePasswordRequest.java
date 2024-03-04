package foxcord.domain.member.dto.request;

public record MemberUpdatePasswordRequest(
        String oldPassword,
        String newPassword
) {

}

package foxcord.domain.member.dto.request;

public record SignUpRequest(
        String email,
        String password,
        String nickname
) {

}

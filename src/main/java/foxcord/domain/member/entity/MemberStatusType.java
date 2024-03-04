package foxcord.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatusType {
    FALSE("미가입"),
    TRUE("가입");

    private final String status;
}

package foxcord.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {@UniqueConstraint(name = "email", columnNames = {"EMAIL"})})
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    private String nickname;
    private String profileImg;
    private String introduce;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final MemberStatusType status = MemberStatusType.FALSE;

    private final LocalDateTime createdDate = LocalDateTime.now();

    private Member(String email, String password) {
        this.email = email;
        this.password = password;
        this.nickname = makeRandomNickname();

    }

    private Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static Member from(String email, String password, String nickname) {
        if (nickname == null) {
            //nickname 랜덤생성
            return new Member(email, password);
        } else {
            return new Member(email, password, nickname);
        }
    }

    public void updateMemberInfo(String profileImg, String password, String nickname,
            String introduce) {
        this.profileImg = profileImg;
        this.password = password;
        this.nickname = nickname;
        this.introduce = introduce;
    }

    public void updateMemberPassword(String oldPassword, String newPassword) {
        this.password = newPassword;
    }

    private String makeRandomNickname() {
        UUID nickname = UUID.randomUUID();
        return nickname.toString();
    }

}

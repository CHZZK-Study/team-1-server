package foxcord.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {@UniqueConstraint(name = "email", columnNames = {"EMAIL"})})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
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
    private MemberStatusType status = MemberStatusType.FALSE;

    private LocalDateTime createdDate = LocalDateTime.now();


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

    public void updateMemberInfo(String profileImg, String password, String nickname, String introduce) {
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

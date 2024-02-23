package foxcord.domain.member.service;

import foxcord.domain.member.dto.request.SignUpRequest;
import foxcord.domain.member.entity.Member;
import foxcord.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
public class FoxcordSignUpServiceTest {

    @Autowired
    FoxcordSignUpService foxcordSignUpService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void init() {
        foxcordSignUpService = new FoxcordSignUpService(memberRepository);
    }

//    @AfterEach
//    void clearAll() {
//        memberRepository.deleteAll();
//    }

    @Test
    @DisplayName("닉네임 없이 회원을 생성한다")
    void signup_with_no_nickname() {
        //given
        SignUpRequest signUpRequest = new SignUpRequest("gsafe12@gmail.com", "1234", null);
        //when

        Long savedId = foxcordSignUpService.signup(signUpRequest);
        //then
        Member member = memberRepository.findById(savedId).get();
        assertThat(member.getId()).isEqualTo(savedId);
        assertThat(member.getEmail()).isNotNull();

    }

    @Test
    @DisplayName("이메일 중복을 체크한다.")
    void checkduplicateEmail() {
        //given
        SignUpRequest signUpRequest = new SignUpRequest("gsafe12@gmail.com", "1234", null);
        //when
        //then
        assertThatThrownBy(() -> foxcordSignUpService.signup(signUpRequest)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("닉네임 포함해 회원을 생성한다")
    void signup_with_nickname() {
        //given
        SignUpRequest signUpRequest = new SignUpRequest("gsafe123@gmail.com", "1234", "lee");
        //when

        Long savedId = foxcordSignUpService.signup(signUpRequest);
        //then
        Member member = memberRepository.findById(savedId).get();
        assertThat(member.getId()).isEqualTo(savedId);
        log.info(member.getNickname());

        assertThat(member.getNickname()).isEqualTo(signUpRequest.nickname());

    }
}

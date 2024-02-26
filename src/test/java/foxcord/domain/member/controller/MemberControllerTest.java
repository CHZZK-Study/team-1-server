package foxcord.domain.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import foxcord.domain.member.dto.request.MemberUpdatePasswordRequest;
import foxcord.domain.member.dto.request.MemberUpdateRequest;
import foxcord.domain.member.entity.Member;
import foxcord.domain.member.repository.MemberRepository;
import foxcord.domain.member.service.MemberService;
import foxcord.domain.member.service.MemberServiceImpl;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    @Transactional
    @DisplayName("회원정보 변경 확인")
    void checkUpdatedMemberInfo() throws Exception {
        //given
        Member member = Member.from("gsafe1213", "1234", "lee");
        memberRepository.save(member);
        MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest("myprofile.jpeg",
                "change1234!", "abcscsa", "안녕하세요");
        String content = objectMapper.writeValueAsString(memberUpdateRequest);

        //when
        mockMvc.perform(patch("/member/info")
                        .cookie(new Cookie("JWT", "1"))
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //then
        Assertions.assertThat(member.getProfileImg()).isEqualTo(memberUpdateRequest.profileImg());
        Assertions.assertThat(member.getPassword()).isEqualTo(memberUpdateRequest.password());
        Assertions.assertThat(member.getNickname()).isEqualTo(memberUpdateRequest.nickname());
        Assertions.assertThat(member.getIntroduce()).isEqualTo(memberUpdateRequest.introduce());

    }

    @Test
    @Transactional
    @DisplayName("비밀번호 변경 확인")
    void checkUpdatedPassword() throws Exception {
        //given
        Member member = Member.from("gsafe1213", "1234", "lee");
        memberRepository.save(member);
        MemberUpdatePasswordRequest memberUpdatePasswordRequest = new MemberUpdatePasswordRequest(
                "1234", "new1234");
        String content = objectMapper.writeValueAsString(memberUpdatePasswordRequest);

        //when
        mockMvc.perform(patch("/member/info/password")
                        .cookie(new Cookie("JWT", "1"))
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //then
        Assertions.assertThat(member.getPassword())
                .isEqualTo(memberUpdatePasswordRequest.newPassword());
    }
}

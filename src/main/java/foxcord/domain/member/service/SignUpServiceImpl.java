package foxcord.domain.member.service;

import foxcord.domain.member.dto.request.SignUpRequest;
import foxcord.domain.member.entity.Member;
import foxcord.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignUpServiceImpl implements SignUpService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long signup(SignUpRequest signUpRequest) {
        if (memberRepository.findByEmail(signUpRequest.email()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }

        Member savedMember = memberRepository.save(
                Member.from(signUpRequest.email(), signUpRequest.password(),
                        signUpRequest.nickname()));

        return savedMember.getId();
    }
}

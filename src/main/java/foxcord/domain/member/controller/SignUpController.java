package foxcord.domain.member.controller;

import foxcord.domain.member.dto.request.SignUpRequest;
import foxcord.domain.member.service.SignUpService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest) {
        Long memberId = signUpService.signup(signUpRequest);
        return ResponseEntity.created(URI.create("/signup/" + memberId)).build();
    }

}

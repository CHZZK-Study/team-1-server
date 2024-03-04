package foxcord.domain.member.service;

import foxcord.domain.member.dto.request.SignUpRequest;

public interface SignUpService {

    Long signup(SignUpRequest signUpRequest);
}

package techtales.techtalesbe.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techtales.techtalesbe.common.exception.CustomException;
import techtales.techtalesbe.user.application.dto.UserSignupRequest;
import techtales.techtalesbe.user.domain.enums.UserRole;
import techtales.techtalesbe.user.domain.model.Role;
import techtales.techtalesbe.user.domain.model.User;
import techtales.techtalesbe.user.domain.repository.RoleRepository;
import techtales.techtalesbe.user.domain.repository.UserRepository;
import techtales.techtalesbe.user.domain.service.UserValidator;

import static techtales.techtalesbe.common.exception.CustomErrorCode.INVALID_ROLE;

@Service
@RequiredArgsConstructor
public class UserSignupService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    @Transactional
    public void signup(UserSignupRequest userSignupRequest) {
        userValidator.validateNewUser(userSignupRequest.getNickName(), userSignupRequest.getEmail(), userSignupRequest.getPhoneNumber());

        String encodedPassword = passwordEncoder.encode(userSignupRequest.getPassword());
        Role role = roleRepository.findById(UserRole.ROLE_USER)
                .orElseThrow(() -> new CustomException(INVALID_ROLE));

        User user = User.create(
                userSignupRequest.getUserName(),
                userSignupRequest.getNickName(),
                userSignupRequest.getEmail(),
                userSignupRequest.getPhoneNumber(),
                encodedPassword,
                userSignupRequest.getBirth(),
                role
        );

        userRepository.save(user);
    }
}

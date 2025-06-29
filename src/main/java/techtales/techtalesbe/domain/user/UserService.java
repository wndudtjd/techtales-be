package techtales.techtalesbe.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techtales.techtalesbe.domain.entity.Role;
import techtales.techtalesbe.domain.entity.User;
import techtales.techtalesbe.domain.enums.UserRole;
import techtales.techtalesbe.domain.repository.RoleRepository;
import techtales.techtalesbe.domain.repository.UserRepository;
import techtales.techtalesbe.domain.user.dto.SignupRequestDTO;
import techtales.techtalesbe.global.exception.CustomException;

import java.util.Optional;

import static techtales.techtalesbe.global.exception.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDTO signupRequestDto) {
        String userName = signupRequestDto.getUserName();
        String nickName = signupRequestDto.getNickName();
        String email = signupRequestDto.getEmail();
        String phoneNumber = signupRequestDto.getPhoneNumber();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String birth = signupRequestDto.getBirth();
        Role role = roleRepository.findById(UserRole.ROLE_USER)
                .orElseThrow(() -> new CustomException(INVALID_ROLE));


        // 닉네임 중복체크
        Optional<User> foundNickName = userRepository.findByNickName(nickName);
        if (foundNickName.isPresent()) throw new CustomException(DUPLICATE_NICKNAME);

        // 이메일 중복체크
        Optional<User> foundEmail = userRepository.findByEmail(email);
        if (foundEmail.isPresent()) throw new CustomException(DUPLICATE_EMAIL);

        // 전화번호 중복체크
        Optional<User> foundPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (foundPhoneNumber.isPresent()) throw new CustomException(DUPLICATE_PHONENUMBER);

        User user = User.builder()
                .userName(userName)
                .nickName(nickName)
                .email(email)
                .phoneNumber(phoneNumber)
                .password(password)
                .birth(birth)
                .role(role)
                .build();
        userRepository.save(user);
    }
}

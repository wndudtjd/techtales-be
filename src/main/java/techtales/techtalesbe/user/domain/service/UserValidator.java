package techtales.techtalesbe.user.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import techtales.techtalesbe.common.exception.CustomException;
import techtales.techtalesbe.user.domain.model.User;
import techtales.techtalesbe.user.domain.repository.UserRepository;

import java.util.Optional;

import static techtales.techtalesbe.common.exception.CustomErrorCode.*;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validateNewUser(String nickName, String email, String phoneNumber) {
        // 닉네임 중복체크
        Optional<User> foundNickName = userRepository.findByNickName(nickName);
        if (foundNickName.isPresent()) throw new CustomException(DUPLICATE_NICKNAME);

        // 이메일 중복체크
        Optional<User> foundEmail = userRepository.findByEmail(email);
        if (foundEmail.isPresent()) throw new CustomException(DUPLICATE_EMAIL);

        // 전화번호 중복체크
        Optional<User> foundPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (foundPhoneNumber.isPresent()) throw new CustomException(DUPLICATE_PHONENUMBER);
    }
}

package techtales.techtalesbe.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techtales.techtalesbe.domain.user.dto.SignupRequestDTO;
import techtales.techtalesbe.global.exception.ResponseMessage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage<String>> signup(@Validated @RequestBody SignupRequestDTO signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseMessage.SuccessResponse("회원가입 성공","");
    }
}

package techtales.techtalesbe.user.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techtales.techtalesbe.common.exception.ResponseMessage;
import techtales.techtalesbe.common.exception.ResponseMessage;
import techtales.techtalesbe.user.application.dto.UserSignupRequest;
import techtales.techtalesbe.user.application.service.UserSignupService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserSignupService userSignupService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage<String>> signup(@Validated @RequestBody UserSignupRequest userSignupRequest) {
        userSignupService.signup(userSignupRequest);
        return ResponseMessage.SuccessResponse("회원가입 성공","");
    }
}

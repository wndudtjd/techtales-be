package techtales.techtalesbe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/log-test")
    public String logTest() {
        logger.info("INFO 로그 테스트");
        logger.debug("DEBUG 로그 테스트");
        logger.error("ERROR 로그 테스트");
        return "로그 출력 완료";
    }
}

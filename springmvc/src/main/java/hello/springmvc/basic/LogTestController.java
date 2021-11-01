package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        log.trace("trace log={}", name);
        log.trace("trace log="+ name); // 사용 X.
        // logging level이 info부터 적용된다면 trace log는 출력되지도 않는데
        // trace log = name의 문자열 연산이 일어나기 때문에 메모리를 사용함
        // {}, name 의 경우에는 파라미터를 넘기는 것이기 때문에 연산이 일어나지 않음


        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }


}

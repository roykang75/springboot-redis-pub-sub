package xyz.oiio.springboot_redis_pub_sub.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceB {

    public void sleep() {
        log.info("ServiceB.sleep 5sec");
        try {
            Thread.sleep(5000); //1초 대기
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("ServiceB.sleep done");
    }
}

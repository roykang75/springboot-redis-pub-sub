package xyz.oiio.springboot_redis_pub_sub.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceA {

    public void sleep() {
        log.info("ServiceA.sleep 8sec");
        try {
            Thread.sleep(8000); //1초 대기
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("ServiceA.sleep done");
    }
}

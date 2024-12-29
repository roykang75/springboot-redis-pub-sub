package xyz.oiio.springboot_redis_pub_sub.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NewsMessageReceiver {

    public void receiveMessage(String message) {
        log.info("NewsMessageReceiver.Received news message: {}", message);
    }
}

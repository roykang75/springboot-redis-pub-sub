package xyz.oiio.springboot_redis_pub_sub.sub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.oiio.springboot_redis_pub_sub.service.ServiceB;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChatMessageReceiver {

    private final ServiceB serviceB;

    public void receiveMessage(String message) {
        log.info("ChatMessageReceiver.Received chat message: {}", message);
        serviceB.sleep();
    }
}

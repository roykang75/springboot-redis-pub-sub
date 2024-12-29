package xyz.oiio.springboot_redis_pub_sub.sub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.oiio.springboot_redis_pub_sub.service.ServiceA;

@Slf4j
@RequiredArgsConstructor
@Component
public class AlertMessageReceiver {

    private final ServiceA serviceA;

    public void receiveMessage(String message) {
        log.info("AlertMessageReceiver.Received alert message: {}", message);
        serviceA.sleep();
    }
}

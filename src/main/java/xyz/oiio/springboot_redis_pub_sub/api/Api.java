package xyz.oiio.springboot_redis_pub_sub.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.oiio.springboot_redis_pub_sub.pub.MessageSender;


@Slf4j
@RequiredArgsConstructor
@RestController
public class Api {

    private final MessageSender publisher;

    @GetMapping("/publish")
    public ResponseEntity<String> publish() {
        String message = "Hello World!";
        publisher.sendChatMessage(message);
        publisher.sendNewsMessage(message);
        publisher.sendAlertMessage(message);
        return ResponseEntity.ok("Message published");
    }
}

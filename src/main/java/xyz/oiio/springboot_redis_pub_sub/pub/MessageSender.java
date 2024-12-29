package xyz.oiio.springboot_redis_pub_sub.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import xyz.oiio.springboot_redis_pub_sub.config.RedisConfig;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageSender {

    private final RedisTemplate<String, String> stringRedisTemplate;
//    private RedisTemplate<byte[], byte[]> binaryRedisTemplate;

    public void sendChatMessage(String message) {
        log.info("Sending chat message: {}", message);
        stringRedisTemplate.convertAndSend(RedisConfig.CHAT_CHANNEL, message);
    }

    public void sendNewsMessage(String message) {
        log.info("Sending news message: {}", message);
        stringRedisTemplate.convertAndSend(RedisConfig.NEWS_CHANNEL, message);
    }

    public void sendAlertMessage(String message) {
        log.info("Sending alert message: {}", message);
        stringRedisTemplate.convertAndSend(RedisConfig.ALERT_CHANNEL, message);
    }

//    public void sendChatBinaryMessage(byte[] message) {
//        log.info("Sending chat binary message");
//        binaryRedisTemplate.convertAndSend(RedisConfig.CHAT_CHANNEL.getBytes(), message);
//    }
//
//    public void sendNewsBinaryMessage(byte[] message) {
//        log.info("Sending news binary message");
//        binaryRedisTemplate.convertAndSend(RedisConfig.NEWS_CHANNEL.getBytes(), message);
//    }
//
//    public void sendAlertBinaryMessage(byte[] message) {
//        log.info("Sending alert binary message");
//        binaryRedisTemplate.convertAndSend(RedisConfig.ALERT_CHANNEL.getBytes(), message);
//    }
}

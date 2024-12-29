package xyz.oiio.springboot_redis_pub_sub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import xyz.oiio.springboot_redis_pub_sub.sub.AlertMessageReceiver;
import xyz.oiio.springboot_redis_pub_sub.sub.ChatMessageReceiver;
import xyz.oiio.springboot_redis_pub_sub.sub.NewsMessageReceiver;

@Configuration
public class RedisConfig {
    // 여러 채널 정의
    public static final String CHAT_CHANNEL = "chat";
    public static final String NEWS_CHANNEL = "news";
    public static final String ALERT_CHANNEL = "alert";

    @Bean
    public ChannelTopic chatChannel() {
        return new ChannelTopic(CHAT_CHANNEL);
    }

    @Bean
    public ChannelTopic newsChannel() {
        return new ChannelTopic(NEWS_CHANNEL);
    }

    @Bean
    public ChannelTopic alertChannel() {
        return new ChannelTopic(ALERT_CHANNEL);
    }

    @Bean
    public RedisTemplate<byte[], byte[]> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
        MessageListenerAdapter chatMessageListener,
        MessageListenerAdapter newsMessageListener,
        MessageListenerAdapter alertMessageListener) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // 각 채널별 메시지 리스너 등록
        container.addMessageListener(chatMessageListener, chatChannel());
        container.addMessageListener(newsMessageListener, newsChannel());
        container.addMessageListener(alertMessageListener, alertChannel());

        return container;
    }

    @Bean
    public MessageListenerAdapter chatMessageListener(ChatMessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    public MessageListenerAdapter newsMessageListener(NewsMessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    public MessageListenerAdapter alertMessageListener(AlertMessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}

package xyz.oiio.springboot_redis_pub_sub.config;

import java.util.concurrent.Executors;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class VirtualThreadConfig {

    // Web Request 를 처리하는 Tomcat 이 Virtual Thread를 사용하여 유입된 요청을 처리하도록 한다.
    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler -> {
            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        };
    }

    // Async Task에 Virtual Thread 사용
    @Bean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)
    public AsyncTaskExecutor asyncTaskExecutor() {
        final TaskExecutorAdapter taskExecutorAdapter = new TaskExecutorAdapter(
                Executors.newVirtualThreadPerTaskExecutor());
        taskExecutorAdapter.setTaskDecorator(new MdcTaskDecorator());
        return taskExecutorAdapter;
    }
}

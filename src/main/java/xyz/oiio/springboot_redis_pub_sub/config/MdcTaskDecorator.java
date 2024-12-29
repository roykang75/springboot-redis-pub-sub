package xyz.oiio.springboot_redis_pub_sub.config;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

public class MdcTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(final Runnable runnable) {
        final Map<String, String> mdcContext = MDC.getCopyOfContextMap();
        return () -> {
            if (mdcContext != null) {
                MDC.setContextMap(mdcContext);
            }
            runnable.run();

            MDC.clear();
        };
    }
}
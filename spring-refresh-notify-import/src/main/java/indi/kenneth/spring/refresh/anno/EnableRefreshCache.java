package indi.kenneth.spring.refresh.anno;


import indi.kenneth.spring.refresh.channel.ChannelEnum;
import indi.kenneth.spring.refresh.registrar.RefreshCacheRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RefreshCacheRegistrar.class})
public @interface EnableRefreshCache {
    ChannelEnum channel();

}

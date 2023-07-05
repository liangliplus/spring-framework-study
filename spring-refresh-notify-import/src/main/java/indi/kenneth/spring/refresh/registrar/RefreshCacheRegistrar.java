package indi.kenneth.spring.refresh.registrar;

import indi.kenneth.spring.refresh.anno.EnableRefreshCache;
import indi.kenneth.spring.refresh.channel.ChannelEnum;
import indi.kenneth.spring.refresh.channel.ZookeeperSyncChannel;
import indi.kenneth.spring.refresh.config.ZkConfig;
import indi.kenneth.spring.refresh.util.RefreshUtils;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class RefreshCacheRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //先获取标记注解类上的 @EnableRefreshCache
        AnnotationAttributes enableRefreshCache = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableRefreshCache.class.getName()));
        //在解析配置对应的channel
        if (enableRefreshCache != null) {
            ChannelEnum channel = enableRefreshCache.getEnum("channel");
            registerCommon(registry);
            registerChannelBeanDefinitions(channel, registry);

        }


    }

    private void registerCommon(BeanDefinitionRegistry registry) {
        this.createBean(RefreshUtils.class,registry);
    }

    private void registerChannelBeanDefinitions(ChannelEnum channel, BeanDefinitionRegistry registry) {
        switch (channel) {
            //zk 注册一个zkConfig 和 一个实现SyncDataChannel的zk组件类
            case ZK:
                this.createBean(ZkConfig.class,registry);
                this.createBean(ZookeeperSyncChannel.class,registry);
                break;
            case REDIS:
                throw new UnsupportedOperationException("暂不支持redis channel");
            default:
                throw new RuntimeException("channel type exception");

        }

    }

    private void createBean(Class<?> clazz,BeanDefinitionRegistry registry) {
        if (!registry.containsBeanDefinition(clazz.getSimpleName())) {
            RootBeanDefinition beanDefinition = new RootBeanDefinition();
            beanDefinition.setBeanClass(clazz);
            beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            registry.registerBeanDefinition(clazz.getSimpleName(),beanDefinition);
        }

    }
}

package com.example.ir.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.BeanFactoryNativeConfigurationProcessor;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.NativeConfigurationRegistry;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.NativeProxyEntry;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.nativex.hint.TypeAccess;

import java.util.Map;

@Slf4j
public class ClientsBeanFactoryNativeConfigurationProcessor
        implements BeanFactoryNativeConfigurationProcessor {

    @Override
    public void process(ConfigurableListableBeanFactory beanFactory, NativeConfigurationRegistry registry) {
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(Client.class);
        if (log.isInfoEnabled()) {
            log.info("there are " + beansWithAnnotation.size() + " beans with this annotation.");
        }

        beansWithAnnotation.forEach((bn, e) -> {
            log.info("found a bean of type " + e.getClass().getCanonicalName());
            registry.reflection().forType(e.getClass()).withAccess(TypeAccess.values()).build();
            registry.proxy().add(NativeProxyEntry.ofInterfaces(e.getClass().getInterfaces()));
        });
    }
}

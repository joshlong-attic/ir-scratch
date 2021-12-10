package com.example.ir.clients;

import lombok.extern.log4j.Log4j2;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.BeanFactoryNativeConfigurationProcessor;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.NativeConfigurationRegistry;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.NativeProxyEntry;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Map;

@Log4j2
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
            Class<?>[] interfaces = e.getClass().getInterfaces();
            for (var i : interfaces)
                log.info("adding interface " + i.getName());
            registry.proxy().add(NativeProxyEntry.ofInterfaces(interfaces));
        });
    }
}

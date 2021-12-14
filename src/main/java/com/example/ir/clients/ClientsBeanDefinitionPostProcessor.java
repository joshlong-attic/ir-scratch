package com.example.ir.clients;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.context.bootstrap.generator.bean.BeanRegistrationWriter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.BeanDefinitionPostProcessor;
import org.springframework.core.ResolvableType;
import org.springframework.util.ClassUtils;

import java.util.Objects;

@Slf4j
class ClientsBeanDefinitionPostProcessor implements BeanDefinitionPostProcessor, BeanFactoryAware {

	private final String enableClientsName = EnableClients.class.getName();
	private ConfigurableBeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (ConfigurableBeanFactory) beanFactory;
	}

	@Override
	public void postProcessBeanDefinition(String beanName, RootBeanDefinition beanDefinition) {
		if (ClassUtils.isPresent(this.enableClientsName, this.beanFactory.getBeanClassLoader()) &&
			beanDefinition.hasBeanClass() &&
			Objects.requireNonNull(beanDefinition.getBeanClassName()).contains(com.example.ir.clients.ClientFactoryBean.class.getName())) {
			this.enrich(beanDefinition);
		}
	}

	@SneakyThrows
	private void enrich(RootBeanDefinition beanDefinition) {

		var beanClassName = beanDefinition.getBeanClassName();
		var propertyValues = beanDefinition.getPropertyValues();
		var type = propertyValues.getPropertyValue("type");
		var value = Objects.requireNonNull(type).getValue();
		log.info("the bean class name is " + beanClassName + " and the resolved type is " + value);
 	beanDefinition.setTargetType(ResolvableType.forClass(Class.forName((String)value)));
		beanDefinition.setAttribute(BeanRegistrationWriter.PRESERVE_TARGET_TYPE, true);
		log.info ("finished") ;
	}


}

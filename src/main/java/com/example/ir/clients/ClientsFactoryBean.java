package com.example.ir.clients;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Method;

@Slf4j
class ClientsFactoryBean<T> implements FactoryBean<T> {

	private Class<?> type;

	@SneakyThrows
	public void setType(String type) {
		this.type = Class.forName(type);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getObject() {
		log.info("trying to create an instance of " + this.type);
		return (T) ProxyFactory.getProxy(this.type, (MethodInterceptor) invocation -> {

			Method method = invocation.getMethod();

			Activator annotation = method.getAnnotation(Activator.class);
			if (null != annotation) {
				System.out.println("you called " + method.getName() +"! enjoy your logs!");
			}

			return null;
		});
	}

	@Override
	public Class<?> getObjectType() {
		return this.type;
	}
}

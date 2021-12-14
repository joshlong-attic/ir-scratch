package com.example.ir.clients;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Method;

@Slf4j
public class ClientFactoryBean<T> implements FactoryBean<T> {

	private Class<?> type;

	@SneakyThrows
	public void setType(String type) {
		this.type = Class.forName(type);
		log.info("the type is " + this.type.getCanonicalName());
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getObject() {
		return (T) ProxyFactory.getProxy(this.type, (MethodInterceptor) invocation -> {

			Method method = invocation.getMethod();

			Activator annotation = method.getAnnotation(Activator.class);
			if (null != annotation) {
				System.out.println("you called " + method.getName());
			}

			return null;
		});
	}

	@Override
	public Class<?> getObjectType() {
		return this.type;
	}
}

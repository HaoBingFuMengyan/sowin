package com.hbf.shiro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

/**
 * 异常跳转配置器
 * @author haobingfu
 *
 */
@Configuration
public class ExceptionConfiguration {
	@Bean
	public SimpleMappingExceptionResolver resolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties properties = new Properties();
		properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "WEB-INF/error/unauth");//没权限403
		resolver.setExceptionMappings(properties);
		return resolver;
	}



}

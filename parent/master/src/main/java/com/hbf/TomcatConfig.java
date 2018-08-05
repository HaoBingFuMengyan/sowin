package com.hbf;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Created by haobingfu on 2018/4/14.
 */
@Configuration
public class TomcatConfig {

    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        ConfigurableEmbeddedServletContainer factory = new TomcatEmbeddedServletContainerFactory();
        factory.setDocumentRoot(new File(System.getProperty("user.dir")+"/parent/master/src/main/webapp"));
        return (EmbeddedServletContainerFactory) factory;
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }
}

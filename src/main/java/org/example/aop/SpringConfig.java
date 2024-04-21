package org.example.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(value = {"org.example.aop","org.example.controller","org.example.cache"})
@EnableAspectJAutoProxy
public class SpringConfig {
}
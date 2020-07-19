package com.dai.rabbit.producer.autoconfigure;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * $RabbitProducerAutoConfiguration 自动装配
 * @author daihuhu
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan({"com.dai.rabbit.producer.*"})
public class RabbitProducerAutoConfiguration {
}

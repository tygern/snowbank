package com.tygern.snowbank

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ApplicationContext {
    @Bean
    open fun flakeProvider() = StaticFlakeProvider()
}
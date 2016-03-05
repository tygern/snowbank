package com.tygern.snowbank

import com.tygern.snowbank.flake.StaticFlakeProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ApplicationContext {
    @Bean
    open fun flakeProvider() = StaticFlakeProvider()
}
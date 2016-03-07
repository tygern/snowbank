package com.tygern.snowbank

import com.tygern.snowbank.flake.staticsource.StaticFlakeProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("static")
@Configuration
open class StaticContext {
    @Bean
    open fun flakeProvider() = StaticFlakeProvider()
}
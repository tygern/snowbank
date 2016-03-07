package com.tygern.snowbank

import com.tygern.snowbank.flake.dbsource.DBFlakeProvider
import com.tygern.snowbank.flake.dbsource.FlakeRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("!static")
@Configuration
open class ApplicationContext {
    @Bean
    open fun flakeProvider(flakeRepository: FlakeRepository) = DBFlakeProvider(flakeRepository)
}
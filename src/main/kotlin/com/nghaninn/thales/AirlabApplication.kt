package com.nghaninn.thales

import com.nghaninn.thales.configuration.SecretConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.lang.Exception

@SpringBootApplication
@EnableConfigurationProperties(SecretConfiguration::class)
class AirlabApplication

fun main(args: Array<String>) {
	try {
		runApplication<AirlabApplication>(*args)
	} catch (e: Exception) {
		e.printStackTrace()
	}
}

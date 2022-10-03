package com.nghaninn.thales

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.lang.Exception

@SpringBootApplication
class AirlabApplication

fun main(args: Array<String>) {
	try {
		runApplication<AirlabApplication>(*args)
	} catch (e: Exception) {
		e.printStackTrace()
	}
}

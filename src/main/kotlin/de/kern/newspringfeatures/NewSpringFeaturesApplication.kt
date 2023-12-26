package de.kern.newspringfeatures

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class NewSpringFeaturesApplication

fun main(args: Array<String>) {
    runApplication<NewSpringFeaturesApplication>(*args)
}

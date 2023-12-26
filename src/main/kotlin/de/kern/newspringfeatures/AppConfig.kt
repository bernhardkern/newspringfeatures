package de.kern.newspringfeatures

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestClient

@Configuration
@EnableScheduling
class AppConfig {

//    @Bean
//    fun applicationRunner(todoRepository: TodoRepository) = ApplicationRunner {
//        todoRepository.create(
//            Todo(
//                id = 1,
//                userId = 1,
//                title = "new features",
//                completed = true
//            )
//        )
//    }

    @Bean
    fun restClient(): RestClient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com/users/1").build()

}
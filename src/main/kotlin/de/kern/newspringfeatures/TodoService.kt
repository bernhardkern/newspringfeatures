package de.kern.newspringfeatures

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit.SECONDS

@Service
class TodoService(
    val todoAdapter: TodoAdapter,
    val todoRepository: TodoRepository,
) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Scheduled(initialDelay = 1, fixedRate = 30, timeUnit = SECONDS)
    fun saveRandomTodo() {
        (1..20).random().let { randomId ->
            todoRepository.findByIdOrNull(randomId)
                ?: todoAdapter.findById(randomId)
                    .let(todoRepository::create)
                    .also { log.info("created new todo with id $randomId") }
        }
    }
}
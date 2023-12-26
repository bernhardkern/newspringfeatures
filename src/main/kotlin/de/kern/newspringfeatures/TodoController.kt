package de.kern.newspringfeatures

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/todos")
@EnableScheduling
class TodoController(val todoRepository: TodoRepository) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getAll() =
        ResponseEntity.ok().body(todoRepository.findAll())

    @GetMapping("/{id}")
    fun findTodo(@PathVariable id: Int) =
        ResponseEntity.ok().body(todoRepository.findByIdOrNull(id))

    @PostMapping
    fun create(@RequestBody todo: Todo): ResponseEntity<Todo> =
        todoRepository.create(todo)
            .also { log.info("created todo with id ") }
            .let { ResponseEntity.created(URI.create("http://localhost:9999/api/todos/${todo.id}")).build() }
}
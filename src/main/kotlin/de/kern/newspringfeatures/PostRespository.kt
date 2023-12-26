package de.kern.newspringfeatures

import kotlin.jvm.optionals.getOrNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Component

@Component
class TodoRepository(val jdbcClient: JdbcClient) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun findAll(): List<Todo> =
        jdbcClient
            .sql("SELECT id, user_id, title, completed from todo")
            .query(Todo::class.java).list()

    fun findByIdOrNull(id: Int): Todo? =
        jdbcClient
            .sql("SELECT id, user_id, title, completed from todo WHERE id = :id")
            .param("id", id)
            .query(Todo::class.java).optional().getOrNull()

    fun create(todo: Todo) =
        jdbcClient
            .sql("INSERT INTO todo (id, user_id, title, completed) VALUES (?, ?, ?, ?)")
            .params(todo.id, todo.userId, todo.title, todo.completed)
            .update()

}
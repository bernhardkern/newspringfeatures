package de.kern.newspringfeatures

import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class TodoAdapter(val restClient: RestClient) {

    fun findById(id: Int): Todo = restClient
        .get()
        .uri {
            it.path("/todos")
                .queryParam("id", id).build()
        }
        .retrieve()
        .body(typeReference<List<Todo>>())!!
        .singleOrNull()
        ?: error("todo with id $id does not exist")


}

inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}

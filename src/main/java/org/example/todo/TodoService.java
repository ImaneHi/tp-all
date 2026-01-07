package org.example.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class TodoService {

    List<Todo> todos = new ArrayList<>();
    AtomicLong idCounter = new AtomicLong(1);


    private Optional<Todo> findTodoById(Long id) {
        return todos.stream()
                .filter(todo -> Objects.equals(todo.getId(), id))
                .findFirst();
    }

    public Todo addTodo(Todo todo){
        if (todo.getTitle() == null || todo.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }


        Long newId = idCounter.getAndIncrement();
        todo.setId(newId);


        if (!todo.isCompleted()) {
            todo.setCompleted(false);
        }

        todos.add(todo);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos) ;
    }

    public Todo completeTodo(Long id) {
        Todo todo = findTodoById(id).orElseThrow(()-> new IllegalArgumentException("not found : "));
        todo.setCompleted(true);
        return todo;
    }

    public boolean deleteTodo(Long id) {
        Todo todo = findTodoById(id).orElseThrow(() -> new IllegalArgumentException("not found : "));
        return todos.remove(todo);
    }
}

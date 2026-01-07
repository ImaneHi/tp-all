package org.example.todo;
import org.junit.Test;

import static org.junit.Assert.*;

public class TodoServiceTest {
    @Test
    public void testAddTodo() {
        TodoService service = new TodoService();

        Todo todo = new Todo();
        todo.setTitle("Learn JUnit");

        Todo savedTodo = service.addTodo(todo);

        assertEquals(1, service.getAllTodos().size());
        assertEquals("Learn JUnit", savedTodo.getTitle());
        assertFalse(savedTodo.isCompleted());
        assertEquals(1L, savedTodo.getId());
    }

    @Test
    public void testGetAllTodos(){
        TodoService service = new TodoService();

        Todo t1 = new Todo();
        t1.setTitle("Task 1");

        Todo t2 = new Todo();
        t2.setTitle("Task 2");

        service.addTodo(t1);
        service.addTodo(t2);

        assertEquals(2, service.getAllTodos().size());
    }

    @Test
    public void testCompleteTodo() {
        TodoService service = new TodoService();

        Todo todo = new Todo();
        todo.setTitle("Finish homework");

        Todo savedTodo = service.addTodo(todo);
        service.completeTodo(savedTodo.getId());

        assertTrue(savedTodo.isCompleted());
    }

    @Test
    public void testDeleteTodo() {
        TodoService service = new TodoService();

        Todo todo = new Todo();
        todo.setTitle("Task to delete");

        Todo savedTodo = service.addTodo(todo);
        boolean deleted = service.deleteTodo(savedTodo.getId());

        assertTrue(deleted);
        assertEquals(0, service.getAllTodos().size());
    }

}


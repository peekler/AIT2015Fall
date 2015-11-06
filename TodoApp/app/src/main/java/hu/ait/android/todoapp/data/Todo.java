package hu.ait.android.todoapp.data;

import com.orm.SugarRecord;

/**
 * Created by peter on 2015. 10. 15..
 */
public class Todo extends SugarRecord<Todo> {

    private String todo;
    private boolean done;

    public Todo() {
    }

    public Todo(String todo, boolean done) {
        this.todo = todo;
        this.done = done;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

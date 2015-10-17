package demo.android.app.hu.checklistdemo.data;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Peter on 2014.10.23..
 */
public class Todo implements Serializable {
    @DatabaseField(generatedId = true)
    int id = -1;
    @DatabaseField
    String todo;
    @DatabaseField
    boolean done;

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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

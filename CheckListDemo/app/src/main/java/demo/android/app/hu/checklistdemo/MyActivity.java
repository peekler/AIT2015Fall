package demo.android.app.hu.checklistdemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import demo.android.app.hu.checklistdemo.adapter.TodoListAdapter;
import demo.android.app.hu.checklistdemo.data.DBHelper;
import demo.android.app.hu.checklistdemo.data.Todo;


public class MyActivity extends ListActivity {

    public static final int REQUEST_CODE_CREATE_TODO = 101;
    private DBHelper databaseHelper = null;
    private Dao<Todo, Integer> todoDAO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        databaseHelper = new DBHelper(getApplicationContext());

        List<Todo> initialTodos = new ArrayList<Todo>();
        try {
            initialTodos = queryAllTodo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TodoListAdapter todoListAdapter = new TodoListAdapter(this,initialTodos);
        setListAdapter(todoListAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseHelper = new DBHelper(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    public Todo createTodo(Todo todo) {
        try {
            getTodoDAO().create(todo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todo;
    }

    // this updates the Todo in the DB
    public void updateTodoWithDoneState(Todo todo) {
        try {
            getTodoDAO().update(todo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Todo> queryAllTodo() throws SQLException {
        return (ArrayList<Todo>) getTodoDAO().queryForAll();
    }

    public Dao<Todo, Integer> getTodoDAO() throws SQLException {
        if (todoDAO == null) {
            todoDAO = databaseHelper.getDao(Todo.class);
        }
        return todoDAO;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                Todo newTodo = (Todo) data.getSerializableExtra(CreateTodoActivity.KEY_TODO);
                // after this the newTodo will also have an id
                newTodo = createTodo(newTodo);
                ((TodoListAdapter) getListAdapter()).addTodo(newTodo);
                ((TodoListAdapter) getListAdapter()).notifyDataSetChanged();
                Toast.makeText(this, "Place added to the list!", Toast.LENGTH_LONG).show();
                break;
            case RESULT_CANCELED:
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_new_todo) {
            Intent i = new Intent();
            i.setClass(this, CreateTodoActivity.class);
            startActivityForResult(i, REQUEST_CODE_CREATE_TODO);

            return true;
        } else  if (id == R.id.action_delete_all) {
            try {
                // this clears all row in the table
                TableUtils.clearTable(databaseHelper.getConnectionSource(),Todo.class);
                // this removes the elements in the list and refreshes it
                ((TodoListAdapter)getListAdapter()).removeAllItem();
                ((TodoListAdapter) getListAdapter()).notifyDataSetChanged();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

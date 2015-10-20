package hu.ait.android.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;

import hu.ait.android.todoapp.adapter.TodoRecylerAdapter;
import hu.ait.android.todoapp.touch.TodoListTouchHelper;

public class MainActivity extends AppCompatActivity {

    private TodoRecylerAdapter adapter;
    private EditText etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new TodoRecylerAdapter(this);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        TodoListTouchHelper touchHelperCallback = new TodoListTouchHelper(
                adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(
                touchHelperCallback);
        touchHelper.attachToRecyclerView(recyclerView);

        etNote = (EditText) findViewById(R.id.etTodo);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            adapter.addTodo(etNote.getText().toString());
        }
    }
}

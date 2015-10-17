package demo.android.app.hu.checklistdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import demo.android.app.hu.checklistdemo.data.Todo;


public class CreateTodoActivity extends Activity {

    public static final String KEY_TODO = "KEY_TODO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        final EditText etTodo = (EditText) findViewById(R.id.etTodo);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResult = new Intent();
                intentResult.putExtra(KEY_TODO,
                        new Todo(etTodo.getText().toString(), false));
                setResult(RESULT_OK,intentResult);
                finish();
            }
        });
    }
}

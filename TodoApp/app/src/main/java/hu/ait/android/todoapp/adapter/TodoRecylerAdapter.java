package hu.ait.android.todoapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.todoapp.R;
import hu.ait.android.todoapp.data.Todo;

/**
 * Created by peter on 2015. 10. 15..
 */
public class TodoRecylerAdapter extends
        RecyclerView.Adapter<TodoRecylerAdapter.ViewHolder> {

    private List<Todo> todos;
    private Context context;

    public TodoRecylerAdapter(Context context) {
        this.context = context;
        todos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            todos.add(new Todo("Todo "+i, false));
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View todoRow = LayoutInflater.from(
                context).inflate(R.layout.todo_row, parent, false);
        return new ViewHolder(todoRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Todo todo = todos.get(position);
        holder.tvTodo.setText(todo.getTodo());
        holder.cbDone.setChecked(todo.isDone());
        holder.cbDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todo.setDone(((CheckBox)v).isChecked());
                Toast.makeText(context,
                        "Todo clicked "+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTodo;
        private final CheckBox cbDone;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTodo = (TextView) itemView.findViewById(R.id.tvTodo);
            cbDone = (CheckBox) itemView.findViewById(R.id.cbDone);
        }
    }
}

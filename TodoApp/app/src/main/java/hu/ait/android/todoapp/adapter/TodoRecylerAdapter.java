package hu.ait.android.todoapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
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
    private int lastPosition = -1;

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

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void addTodo(String todo) {
        todos.add(new Todo(todo, false));
        notifyDataSetChanged();
    }

    public void removeTodo(int index) {
        todos.remove(index);
        //notifyDataSetChanged();
        notifyItemRemoved(index);
    }

    public void swapTodos(int oldPosition, int newPosition) {
        if (oldPosition < newPosition) {
            for (int i = oldPosition; i < newPosition; i++) {
                Collections.swap(todos, i, i + 1);
            }
        } else {
            for (int i = oldPosition; i > newPosition; i--) {
                Collections.swap(todos, i, i - 1);
            }
        }
        notifyItemMoved(oldPosition, newPosition);
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
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

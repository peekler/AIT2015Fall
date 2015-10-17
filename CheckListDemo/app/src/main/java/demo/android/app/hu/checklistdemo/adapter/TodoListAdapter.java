package demo.android.app.hu.checklistdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import demo.android.app.hu.checklistdemo.MyActivity;
import demo.android.app.hu.checklistdemo.R;
import demo.android.app.hu.checklistdemo.data.Todo;

/**
 * Created by Peter on 2014.10.23..
 */
public class TodoListAdapter extends BaseAdapter {
    private Context context;
    private List<Todo> todoList;

    public TodoListAdapter(Context context, List<Todo> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    public void addTodo(Todo place) {
        todoList.add(place);
    }

    public void removeItem(int index) {
        todoList.remove(index);
    }
    public void removeAllItem() {
        todoList.clear();
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public Object getItem(int i) {
        return todoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    static class ViewHolder {
        TextView tvTodo;
        CheckBox cbDone;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.todo_list_row, null);
            ViewHolder holder = new ViewHolder();
            holder.tvTodo = (TextView) v.findViewById(R.id.tvTodo);
            holder.cbDone = (CheckBox) v.findViewById(R.id.cbDone);
            v.setTag(holder);
        }

        final Todo todo = todoList.get(position);
        if (todo != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.tvTodo.setText(todo.getTodo());
            holder.cbDone.setChecked(todo.isDone());
            holder.cbDone.setOnClickListener(new OnClickListener) {
                @Override
                public void onClick(View v)
                    todo.setDone(((CheckBox)v).isChecked());
                    ((MyActivity)context).updateTodoWithDoneState(todo);
                }
            });
        }
        return v;
    }
}

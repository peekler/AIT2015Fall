package hu.ait.android.noteinflater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etNote;
    private LinearLayout layoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNote = (EditText) findViewById(R.id.etNote);
        layoutContainer = (LinearLayout) findViewById(R.id.layoutContainer);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            // save the note here
            if (!"".equals(etNote.getText().toString())) {
                View row = getLayoutInflater().inflate(R.layout.row_note, null);
                TextView tvNote = (TextView) row.findViewById(R.id.tvNote);
                tvNote.setText(etNote.getText().toString());

                etNote.setText("");

                // add the row to the linearlayout
                layoutContainer.addView(row);
            } else {
                etNote.setError("The field should not be empty!");
            }
        }
    }
}

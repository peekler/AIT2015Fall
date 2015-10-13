package hu.ait.android.noteinflater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
                final View row = getLayoutInflater().inflate(
                        R.layout.row_note, null);
                TextView tvNote = (TextView) row.findViewById(R.id.tvNote);
                tvNote.setText(etNote.getText().toString());
                etNote.setText("");

                Button btnDelRow = (Button) row.findViewById(R.id.btnDelRow);
                btnDelRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layoutContainer.removeView(row);
                    }
                });

                // add the row to the linearlayout
                layoutContainer.addView(row, 0);
            } else {
                etNote.setError("The field should not be empty!");
            }
        } else if (v.getId() == R.id.btnDeleteAll) {
            layoutContainer.removeAllViews();

        }
    }
}

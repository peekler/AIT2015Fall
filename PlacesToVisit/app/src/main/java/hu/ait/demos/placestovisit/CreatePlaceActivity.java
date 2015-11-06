package hu.ait.demos.placestovisit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import hu.ait.demos.placestovisit.data.Place;


public class CreatePlaceActivity extends AppCompatActivity {
    public static final String KEY_PLACE = "KEY_PLACE";
    private Spinner spinnerPlaceType;
    private EditText etPlace;
    private EditText etPlaceDesc;
    private Place placeToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_place);

        if (getIntent().getSerializableExtra(MainActivity.KEY_EDIT) != null) {
            placeToEdit = (Place) getIntent().getSerializableExtra(MainActivity.KEY_EDIT);
        }

        spinnerPlaceType = (Spinner) findViewById(R.id.spinnerPlaceType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.placetypes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlaceType.setAdapter(adapter);

        etPlace = (EditText) findViewById(R.id.etPlaceName);
        etPlaceDesc = (EditText) findViewById(R.id.etPlaceDesc);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePlace();
            }
        });

        if (placeToEdit != null) {
            etPlace.setText(placeToEdit.getPlaceName());
            etPlaceDesc.setText(placeToEdit.getDescription());
            spinnerPlaceType.setSelection(placeToEdit.getPlaceType().getValue());
        }
    }

    private void savePlace() {
        Intent intentResult = new Intent();
        Place placeResult = null;
        if (placeToEdit != null) {
            placeResult = placeToEdit;
        } else {
            placeResult = new Place();
            placeResult.setPickUpDate(new Date(System.currentTimeMillis()));
        }

        placeResult.setPlaceName(etPlace.getText().toString());
        placeResult.setDescription(etPlaceDesc.getText().toString());
        placeResult.setPlaceType(
                Place.PlaceType.fromInt(spinnerPlaceType.getSelectedItemPosition()));

        intentResult.putExtra(KEY_PLACE, placeResult);
        setResult(RESULT_OK, intentResult);
        finish();
    }
}

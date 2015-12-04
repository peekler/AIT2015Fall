package hu.bme.aut.android.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView tvSensors;
    private TextView tvSensorValue;

    private SensorManager sensorManager;
    private Sensor sensorAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSensors = (TextView) findViewById(R.id.tvSensors);
        tvSensorValue = (TextView) findViewById(R.id.tvSensorValue);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //listSensors();
        sensorAcc=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    private void listSensors() {
        List<Sensor> sensorList =
                sensorManager.getSensorList(Sensor.TYPE_ALL);
        tvSensors.setText("");
        for (Sensor sensor : sensorList) {
            tvSensors.append(sensor.getName()+"\n");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorAcc,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float accX = event.values[0];
        float accY = event.values[1];
        float accZ = event.values[2];

        tvSensorValue.setText(
                "X: "+accX+"\n"+
                "Y: "+accY+"\n"+
                "Z: "+accZ
        );
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}

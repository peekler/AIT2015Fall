package hu.ait.android.animationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Animation pushAnim = AnimationUtils.loadAnimation(
                MainActivity.this, R.anim.push_anim);
        final Animation sendAnim = AnimationUtils.loadAnimation(
                this, R.anim.send_anim
        );

        sendAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(MainActivity.this, "Message sent",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        final LinearLayout layoutMsg = (LinearLayout) findViewById(R.id.layoutMessage);

        Button btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMsg.startAnimation(sendAnim);
            }
        });

        //pushAnim.setRepeatMode(Animation.INFINITE);

        final Button btnPress = (Button) findViewById(R.id.btnPress);
        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // play animation
                btnPress.startAnimation(pushAnim);
            }
        });
    }
}

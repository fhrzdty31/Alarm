package www.fhrzdty31.sch.id.alarm.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import www.fhrzdty31.sch.id.alarm.Alarm;
import www.fhrzdty31.sch.id.alarm.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(Splash.this, Alarm.class);
            startActivity(i);
        }, 3000);
    }
}
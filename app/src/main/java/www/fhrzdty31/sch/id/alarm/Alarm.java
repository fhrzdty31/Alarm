package www.fhrzdty31.sch.id.alarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class Alarm extends AppCompatActivity {

    TimePicker timePic;
    Button btnSet;
    private int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        timePic = findViewById(R.id.time_pic);
        btnSet = findViewById(R.id.btn_set);

        timePic.setOnTimeChangedListener((timePicker, sHour, sMinute) -> {
            hour = sHour;
            minute = sMinute;
        });
        btnSet.setOnClickListener(view -> {
            Toast.makeText(Alarm.this, "Set Alarm " + hour + " : " + minute, Toast.LENGTH_SHORT).show();
            setAlarm();
            setNotifikasi();
        });
    }

    private void setAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar call_alarm = Calendar.getInstance();
        Calendar call_now = Calendar.getInstance();

        call_alarm.setTime(new Date());
        call_now.setTime(new Date());

        call_alarm.set(Calendar.HOUR_OF_DAY, hour);
        call_alarm.set(Calendar.MINUTE, minute);
        call_alarm.set(Calendar.SECOND, 0);

        if (call_alarm.before(call_now)) {
            call_alarm.add(Calendar.DATE, 1);
        }

        Intent i = new Intent(Alarm.this, BroadcastReceiverPage.class);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent p = PendingIntent.getBroadcast(Alarm.this, 0, i, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, call_alarm.getTimeInMillis(), p);
    }

    private void setNotifikasi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notify", "Alarm", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Alarm Reminder");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
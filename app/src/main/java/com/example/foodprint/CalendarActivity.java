package com.example.foodprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;

    TextView thedate;
    TextView btngocalendar;
    Date rdate;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = year + "/" + month + "/" + dayOfMonth;

                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                rdate = c.getTime();
                String strDate = dateFormat.format(rdate);
                Log.d("printies", ""+strDate);

                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                intent.putExtra("date", strDate);
                startActivity(intent);

            }
        });
    }
}
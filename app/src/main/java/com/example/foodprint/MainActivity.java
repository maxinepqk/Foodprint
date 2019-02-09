package com.example.foodprint;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;

//import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static HashMap<String, String> foodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Initialise foodItems
        foodItems = new HashMap<String, String>();


//        RetrieveFeedTask testing = new RetrieveFeedTask();
//        testing.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Global foodItems get set functions
    public static void setFoodItem(String foodName, String expDate) {
        foodItems.put(expDate, foodName);
        Log.d("printies", "i set food " + foodName + " to date " +expDate);
    }

    public static String getFoodItem(String expDate) {
        String foodName = foodItems.get(expDate);
        Log.d("printies", "i get food " + foodName + " from date " +expDate);
        return foodName;
    }

}

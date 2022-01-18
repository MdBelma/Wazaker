package com.hazem.wazaker.Activites.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.badoualy.stepperindicator.BuildConfig;
import com.hassanjamil.hqibla.CompassActivity;
import com.hassanjamil.hqibla.Constants;
import com.hazem.wazaker.Activites.AzkarListActivity;
import com.hazem.wazaker.Activites.CounterActivity.CounterActivity;
import com.hazem.wazaker.Activites.EveningAzkarActivity;
import com.hazem.wazaker.Activites.FortyHadithListActivity;
import com.hazem.wazaker.Activites.MorningAzkarActivity;
import com.hazem.wazkar.R;
import com.hazem.wazkar.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    private int totalcounts = 0;

    @Override
    protected void onStart() {
        super.onStart();
        getTotalZekerCountsfromSharedPreferences();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        displayNotification();

    }

    private void displayNotification() {
        Notifications.addNotification(this);
    }

    @SuppressLint("SetTextI18n")
    public void getTotalZekerCountsfromSharedPreferences() {
        sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        totalcounts = sharedPreferences.getInt("zekertotalcounts", 0);
        setTotalzekerCountstext();
    }

    @SuppressLint("SetTextI18n")
    public void setTotalzekerCountstext() {
        binding.Totalzekertext.setText(getString(R.string.totalzeker) + "  " + totalcounts);
    }

    public void openCounterActivity(View view) {
        openActivity(CounterActivity.class);
    }

    public void openeveningAzkarActivity(View view) {
        openActivity(EveningAzkarActivity.class);
    }

    public void openMorningAzkarActivity(View view) {
        openActivity(MorningAzkarActivity.class);
    }

    public void openFortyHadithActivity(View view) {
        openActivity(FortyHadithListActivity.class);
    }

    public void openAzkarActivity(View view) {
        openActivity(AzkarListActivity.class);
    }

    public void openActivity(Class activity) {
        Intent intent = new Intent(getBaseContext(), activity);
        startActivity(intent);
    }

    public void openQiblaActivity(View view) {
        Intent intent = new Intent(MainActivity.this, CompassActivity.class);
        intent.putExtra(Constants.TOOLBAR_BG_COLOR, "#FFFFFF");        // Toolbar Background color
        intent.putExtra(Constants.COMPASS_BG_COLOR, "#FFFFFF");        // Compass background color
        intent.putExtra(Constants.ANGLE_TEXT_COLOR, "#000000");        // Angle Text color
        intent.putExtra(Constants.DRAWABLE_DIAL, R.drawable.dial);    // Your dial drawable resource
        intent.putExtra(Constants.DRAWABLE_QIBLA, R.drawable.qibla);    // Your qibla indicator drawable resource
        intent.putExtra(Constants.FOOTER_IMAGE_VISIBLE, View.VISIBLE | View.INVISIBLE | View.GONE);    // Footer World Image visibility
        intent.putExtra(Constants.LOCATION_TEXT_VISIBLE, View.VISIBLE | View.INVISIBLE | View.GONE); // Location Text visibility
        startActivity(intent);
    }

    public void shareApp(View view) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String shareMessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            Toast.makeText(getBaseContext(), "خلل في مشاركة التطبيق، المرجو الإعادة", Toast.LENGTH_SHORT).show();
        }
    }


}
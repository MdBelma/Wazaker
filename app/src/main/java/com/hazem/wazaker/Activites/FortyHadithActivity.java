package com.hazem.wazaker.Activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.badoualy.stepperindicator.StepperIndicator;
import com.hazem.wazaker.Adapters.SlideHadithAdapter;
import com.hazem.wazaker.Data_sets.FortyDataSet;
import com.hazem.wazkar.R;
import com.hazem.wazkar.databinding.ActivityFortyBinding;

public class FortyHadithActivity extends AppCompatActivity {

    String[] Hadiths;
    int[] Hadithcount;

    ActivityFortyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forty);

        int index = getIntent().getIntExtra("index", 0);
        LoadData(index);

        setAdapter();

        StepperIndicator indicator = findViewById(R.id.STEPP);
        indicator.setViewPager(binding.viewpager);

        binding.copybtn.setOnClickListener(v -> {
            String item = Hadiths[binding.viewpager.getCurrentItem()];

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, item);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });
    }

    private void LoadData(int index) {
        Hadiths = FortyDataSet.getFortyList(index);
        Hadithcount = new int[Hadiths.length];
    }

    private void setAdapter() {
        SlideHadithAdapter fortyAdapter = new SlideHadithAdapter(this, Hadiths);
        binding.viewpager.setAdapter(fortyAdapter);
    }
}


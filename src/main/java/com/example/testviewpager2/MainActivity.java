package com.example.testviewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    void InitInternetReceiver() {
        InternetReceiver receiver = new InternetReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.conn.WIFI_STATE_CHANGED");
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new MyAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position){
                        switch (position){
                            case 0: {
                                tab.setText("Sensors");
                                tab.setIcon(R.drawable.ic_sensors);
                                break;
                            }
                            case 1: {
                                tab.setText("Map");
                                tab.setIcon(R.drawable.ic_map);
                                break;
                            }
                            case 2: {
                                tab.setText("Permission");
                                tab.setIcon(R.drawable.ic_permission);
                                break;
                            }
                            case 4: {
                                tab.setText("Camera");
                                tab.setIcon(R.drawable.ic_camera);
                                break;
                            }
                            case 3: {
                                tab.setText("LocalDB");
                                tab.setIcon(R.drawable.ic_localdb);
                                break;
                            }
                        }
                    }
                }
        );
        tabLayoutMediator.attach();

        InitInternetReceiver();


    }
}
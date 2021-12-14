package com.example.testviewpager2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.hardware.SensorListener;
import android.widget.Toast;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.button.MaterialButton;

public class SensorsFragment extends androidx.fragment.app.Fragment{

    private SensorManager sensorManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private final SensorEventListener workingSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        public void onSensorChanged(SensorEvent event) {
            if(getActivity() != null) {
                // Проверка датчика на null
                if (event.values != null) {
                    // Получаем показания датчиков
                    if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                        TextView light = getActivity().findViewById(R.id.Light);
                        if (light != null)
                            light.setText("" + event.values[0]);
                    } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                        TextView prox = getActivity().findViewById(R.id.Proximity);
                        if (prox != null)
                            prox.setText("" + event.values[0]);
                    }
                }
            }
        }
    };
    void InitSensors(){
        // Получаем доступ к датчикам
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        // Создаем шаблон датчика освещения
        Sensor sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        // Если датчик есть, то регистрируем его
        if(sensorLight != null){
            sensorManager.registerListener(workingSensorEventListener, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        // Создаем шаблон датчика приближения
        Sensor sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        // Если датчик есть, то регистрируем его
        if(sensorProximity != null){
            sensorManager.registerListener(workingSensorEventListener, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensors,
                container, false);

        // Привязываем кнопку
        MaterialButton checkBtn = (MaterialButton) view.findViewById(R.id.checkInternet);
        // Привязываем к кнопку слушатель по нажатию
        checkBtn.setOnClickListener(new View.OnClickListener()
        {
            // Обрабатываем если произошло нажатие
            @Override
            public void onClick(View v)
            {
                // Класс, отвечающий на вопросы о состоянии сетевого подключения
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                // Описывает состояние сетевого интерфейса
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                // Если сеть подключена, то вывести соответствующее смс и наоборот
                if (networkInfo != null && networkInfo.isConnected()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Connected to the network", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Not connected to the network", Toast.LENGTH_SHORT).show();
                }
            }
        });
        InitSensors();
        return view;
    }
}
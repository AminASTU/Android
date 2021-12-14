package com.example.testviewpager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyAdapter extends FragmentStateAdapter {
    public MyAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new SensorsFragment();
            case 1:
                return new MapFragment();
            case 2:
                return new PermissionFragment();
            case 4:
                return new CameraFragment();
            case 3:
                return new LocalDBFragment();
            default:
                return new PermissionFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

}

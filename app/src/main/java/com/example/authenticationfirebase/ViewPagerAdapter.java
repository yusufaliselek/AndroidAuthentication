package com.example.authenticationfirebase;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FragmentChat();
        }
        else if (position==1) {
            return new FragmentFriends();
        }
        else return new FragmentProfile();
    }

    @Override
    public int getCount() {
        return 3;
    }
}

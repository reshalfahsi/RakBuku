package com.indonesia3gger.root.rakbuku.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.indonesia3gger.root.rakbuku.fragment.BorrowFragment;
import com.indonesia3gger.root.rakbuku.fragment.LendFragment;
import com.indonesia3gger.root.rakbuku.fragment.LibraryFragment;
import com.indonesia3gger.root.rakbuku.fragment.NewBookFragment;
import com.indonesia3gger.root.rakbuku.fragment.ProfileFragment;

public class MainFragmentAdapter extends FragmentPagerAdapter {
    //nama tab nya
    String[] title = new String[]{
            "Profile", "Borrow", "Lend", "Library", "New Book"
    };

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    //method ini yang akan memanipulasi penampilan Fragment dilayar
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment = new ProfileFragment();
                break;
            case 1:
                fragment = new BorrowFragment();
                break;
            case 2:
                fragment = new LendFragment();
                break;
            case 3:
                fragment = new LibraryFragment();
                break;
            case 4:
                fragment = new NewBookFragment();
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {

//        return title[position];
    return null;
    }

    @Override
    public int getCount() {
        return title.length;
    }
}

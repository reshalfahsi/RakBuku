package com.indonesia3gger.root.rakbuku;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.indonesia3gger.root.rakbuku.adapter.MainFragmentAdapter;

public class MainActivity extends AppCompatActivity {
    //deklarasi semua komponen View yang akan digunakan
    private Toolbar toolbar;
    private ViewPager pager;
    private TabLayout tabs;
    private int image[] = new int[7];
    private int image_pressed[] = new int[7];
    private boolean first_time = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //set up toolbar
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Material Tab");

        //inisialisasi tab dan pager
        pager = (ViewPager)findViewById(R.id.pager);
        tabs = (TabLayout)findViewById(R.id.tabs);

        //set object adapter kedalam ViewPager
        pager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));

        //Manimpilasi sedikit untuk set TextColor pada Tab
//        tabs.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),
//                getResources().getColor(android.R.color.white));

        //set tab ke ViewPager
        tabs.setupWithViewPager(pager);

        image[0] = R.drawable.profile_button;
        image[1] = R.drawable.borrow_button;
        image[2] = R.drawable.lend_button;
        image[3] = R.drawable.library_button;
        image[4] = R.drawable.newbook_button;

        image_pressed[0] = R.drawable.profile_button_pressed;
        image_pressed[1] = R.drawable.borrow_button_pressed;
        image_pressed[2] = R.drawable.lend_button_pressed;
        image_pressed[3] = R.drawable.library_button_pressed;
        image_pressed[4] = R.drawable.newbook_button_pressed;

        for(int i=0; i<tabs.getTabCount(); i++)
            tabs.getTabAt(i).setIcon(image[i]);

        if(first_time == true)
        {
            tabs.getTabAt(0).setIcon(image_pressed[0]);
            getSupportActionBar().setTitle("Profile");
            first_time = false;
        }

        tabs.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(pager){

                    @Override
                    public void onTabSelected(TabLayout.Tab tab)
                    {
                        super.onTabSelected(tab);
                        int tabnumberselected = tab.getPosition();
                        switch (tabnumberselected)
                        {
                            case 0 : {
                                tabs.getTabAt(0).setIcon(image_pressed[0]);
                                getSupportActionBar().setTitle("Profile");
                                break;
                            }
                            case 1 : {
                                tabs.getTabAt(1).setIcon(image_pressed[1]);
                                getSupportActionBar().setTitle("Borrow");
                                break;
                            }
                            case 2: {
                                tabs.getTabAt(2).setIcon(image_pressed[2]);
                                getSupportActionBar().setTitle("Lend");
                                break;
                            }
                            case 3: {
                                tabs.getTabAt(3).setIcon(image_pressed[3]);
                                getSupportActionBar().setTitle("Library");
                                break;
                            }
                            case 4: {
                                tabs.getTabAt(4).setIcon(image_pressed[4]);
                                getSupportActionBar().setTitle("New Book");
                                break;
                            }
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab)
                    {
                        super.onTabUnselected(tab);
                        int tabnumberunselected = tab.getPosition();
                        switch (tabnumberunselected)
                        {
                            case 0 : {
                                if(first_time == false)
                                    tabs.getTabAt(0).setIcon(image[0]);
                                break;
                            }
                            case 1 :
                                tabs.getTabAt(1).setIcon(image[1]);
                                break;
                            case 2:
                                tabs.getTabAt(2).setIcon(image[2]);
                                break;
                            case 3:
                                tabs.getTabAt(3).setIcon(image[3]);
                                break;
                            case 4:
                                tabs.getTabAt(4).setIcon(image[4]);
                                break;
                        }

                    }

                }
        );

        //konfigurasi Gravity Fill untuk Tab berada di posisi yang proposional
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

    }
}

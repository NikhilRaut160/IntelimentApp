package com.nikhil.intelimentapp.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.matthewtamlin.sliding_intro_screen_library.SelectionIndicator;
import com.nikhil.intelimentapp.Fragments.FourFragment;
import com.nikhil.intelimentapp.Fragments.FragmentOne;
import com.nikhil.intelimentapp.Fragments.ThreeFragment;
import com.nikhil.intelimentapp.Fragments.TwoFragment;
import com.nikhil.intelimentapp.R;
import com.nikhil.intelimentapp.Utility.UIUtil;

import java.util.ArrayList;
import java.util.List;

public class ScenarioOneActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private List<Fragment> fragmentList;
    private int SELECT_TAB = 0;
    private Button btnRed, btnYellow, btnGreen;
    private TextView hvItem1, hvItem2, hvItem3, hvItem4, hvItem5, tvDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_one);

        final SelectionIndicator selectInd = (SelectionIndicator) findViewById(R.id.selectInd);

        bindView();

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentOne());
        fragmentList.add(new TwoFragment());
        fragmentList.add(new ThreeFragment());
        fragmentList.add(new FourFragment());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), fragmentList);
        mPager.setAdapter(mPagerAdapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                SELECT_TAB = position;
                selectInd.setActiveItem(position, true);
                OnChangeFragment();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (SELECT_TAB) {
                    case 0:
//                        UIUtil.ShowToast(getApplicationContext(), "First Fragment Is Click");
                        UIUtil.AppLog("First Fragment Is Click");
                        setDetailsText("First Fragment Is Click");
                        break;
                    case 1:
//                        UIUtil.ShowToast(getApplicationContext(), "Second Fragment Is Click");
                        UIUtil.AppLog("Second Fragment Is Click");
                        setDetailsText("First Fragment Is Click");
                        break;
                    case 2:
//                        UIUtil.ShowToast(getApplicationContext(), "Third Fragment Is Click");
                        UIUtil.AppLog("Third Fragment Is Click");
                        break;
                    case 3:
//                        UIUtil.ShowToast(getApplicationContext(), "Fourth Fragment Is Click");
                        UIUtil.AppLog("Fourth Fragment Is Click");
                        break;
                }
            }
        });


//        mPagerAdapter.instantiateItem(mPager, )

    }

    private void OnChangeFragment() {
        switch (SELECT_TAB) {
            case 0:
                UIUtil.AppLog("First Fragment Is Select");
                setDetailsText("First Fragment Is Select");
                break;
            case 1:
                UIUtil.AppLog("Second Fragment Is Select");
                setDetailsText("Second Fragment Is Select");
                break;
            case 2:
                UIUtil.AppLog("Third Fragment Is Select");
                setDetailsText("Third Fragment Is Select");
                break;
            case 3:
                UIUtil.AppLog("Fourth Fragment Is Select");
                setDetailsText("Fourth Fragment Is Select");
                break;
        }
    }

    private void bindView() {

        UIUtil.AppLog("bindView Method Called");

        btnRed = (Button) findViewById(R.id.btnRed);
        btnYellow = (Button) findViewById(R.id.btnYellow);
        btnGreen = (Button) findViewById(R.id.btnGreen);

        btnRed.setOnClickListener(this);
        btnYellow.setOnClickListener(this);
        btnGreen.setOnClickListener(this);

        hvItem1 = (TextView) findViewById(R.id.hvItem1);
        hvItem2 = (TextView) findViewById(R.id.hvItem2);
        hvItem3 = (TextView) findViewById(R.id.hvItem3);
        hvItem4 = (TextView) findViewById(R.id.hvItem4);
        hvItem5 = (TextView) findViewById(R.id.hvItem5);

        hvItem1.setOnClickListener(this);
        hvItem2.setOnClickListener(this);
        hvItem3.setOnClickListener(this);
        hvItem4.setOnClickListener(this);
        hvItem5.setOnClickListener(this);

        tvDetails = (TextView) findViewById(R.id.tvDetails);
        tvDetails.setOnClickListener(this);


        ((TextView) findViewById(R.id.tvLeft)).setOnClickListener(this);
        ((TextView) findViewById(R.id.tvCenter)).setOnClickListener(this);
        ((TextView) findViewById(R.id.tvRight)).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        UIUtil.AppLog("onClick Method Called");
        tvDetails.setTextColor(getResources().getColor(R.color.light_black));

        switch (v.getId()) {

            case R.id.tvLeft:
                setDetailsText("Left Aline Text View is Click ");
                break;

            case R.id.tvCenter:
                setDetailsText("Center Aline Text View is Click ");
                break;

            case R.id.tvRight:
                setDetailsText("Right Aline Text View is Click ");
                break;

            case R.id.hvItem1:
                changeTabColor(hvItem1, hvItem2, hvItem3, hvItem4, hvItem5);
                setDetailsText("Horizontal ScrollView item 1 is Click ");
                break;

            case R.id.hvItem2:
                changeTabColor(hvItem2, hvItem1, hvItem3, hvItem4, hvItem5);
                setDetailsText("Horizontal ScrollView item 2 is Click ");
                break;

            case R.id.hvItem3:
                changeTabColor(hvItem3, hvItem2, hvItem1, hvItem4, hvItem5);
                setDetailsText("Horizontal ScrollView item 3 is Click ");
                break;

            case R.id.hvItem4:
                changeTabColor(hvItem4, hvItem2, hvItem3, hvItem1, hvItem5);
                setDetailsText("Horizontal ScrollView item 4 is Click ");
                break;

            case R.id.hvItem5:
                changeTabColor(hvItem5, hvItem2, hvItem3, hvItem4, hvItem1);
                setDetailsText("Horizontal ScrollView item 5 is Click ");
                break;

            case R.id.btnRed:
                btnRed.setBackgroundColor(getResources().getColor(R.color.red));
                btnGreen.setBackgroundColor(getResources().getColor(R.color.gray));
                btnYellow.setBackgroundColor(getResources().getColor(R.color.gray));
                tvDetails.setTextColor(getResources().getColor(R.color.red));
                setDetailsText("Red button Click");
                break;

            case R.id.btnGreen:
                btnGreen.setBackgroundColor(getResources().getColor(R.color.green));
                btnRed.setBackgroundColor(getResources().getColor(R.color.gray));
                btnYellow.setBackgroundColor(getResources().getColor(R.color.gray));
                tvDetails.setTextColor(getResources().getColor(R.color.green));
                setDetailsText("Green button Click");
                break;

            case R.id.btnYellow:
                btnYellow.setBackgroundColor(getResources().getColor(R.color.yellow));
                btnGreen.setBackgroundColor(getResources().getColor(R.color.gray));
                btnRed.setBackgroundColor(getResources().getColor(R.color.gray));
                tvDetails.setTextColor(getResources().getColor(R.color.yellow));
                setDetailsText("Yellow button Click");
                break;


        }

    }


    public void setDetailsText(String text) {
        tvDetails.setText("" + text);
    }

    public void changeTabColor(TextView active, TextView inactiveTab1, TextView inactiveTab2, TextView inactiveTab3, TextView inactiveTab4) {

        active.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        active.setTextColor(getResources().getColor(R.color.white));

        inactiveTab1.setBackgroundColor(getResources().getColor(R.color.gray));
        inactiveTab1.setTextColor(getResources().getColor(R.color.light_black));

        inactiveTab2.setBackgroundColor(getResources().getColor(R.color.gray));
        inactiveTab2.setTextColor(getResources().getColor(R.color.light_black));

        inactiveTab3.setBackgroundColor(getResources().getColor(R.color.gray));
        inactiveTab3.setTextColor(getResources().getColor(R.color.light_black));

        inactiveTab4.setBackgroundColor(getResources().getColor(R.color.gray));
        inactiveTab4.setTextColor(getResources().getColor(R.color.light_black));

    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        List<Fragment> fragmentList;

        public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            fragmentList = list;
        }


        //   @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            LayoutInflater inflater = (LayoutInflater)
//                    ScenarioOneActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View theView = fragmentList.get(position).getView();
////            switch (position) {
////                case 0:
////                    theView = inflater.inflate(R.layout.fragment_one, null);
////                    UIUtil.AppLog("First Fragment Is Click");
////                    setDetailsText("First Fragment Is Click");
////                    break;
////                case 1:
////                    theView = inflater.inflate(R.layout.fragment_two, null);
////                    UIUtil.AppLog("Second Fragment Is Click");
////                    setDetailsText("First Fragment Is Click");
////                    break;
////                case 2:
////                    theView = inflater.inflate(R.layout.fragment_three, null);
////                    UIUtil.AppLog("Third Fragment Is Click");
////                    break;
////                case 3:
////                    theView = inflater.inflate(R.layout.fragment_four, null);
////                    UIUtil.AppLog("Fourth Fragment Is Click");
////                    break;
////            }
//
//            theView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //perform action
//                }
//            });
//
//            container.addView(theView, 0);
//            return theView;
//        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }


        @Override
        public int getCount() {
            return 4;
        }
    }
}

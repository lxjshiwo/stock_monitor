package com.example.show;

import java.util.ArrayList;
import java.util.List;

import com.example.show.base.BaseFragment;
import com.example.show.fragment.TimesFragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity {
	
	private List<BaseFragment> mFragments; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();
    }

	private void initView() {
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.frame_content,mFragments.get(0));
		transaction.commit();
	}

	private void initFragment() {
		mFragments = new ArrayList<BaseFragment>();
		mFragments.add(new TimesFragment());
	}
    
    
    


}

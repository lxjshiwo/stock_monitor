/**
 * 
 */
package com.example.show.fragment;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.show.R;
import com.example.show.base.BaseFragment;
import com.example.show.bean.TimesEntity;
import com.example.show.dbhelper.SymbolsOpenHelper;
import com.example.show.provider.SymbolsProvider;
import com.example.show.view.TimesView;

/**
 * @author Administrator
 *
 */
public class TimesFragment extends BaseFragment{

	private static final String TAG = TimesFragment.class.getSimpleName();
	
	
	

	//请求网络相关
	private final OkHttpClient client  = new OkHttpClient();
	private ScheduledExecutorService scheduledExecutorService;


	private TimesView mTimesView;
	
	



	private Button mStart;

	private Button mStop;




	private SymbolsOpenHelper mOpenHelper;



	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		timeList = new ArrayList<TimesEntity>();
		View view = inflater.inflate(R.layout.fragment_times,null);
//		mTimesView = new TimesView(mContext);
		mTimesView = (TimesView) view.findViewById(R.id.timesView);
		mStart = (Button) view.findViewById(R.id.btn_start);
		mStop = (Button) view.findViewById(R.id.btn_stop);
        initListener();
        initDB();
		return view;
	}


	



	private void initDB() {
		

	}







	@Override
	protected void initData() 
	{
		
	}




	@Override
	protected View initView() {
		return null;
	}
	
	
	
	
	
	
	
	

	private void initListener() {
		// TODO Auto-generated method stub
		mStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				getRequest();
			}
		});
		mStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				cancelRequest();
			}

		});
		
	}

 


	

}

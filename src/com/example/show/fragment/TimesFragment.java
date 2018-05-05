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

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.show.view.TimesView;

/**
 * @author Administrator
 *
 */
public class TimesFragment extends BaseFragment{

	private static final String TAG = TimesFragment.class.getSimpleName();
	
	private static final int REFRESHSTOCK = 1;
	//请求的相应价格
	private static String StockCode = "0000001";//"1000025";//"0000001";
	private static String REQUEST_URL = "http://api.money.126.net/data/feed/"+ StockCode + ",money.api";
	//修改相应的股票时间数据
    private int CurrentPos = 0;
	//线程执行方式
	private Runnable runnable;
	
	//请求网络相关
	private final OkHttpClient client  = new OkHttpClient();;
	private ScheduledExecutorService scheduledExecutorService;


	private TimesView mTimesView;
	
	
//	private String[] secTimes = new String[240];
//	private double[] secPrices = new double[240];
//	private int[] secVolume = new int[240];

	private List<TimesEntity> timeList = new ArrayList<TimesEntity>();

	private Button mStart;

	private Button mStop;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REFRESHSTOCK:
                	mTimesView.setTimesList(timeList);
                	break;
            }
        }
    };

	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		timeList = new ArrayList<TimesEntity>();
		View view = inflater.inflate(R.layout.fragment_times,null);
//		mTimesView = new TimesView(mContext);
		mTimesView = (TimesView) view.findViewById(R.id.timesView);
		mStart = (Button) view.findViewById(R.id.btn_start);
		mStop = (Button) view.findViewById(R.id.btn_stop);
        initListener();
		return view;
	}


	

//	@Override
//	protected View initView() {
//	}

	@Override
	protected void initData() 
	{
//		Random rand = new Random();
//		for(int i = 0;i<240;i++)
//		{
//			secTimes[i] = String.valueOf(i);
//			secPrices[i] = Double.parseDouble("100");
//			secVolume[i] = rand.nextInt(100);
//		}
//		for(int i = 0;i<240;i++)
//		{
//			secTimes[i] = String.valueOf(i);
//			secPrices[i] = Double.parseDouble(String.valueOf(rand.nextInt(100)));
//			secVolume[i] = rand.nextInt(100);
//		}
//		for(int i = 0;i<60;i++)
//		{
//			secTimes[i] = String.valueOf(i);
//			secPrices[i] = 100.0f;
//			secVolume[i] = rand.nextInt(100);
//		}
//		for(int i = 59;i<120;i++)
//		{
//			secTimes[i] = String.valueOf(i);
//			secPrices[i] = 110.0f;
//			secVolume[i] = rand.nextInt(100);
//		}
//		for(int i = 119;i<180;i++)
//		{
//			secTimes[i] = String.valueOf(i);
//			secPrices[i] = 120.0f;
//			secVolume[i] = rand.nextInt(100);
//			
//		}
//		for(int i = 179;i<240;i++)
//		{
//			secTimes[i] = String.valueOf(i);
//			secPrices[i] = 130.0f;
//			secVolume[i] = rand.nextInt(100);
//			
//		}
		
		
//		for(int i=0;i<240;i++)
//		{
//			TimesEntity fenshiData = new TimesEntity("0000001",secTimes[i],String.valueOf(secPrices[i]),secVolume[i],"100");
//			timeList.add(fenshiData);
//			mTimesView.setTimesList(timeList);
//		}

		
		
	}




	@Override
	protected View initView() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
//	private  final int DELAYEDTIME=1000;
//    private StockLine mStockLine;
//
//    private List<StockBean> mList=new ArrayList<StockBean>();
//
//
//    private String[] minTimes = new String[120];
//    private int[] minPrices = new int[120];
//
//	
//	private Button btn_start_monitor;
//	private Button btn_stop_monitor;

//	private Timer timer;
//	private TimerTask task;
//	private String[] secTimes = new String[60];
//	private int[] secPrices = new int[60]; 
//
//	
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initData();	
//        initView();
//        initListener();
//
//    }
//
	private void initListener() {
		// TODO Auto-generated method stub
		mStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getRequest();
			}
		});
		mStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cancelRequest();
			}

		});
		
	}

 

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mHandler.sendEmptyMessageDelayed(DELAYEDTIME,1000);
//    }
//
//
//
	/**
     *发起定时网络请求任务
     */
    public void getRequest()
    {
    	scheduledExecutorService = Executors.newScheduledThreadPool(1);
		runnable = new Runnable() {
			
			@Override
			public void run() {
				startApiCall();
			}
		};
    	scheduledExecutorService.scheduleAtFixedRate(runnable,1,60,TimeUnit.SECONDS);
    }
    
	/**
	 *结束定时网络请求任务
	 */
	private void cancelRequest() 
	{
		scheduledExecutorService.shutdownNow();
	}
//	
//	
//	
//	
	/**
	 *请求APi相应数据
	 */
	private void startApiCall() {

    	okhttp3.Request request = new okhttp3.Request.Builder().url(REQUEST_URL).build();
    	client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				//成功获取相应数据
				String content = arg1.body().string();
				String result = parseResult(content);
				String singleStock = parseSingleStock(result);
				parsePrice(singleStock);
//				CurrentPos++;
//				Log.e("MainActivity",);
				
				if(arg1.body() != null)
				{
					arg1.body().close();
				}
				
			}
			



			@Override
			public void onFailure(Call arg0, IOException arg1) {
				Log.e("MainAcivity","失败-----"+arg1.getLocalizedMessage());
				System.out.println("失败");
				
			}
		});
	}
//    
//    
//    
    /**
     *将接口回调数据转换为简单json数据
     */
    public String parseResult(String content)
    {
    	int indexStart = content.indexOf("(");
    	int indexEnd = content.indexOf(")");
		String result = content.substring(indexStart+1,indexEnd);
    	return result;
    }

	private String parseSingleStock(String json) 
	{
		String content = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			content = jsonObject.getString(StockCode);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return content;
	}
	
	private void parsePrice(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			String type = jsonObject.getString("type");
			String symbol = jsonObject.getString("symbol");
			String time = jsonObject.getString("time");
			String price = jsonObject.getString("price");
			String percent = jsonObject.getString("percent");
			String yestClose = jsonObject.getString("yestclose");
			String volume = jsonObject.getString("volume");
			long volumeLong = Long.parseLong(volume);
			int volumeInt = (int) (volumeLong / 100000000);
			Log.e(TAG,volumeInt + "");
			TimesEntity	singleStock = new TimesEntity(StockCode, time, price,volumeInt, yestClose);
			timeList.add(singleStock);
			Message msg = Message.obtain();
			msg.what = REFRESHSTOCK;
			mHandler.sendMessage(msg);
			Log.e(TAG,singleStock.toString());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

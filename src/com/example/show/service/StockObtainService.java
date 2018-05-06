/**
 * 
 */
package com.example.show.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.show.bean.TimesEntity;
import com.example.show.view.TimesView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

/**
 * @author Administrator
 *
 */
public class StockObtainService extends Service {
	
	
	private static final int REFRESHSTOCK = 1;


	private static final String TAG = StockObtainService.class.getSimpleName();
	

	private TimesView mTimesView;
	
	
	private List<TimesEntity> timeList = new ArrayList<TimesEntity>();
	
	
	
	//请求网络相关
	private OkHttpClient client;
	private ScheduledExecutorService scheduledExecutorService;
	private Runnable runnable;
	
	
	
	private static String StockCode = "0000001";//"1000025";//"0000001";
	private static String REQUEST_URL = "http://api.money.126.net/data/feed/"+ StockCode + ",money.api";
	
	
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
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	
	@Override
	public void onCreate() {
		client = new OkHttpClient();
		super.onCreate();
	}
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	
	
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

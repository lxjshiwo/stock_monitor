/**
 * 
 */
package com.example.show.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.show.bean.TimesEntity;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @author Administrator
 *
 */
public class TimesView extends GridChart{
	
	private static final String TAG = TimesView.class.getSimpleName();

	private final int DATA_MAX_COUNT = 4 * 60;
	
	//数据列表
	private List<TimesEntity> timesList ;
	private String yestClose;
	
	
	//相应的位置参数
	private float uperBottom;
	private float uperHeight;
	private float lowerBottom;
	private float lowerHeight;
	private float dataSpacing;
	
	//位置参数
	private double initialWeightedIndex;
	//上部分的高度
	private float uperHalfHigh;
	//下部分的高度
	private float lowerHigh;
	private float uperRate;
	private float lowerRate;

	public TimesView(Context context) {
		super(context);
		init();
	}

	public TimesView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}


	public TimesView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	private void init()
	{
		super.setShowLowerChartTabs(false);
		super.setShowTopTitles(false);
		
		timesList = new ArrayList<TimesEntity>();
		uperBottom = 0;
		uperHeight = 0;
		lowerBottom = 0;
		lowerHeight = 0;
		dataSpacing = 0;
		

		initialWeightedIndex = 0;
		uperHalfHigh = 0;
		lowerHigh = 0;
		uperRate = 0;
		lowerRate = 0;
	}
	
	//重写相应绘制方法
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (timesList == null || timesList.size() <= 0) {
			return;
		}
		//uper_chart_bottom下方表格的底部
		uperBottom = UPER_CHART_BOTTOM - 2;
		//为上部分的高度-4中间的宽度
		uperHeight = getUperChartHeight() - 4;
		lowerBottom = getHeight() - 3;
		lowerHeight = getLowerChartHeight() - 2;
		dataSpacing = (getWidth() - 4) * 10.0f / 10.0f / DATA_MAX_COUNT;

		if (uperHalfHigh > 0) {
//			uperRate = uperHeight / uperHalfHigh   / 2.0f; //uperRate用于转换价格到单位高度
			float gap = (uperHalfHigh / 100.0f);
			uperRate = uperHeight / 2.0f / gap ;
			Log.e(TAG,uperRate + "rate" + "uperHeight"+uperHeight + "uperHalHight"+uperHalfHigh);
		}
		if (lowerHigh > 0) {
			lowerRate = lowerHeight / lowerHigh;//lowerRate用于转换成交量到相应单位高度
		}

		
		// 绘制上部曲线及下部线条
		drawLines(canvas);

		// 绘制坐标标题
		drawTitles(canvas);
	
		
		// 绘制点击时的详细信息 暂时不需要
		//drawDetails(canvas);

	}
	
	
	
	

	private void drawTitles(Canvas canvas) {
		// TODO Auto-generated method stub
		// 绘制Y轴titles
				float viewWidth = getWidth();
				Paint paint = new Paint();
				paint.setTextSize(DEFAULT_AXIS_TITLE_SIZE);

				//下跌的价格画线
				paint.setColor(Color.GREEN);
//				canvas.drawText(new DecimalFormat("#.##").format(initialWeightedIndex - uperHalfHigh), 2,
//						uperBottom, paint);
//				String text = new DecimalFormat("#.##%").format(-uperHalfHigh / initialWeightedIndex);
//				canvas.drawText(text, viewWidth - 5 - text.length() * DEFAULT_AXIS_TITLE_SIZE / 2.0f,
//						uperBottom, paint);
//
//				canvas.drawText(
//						new DecimalFormat("#.##").format(initialWeightedIndex - uperHalfHigh * 0.5f), 2,
//						uperBottom - getLatitudeSpacing(), paint);
//				text = new DecimalFormat("#.##%").format(-uperHalfHigh * 0.5f / initialWeightedIndex);
//				canvas.drawText(text, viewWidth - 5 - text.length() * DEFAULT_AXIS_TITLE_SIZE / 2.0f,
//						uperBottom - getLatitudeSpacing(), paint);

				canvas.drawText(new DecimalFormat("#.##").format(initialWeightedIndex - uperHalfHigh * 0.01f), 2,
						uperBottom, paint);
				String text = new DecimalFormat("#.##%").format(-uperHalfHigh * 0.01f / initialWeightedIndex);
				canvas.drawText(text, viewWidth - 5 - text.length() * DEFAULT_AXIS_TITLE_SIZE / 2.0f,
						uperBottom, paint);

				canvas.drawText(
						new DecimalFormat("#.##").format(initialWeightedIndex - uperHalfHigh * 0.005f), 2,
						uperBottom - getLatitudeSpacing(), paint);
				text = new DecimalFormat("#.##%").format(-uperHalfHigh * 0.005f / initialWeightedIndex);
				canvas.drawText(text, viewWidth - 5 - text.length() * DEFAULT_AXIS_TITLE_SIZE / 2.0f,
						uperBottom - getLatitudeSpacing(), paint);

				paint.setColor(Color.WHITE);
				//今日开盘价格的横线
//				canvas.drawText(new DecimalFormat("#.##").format(initialWeightedIndex), 2, uperBottom
//						- getLatitudeSpacing() * 2, paint);
				canvas.drawText(new DecimalFormat("#.##").format(uperHalfHigh), 2, uperBottom
						- getLatitudeSpacing() * 2, paint);
				text = "0.00%";
				canvas.drawText(text, viewWidth - 6 - text.length() * DEFAULT_AXIS_TITLE_SIZE / 2.0f,
						uperBottom - getLatitudeSpacing() * 2, paint);
				//上涨的价格划分部分
//				paint.setColor(Color.RED);
//				canvas.drawText(
//						new DecimalFormat("#.##").format(uperHalfHigh * 0.5f + initialWeightedIndex), 2,
//						uperBottom - getLatitudeSpacing() * 3, paint);
//				text = new DecimalFormat("#.##%").format(uperHalfHigh * 0.5f / initialWeightedIndex);
//				canvas.drawText(text, viewWidth - 6 - text.length() * DEFAULT_AXIS_TITLE_SIZE / 2.0f,
//						uperBottom - getLatitudeSpacing() * 3, paint);
//
//				canvas.drawText(new DecimalFormat("#.##").format(uperHalfHigh + initialWeightedIndex), 2,
//						DEFAULT_AXIS_TITLE_SIZE, paint);
//				text = new DecimalFormat("#.##%").format(uperHalfHigh / initialWeightedIndex);
//				canvas.drawText(text, viewWidth - 6 - text.length() * DEFAULT_AXIS_TITLE_SIZE / 2.0f,
//						DEFAULT_AXIS_TITLE_SIZE, paint);
				
				paint.setColor(Color.RED);
				canvas.drawText(
						new DecimalFormat("#.##").format(uperHalfHigh * 0.005f + initialWeightedIndex), 2,
						uperBottom - getLatitudeSpacing() * 3, paint);
				text = new DecimalFormat("#.##%").format(uperHalfHigh * 0.005f / initialWeightedIndex);
				canvas.drawText(text, viewWidth - 6 - text.length() * DEFAULT_AXIS_TITLE_SIZE / 2.0f,
						uperBottom - getLatitudeSpacing() * 3, paint);

				canvas.drawText(new DecimalFormat("#.##").format(uperHalfHigh * 0.01f + initialWeightedIndex), 2,
						DEFAULT_AXIS_TITLE_SIZE, paint);
				text = new DecimalFormat("#.##%").format(uperHalfHigh * 0.01f / initialWeightedIndex);
				canvas.drawText(text, viewWidth - 6 - text.length() * DEFAULT_AXIS_TITLE_SIZE / 2.0f,
						DEFAULT_AXIS_TITLE_SIZE, paint);

				// 绘制X轴Titles,uperBottom+DEFAULT_AXIS_TITLE_SIZE 标示相应的y轴坐标
				canvas.drawText("09:30", 2, uperBottom + DEFAULT_AXIS_TITLE_SIZE, paint);
				canvas.drawText("11:30/13:00", viewWidth / 2.0f - DEFAULT_AXIS_TITLE_SIZE * 2.5f,
						uperBottom + DEFAULT_AXIS_TITLE_SIZE, paint);
				canvas.drawText("15:00", viewWidth - 2 - DEFAULT_AXIS_TITLE_SIZE * 2.5f, uperBottom
						+ DEFAULT_AXIS_TITLE_SIZE, paint);
	}

	private void drawLines(Canvas canvas) {
		// TODO Auto-generated method stub
		float x = 0;
		float y = 0;
		//绘制加权未加权相应线条的y值
//		float uperWhiteY = 0;
//		float uperYellowY = 0;
		Paint paint = new Paint();
		for (int i = 0; i < timesList.size() && i < DATA_MAX_COUNT; i++) {
			TimesEntity fenshiData = timesList.get(i);

			// 绘制上部表中曲线
//			float endWhiteY = (float) (uperBottom - (fenshiData.getNonWeightedIndex()
//					+ uperHalfHigh - initialWeightedIndex)
//					* uperRate);
//			float endYelloY = (float) (uperBottom - (fenshiData.getWeightedIndex() + uperHalfHigh - initialWeightedIndex)
//					* uperRate);
			
			Log.e(TAG,fenshiData.getPrice());
			float endY = (float)(uperBottom -(Float.parseFloat(fenshiData.getPrice()) + uperHalfHigh * 0.01 - uperHalfHigh)*uperRate);

			if (i != 0) {
				paint.setColor(Color.WHITE);
				canvas.drawLine( x , y , 3 +dataSpacing * i,endY, paint);
//				canvas.drawLine(x, uperWhiteY, 3 + dataSpacing * i, endWhiteY, paint);
//				paint.setColor(Color.YELLOW);
//				canvas.drawLine(x, uperYellowY, 3 + dataSpacing * i, endYelloY, paint);
			}
			Log.e(TAG,"startX:"+x+"startY:"+y);
			Log.e(TAG,"endX:"+3 + dataSpacing * i +"endY:"+endY);

			x = 3 + dataSpacing * i;
			y = endY;
			//转换坐标 将当前结束坐标赋值给新开始坐标
//			uperWhiteY = endWhiteY;
//			uperYellowY = endYelloY;

			// 绘制下部表内数据线
//			int buy = fenshiData.getBuy();
			int volume = fenshiData.getVolume();
			if(i <= 0)
			{
				paint.setColor(Color.RED);
			}
			else if(Float.parseFloat(fenshiData.getPrice()) >= Float.parseFloat(timesList.get(i-1).getPrice()))
			{
				paint.setColor(Color.RED);
			}
			else
			{
				paint.setColor(Color.GREEN);
			}
//			if (i <= 0) {
//				paint.setColor(Color.RED);
//			} else if (fenshiData.getNonWeightedIndex() >= timesList.get(i - 1)
//					.getNonWeightedIndex()) {
//				paint.setColor(Color.RED);
//			} else {
//				paint.setColor(Color.GREEN);
//			}
			canvas.drawLine(x, lowerBottom, x, lowerBottom - volume * lowerRate, paint);
		}
		
	}

	public void setTimesList(List<TimesEntity> timesList) {
		if (timesList == null || timesList.size() <= 0) {
			return;
		}
		this.timesList = timesList;

		TimesEntity fenshiData = timesList.get(0);
		//获取相应的加权价格与未加权价格
		//double weightedIndex = fenshiData.getWeightedIndex();
		//double nonWeightedIndex = fenshiData.getNonWeightedIndex();
		//获得相应当前价格 
		double price = Double.parseDouble(fenshiData.getPrice());
		//获取相应的成交量
		long volume = fenshiData.getVolume();
		
		yestClose = fenshiData.getYestClose();
		//初始的Index高度为相应的加权价格指数
		//initialWeightedIndex = weightedIndex;
		initialWeightedIndex = Double.parseDouble(fenshiData.getPrice());
		//上部分的最高价格为昨天成交价格的1.1倍数
		uperHalfHigh = Float.parseFloat(yestClose);

		//下部分的空白栏目显示相应的成交量lowerHigh标示相应的最大成交量,昨日成交量的2倍
		lowerHigh = volume * 2;
		for (int i = 1; i < timesList.size() && i < DATA_MAX_COUNT; i++) {
			fenshiData = timesList.get(i);
			//刷新 相应的数据
//			weightedIndex = fenshiData.getWeightedIndex();
//			nonWeightedIndex = fenshiData.getNonWeightedIndex();
			price = Double.parseDouble(fenshiData.getPrice());
//			buy = fenshiData.getBuy();
			volume = fenshiData.getVolume();


//			uperHalfHigh = (float) (uperHalfHigh > Math
//					.abs(nonWeightedIndex - initialWeightedIndex) ? uperHalfHigh : Math
//					.abs(nonWeightedIndex - initialWeightedIndex));
			//uperhalfHigh为最高价格
			uperHalfHigh = (float)(uperHalfHigh > Math.abs(price - initialWeightedIndex)? uperHalfHigh:Math.abs(price - initialWeightedIndex) );
//			uperHalfHigh = (float) (uperHalfHigh > Math.abs(weightedIndex - initialWeightedIndex) ? uperHalfHigh
//					: Math.abs(weightedIndex - initialWeightedIndex));
//			lowerHigh = lowerHigh > buy ? lowerHigh : buy;
			//lowerHigh伟大成交量
			lowerHigh = lowerHigh > volume ? lowerHigh : volume;
		}
//		postInvalidate();
		invalidate();

	}

}

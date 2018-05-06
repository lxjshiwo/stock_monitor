/**
 * 
 */
package com.example.show;

import com.example.show.dbhelper.StockDailyPriceOpenHelper;
import com.example.show.dbhelper.StockDailyPriceOpenHelper.StockDailyPrice;
import com.example.show.dbhelper.SymbolsOpenHelper;
import com.example.show.provider.StockDailyPriceProvider;
import com.example.show.provider.SymbolsProvider;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * @author Administrator
 *
 */
public class TestStockDailyPriceProvider extends AndroidTestCase {

	public void testInsert()
	{

		ContentValues values = new ContentValues();
		values.put(StockDailyPriceOpenHelper.StockDailyPrice.STOCK_SYMBOL_ID,60001);
		values.put(StockDailyPriceOpenHelper.StockDailyPrice.STOCK_OPEN_PRICE,10000);
		values.put(StockDailyPriceOpenHelper.StockDailyPrice.STOCK_MEAN_PRICE,10000);
		values.put(StockDailyPriceOpenHelper.StockDailyPrice.STOCK_VOLUME,1000000);
		values.put(StockDailyPriceOpenHelper.StockDailyPrice.STOCK_PRICE_TIME,"2018:05:06 15:00:00");
		getContext().getContentResolver().insert(StockDailyPriceProvider.URI_DAILY_PRICE, values);
	}

	public void testDelete()
	{
//		getContext().getContentResolver().delete(ContactProvider.URI_CONTACT,ContactOpenHelper.ContactTable.ACCOUNT+"=?", new String[]{"billy@example.com"});
		getContext().getContentResolver().delete(StockDailyPriceProvider.URI_DAILY_PRICE,StockDailyPriceOpenHelper.StockDailyPrice.STOCK_SYMBOL_ID+"=?",new String[]{"60001"});
		
	}
	
	public void testUpdate()
	{
		ContentValues values = new ContentValues();
		values.put(StockDailyPriceOpenHelper.StockDailyPrice.STOCK_SYMBOL_ID,60001);
		values.put(StockDailyPriceOpenHelper.StockDailyPrice.STOCK_OPEN_PRICE,10000);
		values.put(StockDailyPriceOpenHelper.StockDailyPrice.STOCK_MEAN_PRICE,10000);
		values.put(StockDailyPriceOpenHelper.StockDailyPrice.STOCK_VOLUME,9999);
		values.put(StockDailyPriceOpenHelper.StockDailyPrice.STOCK_PRICE_TIME,"2018:05:06 15:00:00");
		getContext().getContentResolver().update(StockDailyPriceProvider.URI_DAILY_PRICE, values,StockDailyPriceOpenHelper.StockDailyPrice.STOCK_SYMBOL_ID + "=?",new String[]{"60001"});
		
	}
	public void testQuery()
	{
		Cursor c = getContext().getContentResolver().query(StockDailyPriceProvider.URI_DAILY_PRICE,null,null,null,null);
		int columnCount = c.getColumnCount();//一共有多少列
		//获得所有的列的数目 
		System.out.println(columnCount);
		
		while(c.moveToNext())
		{
			//循环打印列
			for(int i = 0;i < columnCount;i++)
			{
				System.out.println(c.getString(i)+ " ");
			}
			System.out.println("");
			
		}
			
	}

}

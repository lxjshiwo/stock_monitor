/**
 * 
 */
package com.example.show;


import com.example.show.dbhelper.SymbolsOpenHelper;
import com.example.show.provider.SymbolsProvider;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * @author Administrator
 *
 */
public class TestSymbolsProvider extends AndroidTestCase {
	
private static final String TAG = TestSymbolsProvider.class.getSimpleName();

	public void testInsert()
	{

		ContentValues values = new ContentValues();
		values.put(SymbolsOpenHelper.StockSymbols.STOCK_SYMBOL,"60001");
		values.put(SymbolsOpenHelper.StockSymbols.STOCK_NAME,"平安银行");
		getContext().getContentResolver().insert(SymbolsProvider.URI_SYMBOLS, values);
	}

	public void testDelete()
	{
		//getContext().getContentResolver().delete(ContactProvider.URI_CONTACT,ContactOpenHelper.ContactTable.ACCOUNT+"=?", new String[]{"billy@example.com"});
		getContext().getContentResolver().delete(SymbolsProvider.URI_SYMBOLS,SymbolsOpenHelper.StockSymbols.STOCK_NAME+"=?",new String[]{"平安银行"});
		
	}
	
	public void testUpdate()
	{
		ContentValues values = new ContentValues();
		values.put(SymbolsOpenHelper.StockSymbols.STOCK_SYMBOL,"60003");
		values.put(SymbolsOpenHelper.StockSymbols.STOCK_NAME,"平安银行");
		getContext().getContentResolver().update(SymbolsProvider.URI_SYMBOLS, values,SymbolsOpenHelper.StockSymbols.STOCK_NAME + "=?",new String[]{"平安银行"});
		
	}
	public void testQuery()
	{
		Cursor c = getContext().getContentResolver().query(SymbolsProvider.URI_SYMBOLS,null,null,null,null);
		int columnCount = c.getColumnCount();//一共有多少列
		//获得所有的列的数目 
		System.out.println(columnCount);
		if(c == null)
		{
			Log.e(TAG, "fuck");
		}
		if(c.getCount() <= 0)
		{
			Log.e(TAG,"fuck no msg");
		}
		
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

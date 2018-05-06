/**
 * 
 */
package com.example.show.provider;

import com.example.show.dbhelper.StockDailyPriceOpenHelper;
import com.example.show.dbhelper.SymbolsOpenHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * @author Administrator
 *
 */
public class StockDailyPriceProvider extends ContentProvider {
	
	
	
	//获得相应的认证
	private static final String AUTHORITIES = StockDailyPriceProvider.class.getCanonicalName();
	static UriMatcher mUriMatcher;
	
	public static final Uri URI_DAILY_PRICE = Uri.parse("content://"+AUTHORITIES+"/daily_price");
	private static final int DAILY_PRICE = 1;
	private static final String TAG = StockDailyPriceProvider.class.getSimpleName();
	private StockDailyPriceOpenHelper mHelper;
	
	
	//匹配地址
	static
	{
		mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		mUriMatcher.addURI(AUTHORITIES,"daily_price",DAILY_PRICE);
		
	} 

	

	@Override
	public boolean onCreate() {
		mHelper = new StockDailyPriceOpenHelper(getContext());
		if(mHelper !=null)
		{
			return true;
		}
		return false;
	}

	

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int code = mUriMatcher.match(uri);
		switch (code) 
		{
			case DAILY_PRICE:
				SQLiteDatabase db = mHelper.getWritableDatabase();
				long id = db.insert(StockDailyPriceOpenHelper.T_STOCK_DAILY_PRICE,"", values);
				if(id != -1)
				{
					Log.e(TAG,"-----insert sucess----------------");
					//拼结
					uri = ContentUris.withAppendedId(uri,id);
					//通知ContentObserver数据改变了
					//getContext().getContentResolver().notifyChange(ContactProvider.URI_CONTACT,"指定只有某一个observer可收到");
					getContext().getContentResolver().notifyChange(SymbolsProvider.URI_SYMBOLS,null);//为null表示所有都可以收到
				}
				break;
	
			default:
				break;
		}
		return uri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int deleteCount = 0;
		int code = mUriMatcher.match(uri);
		switch (code) 
		{
			case DAILY_PRICE:
				SQLiteDatabase db = mHelper.getWritableDatabase();
				deleteCount = db.delete(StockDailyPriceOpenHelper.T_STOCK_DAILY_PRICE, selection,selectionArgs);
				if(deleteCount > 0)
				{
					Log.e(TAG,"-----------delete Success--------------");
					getContext().getContentResolver().notifyChange(SymbolsProvider.URI_SYMBOLS, null);
				}
				break;
	
			default:
				break;
		}
		return deleteCount;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int updateCount = 0;
		int code = mUriMatcher.match(uri);
		switch (code) 
		{
			case DAILY_PRICE:
				SQLiteDatabase db = mHelper.getWritableDatabase();
				updateCount = db.update(StockDailyPriceOpenHelper.T_STOCK_DAILY_PRICE, values,selection, selectionArgs);
				if(updateCount > 0)
				{
					Log.e(TAG,"----ContactProvider----updateSuccess-----");
					getContext().getContentResolver().notifyChange(SymbolsProvider.URI_SYMBOLS,null);
				}
				break;
	
			default:
				break;
		}
		return updateCount;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		int code = mUriMatcher.match(uri);
		switch (code) 
		{
			case DAILY_PRICE:
				SQLiteDatabase db = mHelper.getWritableDatabase();
				cursor = db.query(StockDailyPriceOpenHelper.T_STOCK_DAILY_PRICE, projection, selection, selectionArgs,null,null,sortOrder);
				Log.e(TAG,"query success-----------");
				return cursor;
	
			default:
				break;
		}
		return cursor;
	}
}

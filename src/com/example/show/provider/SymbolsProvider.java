/**
 * 
 */
package com.example.show.provider;


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
public class SymbolsProvider extends ContentProvider{
	
	//得到一个类的完整路径 主机地址常量
	public static final String AUTHORITIES = SymbolsProvider.class.getCanonicalName();
	static UriMatcher mUriMatcher;
	
	//对应股票代号表的Uri常量
	public static Uri URI_SYMBOLS = Uri.parse("content://"+AUTHORITIES+"/symbols");
	private static final int SYMBOLS = 1;
	private static final String TAG = SymbolsProvider.class.getSimpleName();
	private SymbolsOpenHelper mHelper;
	
	//地址匹配对象创建
	static{
		mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		//添加一个匹配的规则
		mUriMatcher.addURI(AUTHORITIES,"symbols",SYMBOLS);
		//content://ContactProvider/symbols --> SYMBOLS
	}
	

	@Override
	public boolean onCreate() {
		mHelper = new SymbolsOpenHelper(getContext());
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

	
	//crud 操作

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int code = mUriMatcher.match(uri);
		switch (code) 
		{
			case SYMBOLS:
				SQLiteDatabase db = mHelper.getWritableDatabase();
				long id = db.insert(SymbolsOpenHelper.T_STOCK_SYMBOLS,"", values);
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
			case SYMBOLS:
				SQLiteDatabase db = mHelper.getWritableDatabase();
				deleteCount = db.delete(SymbolsOpenHelper.T_STOCK_SYMBOLS, selection,selectionArgs);
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
			case SYMBOLS:
				SQLiteDatabase db = mHelper.getWritableDatabase();
				updateCount = db.update(SymbolsOpenHelper.T_STOCK_SYMBOLS, values,selection, selectionArgs);
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
			case SYMBOLS:
				SQLiteDatabase db = mHelper.getWritableDatabase();
				cursor = db.query(SymbolsOpenHelper.T_STOCK_SYMBOLS, projection, selection, selectionArgs,null,null,sortOrder);
				Log.e(TAG,"query success-----------");
				return cursor;
	
			default:
				break;
		}
		return cursor;
	}

}

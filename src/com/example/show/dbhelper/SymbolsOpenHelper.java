/**
 * 
 */
package com.example.show.dbhelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * @author Administrator
 *
 */
public class SymbolsOpenHelper extends SQLiteOpenHelper{
	

	final public static String T_STOCK_SYMBOLS = "stock_symbols";
	
	
	
	public class StockSymbols implements BaseColumns
	{
		
		/**
		 * STOCK_SYMBOL 股票号码 
		 * STOCK_NAME 股票名称
		 */
		public static final String STOCK_SYMBOL = "stock_symbol";
		public static final String STOCK_NAME = "stock_name";
		
	}
	
	
	
	
	
	
	private static final String SYMBOLSSQL_CREATE_ENTRIES = 
			 "CREATE TABLE " + T_STOCK_SYMBOLS + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					 	StockSymbols.STOCK_SYMBOL + " INTEGER," +
					 	StockSymbols.STOCK_NAME + " VARCHAR(255)"+ 
	                     " )";
	

	
	
	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public SymbolsOpenHelper(Context context) {
		super(context,"symbols.db",null,1);
	}

	
	
	//创建数据库表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SYMBOLSSQL_CREATE_ENTRIES);
	
	}

	//升级数据库表
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	
}
	
	
	
	
	
	
	



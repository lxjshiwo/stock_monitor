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
public class StockDailyPriceOpenHelper extends SQLiteOpenHelper {
	
	final public static String T_STOCK_DAILY_PRICE = "stock_daily_price";
	
	public class StockDailyPrice implements BaseColumns
	{
		
		
		/**
		 * 
		 * 	STOCK_OPEN_PRICE 开始价格
		 * 	STOCK_MEAN_PRICE 均价
			STOCK_VOLUME 成交量
			STOCK_SYMBOL_ID 股票对应的代号ID
		 * 
		 */
		public static final String STOCK_OPEN_PRICE = "stock_open_price";
		public static final String STOCK_MEAN_PRICE = "stock_mean_price";
		public static final String STOCK_VOLUME = "stock_volume";
		public static final String STOCK_SYMBOL_ID = "stock_symbol_id";
		public static final String STOCK_PRICE_TIME = "stock_price_time";
	}

	
	
	private static final String PRICESQL_CREATE_ENTRIES = 
			 "CREATE TABLE " + T_STOCK_DAILY_PRICE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					 	StockDailyPrice.STOCK_SYMBOL_ID + " INTEGER, "+
					 	StockDailyPrice.STOCK_OPEN_PRICE + " DECIMAL(19,4), "+
					 	StockDailyPrice.STOCK_MEAN_PRICE + " DECIMAL(19,4), " +
					 	StockDailyPrice.STOCK_VOLUME + " BIGINT, " +
					 	StockDailyPrice.STOCK_PRICE_TIME + " DATETIME " +
	                     " )";

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public StockDailyPriceOpenHelper(Context context) {
		super(context,"price.db",null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(PRICESQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}

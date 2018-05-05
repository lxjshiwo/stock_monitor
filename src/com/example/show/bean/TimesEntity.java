/**
 * 
 */
package com.example.show.bean;

/**
 * @author Administrator
 *分时图数据
 */
public class TimesEntity {
	
	

	//股票代号
	private String code;



	//对应time字段数据
	private String time;
	

	//对应price价格字段
	private String price;

	//相应成交量数据
	private int volume;// 交易量
	
	//昨日成交量
	private String yestClose;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getYestClose() {
		return yestClose;
	}

	public void setYestClose(String yestClose) {
		this.yestClose = yestClose;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public TimesEntity(String code,String time,String price,int volume,String yestClose) 
	{
		this.code = code;
		this.time = time;
		this.price = price;
		this.volume = volume;
		this.yestClose = yestClose;
	}

	@Override
	public String toString() {
		return "TimesEntity [code=" + code + ", time=" + time + ", price="
				+ price + ", volume=" + volume + ", yestClose=" + yestClose
				+ "]";
	}
	
	
	
	


}

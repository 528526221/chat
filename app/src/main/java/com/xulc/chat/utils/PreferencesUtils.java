package com.xulc.chat.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.xulc.chat.app.CWApplication;


/**
 * 
 * SharedPreferences工具类
 * 
 * @author xlc
 * 
 */
public class PreferencesUtils {
	/** SharedPreferences对象的引用 */
	private static SharedPreferences mPreferences;
	private static PreferencesUtils utils;


	public static PreferencesUtils getInstance(){
		if (mPreferences==null){
			mPreferences = PreferenceManager.getDefaultSharedPreferences(CWApplication.getInstance());
		}
		if (utils==null){
			utils = new PreferencesUtils();
		}
		return utils;
	}
	/**
	 * 写入String型的SharedPreferences键值对
	 * @param key
	 * @param value
	 */
	public void putString(String key, String value) {
		if (null != mPreferences) {
			mPreferences.edit().putString(key, value).commit();
		}
	}

	public String getString(String key) {
		String value = null;
		
		if (null != mPreferences) {
			value = mPreferences.getString(key, value);
		}
		
		return value;
	}
	
	/**
	 * 写入Boolean型的SharedPreferences键值对
	 * @param key
	 * @param Value
	 */
	public void putBoolean(String key, Boolean Value) {
		if (null != mPreferences) {
			mPreferences.edit().putBoolean(key, Value).commit();
		}
	}

	/**
	 * 得到Boolean型的SharedPreferences的值
	 * @param key
	 * @param defValue
	 * @return
	 */
	public Boolean getBoolean(String key, Boolean defValue) {
		return null != mPreferences && mPreferences.getBoolean(key, defValue);
	}
	
	public void putLong(String key, long value) {
		if (null != mPreferences) {
			mPreferences.edit().putLong(key, value).apply();
		}
	}
	
	public long getLong(String key) {
		long value = 0;
		
		if (null != mPreferences) {
			value = mPreferences.getLong(key, value);
		}
		return value;
	}

}
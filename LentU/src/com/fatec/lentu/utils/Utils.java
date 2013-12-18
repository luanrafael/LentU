package com.fatec.lentu.utils;

import android.telephony.SmsManager;
import android.util.Log;

public class Utils {

	private static String TAG_LOG = "LENTU";
	
	public static void enviaSms(String numero,String mensagem){
			SmsManager manager = SmsManager.getDefault();
			manager.sendTextMessage(numero, null, mensagem, null, null);
	}
	
	public static void logErr(String log){
		Log.e(TAG_LOG, log);
	}
	
	public static void logInf(String log){
		Log.i(TAG_LOG, log);
	}
	
}


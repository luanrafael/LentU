package com.fatec.lentu.utils;

import android.telephony.SmsManager;

public class Utils {

	public static void enviaSms(String numero,String mensagem){
			SmsManager manager = SmsManager.getDefault();
			manager.sendTextMessage(numero, null, mensagem, null, null);
	}
	
}

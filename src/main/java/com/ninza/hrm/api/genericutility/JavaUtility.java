package com.ninza.hrm.api.genericutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random random =new Random();
		int randomNumber=random.nextInt(5000);
		return randomNumber;
	}
	
	public String getsystemdateYYYYDDMM() {
		Date dateobj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date =sdf.format(dateobj);
		return date;	
	}
	public String getrequiredDateYYYYDDMM(int days) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String reqdata =sim.format(cal.getTime());
		return reqdata;
	}

}

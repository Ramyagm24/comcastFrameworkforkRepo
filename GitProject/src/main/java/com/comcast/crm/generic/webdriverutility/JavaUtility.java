package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int randomnumber() {
		Random random=new Random();
		int rdnumber = random.nextInt(5000);
		return rdnumber;
	}
	public String getSystemDateyyyymmdd() {
		Date objdate=new Date();
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(objdate);
		return date;
	}
	public String getRequiredDateyyyymmdd(int days) {
		Date d = new Date();
	
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
		sim.format(d);
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String reqdate = sim.format(cal.getTime());
		return reqdate;
	}
	public String getCurrentDate() {
		LocalDate ld=LocalDate.now();
		String curdate = ld.toString();
		return curdate;
	}
	public String getRequiredDate(int key) {
		LocalDate ld=LocalDate.now();
		LocalDate date=ld.plusMonths(key);
		String reqdate = date.toString();
		return reqdate;
	}
}

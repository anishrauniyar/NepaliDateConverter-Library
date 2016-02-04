package org.converter.NepaliEnglish;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NepaliToEnglish {

	public Date englishDate;
	public Date nepaliDate;
	Calendar ca = Calendar.getInstance();
	
	public void setNepaliDate(String format, String inputNepaliDate){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			ca.setTime(sdf.parse(inputNepaliDate));
			this.nepaliDate = ca.getTime();
		} catch(ParseException e){
			e.printStackTrace();
		}
	}
	
	public String ConvertNepaliToEnglish(String inputFormat, String inputNepaliDate, String outputFormat){
		
		setNepaliDate(inputFormat, inputNepaliDate);
		
		Map<Integer, int[]> nepaliDateList = getNepaliDateList();
		//Starting date of Nepali
		int[] staringNepaliDate = getStartingNepaliDate();
		int[] startingEnglishDate = getStartingEnglishDate();
		int startingNepYear = staringNepaliDate[0];
		int startingNepMonth = staringNepaliDate[1];
		int startingNepDay = staringNepaliDate[2];
		int dayOfWeek = staringNepaliDate[3];
		//Starting date of English
		int startingEngYear = startingEnglishDate[0];
		int startingEngMonth = startingEnglishDate[1];
		int startingEngDay = startingEnglishDate[2];
		//Input Nepali Date
		int nepYear = ca.get(Calendar.YEAR);
		int nepMonth = ca.get(Calendar.MONTH) + 1;
		int nepDay = ca.get(Calendar.DAY_OF_MONTH);
		System.out.println("Nepali Year:" + nepYear + " Month: " + nepMonth + " Day: " + nepDay);
		
		long totalNepDaysCount = 0;
		
		// count total days in-terms of year
		for (int i = startingNepYear; i < nepYear; i++) {
		      for (int j = 1; j <= 12; j++) {
		            totalNepDaysCount += nepaliDateList.get(i)[j];
		      }
		}
		// count total days in-terms of month
		for (int j = startingNepMonth; j < nepMonth; j++) {
		      totalNepDaysCount += nepaliDateList.get(nepYear)[j];
		}
		// count total days in-terms of date
		totalNepDaysCount += nepDay - startingNepDay;
		
		int[] daysInMonth = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int[] daysInMonthOfLeapYear = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		
		// calculation of equivalent english date...
		int engYear = startingEngYear;
		int engMonth = startingEngMonth;
		int engDay = startingEngDay;

		int endDayOfMonth = 0;

		while (totalNepDaysCount != 0) {
		      if (isLeapYear(engYear)) {
		            endDayOfMonth = daysInMonthOfLeapYear[engMonth];
		      } else {
		            endDayOfMonth = daysInMonth[engMonth];
		      }
		      engDay++;
		      dayOfWeek++;
		      if (engDay > endDayOfMonth) {
		            engMonth++;
		            engDay = 1;
		            if (engMonth > 12) {
		                  engYear++;
		                  engMonth = 1;
		            }
		      }
		      if (dayOfWeek > 7) {
		            dayOfWeek = 1;
		      }
		      totalNepDaysCount--;
		}
		
		String requiredDate = engYear+"/"+engMonth+"/"+engDay;
		return requiredDate;
	}

	public String ConvertNepaliToEnglish(Date inputNepaliDate, String outputFormat){
		
		ca.setTime(inputNepaliDate);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DAY_OF_MONTH);
		String inputNepaliDates = year+"/"+month+"/"+day;
		String inputFormat = "yyyy/MM/dd";
		
		setNepaliDate(inputFormat, inputNepaliDates);
		
		Map<Integer, int[]> nepaliDateList = getNepaliDateList();
		//Starting date of Nepali
		int[] staringNepaliDate = getStartingNepaliDate();
		int[] startingEnglishDate = getStartingEnglishDate();
		int startingNepYear = staringNepaliDate[0];
		int startingNepMonth = staringNepaliDate[1];
		int startingNepDay = staringNepaliDate[2];
		int dayOfWeek = staringNepaliDate[3];
		//Starting date of English
		int startingEngYear = startingEnglishDate[0];
		int startingEngMonth = startingEnglishDate[1];
		int startingEngDay = startingEnglishDate[2];
		//Input Nepali Date
		int nepYear = ca.get(Calendar.YEAR);
		int nepMonth = ca.get(Calendar.MONTH);
		int nepDay = ca.get(Calendar.DAY_OF_MONTH);
		
		long totalNepDaysCount = 0;
		
		// count total days in-terms of year
		for (int i = startingNepYear; i < nepYear; i++) {
		      for (int j = 1; j <= 12; j++) {
		            totalNepDaysCount += nepaliDateList.get(i)[j];
		      }
		}
		// count total days in-terms of month
		for (int j = startingNepMonth; j < nepMonth; j++) {
		      totalNepDaysCount += nepaliDateList.get(nepYear)[j];
		}
		// count total days in-terms of date
		totalNepDaysCount += nepDay - startingNepDay;
		
		int[] daysInMonth = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int[] daysInMonthOfLeapYear = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		
		// calculation of equivalent english date...
		int engYear = startingEngYear;
		int engMonth = startingEngMonth;
		int engDay = startingEngDay;

		int endDayOfMonth = 0;

		while (totalNepDaysCount != 0) {
		      if (isLeapYear(engYear)) {
		            endDayOfMonth = daysInMonthOfLeapYear[engMonth];
		      } else {
		            endDayOfMonth = daysInMonth[engMonth];
		      }
		      engDay++;
		      dayOfWeek++;
		      if (engDay > endDayOfMonth) {
		            engMonth++;
		            engDay = 1;
		            if (engMonth > 12) {
		                  engYear++;
		                  engMonth = 1;
		            }
		      }
		      if (dayOfWeek > 7) {
		            dayOfWeek = 1;
		      }
		      totalNepDaysCount--;
		}
		
		String requiredDate = engYear+"/"+engMonth+"/"+engDay;
		SimpleDateFormat sdf = new SimpleDateFormat(outputFormat);
		try {
			ca.setTime(sdf.parse(requiredDate));
			ca.add(Calendar.MONTH, 1);
			this.englishDate = ca.getTime();
		} catch(ParseException e){
			e.printStackTrace();
		}
		return requiredDate;
	}
	
	
	public static boolean isLeapYear(int year) {
		if (year % 100 == 0) {
			return year % 400 == 0;
		} else {
			return year % 4 == 0;
		}
	}
	
	private int[] getStartingEnglishDate() {
		// TODO Auto-generated method stub
		int startingEngYear = 1943;
		int startingEngMonth = 4;
		int startingEngDay = 14;
		int[] startingEnglishDate = {startingEngYear, startingEngMonth, startingEngDay};
		return startingEnglishDate;
	}

	private int[] getStartingNepaliDate() {
		// TODO Auto-generated method stub
		int startingNepYear = 2000;
		int startingNepMonth = 1;
		int startingNepDay = 1;
		int dayOfWeek = Calendar.WEDNESDAY; // 2000/1/1 is Wednesday
		int[] startingNepaliDate = {startingNepYear, startingNepMonth, startingNepDay, dayOfWeek};
		return startingNepaliDate;
	}

	private Map<Integer, int[]> getNepaliDateList() {
		// TODO Auto-generated method stub
		Map<Integer, int[]> nepaliMap = new HashMap<Integer, int[]>();
		
		nepaliMap.put(2000, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
		nepaliMap.put(2001, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2002, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2003, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2004, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
		nepaliMap.put(2005, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2006, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2007, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2008, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31 });
		nepaliMap.put(2009, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2010, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2011, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2012, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 });
		nepaliMap.put(2013, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2014, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2015, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2016, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 });
		nepaliMap.put(2017, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2018, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2019, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
		nepaliMap.put(2020, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2021, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2022, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
		nepaliMap.put(2023, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
		nepaliMap.put(2024, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2025, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2026, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2027, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
		nepaliMap.put(2028, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2029, new int[] { 0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2030, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2031, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
		nepaliMap.put(2032, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2033, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2034, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2035, new int[] { 0, 30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31 });
		nepaliMap.put(2036, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2037, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2038, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2039, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 });
		nepaliMap.put(2040, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2041, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2042, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2043, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 });
		nepaliMap.put(2044, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2045, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2046, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2047, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2048, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2049, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
		nepaliMap.put(2050, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
		nepaliMap.put(2051, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2052, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2053, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
		nepaliMap.put(2054, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
		nepaliMap.put(2055, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2056, new int[] { 0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2057, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2058, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
		nepaliMap.put(2059, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2060, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2061, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2062, new int[] { 0, 30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31 });
		nepaliMap.put(2063, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2064, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2065, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2066, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31 });
		nepaliMap.put(2067, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2068, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2069, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2070, new int[] { 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 });
		nepaliMap.put(2071, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2072, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2073, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 });
		nepaliMap.put(2074, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2075, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2076, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
		nepaliMap.put(2077, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 });
		nepaliMap.put(2078, new int[] { 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2079, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 });
		nepaliMap.put(2080, new int[] { 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
		nepaliMap.put(2081, new int[] { 0, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
		nepaliMap.put(2082, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
		nepaliMap.put(2083, new int[] { 0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30 });
		nepaliMap.put(2084, new int[] { 0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30 });
		nepaliMap.put(2085, new int[] { 0, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30 });
		nepaliMap.put(2086, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
		nepaliMap.put(2087, new int[] { 0, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30 });
		nepaliMap.put(2088, new int[] { 0, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30 });
		nepaliMap.put(2089, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
		nepaliMap.put(2090, new int[] { 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
		return nepaliMap;
	}
}

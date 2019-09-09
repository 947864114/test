package com.mq.util;

import com.mq.util.exception.ExceptionStatus;
import com.mq.util.exception.BlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDD_NO_SPLIT = "yyyyMMdd";
	public static final String YYYYMM = "yyyy/MM";

	public static final String YYYYMMDD_ZH = "yyyy年MM月dd日";
	public static final String YYYYMMDD1 = "yyyyMMddHHmmss";
	public static final String YYYYMMDD2 ="yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDD3 ="yyyy-MM-dd HH:mm";

	public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY; // 中国周一是一周的第一天

	/**
	 *增加日期后的日期
	 * @param datetime
	 * @param datepart
	 * @return
	 */
	public static String getDateTimePart(Date datetime, int datepart) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(datetime.getTime());
		cal.add(Calendar.DATE,datepart);
		//设置要获取到什么样的时间
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
		String date2= sdf.format(cal.getTime());

		return date2;
	}

	//计算收益天数（date）
	public static Date getDateTimePartDate(Date datetime, int datepart) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(datetime.getTime());
		cal.add(Calendar.DATE,datepart);
		//设置要获取到什么样的时间
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
		Date calTime = cal.getTime();

		return calTime;
	}

	/**
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date parseDate(String strDate) {
		return parseDate(strDate, null);
	}

	/**
	 * parseDate
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String strDate, String pattern) {
		Date date = null;
		try {
			if (pattern == null) {
				pattern = YYYYMMDD;
			}
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.parse(strDate);
		} catch (Exception e) {
			throw new BlException(ExceptionStatus.SERVER_ERROR);
		}
		return date;
	}

	/**
	 * format date
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, null);
	}

	/**
	 * format date
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		String strDate = null;
		try {
			if (pattern == null) {
				pattern = YYYYMMDD;
			}
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			strDate = format.format(date);
		} catch (Exception e) {
			logger.error("formatDate error:", e);
		}
		return strDate;
	}

	/**
	 * 取得日期：年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 取得日期：年
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		return month + 1;
	}

	/**
	 * 取得日期：年
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int da = c.get(Calendar.DAY_OF_MONTH);
		return da;
	}

	/**
	 * 取得当天日期是周几
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weekDay = c.get(Calendar.DAY_OF_WEEK) - 1;
		if(weekDay == 0){
			weekDay = 7;
		}
		return weekDay;
	}

	/**
	 * 取得一年的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week_of_year = c.get(Calendar.WEEK_OF_YEAR);
		return week_of_year;
	}

	/**
	 * getWeekBeginAndEndDate
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getWeekBeginAndEndDate(Date date, String pattern) {
		Date monday = getMondayOfWeek(date);
		Date sunday = getSundayOfWeek(date);
		return formatDate(monday, pattern) + " - "
				+ formatDate(sunday, pattern);
	}

	/**
	 * 根据日期取得对应周周一日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMondayOfWeek(Date date) {
		Calendar monday = Calendar.getInstance();
		monday.setTime(date);
		monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return monday.getTime();
	}

	/**
	 * 根据日期取得对应周周日日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSundayOfWeek(Date date) {
		Calendar sunday = Calendar.getInstance();
		sunday.setTime(date);
		sunday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return sunday.getTime();
	}

	/**
	 * 取得月的剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getRemainDayOfMonth(Date date) {
		int dayOfMonth = getDayOfMonth(date);
		int day = getPassDayOfMonth(date);
		return dayOfMonth - day;
	}

	/**
	 * 取得月已经过的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getPassDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得月天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得月第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		 SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD1); 
		  Calendar   c=Calendar.getInstance();//获取当前日期 
		  	c.setTime(date);
	        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.SECOND, 0); 
			c.set(Calendar.MINUTE, 0); 
			c.set(Calendar.MILLISECOND, 0); 
	       String d =  format.format(c.getTime());
	        try {
				return format.parse(d);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return null;
	}

	/**
	 * 取得月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		 SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD1); 
		 Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.SECOND, 59); 
			c.set(Calendar.MINUTE, 59); 
			c.set(Calendar.MILLISECOND, 59); 
	       String d =  format.format(c.getTime());
	        try {
				return format.parse(d);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return null;	
	}

	/**
	 * 取得季度第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfSeason(Date date) {
		return getFirstDateOfMonth(getSeasonDate(date)[0]);
	}

	/**
	 * 取得季度最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfSeason(Date date) {
		return getLastDateOfMonth(getSeasonDate(date)[2]);
	}

	/**
	 * 取得季度天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfSeason(Date date) {
		int day = 0;
		Date[] seasonDates = getSeasonDate(date);
		for (Date date2 : seasonDates) {
			day += getDayOfMonth(date2);
		}
		return day;
	}

	/**
	 * 取得季度剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getRemainDayOfSeason(Date date) {
		return getDayOfSeason(date) - getPassDayOfSeason(date);
	}

	/**
	 * 取得季度已过天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getPassDayOfSeason(Date date) {
		int day = 0;

		Date[] seasonDates = getSeasonDate(date);

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);

		if (month == Calendar.JANUARY || month == Calendar.APRIL
				|| month == Calendar.JULY || month == Calendar.OCTOBER) {// 季度第一个月
			day = getPassDayOfMonth(seasonDates[0]);
		} else if (month == Calendar.FEBRUARY || month == Calendar.MAY
				|| month == Calendar.AUGUST || month == Calendar.NOVEMBER) {// 季度第二个月
			day = getDayOfMonth(seasonDates[0])
					+ getPassDayOfMonth(seasonDates[1]);
		} else if (month == Calendar.MARCH || month == Calendar.JUNE
				|| month == Calendar.SEPTEMBER || month == Calendar.DECEMBER) {// 季度第三个月
			day = getDayOfMonth(seasonDates[0]) + getDayOfMonth(seasonDates[1])
					+ getPassDayOfMonth(seasonDates[2]);
		}
		return day;
	}

	/**
	 * 取得季度月
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getSeasonDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int nSeason = getSeason(date);
		int year = c.get(Calendar.YEAR);
		return getSeasonDateByYear(year, nSeason);
	}
	/**
	 * 根据年和季度取得季度月
	 * 
	 * @return
	 */
	public static Date[] getSeasonDateByYear(Integer year,Integer nSeason) {
		Date[] season = new Date[3];
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.DAY_OF_MONTH, 1);//取当月第一天
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		if(nSeason == null || nSeason < 1 || nSeason > 4){ //没有季度则取按年取当年1月1日
			c.set(Calendar.MONTH, Calendar.JANUARY);//取当年第一月
			season[0] = c.getTime();
		}else if (nSeason == 1) {// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}


	/**
	 * 
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {

		int season = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}

	/**
	 * 取得年第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	public static String getCurrencyTimeString(){
		return String.valueOf(Calendar.getInstance().getTimeInMillis());
	}

	/**
	 * 取得年最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, Calendar.DECEMBER);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}


	/**
	 * 获取时间零点 如：2016-01-01 00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartTimeOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取时间零点 如：2016-01-01 00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndTimeOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	/**
	 * 获取该时间所在月份的最大天数
	 * @param date
	 * @return
	 */
	public static int getMaxDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 判断是否是周末
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weekDay = c.get(Calendar.DAY_OF_WEEK);
		if(weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY){
			return true;
		}
		return false;
	}
	/**
	 * @Title: daysBetween
	 * @Description: 计算两个日期相差多少天
	 * @param startDate
	 * @param endDate
	 * @return int
	 * @throws
	 */
	public static int daysBetween(Date startDate, Date endDate) {
		long time1 = startDate.getTime();
        long time2 = endDate.getTime();         
        Double between_days=Math.ceil((time2-time1)/(1000*3600*24*1.0));
		return between_days.intValue();
	}
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int minsBetween(Date startDate, Date endDate) {
		long time1 = startDate.getTime();
        long time2 = endDate.getTime();  
        Double between_mins;
        if(time2>=time1)
        	between_mins =Math.ceil((time2-time1)/(1000*60));
        else between_mins = Math.ceil((time1-time2)/(1000*60));
		return between_mins.intValue();
	}
	/**
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int secondBetween(Date startDate, Date endDate) {
		long time1 = startDate.getTime();
        long time2 = endDate.getTime();
        Double between_mins;
        if(time2>=time1)
        	between_mins =Math.ceil((time2-time1)/(1000));
        else between_mins = Math.ceil((time1-time2)/(1000));
		return between_mins.intValue();
	}
	//比较两个时间的年月,-1:date1<date2, 0:date1==date2, 1:date1>date2
	public static int compareYearMonth(Date date1,Date date2){
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMM);
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(DateUtils.getYear(date1)+"/"+ DateUtils.getMonth(date1));
			d2 = sdf.parse(DateUtils.getYear(date2)+"/"+ DateUtils.getMonth(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d1.compareTo(d2);
	}



	/*
	 * 将时间戳转换为时间
	 */
	public static Date stampToDate(long s){
		Date res=new Date(s*1000l);
		return res;
	}

	/**
	 * 得到今天以前指定天数的时间，如：（-）向前数一天的时间（+）向后数一天的时间
	 */
	public static Date getBeforeSomeDays(int number){
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DATE, -number);
		return calendar.getTime();
	}

	/**
	 * 得到今天以前指定天数的时间，向后数一天的时间
	 */
	public static Date getAfterSomeDays(int number){
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DATE, number);
		return calendar.getTime();
	}

	/**
	 * 得到两个时间的天数差
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	public static int getDayNumBetweenDate(Date startDt, Date endDt) {
		Long quot = 0l, days = 0l;
		quot = endDt.getTime() - startDt.getTime();
		days = quot / 1000 / 60 / 60 / 24;
		return days.intValue();
	}



	/**
	 * 根据给定日期设置 指定日期
	 * @param datetime
	 * @param datepart
	 * @param number
	 * @return
	 */
	public static Timestamp roll(Date datetime, int datepart, int number) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(datetime.getTime());
		calendar.add(datepart, number);
		return new Timestamp(calendar.getTimeInMillis());
	}

	public static void main(String[] args) {
		/*Date d = parseDate("2017-12-18", DateUtils.YYYYMMDD);

		System.out.println(DateUtils.formatDate(roll(d, Calendar.DATE, 15), DateUtils.YYYYMMDD));*/
//		Date date = getDateTimePartDate(new Date(),1);
//		System.out.println(System.currentTimeMillis());
//		System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date(System.currentTimeMillis())));


		// Date date = new Date();
		//
		// String strDate = DateUtils.formatDate(date, "yyyy-MM-dd");
		//
		// System.out.println(strDate + " 今天是哪一年？" + getYear(date));
		// System.out.println(strDate + " 今天是哪个月？" + getMonth(date));
		// System.out.println(strDate + " 今天是几号？" + getDay(date));
		// System.out.println(strDate + " 今天是周几？" + getWeekDay(date));
		// System.out.println(strDate + " 是一年的第几周？" + getWeekOfYear(date));
		// System.out.println(strDate + " 所在周起始结束日期？"
		// + getWeekBeginAndEndDate(date, "yyyy年MM月dd日"));
		// System.out.println(strDate + " 所在周周一是？"
		// + formatDate(getMondayOfWeek(date)));
		// System.out.println(strDate + " 所在周周日是？"
		// + formatDate(getSundayOfWeek(date)));
		//
		// System.out.println(strDate + " 当月第一天日期？"
		// + formatDate(getFirstDateOfMonth(date)));
		// System.out.println(strDate + " 当月最后一天日期？"
		// + formatDate(getLastDateOfMonth(date)));
		// System.out.println(strDate + " 当月天数？" + getDayOfMonth(date));
		// System.out.println(strDate + " 当月已过多少天？" + getPassDayOfMonth(date));
		// System.out.println(strDate + " 当月剩余多少天？" +
		// getRemainDayOfMonth(date));
		//
		// System.out.println(strDate + " 所在季度第一天日期？"
		// + formatDate(getFirstDateOfSeason(date)));
		// System.out.println(strDate + " 所在季度最后一天日期？"
		// + formatDate(getLastDateOfSeason(date)));
		// System.out.println(strDate + " 所在季度天数？" + getDayOfSeason(date));
		// System.out.println(strDate + " 所在季度已过多少天？" +
		// getPassDayOfSeason(date));
		// System.out
		// .println(strDate + " 所在季度剩余多少天？" + getRemainDayOfSeason(date));
		// System.out.println(strDate + " 是第几季度？" + getSeason(date));
		// System.out.println(strDate + " 所在季度月份？"
		// + formatDate(getSeasonDate(date)[0], "yyyy年MM月") + "/"
		// + formatDate(getSeasonDate(date)[1], "yyyy年MM月") + "/"
		// + formatDate(getSeasonDate(date)[2], "yyyy年MM月"));
		// System.out.println(strDate + " 所在年第一天日期？"
		// + formatDate(getFirstDateOfYear(date)));
		// System.out.println(strDate + " 所在年最后一天日期？"
		// + formatDate(getLastDateOfYear(date)));
//		DateUtils d = new DateUtils();
//		System.out.println(d.parseDate("20160701120503", YYYYMMDD1));
//		System.out.println(minsBetween(d.parseDate("20160701120503", YYYYMMDD1), d.parseDate("20160701140503", YYYYMMDD1)));

//		Date d = new Date();
//		System.out.println(formatDate(d, YYYYMMDD));
		String date1 = "2019-07-22 23:59:59";
		String date2 = "2019-07-23 00:00:00";

		Date d1 = DateUtils.parseDate(date1, DateUtils.YYYYMMDD);
		Date d2 = DateUtils.parseDate(date2, DateUtils.YYYYMMDD);
		System.out.println(d2.compareTo(d1));
	}
}

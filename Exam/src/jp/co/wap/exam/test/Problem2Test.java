package jp.co.wap.exam.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import jp.co.wap.exam.Problem1;
import jp.co.wap.exam.Problem2;
import jp.co.wap.exam.lib.Interval;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class Problem2Test {

	@Test
	public void testGetMaxWorkingTime() {
		Problem2 p = new Problem2();
		// example Figure 1
		Interval interval1 = new Interval("08:00", "12:00");
		Interval interval2 = new Interval("06:00", "09:00");
		Interval interval3 = new Interval("11:00", "13:00");
		List<Interval> figure1 = Arrays.asList(interval1, interval2, interval3);
		assertThat(p.getMaxWorkingTime(figure1), is(300));
		// example Figure 2
		List<Interval> figure2 = Arrays.asList(new Interval("09:00", "12:30"),
				new Interval("06:00", "09:30"), new Interval("12:00", "14:30"),
				new Interval("10:00", "10:30"), new Interval("11:00", "13:30"));
		assertThat(p.getMaxWorkingTime(figure2), is(390));
		// other testcases
		assertThat(p.getMaxWorkingTime(null), is(0));
		assertThat(p.getMaxWorkingTime(Arrays.asList(new Interval("09:00",
				"12:00"))), is(180));
		assertThat(p.getMaxWorkingTime(Arrays.asList(new Interval("09:00",
				"12:00"), new Interval("12:00", "13:00"))), is(180));
		assertThat(p.getMaxWorkingTime(Arrays.asList(new Interval("09:00",
				"12:00"), new Interval("11:00", "14:30"))), is(210));
		assertThat(p.getMaxWorkingTime(Arrays.asList(new Interval("09:00",
				"12:00"), new Interval("13:00", "13:00"), new Interval("11:00",
				"14:30"))), is(210));
		assertThat(p.getMaxWorkingTime(Arrays.asList(new Interval("09:00",
				"09:00"), new Interval("11:00", "11:00"))), is(0));
		assertThat(p.getMaxWorkingTime(Arrays.asList(new Interval("09:00",
				"09:00"), new Interval("09:00", "09:00"), new Interval("24:00", "24:00"))), is(0));
		assertThat(p.getMaxWorkingTime(Arrays.asList(new Interval("09:00",
				"09:00"), new Interval("09:00", "09:00"), new Interval("00:00", "24:00"))), is(1440));
	
	}

	@Test
	public void testGetMaxWorkingTimePerformance() {
		Problem2 p = new Problem2();
		int num = 10000;
		List<Interval> randomGenIntervals = randomGenIntervals(num);
		long current = System.currentTimeMillis();
		p.getMaxWorkingTime(randomGenIntervals);
		// System.out.println("time:" + (System.currentTimeMillis() - current));
	}

	public static List<Interval> randomGenIntervals(int num) {
		List<Interval> intervals = new ArrayList<Interval>(num);
		while (num-- > 0) {
			intervals.add(randomGenInterval());
		}
		return intervals;
	}

	private static Interval randomGenInterval() {
		int startTime = (int) (1440 * Math.random());
		int endTime = startTime
				+ (int) ((1440 - startTime) * Math.random() + 1);
		String beginTimeStr = String.format("%02d:%02d", startTime / 60,
				startTime % 60);
		String endTimeStr = String.format("%02d:%02d", endTime / 60,
				endTime % 60);
		return new Interval(beginTimeStr, endTimeStr);
	}
}

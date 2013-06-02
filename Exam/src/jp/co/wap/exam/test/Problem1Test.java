package jp.co.wap.exam.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jp.co.wap.exam.Problem1;
import jp.co.wap.exam.lib.Interval;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class Problem1Test {

	@Test
	public void testGetMaxIntervalOverlapCount() {
		Problem1 p = new Problem1();

		// example Figure 1
		Interval interval1 = new Interval("08:00", "12:00");
		Interval interval2 = new Interval("06:00", "09:00");
		Interval interval3 = new Interval("11:00", "13:00");
		List<Interval> figure1 = Arrays.asList(interval1, interval2, interval3);
		assertThat(p.getMaxIntervalOverlapCount(figure1), is(2));

		// example Figure 2
		List<Interval> figure2 = Arrays.asList(new Interval("09:00", "12:30"),
				new Interval("06:00", "09:30"), new Interval("12:00", "14:30"),
				new Interval("10:00", "10:30"), new Interval("11:00", "13:30"));
		assertThat(p.getMaxIntervalOverlapCount(figure2), is(3));
		// other examples
		assertThat(p.getMaxIntervalOverlapCount(new ArrayList<Interval>()),
				is(0));
		assertThat(p.getMaxIntervalOverlapCount(null), is(0));
		assertThat(p.getMaxIntervalOverlapCount(Arrays.asList(new Interval(
				"09:00", "09:30"), new Interval("06:00", "09:00"))), is(2));
		assertThat(p.getMaxIntervalOverlapCount(Arrays.asList(new Interval(
				"09:00", "09:30"), new Interval("06:00", "09:30"))), is(2));
		assertThat(p.getMaxIntervalOverlapCount(Arrays.asList(new Interval(
				"09:00", "09:00"), new Interval("06:00", "09:30"))), is(2));
		assertThat(p.getMaxIntervalOverlapCount(Arrays.asList(new Interval(
				"09:00", "09:00"), new Interval("06:00", "09:30"))), is(2));
		assertThat(p.getMaxIntervalOverlapCount(Arrays.asList(new Interval(
				"09:00", "09:00"), new Interval("09:00", "09:00"))), is(2));
		assertThat(p.getMaxIntervalOverlapCount(Arrays.asList(new Interval(
				"08:00", "09:00"), new Interval("09:00", "09:00"),
				new Interval("09:00", "19:00"))), is(3));
	}

	@Test
	public void testGetMaxIntervalOverlapCountPerformance() {
		Problem1 p = new Problem1();
		int num = 0;
		while ((num += 500) <= 10000) {
			List<Interval> randomGenIntervals = Problem2Test
					.randomGenIntervals(num);
			long current = System.currentTimeMillis();
			p.getMaxIntervalOverlapCount(randomGenIntervals);
			// System.out.println("benchmark:" + num + " time:"
			// + (System.currentTimeMillis() - current));
		}

	}
}

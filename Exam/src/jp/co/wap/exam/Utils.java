package jp.co.wap.exam;

import java.util.Comparator;
import java.util.List;

import jp.co.wap.exam.lib.Interval;

public class Utils {
	/**
	 * Find the nearest mutually exclusive (non-overlapping) interval that
	 * starts before a given BeginMinuteUnit.
	 * 
	 * @param orderedIntervals
	 * @param beginMinUnit
	 * @return an integer represents the index (start from 1) of target interval
	 */
	public static int bisecRightID(List<Interval> orderedIntervals,
			int beginMinUnit) {
		int m;
		int l = 0, r = orderedIntervals.size() - 1;
		while (r >= l) {
			m = (l + r) / 2;
			if (orderedIntervals.get(m).getEndMinuteUnit() < beginMinUnit)
				l = m + 1;
			else
				r = m - 1;
		}
		return r + 1;
	}

	public static Comparator<Interval> endMinuteUnitComparator = new Comparator<Interval>() {

		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.getEndMinuteUnit() - o2.getEndMinuteUnit();
		}

	};
}

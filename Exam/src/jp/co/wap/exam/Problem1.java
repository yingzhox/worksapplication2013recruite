package jp.co.wap.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.co.wap.exam.lib.Interval;

public class Problem1 {
	/**
	 * The main idea is that, first we sort(merge sort) the list according to
	 * the end time of each interval, and then we traverse the sorted list from
	 * tail to the head , for each interval , we will count the number of
	 * overlapped intervals before it (performing a binary search to find the
	 * nearest interval whose EndMinuteUnit is less that current interval's
	 * BeginMinuteUnit, the number of overlapped intervals is the difference
	 * between their indexes), 2 pruning methods are used to reduce the
	 * complexity greatly: 1. If the number of intervals including current
	 * interval is less than the maxOverlap already found, then we will stop
	 * search, since even if all the intervals before current interval are
	 * overlapped, they will still not exceed the maxOverlap. 2 If current
	 * interval's begin time is greater than previous one, then we will not
	 * check the overlap of this interval , since the overlap is already
	 * accounted in the previous interval.
	 * 
	 * @param intervals
	 *            a list of intervals
	 * @return an integer represents the max overlap number of intervals
	 **/
	public int getMaxIntervalOverlapCount(List<Interval> intervals) {
		if (null == intervals || intervals.size() < 1)
			return 0;
		if (intervals.size() < 2)
			return 1;
		ArrayList<Interval> tempIntervals = new ArrayList<Interval>(intervals);
		Collections.sort(tempIntervals,Utils.endMinuteUnitComparator);
		int last = tempIntervals.size();
		int maxOverlap = 0;
		Interval previousCurrent = null;
		while (last-- > 0) {
			if (last + 1 <= maxOverlap)
				break;
			Interval current = tempIntervals.get(last);
			if (previousCurrent != null
					&& previousCurrent.getBeginMinuteUnit() <= current
							.getBeginMinuteUnit())
				continue;
			int bisecRightID = Utils.bisecRightID(tempIntervals,
					current.getBeginMinuteUnit());
			int overlap = last - (bisecRightID) + 1;
			previousCurrent = current;
			if (overlap > maxOverlap)
				maxOverlap = overlap;
		}
		return maxOverlap;
	}
}

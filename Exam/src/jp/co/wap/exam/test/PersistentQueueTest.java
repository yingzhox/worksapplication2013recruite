package jp.co.wap.exam.test;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import jp.co.wap.exam.PersistentQueue;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class PersistentQueueTest {

	@Test//(expected=NoSuchElementException.class)
	public void testPersistentQueue() {
		PersistentQueue<Integer> p = new PersistentQueue<Integer>();
		assertThat(p.size(), is(0));
		p = p.enqueue(2);
		assertThat(p.size(), is(1));
		p = p.enqueue(9);
		assertThat(p.peek(), is(2));
		assertThat(p.size(), is(2));
		p = p.dequeue();
		assertThat(p.peek(), is(9));
		p = p.enqueue(10);
		assertThat(p.peek(), is(9));
		p = p.dequeue();
		assertThat(p.peek(), is(10));
		p = p.dequeue();
		assertThat(p.size(), is(0));
		p.peek();
	}

	@Test
	public void testPersistentQueuePerformance() {
		PersistentQueue<Integer> p = new PersistentQueue<Integer>();
		int num = 0;
		int max = 1000000;
		long current = System.currentTimeMillis();
		while (++num <= max) {
			int value = (int) (Math.random() * 100);
			p = p.enqueue(value);
		}
		// System.out.println("enqueue time:"
		// + (System.currentTimeMillis() - current));
		current = System.currentTimeMillis();
		while (--num > 0) {
			p = p.dequeue();
		}
		// System.out.println("dequeue time:"
		// + (System.currentTimeMillis() - current));
	}
}

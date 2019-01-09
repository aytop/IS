package util;

import java.util.concurrent.TimeUnit;

public class EventCounter {

	private long value;

	public EventCounter() {
		this(0);
	}

	public EventCounter(long initialValue) {
		this.value = initialValue;
	}

	public synchronized void await(long value) throws InterruptedException {
		while (this.value < value) {
			wait();
		}
	}

	public synchronized void awaitUninterruptibly(long value) {
		if (tryAwait(value)) {
			return;
		}
		boolean wasInterrupted = Thread.interrupted();
		while (this.value < value) {
			try {
				wait();
			} catch (InterruptedException e) {
				wasInterrupted = true;
			}
		}
		if (wasInterrupted) {
			Thread.currentThread().interrupt();
		}
	}

	public synchronized boolean tryAwait(long value) {
		return this.value >= value;
	}

	public synchronized boolean tryAwait(long value, long timeout, TimeUnit unit) throws InterruptedException {
		long endTime = System.nanoTime() + unit.toNanos(timeout);
		while ((this.value < value) && ((timeout = endTime - System.nanoTime()) > 0)) {
			wait(timeout / 1_000_000, (int) (timeout % 1_000_000));
		}
		return tryAwait(value);
	}

	public synchronized void advance() {
		advance(1);
	}

	public synchronized void advance(long delta) {
		if (delta < 1) {
			throw new IllegalArgumentException("delta");
		}
		if (Long.MAX_VALUE - delta < value) {
			throw new IllegalMonitorStateException();
		}
		value = value + delta;
		notifyAll();
	}

	public synchronized long value() {
		return value;
	}

	public String toString() {
		return super.toString() + "[Value = " + value() + "]";
	}
}

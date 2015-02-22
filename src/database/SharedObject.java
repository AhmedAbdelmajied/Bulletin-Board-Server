package database;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedObject {

	private int data;
	private static SharedObject sharedObject;
	private ReadWriteLock readWriteLock;

	private SharedObject() {
		data = -1;
		readWriteLock = new ReentrantReadWriteLock(true);
	}

	public int getData() {
		readWriteLock.readLock().lock();
		try {
			return data;
		} finally {
			readWriteLock.readLock().unlock();
		}
	}

	public void setData(int data) {
		readWriteLock.writeLock().lock();
		try {
			this.data = data;
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	public static SharedObject create() {
		if (sharedObject == null)
			sharedObject = new SharedObject();

		return sharedObject;
	}

}

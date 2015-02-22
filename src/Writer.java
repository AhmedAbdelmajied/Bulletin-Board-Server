import comunication.SenderReceiver;
import database.SharedObject;

public class Writer extends Thread {

	private SenderReceiver senderReceiver;
	private SharedObject sharedObject;

	private boolean stoped;

	public Writer(SenderReceiver senderReceiver) {
		this.senderReceiver = senderReceiver;
		this.sharedObject = SharedObject.create();

		stoped = false;
	}

	public void run() {
		while (!stoped) {

		}
	}

	public void close() {
		stoped = true;
		senderReceiver.close();
	}

}

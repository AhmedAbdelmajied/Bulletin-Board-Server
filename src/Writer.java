import comunication.SenderReceiver;
import database.SharedObject;

public class Writer extends Thread {

	private SenderReceiver senderReceiver;
	private SharedObject sharedObject;

	public Writer(SenderReceiver senderReceiver) {
		this.senderReceiver = senderReceiver;
		this.sharedObject = SharedObject.create();

	}

	public void run() {

	}

}

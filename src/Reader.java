import comunication.SenderReceiver;
import database.SharedObject;

public class Reader extends Thread {

	private SenderReceiver senderReceiver;
	private SharedObject sharedObject;

	boolean stoped;

	public Reader(SenderReceiver senderReceiver) {
		this.senderReceiver = senderReceiver;
		this.sharedObject = SharedObject.create();

		stoped = false;
	}

	public void run() {
		
		while (!stoped) {
			String commend = new String(senderReceiver.receive());
			// String parsedCommend [] =commend.split("\ ");
		}
	}

	public void close() {

		stoped = true;
		senderReceiver.close();
	}

}

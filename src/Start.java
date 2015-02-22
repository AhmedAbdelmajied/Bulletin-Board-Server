import java.io.IOException;
import java.util.ArrayList;

import comunication.SenderReceiver;
import listeners.NewPeersListener;
import listeners.ServerListeners;

public class Start {

	private static ArrayList<Thread> readersList = new ArrayList<Thread>();
	private static ArrayList<Thread> writersList = new ArrayList<Thread>();

	private static ServerListeners listener = new ServerListeners() {

		public void FoundNewPeer(SenderReceiver senderReceiver, char type) {
			switch (type) {
			case 'R':
				Thread reader = new Reader(senderReceiver);
				reader.start();
				readersList.add(reader);
				break;
			case 'W':
				Thread writer = new Writer(senderReceiver);
				writer.start();
				writersList.add(writer);
				break;

			default:
				break;
			}
		}

	};

	public static void main(String[] args) {

		try {
			Thread t = new NewPeersListener(12200, listener);
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO take input from user to close socket
		close();

	}

	private static void close() {

	}

}

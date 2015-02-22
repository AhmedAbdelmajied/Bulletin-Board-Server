package listeners;

import java.io.IOException;
import java.net.*;

import comunication.SenderReceiver;

public class NewPeersListener extends Thread {

	private ServerSocket serverSocket;
	private ServerListeners listener;
	private boolean stoped;

	public NewPeersListener(int port, ServerListeners listener)
			throws IOException {
		this.listener = listener;
		stoped = false;
		serverSocket = new ServerSocket(port);

	}

	public void run() {

		while (!stoped) {
			try {
				Socket clientSocket;
				clientSocket = serverSocket.accept();
				SenderReceiver senderReceiver = new SenderReceiver(clientSocket);
				char type = (char) senderReceiver.receive()[0];
				listener.FoundNewPeer(senderReceiver, type);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void close() {
		try {
			serverSocket.close();
			stoped = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

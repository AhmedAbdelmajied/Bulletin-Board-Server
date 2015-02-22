package comunication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SenderReceiver {

	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;

	public SenderReceiver(Socket socket) throws IOException {
		this.socket = socket;
		bis = new BufferedInputStream(socket.getInputStream());
		bos = new BufferedOutputStream(socket.getOutputStream());
	}

	public void send(byte[] data) throws IOException {
		byte[] size = new byte[4];
		int arraySize = data.length;
		// convert arraySize to byte array
		for (int i = 0; i < size.length; i++) {
			size[i] = (byte) (arraySize & 0xff);
			arraySize >>= 8;
		}
		bos.write(size);
		bos.write(data);
		bos.flush();

	}

	public byte[] receive() {
		try {
			// ---------- receive size ----------
			// byte array to carry size of data
			byte[] size = new byte[4];

			int check = bis.read(size);
			if (check == -1)
				return null;

			// convert received size from byte array to integer
			int dataLenght = 0;
			for (int i = size.length - 1; i >= 0; i--) {
				dataLenght |= (size[i] & 0xff);
				if (i != 0)
					dataLenght <<= 8;
			}
			// ---------- receive data ----------
			// byte array to carry received data
			byte[] data = new byte[dataLenght];

			int offset = 0;
			int numRead = 0;
			while (offset < data.length
					&& (numRead = bis.read(data, offset, data.length - offset)) >= 0) {
				offset += numRead;
			}

			// Ensure all the bytes have been read in
			if (offset < data.length)
				throw new IOException("Could not completely receieve image ");

			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void close() {
		try {
			socket.close();
			bis.close();
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

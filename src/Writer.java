import java.io.IOException;

import comunication.SenderReceiver;
import database.SharedObject;

public class Writer extends Thread {

	private SenderReceiver senderReceiver;
	private SharedObject sharedObject;
	private String foundCmnd = "200 OK" ;
	private String errNotFoundCmnd = "404 Not Found" ;
	private String crtdCmnd = "201 Created";
	private String errNotAccptCmnd = "406 Not Acceptable";
	

	private boolean stoped;

	public Writer(SenderReceiver senderReceiver) {
		this.senderReceiver = senderReceiver;
		this.sharedObject = SharedObject.create();

		stoped = false;
	}

	public void run() {
		while (!stoped) {
			String cmnd= new String (senderReceiver.receive());
			
			String [] cmnd_arr = cmnd.split("\\s+") ;  // split on any special character
			
			if(cmnd_arr[0].equals("write")){
				cmnd = foundCmnd+ sharedObject.getData();
				
				try {
					
					senderReceiver.send(cmnd.getBytes()) ;
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					
					senderReceiver.send(errNotFoundCmnd.getBytes()) ;
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			

		}
	}

	public void close() {
		stoped = true;
		senderReceiver.close();
	}

}

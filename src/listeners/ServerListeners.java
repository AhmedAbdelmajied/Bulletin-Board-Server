package listeners;
import comunication.SenderReceiver ;

public interface ServerListeners {

	public void FoundNewPeer(SenderReceiver senderReceiver , char type);
}

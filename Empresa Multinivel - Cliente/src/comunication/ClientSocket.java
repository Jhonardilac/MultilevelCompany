package comunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
	
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	public ClientSocket(String host, int port) throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);
		this.dataInputStream = new DataInputStream(this.socket.getInputStream());
		this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public DataInputStream getDataInputStream() {
		return dataInputStream;
	}
	
	public DataOutputStream getDataOutputStream() {
		return dataOutputStream;
	}

	public void sendMessage(String message) throws IOException {
		this.dataOutputStream.writeUTF(message);
	}

	public String reciveMessage() throws IOException {
		String message = "";
		while (true) {
			message = dataInputStream.readUTF();
			if (!message.equals("")) {
				break;
			}
		}
		return message;
	}
}
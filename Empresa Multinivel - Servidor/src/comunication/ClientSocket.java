package comunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket {

	private int id;
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public ClientSocket(int id, Socket socket) throws IOException {
		this.id = id;
		this.socket = socket;
		this.dataInputStream = new DataInputStream(socket.getInputStream());
		this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
	}
	
	public void sendMessage(String message) throws IOException{
		this.dataOutputStream.writeUTF(message);
	}
	
	public int getId() {
		return id;
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
}
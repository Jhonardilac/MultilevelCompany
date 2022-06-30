package threads;

import comunication.ClientSocket;
import controllers.Controller;

public class ThreadLoadData extends Thread{

	private Controller controller;
	private ClientSocket clientSocket;
	
	public ThreadLoadData(Controller controller, ClientSocket clientSocket) {
		this.controller = controller;
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		super.run();
		controller.setAuthenticationMode();
		if (controller.loadUser()) {
			controller.setLoadingMode();
			controller.loadMembers();
			controller.loadCatalogue();
			controller.loadOrders();
			controller.loadMessages();
			controller.setLoginFrameVisible(false);
			controller.setHomeFrameVisible(true);
			controller.setNormalMode();
			new ThreadHostListener(controller, clientSocket).start();
		}
	}
}
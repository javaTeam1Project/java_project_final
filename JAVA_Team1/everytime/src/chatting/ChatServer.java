package chatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import DTO.LoginUser;

public class ChatServer {
	ServerSocket serverSocket;
	Socket socket;
	List<Thread> list;		// ServerSocketThread 객체 저장
	
	public ChatServer() {
		list = new ArrayList<Thread>();
		System.out.println("서버가 시작되었습니다.");
	}
	public void giveAndTake() {
		try {
			serverSocket = new ServerSocket(5420);		
			serverSocket.setReuseAddress(true); 		
			
			while(true) {
				socket = serverSocket.accept();		
				ServerSocketThread thread = new ServerSocketThread(this, socket);	
				addClient(thread);		// 리스트에 쓰레드 객체 저장
				thread.start();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private synchronized void addClient(ServerSocketThread thread) {
		list.add(thread);
	}		

	public synchronized void removeClient(Thread thread) {
		list.remove(thread);
	}

	public synchronized void broadCasting(String str) {
		for(int i = 0; i < list.size(); i++) {
			ServerSocketThread thread = (ServerSocketThread)list.get(i);
			thread.sendMessage(str);
		}
	}
}
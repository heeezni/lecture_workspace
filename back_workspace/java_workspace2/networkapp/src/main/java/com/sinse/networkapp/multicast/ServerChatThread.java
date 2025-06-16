package com.sinse.networkapp.multicast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

// 접속자가 감지되었을 때, 소켓을 넘겨받아, 그 소켓과 연결된 클라이언트와 끝없는 메시지를 주고받자
public class ServerChatThread extends Thread{
	GUIServer guiserver;
	Socket socket; //서버로부터 넘겨받은 소켓, 스트림을 뽑을 수 있으므로
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public ServerChatThread(GUIServer guiserver, Socket socket) {
		this.guiserver=guiserver;
		this.socket=socket;
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			listen();
		}
	}
	
	// 수신
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine(); // 클라이언트가 전송한 메시지 청취
			guiserver.area.append(msg+"\n");
			
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	// 발신
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

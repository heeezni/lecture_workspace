package com.sinse.networkapp.multicast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/*메시지를 보낼 때만 청취를 하게되면, 실시간으로 서버에서 진행되고 있는 메시지들을
모두 받을 수 없으므로, 유저가 특별한 액션을 취하지 않아도 실시간으로 메세지를 받기 위해
스레드로 정의하고, 특히 listen을 무한루프로 두자!*/
public class ClientChatThread extends Thread{
	Client client; //area등을 보유한 GUI 클래스
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public ClientChatThread(Client client, Socket socket) {
		this.client=client;
		this.socket=socket;
		
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//서버에 메세지 보내기 (사용자가 보내기 원할때. 실시간, 지속적 호출 x)
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버의 메시지를 실시간 청취한다
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine();
			client.area.append(msg+"\n");
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
}

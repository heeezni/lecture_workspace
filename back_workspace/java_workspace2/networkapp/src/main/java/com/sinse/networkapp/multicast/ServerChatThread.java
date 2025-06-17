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
	private boolean isRunning = true;
	
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
		while(isRunning) {
			listen();
		}
	}
	
	// 수신
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine(); // 클라이언트가 전송한 메시지 청취
			guiserver.area.append(msg+"\n");
			
			// 서버에 접속한 모든 유저와 1:1 대응하는 
			//ServerChatThread수만큼 반복하면서 메시지를 보내자
			for(int i=0; i<guiserver.vec.size();i++) {
				//해당 서버 챗 스레드의 send메서드 호출
				ServerChatThread st=guiserver.vec.get(i);
				st.send(msg);				
			}
		} catch (IOException e) {
			/*상대방 클라이언트가 나가버리면 (소켓을 끊어버리면
			 * 나는 더이상 접속자 명단에 들어있으면 안되므로, 나를 제거하자*/
			isRunning=false;
			guiserver.vec.remove(this); //서버스레드인 나를 죽이기 (나는 상대방과 1:1매칭되어있어서)
			guiserver.area.append("현재 접속자"+guiserver.vec.size()+" 명\n");
			System.out.println("어! 누구 나갔다");
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

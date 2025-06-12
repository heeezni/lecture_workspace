package com.sinse.networkapp.unicast;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIServer extends JFrame implements Runnable {
	JPanel p_north;
	JPanel p_south;
	JPanel p_center;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;

	Thread thread; // 서버가동시, 메인스레드가 accept()에서 대기상태에 빠지지 않게 하기 위해
	ServerSocket server;

	public GUIServer() {
		p_north = new JPanel();
		t_port = new JTextField("9999");
		bt = new JButton("서버 가동");
		p_center = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();

		t_port.setPreferredSize(new Dimension(150, 45));
		p_north.setPreferredSize(new Dimension(300, 50));
		scroll.setPreferredSize(new Dimension(300, 400));
		p_south.setPreferredSize(new Dimension(300, 50));

		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		p_center.add(scroll);
		add(p_center);
		add(p_south, BorderLayout.SOUTH);

		bt.addActionListener(e -> {
			thread = new Thread(GUIServer.this); // runnable인 자를 대입
			thread.start();
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0); // 창 닫으면 프로세스 죽이기
			}
		});

		setSize(350, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void run() {
		startServer();
	}

	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			
			while(true) {
				area.append("서버 생성 및 접속자 청취 중....\n");
				Socket socket = server.accept(); // 여기서 대기 상태에 빠지므로, 우리는 스레드로 처리했음
				area.append(socket.getInetAddress().getHostAddress() + " 님 접속 \n");
				
				//접속자 1명당 대화용 스레드인 ServerThread인스턴스를 만들면서 Socket넘기기
				ServerThread st=new ServerThread(this, socket);
			}

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GUIServer();
	}
}

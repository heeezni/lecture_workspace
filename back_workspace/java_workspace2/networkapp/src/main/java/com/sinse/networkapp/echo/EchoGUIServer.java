package com.sinse.networkapp.echo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoGUIServer extends JFrame {
	JPanel p_north;
	JPanel p_south;
	JPanel p_center;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server;
	Thread thread; // 메인스레드가 대기상태에 빠지지 않도록 스레드로 accept 구현

	public EchoGUIServer() {
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
			thread = new Thread() {
				public void run() {
					runServer();
				}
			};
			thread.start(); // 스레드 가동 시작
		});

		setBounds(350, 0, 350, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void runServer() {
		String port = t_port.getText();
		try {
			server = new ServerSocket(Integer.parseInt(port));
			area.append("서버 생성 및 접속자 청취 중...\n");

			Socket socket = server.accept();// 접속자 기다리기 접속 발견시 Socket반환
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip + "님 접속 \n");

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 우리가 실행부라고 알고 있었던 존재가 사실 메인 스레드라 불리는 프로그램 운영 스레드이다 아이폰, 안드로이드 ... 기타 프로그램에서,
	 * 프로그램을 운영하는 메인스레드에게 금기하는 것 1) 무한루프 2) 대기상태 accept(), read() 이런거 메인 스레드를 대기 상태에
	 * 빠뜨리면, 이벤트 감지 불가, GUI 그래픽 처리 불가. 모든게 다 멈춘다
	 */
	public static void main(String[] args) {

		new EchoGUIServer();
	}
}

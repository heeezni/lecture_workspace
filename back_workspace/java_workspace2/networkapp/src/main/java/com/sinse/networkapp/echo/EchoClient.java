package com.sinse.networkapp.echo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame {
	JPanel p_north;
	JPanel p_south;
	JPanel p_center;
	JComboBox cb_ip;
	JTextField t_port;
	JTextField t_input;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	Socket socket; // 대화용 소켓, 이 객체를 메모리에 올릴 때 접속이 발생함
					// 접속이 성공되면, 그 시점부터 연결이 이루어진 것이므로,
					// 스트림을 통해 데이터를 주고받을 수 있음
	BufferedWriter buffw;
	BufferedReader buffr;

	public EchoClient() {
		p_north = new JPanel();
		cb_ip = new JComboBox();
		t_port = new JTextField("9999");
		bt = new JButton("접속");
		p_center = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField();

		t_port.setPreferredSize(new Dimension(150, 45));
		p_north.setPreferredSize(new Dimension(300, 50));
		scroll.setPreferredSize(new Dimension(300, 400));
		t_input.setPreferredSize(new Dimension(300, 20));

		cb_ip.addItem("192.168.60.19");

		p_north.add(cb_ip);
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		p_center.add(scroll);
		add(p_center);
		p_south.add(t_input);
		add(p_south, BorderLayout.SOUTH);

		// 접속버튼과 리스너 연결
		// 람다는 함수형 인터페이스만 가능 (메서드가 하나)
		bt.addActionListener(e -> {
			connect();
		});

		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터쳤을때만
					// 서버로 내보내기 (출력)
					String msg = t_input.getText();
					send(msg); // 사용자가 입력한 메세지를 매개변수로 전달
				}
			}
		});

		setSize(350, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// 실행중인 프로그램에서, 데이터를 내보내야하므로, 필요한 스트림은 바로 출력 스트림
	public void send(String msg) {
		try {
			buffw.write(msg + "\n"); // 서버로 한 줄 보내기 (\n 꼭!!! 넣어줘야함 안그러면 무한대기)
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		// 소켓서버에 접속해보기
		String ip = (String) cb_ip.getSelectedItem();

		// 접속 시도
		try {
			socket = new Socket(ip, Integer.parseInt(t_port.getText()));

			// 소켓으로부터 스트림얻어오자
			// 바이트 기반 출력스트림 (말하기)
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // 3단계 빨대로 업그레이드

			// 바이트 기반 입력스트림(듣기)
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 3단계 빨대로 업그레이드

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new EchoClient();
	}

}

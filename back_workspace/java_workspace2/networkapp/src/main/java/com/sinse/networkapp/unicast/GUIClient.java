package com.sinse.networkapp.unicast;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIClient extends JFrame implements Runnable {
	JPanel p_north;
	JPanel p_south;
	JPanel p_center;
	JComboBox cb_ip;
	JTextField t_port;
	JTextField t_input;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;

	Thread thread; // 접속 지연 시 실행부가 대기상태에 빠질 수 있으므로,
					// 메인스레드로 시도하지말고 별도의 스레드로 진행
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;

	public GUIClient() {
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
		t_input.setPreferredSize(new Dimension(300, 50));

		cb_ip.addItem("192.168.60.5");
		cb_ip.addItem("192.168.60.41");

		p_north.add(cb_ip);
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		p_center.add(scroll);
		add(p_center);
		p_south.add(t_input);
		add(p_south, BorderLayout.SOUTH);

		bt.addActionListener(e -> {
			thread = new Thread(GUIClient.this);
			thread.start();
		});

		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					send(); // 보내고
					t_input.setText("");

					receive(); // 받기
				}
			}

		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setBounds(350, 0, 350, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	// 수신
	public void receive() {
		String msg = null;

		try {
			msg = buffr.readLine();
			area.append(msg + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 발신
	public void send() {
		String msg = t_input.getText();

		try {
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connectServer() {
		try {
			socket = new Socket((String) cb_ip.getSelectedItem(), Integer.parseInt(t_port.getText()));
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		connectServer();
	}

	public static void main(String[] args) {
		new GUIClient();
	}

}

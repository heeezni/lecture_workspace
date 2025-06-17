package com.sinse.networkapp.multicast;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements Runnable{
	JPanel p_north;
	JPanel p_south;
	JPanel p_center;
	JComboBox cb_ip;
	JTextField t_port;
	JTextField t_input;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	Thread thread; // 접속 스레드
	ClientChatThread clientThread; //채팅용 스레드
	
	
	public Client() {
		p_north=new JPanel();
		cb_ip=new JComboBox();
		t_port=new JTextField("9999");
		bt=new JButton("접속");
		p_center=new JPanel();
		area=new JTextArea();
		scroll=new JScrollPane(area);
		p_south=new JPanel();
		t_input=new JTextField();
		thread=new Thread(this); 
		
		t_port.setPreferredSize(new Dimension(100,45));
		p_north.setPreferredSize(new Dimension(300,50));
		scroll.setPreferredSize(new Dimension(300,400));
		p_south.setPreferredSize(new Dimension(300,50));
		t_input.setPreferredSize(new Dimension(300,30));
		
		cb_ip.addItem("192.168.60.14"); //ip 잘 확인하기
		cb_ip.addItem("192.168.60.20"); //강사님 ip
		
		p_north.add(cb_ip);
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		p_center.add(scroll);
		add(p_center);
		p_south.add(t_input);
		add(p_south, BorderLayout.SOUTH);
		
		bt.addActionListener(e->{
			// 서버에 접속 시작
			thread.start();
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					clientThread.send(t_input.getText()); //엔터쳤을 때 보내기
					t_input.setText("");
				}
			}
			
		});
		
		setBounds(350,0,350,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// 접속 : 서버의 ip와 포트번호 이용하여 소켓을 생성하는 것
	public void connectServer() {
		String ip=(String)cb_ip.getSelectedItem();
		int port = Integer.parseInt(t_port.getText());
		
		try {
			Socket socket=new Socket(ip, port);
			//접속 이후부터 채팅은 스레드가 담당하므로, 소켓을 스레드에게 전달해주자!
			clientThread = new ClientChatThread(this, socket);
			clientThread.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		connectServer();
	}
	

	
	
	public static void main(String[] args) {
		new Client();
	}

}

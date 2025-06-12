package com.sinse.networkapp.unicast;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class GUIClient extends JFrame implements Runnable{
	JPanel p_north;
	JPanel p_south;
	JPanel p_center;
	JComboBox cb_ip;
	JTextField t_port;
	JTextField t_input;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	
	Thread thread; //접속 지연 시 실행부가 대기상태에 빠질 수 있으므로, 
						//메인스레드로 시도하지말고 별도의 스레드로 진행
	Socket socket;
	
	public GUIClient() {
		p_north=new JPanel();
		cb_ip=new JComboBox();
		t_port=new JTextField("9999");
		bt=new JButton("접속");
		p_center=new JPanel();
		area=new JTextArea();
		scroll=new JScrollPane(area);
		p_south=new JPanel();
		t_input=new JTextField();
		
		t_port.setPreferredSize(new Dimension(150,45));
		p_north.setPreferredSize(new Dimension(300,50));
		scroll.setPreferredSize(new Dimension(300,400));
		t_input.setPreferredSize(new Dimension(300,50));
		
		cb_ip.addItem("192.168.60.19");
		cb_ip.addItem("192.168.60.41");
		
		p_north.add(cb_ip);
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		p_center.add(scroll);
		add(p_center);
		p_south.add(t_input);
		add(p_south, BorderLayout.SOUTH);
		
		bt.addActionListener(e->{
			thread=new Thread(GUIClient.this);
			thread.start();
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setBounds(350,0,350,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void run() {
		connectServer();
	}
	
	public void connectServer() {
		try {
			socket=new Socket((String)cb_ip.getSelectedItem(), Integer.parseInt(t_port.getText()));
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	public static void main(String[] args) {
		new GUIClient();
	}

}

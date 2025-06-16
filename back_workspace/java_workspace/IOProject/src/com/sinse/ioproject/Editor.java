package com.sinse.ioproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Editor extends JFrame implements ActionListener{
	JMenuBar bar; //눈에 보이지는 않지만 메뉴들을 얹혀놓을 막대기
						//위치가 딱 정해져 있다. 윈도우 창 상단 고정
	JMenu[] menu=new JMenu[5]; //공간만 확보. 메뉴는 아직 X
													//공간엔 JMenu만 허용
	String[] menuTitle= {"파일", "편집", "서식", "보기", "도움말"};
	JMenuItem[] item=new JMenuItem[8];
	String[] itemTitle={"새로 만들기","새 창","열기","저장","다른 이름으로 저장","페이지 설정","인쇄","끝내기"};
	JTextArea area;
	JFileChooser chooser;
	
	//메뉴의 이름이 너무 불편해(배열이라) 직관성을 부여하기 위한 상수 정의
	public static final int FILE=0;
	public static final int EDIT=1;
	public static final int STYLE=2;
	public static final int VIEW=3;
	public static final int HELP=4;
	
	public  Editor() {
		bar= new JMenuBar();
		for(int i=0; i<menu.length; i++) {
			menu[i]=new JMenu(menuTitle[i]);
		}
		for(int i=0; i<item.length; i++) {
			item[i]=new JMenuItem(itemTitle[i]);
		}
		
		bar.setBackground(Color.PINK);
		
		//메뉴들 조립 (메뉴 아이템을 파일메뉴에 부착)
		for(int i=0; i<item.length; i++) {
			menu[FILE].add(item[i]);
			if(i==4 || i==6)menu[FILE].addSeparator();
		}
		area=new JTextArea();
		chooser = new JFileChooser("C:\\lecture_workspace\\back_workspace\\java_workspace");
		
		//메뉴바에 메뉴 부착
		for (int i=0; i<menu.length; i++) {
			bar.add(menu[i]);
		}
		
		//바 부착
		this.setJMenuBar(bar);
		add(area);
		
		//이벤트 연결
		item[item.length -1].addActionListener(this); //exit 버튼
		item[2].addActionListener(this); //열기 버튼
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0,200,800,600);
		setVisible(true);
	}
	public void openFile() {
		//어떤 파일을 대상으로 열 지는 개발자가 아니라 사용자가 결정
		//새창을 띄워주자
		File file=null; //if문 안에 넣어버리면 아래의 fis가 못쓰니까
		int result = chooser.showOpenDialog(this);
		if(result==JFileChooser.APPROVE_OPTION) { //열기 누르면
			//유저가 선택한 파일을 얻어와서 스트림을 생성하자
			file= chooser.getSelectedFile();
		} else {
			return; // 취소했으면 아무 일도 하지 않고 return
		}
		
		FileInputStream fis=null; //파일을 대상으로 한 입력 스트림
		try {
			fis=new FileInputStream(file);
			//파일의 끝까지 1바이트씩 읽어가면서  area에 추가
			int data=-1;
			
			while(true) {
				data=fis.read();
				if(data==-1)break;
				area.append(Character.toString((char)data));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis!=null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==item[item.length-1]) {
			if(JOptionPane.showConfirmDialog(this, "프로그램을 종료하시겠습니까?")==JOptionPane.OK_OPTION) {
				System.exit(0); //프로그래밍적으로 프로세스 종료
			}
		}else if(e.getSource()==item[2]) {
			openFile();
			
		}
	}

	public static void main(String[] args) {
		new Editor();
	}
}

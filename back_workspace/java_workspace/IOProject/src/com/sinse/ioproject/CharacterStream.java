package com.sinse.ioproject;
/*
 * 스트림의 분류
 * 1) 방향 
 * 	-입력
 * 	-출력
 * 2) 데이터 처리방법
 * 	-바이트(근본) : 1byte씩
 * 	-문자 : 1문자씩 (문자 이해 스트림)
 * 	-버퍼기반
 * */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CharacterStream {
	//바이트 기반 스트림의 특징 : 입력 ~~InputStream, 출력 ~~OutputStream
	//문자 기반 스트림 : 입력 ~~Reader,  출력 ~~Writer
	
	FileInputStream fis; //파일을 대상으로 한 바이트 기반의 입력 스트림
	FileOutputStream fos; //파일을 대상으로 한 바이트 기반의 출력 스트림
	//복사에 용이
	InputStreamReader is; //기존에 이미 존재하는 바이트 기반의 입력 스트림을 업그레이드(덧붙여사용 빨대 바깥에 더 두꺼운 빨대)
	OutputStreamWriter os; //기존에 이미 존재하는 바이트 기반의 출력 스트림에 덧붙여 사용
	//결론: 모든 스트림의 기반 스트림은 '바이트 기반' 스트림이다
	//바이트 기반의 스트림만 존재한다면 얼마든지, 문자 기반으로 업그레이드 할 수 있음
	
	//아래 두 클래스는 파일에 국한되므로, 파일관련된 작업에만 유용해서 범용성 떨어짐
	FileReader reader; //파일을 대상으로 한 문자 기반 입력 스트림
	//문자 안 깨짐. 1byte씩 읽어들이는 것이 아니라, 한 문자씩 읽음
	FileWriter writer;

	
	String name="C:/lecture_workspace/back_workspace/java_workspace/IOProject/res/memo.txt";
	String name2="C:/lecture_workspace/back_workspace/java_workspace/IOProject/res/memo_copy.txt";
	
	public CharacterStream() {
		try{	
			//실행 중인 프로그램으로 메모장을 구성하는 데이터를 입력해보자
			fis = new FileInputStream(name);
			fos= new FileOutputStream(name2);
			
			is = new InputStreamReader(fis); //빨대가 2단계로 업그레이드 
			os = new OutputStreamWriter(fos); //빨대가 2단계로 업그레이드
			
			int data=-1;
			
			while(true) {
				data=is.read(); //한 알갱이 → 한 문자(1byte)
				if(data==-1)break;
				//읽어마신 데이터를 다시 내뱉자. 빈 파일을 대상으로
				os.write(data);
				System.out.print((char)data);
			}	
		}catch(FileNotFoundException e) {
			System.out.println("파일 못 찾겠음");
		}catch(IOException e) {
			System.out.println("입출력 오류");
		} finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
			if(os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
		}
	}
	
	public static void main(String[] args) {
		new CharacterStream();
	}
}

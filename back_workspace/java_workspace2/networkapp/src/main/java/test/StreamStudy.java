package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StreamStudy {
	String path = "C://public/data.txt";

	// 바이트 기반의 스트림으로 읽기
	public void readByte() {

		FileInputStream fis = null;

		// 문서파일을 읽고 출력
		try {
			fis = new FileInputStream(path); // 파일 열기

			while (true) {
				int data = fis.read(); // 1byte 읽기
				if (data == -1)
					break;
				System.out.print((char) data); // 숫자 그대로 출력 (char로 바꾸려면 (char)data)
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close(); // 자원 해제
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 문자 기반의 스트림으로 읽기
	public void readReader() {
		FileInputStream fis = null;
		InputStreamReader reader = null;

		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);

			// 주의) 문자 기반 스트림은 데이터를 읽어들일 때, 1문자씩 읽어들임 (2byte씩 읽는 거아님)
			// 그냥 2byte를 묶어서 문자로 해석할 수 있는 능력이 있는 것 뿐
			while (true) {
				int data = -1;
				data = reader.read();
				if (data == -1)
					break;
				System.out.print((char) data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	// 버퍼 기반의 스트림으로 읽기
	public void readBuffer() {
		FileInputStream fis = null; // 영문이 깨지지 않는 스트림
		InputStreamReader reader = null; // 한글까지 깨지지 않는 스트림
		BufferedReader buffr = null; // 한글까지 깨지지 않으면서, 1자씩 읽는 것이 아니라
										// 줄바꿈 문자 만날 때까지 버퍼에 문자 모음 (문자열화)

		try {
			buffr = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

			while (true) {
				String data = null;
				data = buffr.readLine(); // read아니고 readLine
				if (data == null) break;
				System.out.println(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffr != null)
				try {
					buffr.close(); //최상위스트림만 닫으면 됨
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) {
		StreamStudy ss = new StreamStudy();
		// ss.readByte();
		// ss.readReader();
		ss.readBuffer();
	}
}

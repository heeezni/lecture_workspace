/*실행중인 Java프로그램에서 디스크의 파일에 접근+데이터를 읽고
프로그램으로 불러들여 출력해보자*/
package gui.io;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileLoader{
	public static void main(String[] args) {
		//실행중인 프로그램이 파일 등의 자원의 데이터를 읽어들이기 위해서는 
		//**스트림 객체**가 필요하다
		/*
		Stream: 현실에서 물 줄기/물의 흐름 의미
					프로그램에서는 그 대상이 물이 아닌 데이터
		흐름의 방향은 2가지 유형이 있음
		IO(입출력)
		1) 실행 중인 프로그램으로 들어오는 흐름 Input(입력)
		2) 실행 중인 프로그램에서 데이터가 나가면 Output(출력)
		*/
		
		//파일을 대상으로 한 입력 객체(파일을 읽어들일 수 있는 객체)
		String name="C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/memo.txt";
		//디스크에 있는 파일에 스트림이 생성되는 순간
		
		/*FileNotFoundException 
		문법 상 문제없지만, 프로그램 코드 상의 문제 외의 문제때문에 프로그램이 정상 수행이 될 수 없는 상황
		이 상황을 방지하기 위해 컴파일러 차원에서 컴파일 거부 중*/
		FileInputStream fis=null; //지역변수는 반드시 초기화!
		try{ //우려 발생될 가능있다
			fis=new FileInputStream(name);
			int data;
			while(true){ //무한루프
				data=fis.read(); //1byte 읽어들임
				if(data==-1) break; //데이터가 -1이라면 멈춰
				System.out.print((char)data);
			}
		}catch(FileNotFoundException e){ //정상 수행 유도
			//catch문의 소괄호 안에 에러의 원인이 되는 객체의 인스턴스를 생성하여 전달해준다
			//에러의 원인 객체라서 → 개발자가 추출해서 분석한다거나 자세한 에러 파악이 가능하도록
			
			//만약 파일이 없다면, 파일 복구X 
			//원인 등을 알려주거나, 로그를 남기는 등의 처리
			System.out.println("파일을 찾을 수 없습니다.");
		}catch(IOException e){ //다중 catch문
			System.out.println("입출력 도중 에러가 발생했습니다.");
		}finally{
			//실행부가 try문을 수행하던, catch문을 수행하던 (어느 쪽을 수행하던) 반드시 거쳐나가는 영역
			//성공 시 try → finally, 실패 시 catch → finally	
			if(fis !=null){//메모리 올라왔을 때만 닫겠다
				try{
					//DB와 스트림은 반드시 닫아야 한다!
					fis.close(); //스트림 닫기
				}catch(IOException e){
					//일반 유저가 아닌 개발자를 위한 로그 출력
					e.printStackTrace(); //에러의 원인을 알 수 있는 메서드
				}
			}
		}	
	}
}

/*메모장파일 읽어서 콘솔창에 출력하기*/

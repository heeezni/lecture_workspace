package table;

import java.lang.reflect.Constructor;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/*
 * JTable은 개발분야에서 전해내려오는 많이 알려진 개발 방법(=패턴) 중 하나인 MVC 패턴을 구현한 컴포넌트
 * 완벽하지는 않음 →  M과 C역할을 동시에 수행함
 * 데이터를 보유할 뿐만 아니라, 그 데이터를 디자인 영역에 반영하는 코드도 포함되어 있기 때문
 * 결론) 중요한 것은 JTable과 데이터를 분리시켜놓은 기술임 
 * */

/*TableModelListener: 모델이 보유한 데이터를 유저가 수정할 때 발생되는 이벤트를 감지하는 리스너*/
public class MyModel extends AbstractTableModel implements TableModelListener{
	
	//회원정보 (층, 호를 표현하기 위한 이차원배열 형태의 데이터가 필요)
	String[][] rows=new String[0][4]; //데이터 수용 능력은 4로 늘리기(pk포함)
	String[] columns= {"ID", "Name", "Tel"}; //컬럼 표현은 그대로(pk안보여주기 위해)
	
	MemberRegist memberRegist; //제어가 필요해서 주소값 보유위한 has a 관계 선언
	
	public MyModel(MemberRegist memberRegist) {
		this.memberRegist=memberRegist; //생성자 주입을 이용한 멤버변수 대입
		
		//모델과 리스너 연결
		this.addTableModelListener(this); //나의 레코드가 변경될 때 그것을 감지하겠다
		
	}
	
	@Override
	//테이블에 보여질 총 레코드 수
	public int getRowCount() {
		return rows.length;
	}
	@Override
	//테이블을 구성하는 컬럼수 
	public int getColumnCount() {
	    return columns.length;
	}
	
	@Override
	//컬럼의 이름 반환해주는 메서드
	//아래의 메서드는 컬럼 수만큼 반복하면서 호출되는데, 이때 매개변수로 넘겨받는 col의 값은 자동증가하면서 전달됨
	public String getColumnName(int col) {
		return columns[col];
	}

	//getValueAt메서드는 getRowCount()*getColumnCount() 수만큼 호출하면서
	//표를 이루는 각 셀 (행,열) 좌표마다 어떠한 값을 넣을 지 return이 결정한다.
	public Object getValueAt(int row, int col) {
		//System.out.println("row="+row+", column="+col);
		return rows[row][col];
	}
	
	//유저가 테이블 셀에서 아무리 데이터를 편집한다 하더라도, 현재 모델이 보유한 이차원 배열을 수정하지 않는 한
	//값 수정 반영이 되지 않는다 따라서 값 변경을 위한 setter 필요
	//셀에서 원하는 데이터 K를 입력하고 엔터를 치는 순간, 해당 셀의 row와 col, k값이 전달됨
	public void setValueAt(Object value, int row, int col) {//무엇을 몇층 몇호수에?
		System.out.println("당신은 "+row+", "+col+"의 데이터를 "+value+"(으)로 변경하고싶나요?");
		
		//모델이 이차원 배열에 반영하기
		rows[row][col]=(String)value;
		
		//프레임이 보유한 edit()를 호출하려면?
		memberRegist.edit(rows[row]); //데이터 베이스도 수정되어야 하니까
	}
	
	@Override
	//지정한 셀을 편집할 수 있게
	public boolean isCellEditable(int row, int col) {
		System.out.println(row+"행 , "+col+"열은 수정 가능합니다.");
		/*if(col==0) {
			return false;
		}else {
			return true;
		}*/
		return (col==0)? false : true; //아이디만 수정 불가하게 (0열만 수정불가)
	}
	
	
	@Override
	//테이블의 한 셀을 수정 후 엔터 치는 순간 이 메서드 호출됨
	public void tableChanged(TableModelEvent e) {
		System.out.println("편집했어?");
	}
}

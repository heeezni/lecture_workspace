package table;

import javax.swing.table.AbstractTableModel;

/*
 * JTable은 개발분야에서 전해내려오는 많이 알려진 개발 방법(=패턴) 중 하나인 MVC 패턴을 구현한 컴포넌트
 * 완벽하지는 않음 →  M과 C역할을 동시에 수행함
 * 데이터를 보유할 뿐만 아니라, 그 데이터를 디자인 영역에 반영하는 코드도 포함되어 있기 때문
 * 결론) 중요한 것은 JTable과 데이터를 분리시켜놓은 기술임 
 * */


public class MyModel extends AbstractTableModel{
	
	//회원정보 (층, 호를 표현하기 위한 이차원배열 형태의 데이터가 필요)
	String[][] rows=new String[0][3];
	String[] columns= {"ID", "Name", "Tel"};

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
	
	@Override
	//지정한 셀을 편집할 수 있는지 없는지
	public boolean isCellEditable(int row, int col) {
		System.out.println(row+"행 , "+col+"열은 수정 가능합니다.");
		return true;
	}
	
}

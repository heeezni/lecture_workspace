package gui.font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gui.font.FontInfo;
import java.awt.Font;

public class FontSet extends Frame implements ActionListener{
	TextArea area;
	Panel p_south;
	Button bt;
	FontInfo fontInfo=null;
	
	public FontSet(){
	area=new TextArea();
	p_south=new Panel();
	bt=new Button("서식");
	
	area.setBackground(Color.YELLOW);
	
	add(area);
	p_south.add(bt);
	add(p_south, BorderLayout.SOUTH);
	
	bt.addActionListener(this);
	
	setSize(300,400);
	setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent e){
		fontInfo=new FontInfo(this);
		Font f=area.getFont(); //폰트알아보기
		fontInfo.t_fontSize.setText("폰트 사이즈는 "+f.getSize());
		//fontInfo.t_fontColor.setText("폰트 색상은 "+);
	}
	
	public static void main(String[] args) {
		new FontSet();
}

}

//java.awt.Font[family=Dialog,name=Dialog,style=plain,size=12]

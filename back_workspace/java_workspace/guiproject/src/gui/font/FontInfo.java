package gui.font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Font;

public class FontInfo extends Frame implements ActionListener{
	TextField t_fontSize;
	TextField t_fontColor;
	Panel p_center;
	Panel p_south;
	Button bt;
	FontSet fontSet=null;
	
	public FontInfo(FontSet fontSet){
		t_fontSize=new TextField(30);
		t_fontColor=new TextField(30);
		p_center=new Panel();
		p_south=new Panel();
		bt=new Button("적용");
		
		this.fontSet=fontSet;
		
		Dimension d=new Dimension(110, 80);
		t_fontSize.setPreferredSize(d);
		t_fontColor.setPreferredSize(d);
		
		p_center.add(t_fontSize);
		p_center.add(t_fontColor);
		add(p_center);
		p_south.add(bt);
		add(p_south, BorderLayout.SOUTH);
		
		bt.addActionListener(this);
		
		setSize(300,400);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		//fontSet.area.setFont(Font f); //폰트 설정
	}
}

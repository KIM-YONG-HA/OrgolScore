
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Test {

	
	public Test() {
		
		JFrame frame = new JFrame();
		
		
		frame.setSize(300,300);
		//frame.setLayout(new GridLayout());
		frame.add(new JButton("test1"));
		
		
		Container c = frame.getContentPane();
		c.setLayout(new GridLayout(2,2));
		c.add(new JButton("test2"));
		c.add(new JButton("test3"));
		c.setLayout(new FlowLayout());
		c.add(new JButton("test4"));
		
		
		//frame.add(contentPane);
		frame.setVisible(true);
		
		
		
	}
	
	
	public static void main(String[] args) {

		new Test();
		
		

	}

}

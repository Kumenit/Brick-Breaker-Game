package brick;

import javax.swing.JFrame;

public class fream {
	public fream()
	{
	JFrame fr=new JFrame();
	fr.add(new panel());
	fr.setTitle("Brick");
	fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fr.setResizable(true);
	fr.pack();
	fr.setVisible(true);
	fr.setLocationRelativeTo(null);
	}
}


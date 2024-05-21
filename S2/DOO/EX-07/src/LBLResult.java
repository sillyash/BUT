import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

public class LBLResult extends JLabel {

	public LBLResult() {
		super();
		Initialize();
	}

	public LBLResult(String text) {
		super();
		setText(text);
		Initialize();
	}

	public LBLResult(Icon image) {
		super(image);
		Initialize();
	}

	public LBLResult(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		Initialize();
	}

	public LBLResult(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		Initialize();
	}

	public LBLResult(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		Initialize();
	}
	
	public void Initialize() {
		setBackground(Color.black);
		setOpaque(true);
		setForeground(Color.green);
		setHorizontalAlignment(JLabel.LEFT);
		setFont(new Font("Courier",Font.BOLD,48));
	}

}

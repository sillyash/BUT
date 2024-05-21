import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class PanelCalcNumbers extends JPanel {
	
	private static final long serialVersionUID = 6952862995164344334L;
	protected ArrayList<JButton> BTNNums;
	protected JButton BTNPlus;
	protected JButton BTNMinus;
	protected JButton BTNMult;
	protected JButton BTNDiv;
	protected JButton BTNEqual;
	protected JButton BTNClear;
	protected GridLayout grid;
	
	public PanelCalcNumbers() {
		Initialize();
	}

	public PanelCalcNumbers(LayoutManager layout) {
		super(layout);
		Initialize();
	}

	public PanelCalcNumbers(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		Initialize();
	}

	public PanelCalcNumbers(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		Initialize();
	}
	
	private void Initialize() {
		grid = new GridLayout(4,4);
		
		BTNPlus = new JButton("+");
		BTNMinus = new JButton("-");
		BTNMult = new JButton("x");
		BTNDiv = new JButton("/");
		BTNEqual = new JButton("=");
		BTNClear = new JButton("C/CE");
		
		BTNNums = new ArrayList<JButton>();
		for (int i=0; i<10; i++) {
			BTNNums.add(new JButton(String.valueOf(i)));
		}
		
		SetStyle();
		AddElementsToSelf();
		SetButtonActions();
		
		this.setLayout(grid);
		this.setVisible(true);
	}
	
	private void SetStyle() {
		for (JButton btn : BTNNums) {
			btn.setFont(new Font("Courier",Font.BOLD, 24));
			btn.setBorder(BorderFactory.createLineBorder(Color.pink));
		}
		BTNPlus.setFont(new Font("Courier",Font.BOLD, 24));
		BTNMinus.setFont(new Font("Courier",Font.BOLD, 24));
		BTNMult.setFont(new Font("Courier",Font.BOLD, 24));
		BTNDiv.setFont(new Font("Courier",Font.BOLD, 24));
		
		BTNEqual.setFont(new Font("Courier",Font.BOLD, 24));
		BTNEqual.setBorder(BorderFactory.createLineBorder(Color.magenta));
		BTNClear.setFont(new Font("Courier",Font.BOLD, 24));
		BTNClear.setBorder(BorderFactory.createLineBorder(Color.magenta));
	}
	
	private void AddElementsToSelf() {
		add(BTNNums.get(1));
		add(BTNNums.get(2));
		add(BTNNums.get(3));
		add(BTNPlus);
		
		add(BTNNums.get(4));
		add(BTNNums.get(5));
		add(BTNNums.get(6));
		add(BTNMinus);
		
		add(BTNNums.get(7));
		add(BTNNums.get(8));
		add(BTNNums.get(9));
		add(BTNMult);
		
		add(BTNClear);
		add(BTNNums.get(0));
		add(BTNEqual);
		add(BTNDiv);
	}
	
	private void SetButtonActions() {
		
	}

}

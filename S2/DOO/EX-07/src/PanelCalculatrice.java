import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class PanelCalculatrice extends JPanel {
	
	protected JLabel LBLResult;
	protected ArrayList<JButton> BTNNums;
	protected JButton BTNPlus;
	protected JButton BTNMinus;
	protected JButton BTNMult;
	protected JButton BTNDiv;
	protected JButton BTNEqual;
	protected JButton BTNClear;
	protected GridLayout Grid;
	protected LayoutManager LytMgr;
	
	public PanelCalculatrice() {
		InitializeElements();
		SetStyle();
		SetLayout();
		AddElementsToSelf();
		SetButtonActions();
		this.setVisible(true);
	}

	public PanelCalculatrice(LayoutManager layout) {
		super(layout);
		InitializeElements();
		SetStyle();
		SetLayout();
		AddElementsToSelf();
		SetButtonActions();
		this.setVisible(true);;
	}

	public PanelCalculatrice(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		InitializeElements();
		SetStyle();
		SetLayout();
		AddElementsToSelf();
		SetButtonActions();
		this.setVisible(true);
	}

	public PanelCalculatrice(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		InitializeElements();
		SetStyle();
		SetLayout();
		AddElementsToSelf();
		SetButtonActions();
		this.setVisible(true);
	}
	
	private void InitializeElements() {
		InitializeLayout();
		LBLResult = new JLabel("0");
		BTNPlus = new JButton("+");
		BTNMinus = new JButton("-");
		BTNMult = new JButton("x");
		BTNDiv = new JButton("/");
		BTNEqual = new JButton("=");
		BTNClear = new JButton("C/CE");
		Grid = new GridLayout();
		
		BTNNums = new ArrayList<JButton>();
		for (int i=0; i<10; i++) {
			BTNNums.add(new JButton(String.valueOf(i)));
		}
	}
	
	private void InitializeLayout() {
		LytMgr = new LayoutManager() {
			@Override
			public void addLayoutComponent(String name, Component comp) {
				// TODO aaaaa
			}

			@Override
			public void removeLayoutComponent(Component comp) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Dimension preferredLayoutSize(Container parent) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Dimension minimumLayoutSize(Container parent) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void layoutContainer(Container parent) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	private void SetStyle() {
		LBLResult.setBackground(Color.black);
		LBLResult.setOpaque(true);
		LBLResult.setForeground(Color.green);
		LBLResult.setHorizontalAlignment(JLabel.RIGHT);
		LBLResult.setFont(new Font("Courier",Font.BOLD,48));
		
		for (JButton btn : BTNNums) {
			btn.setFont(new Font("Courier",Font.BOLD, 24));
			btn.setBorder(BorderFactory.createLineBorder(Color.pink));
		}
		BTNPlus.setFont(new Font("Courier",Font.BOLD, 24));
		BTNMinus.setFont(new Font("Courier",Font.BOLD, 24));
		BTNMult.setFont(new Font("Courier",Font.BOLD, 24));
		BTNDiv.setFont(new Font("Courier",Font.BOLD, 24));
		BTNEqual.setFont(new Font("Courier",Font.BOLD, 24));
		BTNClear.setFont(new Font("Courier",Font.BOLD, 24));
	}
	
	private void SetLayout() {
		
	}
	
	private void AddElementsToSelf() {
		this.add(LBLResult);
		for (JButton btn : BTNNums) {
			this.add(btn);
		}
		this.add(BTNPlus);
		this.add(BTNMinus);
		this.add(BTNEqual);
		this.add(BTNClear);
		this.add(BTNDiv);
		this.add(BTNMult);
	}
	
	private void SetButtonActions() {
		
	}
	
	public static void main(String args[]) {
		JFrame window = new JFrame("Calculatrice");
		
		window.setBounds(500, 300, 450, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(new PanelCalculatrice());
		window.setVisible(true);
		
	}

}

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameCalculatrice extends JFrame {
	
	protected static final int X = 300;
	protected static final int Y = 300;
	protected static final int HEIGTH = 700;
	protected static final int WIDTH = 500;
	protected JPanel buttons;
	protected JLabel calcScreen;
	protected GridLayout grid;
	
	// ---------- Constructors ----------

	public FrameCalculatrice() throws HeadlessException {
		super();
		Initialize();
	}

	public FrameCalculatrice(GraphicsConfiguration gc) {
		super(gc);
		Initialize();
	}

	public FrameCalculatrice(String title) throws HeadlessException {
		super(title);
		Initialize();
	}

	public FrameCalculatrice(String title, GraphicsConfiguration gc) {
		super(title, gc);
		Initialize();
	}
	
	// ---------- Methods ----------

	public void Initialize() {
		this.grid = new GridLayout(2,1);
		
		this.setLocation(X, Y);
		this.setMinimumSize(new Dimension(400, 500));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Calculatrice");
		this.setLayout(grid);
		
		addElementsToSelf();
		
		this.pack();
		this.validate();
		
		this.setVisible(true);
	}
	
	public void addElementsToSelf() {
		this.buttons = new PanelCalcNumbers();
		this.calcScreen = new LBLResult("0");
		
		this.getContentPane().add(calcScreen);
		this.getContentPane().add(buttons);
	}
	
	public static void main(String[] args) {
		new FrameCalculatrice();
	}

}

import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelPerimetre extends JPanel {
	JButton BTNCalculer;
	JButton BTNNettoyer;
	JLabel LBLLongueur;
	JLabel LBLLargeur;
	JLabel LBLResultat;
	JTextField TXFLongueur;
	JTextField TXFLargeur;
	
	public PanelPerimetre() {
		BTNCalculer = new JButton("Calculer");
		BTNNettoyer = new JButton("Nettoyer");
		LBLLongueur = new JLabel("Longueur :   ");
		LBLLargeur = new JLabel ("Largeur  :   ");
		LBLResultat = new JLabel("Résultat :   ");
		TXFLongueur = new JTextField(15);
		TXFLargeur = new JTextField(15);
		
		TXFLongueur.setHorizontalAlignment(JTextField.CENTER);
		TXFLargeur.setHorizontalAlignment(JTextField.CENTER);
		
		this.add(LBLLongueur);
		this.add(TXFLongueur);
		
		this.add(LBLLargeur);
		this.add(TXFLargeur);
		
		this.add(LBLResultat);
		
		this.add(BTNNettoyer);
		this.add(BTNCalculer);
		
		this.setVisible(true);
	}

	public PanelPerimetre(LayoutManager layout) {
		super(layout);
		BTNCalculer = new JButton("Calculer");
		BTNNettoyer = new JButton("Nettoyer");
		LBLLongueur = new JLabel("Longueur :   ");
		LBLLargeur = new JLabel ("Largeur  :   ");
		LBLResultat = new JLabel("Résultat :   ");
		TXFLongueur = new JTextField(15);
		TXFLargeur = new JTextField(15);
		
		TXFLongueur.setHorizontalAlignment(JTextField.CENTER);
		TXFLargeur.setHorizontalAlignment(JTextField.CENTER);
		
		this.add(LBLLongueur);
		this.add(TXFLongueur);
		
		this.add(LBLLargeur);
		this.add(TXFLargeur);
		
		this.add(LBLResultat);
		
		this.add(BTNNettoyer);
		this.add(BTNCalculer);
		
		this.setVisible(true);
	}

	public PanelPerimetre(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		BTNCalculer = new JButton("Calculer");
		BTNNettoyer = new JButton("Nettoyer");
		LBLLongueur = new JLabel("Longueur :   ");
		LBLLargeur = new JLabel ("Largeur  :   ");
		LBLResultat = new JLabel("Résultat :   ");
		TXFLongueur = new JTextField(15);
		TXFLargeur = new JTextField(15);
		
		TXFLongueur.setHorizontalAlignment(JTextField.CENTER);
		TXFLargeur.setHorizontalAlignment(JTextField.CENTER);
		
		this.add(LBLLongueur);
		this.add(TXFLongueur);
		
		this.add(LBLLargeur);
		this.add(TXFLargeur);
		
		this.add(LBLResultat);
		
		this.add(BTNNettoyer);
		this.add(BTNCalculer);
		
		this.setVisible(true);
	}

	public PanelPerimetre(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		BTNCalculer = new JButton("Calculer");
		BTNNettoyer = new JButton("Nettoyer");
		LBLLongueur = new JLabel("Longueur :   ");
		LBLLargeur = new JLabel ("Largeur  :   ");
		LBLResultat = new JLabel("Résultat :   ");
		TXFLongueur = new JTextField(15);
		TXFLargeur = new JTextField(15);
		
		TXFLongueur.setHorizontalAlignment(JTextField.CENTER);
		TXFLargeur.setHorizontalAlignment(JTextField.CENTER);
		
		this.add(LBLLongueur);
		this.add(TXFLongueur);
		
		this.add(LBLLargeur);
		this.add(TXFLargeur);
		
		this.add(LBLResultat);
		
		this.add(BTNNettoyer);
		this.add(BTNCalculer);
		
		this.setVisible(true);
	}
	
	public static void main(String args[])
	{
		JFrame window = new JFrame("Périmètre");
		
		window.setBounds(750, 500, 280, 120);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(new PanelPerimetre());
		window.setVisible(true);
	}

}

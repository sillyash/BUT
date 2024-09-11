import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelPerimetre extends JPanel {
	protected JButton BTNCalculer;
	protected JButton BTNNettoyer;
	protected JLabel LBLLongueur;
	protected JLabel LBLLargeur;
	protected JLabel LBLResultat;
	protected JTextField TXFLongueur;
	protected JTextField TXFLargeur;
	
	public PanelPerimetre() {
		InitializeElements();
		AddElementsToSelf();
		SetButtonActions();
		this.setVisible(true);
	}

	public PanelPerimetre(LayoutManager layout) {
		this();
	}

	public PanelPerimetre(boolean isDoubleBuffered) {
		this();
	}

	public PanelPerimetre(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		InitializeElements();
		AddElementsToSelf();
		SetButtonActions();
		this.setVisible(true);
	}
	
	private void InitializeElements() {
		BTNCalculer = new JButton("Calculer");
		BTNNettoyer = new JButton("Nettoyer");
		LBLLongueur = new JLabel("Longueur :   ");
		LBLLargeur = new JLabel ("Largeur  :   ");
		LBLResultat = new JLabel("Résultat :      ");
		TXFLongueur = new JTextField(15);
		TXFLargeur = new JTextField(15);
		TXFLongueur.setHorizontalAlignment(JTextField.CENTER);
		TXFLargeur.setHorizontalAlignment(JTextField.CENTER);
	}
	
	private void ClearFields() {
		TXFLongueur.setText("");
		TXFLargeur.setText("");
		LBLResultat.setText("Résultat :      ");
	}
	
	private void AddElementsToSelf() {
		this.add(LBLLongueur);
		this.add(TXFLongueur);
		this.add(LBLLargeur);
		this.add(TXFLargeur);
		this.add(LBLResultat);
		this.add(BTNNettoyer);
		this.add(BTNCalculer);
	}
	
	private boolean IsNumeric(String str) {
		if (str == "" || str == null) return false;
		else try {
			@SuppressWarnings("unused")
			Double dbl = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
	        return false;
	    }
		return true;
	}
	
	private boolean CheckTXFValues() {
		return (IsNumeric(TXFLargeur.getText()) && IsNumeric(TXFLongueur.getText()));
	}
	
	private void SetButtonActions() {
		BTNCalculer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!CheckTXFValues()) return;
				
				Double largeur = Double.parseDouble(TXFLargeur.getText());
				Double longueur = Double.parseDouble(TXFLongueur.getText());
				
				LBLResultat.setText("Résultat : " + largeur*longueur);
			}
		});
		
		BTNNettoyer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClearFields();
			}
		});
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

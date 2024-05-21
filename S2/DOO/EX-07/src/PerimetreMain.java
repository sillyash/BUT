import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PerimetreMain {
	
	public static boolean IsNumeric(String str) {
		if (str == "" || str == null) return false;
		else try {
			Double dbl = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
	        return false;
	    }
		return true;
	}
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Périmètre");
		window.setBounds(750, 500, 280, 120);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		window.add(panel);
		
		JButton BTNCalculer = new JButton("Calculer");
		JButton BTNNettoyer = new JButton("Nettoyer");
		
		JLabel LBLLongueur = new JLabel("Longueur :   ");
		JLabel LBLLargeur = new JLabel ("Largeur  :   ");
		JLabel LBLResultat = new JLabel("Résultat :   ");
		
		JTextField TXFLongueur = new JTextField(15);
		JTextField TXFLargeur = new JTextField(15);
		
		BTNCalculer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(IsNumeric(TXFLargeur.getText()) && IsNumeric(TXFLongueur.getText()))) return;
				
				Double largeur = Double.parseDouble(TXFLargeur.getText());
				Double longueur = Double.parseDouble(TXFLongueur.getText());
				
				LBLResultat.setText("Résultat : " + largeur*longueur);
			}
		});
		
		BTNNettoyer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TXFLongueur.setText("");
				TXFLargeur.setText("");
				LBLResultat.setText("Résultat :      ");
			}
		});
		
		TXFLongueur.setHorizontalAlignment(JTextField.CENTER);
		TXFLargeur.setHorizontalAlignment(JTextField.CENTER);
		
		panel.add(LBLLongueur);
		panel.add(TXFLongueur);
		
		panel.add(LBLLargeur);
		panel.add(TXFLargeur);
		
		panel.add(LBLResultat);
		
		panel.add(BTNNettoyer);
		panel.add(BTNCalculer);
		
		panel.setVisible(true);
		window.setVisible(true);
	}
}

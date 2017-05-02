package graphique;

import java.awt.Color;

import javax.swing.JList;

import modele.bateau.Bateau;

public class ListeBateau extends JList<Bateau> {
	
	public ListeBateau()
	{
		super();
		this.setCellRenderer(new ListeBateauComponent());
		this.setBackground(Color.LIGHT_GRAY);
	}
}

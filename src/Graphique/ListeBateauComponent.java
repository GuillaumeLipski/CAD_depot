package graphique;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import modele.bateau.Bateau;

public class ListeBateauComponent implements ListCellRenderer<Bateau> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Bateau> list, Bateau value, int index,
			boolean isSelected, boolean cellHasFocus) {

	     String s = value.toString();
	     JLabel label = new JLabel(s);
	     label.setOpaque(true);

	     if(value.estDetruit()) {
	    	 label.setForeground(new Color(220,20,20));
	     } else {
	    	 label.setForeground(new Color(0,125,125));
	     }

	     label.setBackground(Color.LIGHT_GRAY);
	     return label;
	}

}

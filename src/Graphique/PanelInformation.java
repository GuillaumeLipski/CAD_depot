package Graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelInformation extends JPanel {
	public PanelInformation()
	{
		super();
		this.setBackground(Color.WHITE);
		this.setMinimumSize(new Dimension(100,600));
		this.setLayout(new GridLayout(10,10,2,2));

	}
}

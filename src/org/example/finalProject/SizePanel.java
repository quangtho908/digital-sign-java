package org.example.finalProject;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class SizePanel extends JPanel {
	public JComboBox<Integer> keySizeSelection;

	private static final long serialVersionUID = 1L;
	
	public SizePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel keySizeLabel = new JLabel("Key size");
		add(keySizeLabel, BorderLayout.NORTH);
		
		keySizeSelection = new JComboBox<Integer>();
		add(keySizeSelection, BorderLayout.CENTER);
		
		keySizeSelection.addActionListener((ActionEvent e) -> {
			Store.keySize = (int) keySizeSelection.getSelectedItem();
		});;

	}

}

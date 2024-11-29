package org.example.finalProject;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class SignPanel extends JPanel {
	
	public JComboBox<String> signSelection;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SignPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel SignLabel = new JLabel("Signature");
		add(SignLabel, BorderLayout.NORTH);
		
		signSelection = new JComboBox<String>();
		add(signSelection, BorderLayout.CENTER);
		
		signSelection.addActionListener((ActionEvent e) -> {
			Store.signature = (String) signSelection.getSelectedItem();
		});

	}

}

package org.example.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FileChoosen extends JDialog {
	
	public String file;
	public JFileChooser fileChooser;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public FileChoosen() {
		setVisible(false);
		setBounds(100, 100, 600, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		fileChooser = new JFileChooser();
		contentPanel.add(fileChooser);
	
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

}

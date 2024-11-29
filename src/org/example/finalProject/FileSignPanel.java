package org.example.finalProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.example.dialog.FileChoosen;

public class FileSignPanel extends JPanel{
	public CustomTextField inputFile;
	private JTextArea outputFile;
	private static final long serialVersionUID = 1L;
	
	public FileSignPanel() {
		super();
		FileChoosen fileChoosen = new FileChoosen();
		JFileChooser fileChooser = fileChoosen.fileChooser;
		
		
		setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		add(new JLabel("Encrypt file"), BorderLayout.NORTH);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		
		JPanel fileChooserPanel = new JPanel();
		fileChooserPanel.setLayout(new BoxLayout(fileChooserPanel, BoxLayout.X_AXIS));
		inputFile = new CustomTextField("File Input");
		JButton browseFile = new JButton("Browse");
		
		fileChooser.addActionListener((ActionEvent e) -> {
			String action = e.getActionCommand();
			switch(action) {
				case "ApproveSelection":
					inputFile.setText(fileChooser.getSelectedFile().getPath());
					break;
				default:
					return;
			}
			fileChoosen.setVisible(false);
		});
		
		browseFile.addActionListener((ActionEvent e) -> {
			fileChoosen.setVisible(true);
		});
		
		fileChooserPanel.add(inputFile);
		fileChooserPanel.add(browseFile);
		bodyPanel.add(fileChooserPanel);
		
		outputFile = new JTextArea();
		outputFile.setColumns(45);
		outputFile.setRows(4);
		bodyPanel.add(new JScrollPane(outputFile));
		
		JPanel action = new JPanel();
		bodyPanel.add(action);
		

		JButton hashBtn = new JButton("Sign");
		hashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign = Store.handler.signFile(inputFile.getText());
				outputFile.setText(sign);
			}
		});
		
		JButton verifyBtn = new JButton("Verify");
		verifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean verify = Store.handler.verifyFile(inputFile.getText(), outputFile.getText());
				
				if(verify) {
					Store.notice.setVisible(true);
					Store.notice.setContent("Verify Successfully");
				} else {
					Store.notice.setVisible(true);
					Store.notice.setContent("Verify failed, sign is invalid");
				}
			}
		});
		action.add(hashBtn);
		action.add(verifyBtn);
		
		add(bodyPanel);
	}
}

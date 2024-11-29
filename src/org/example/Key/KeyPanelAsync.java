package org.example.Key;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import org.example.finalProject.Store;

import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class KeyPanelAsync extends JPanel {

	private static final long serialVersionUID = 1L;


	/**
	 * Create the panel.
	 */
	public KeyPanelAsync() {
		super();
		KeyPairHandler keyPairHandler = Store.keyPairHandler;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel privateKeyPanel = new JPanel();
		add(privateKeyPanel);
		privateKeyPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel privateKey = new JLabel("Private Key");
		privateKeyPanel.add(privateKey, BorderLayout.NORTH);
		
		JTextArea privateKeyInput = new JTextArea();
		JScrollPane privScrollPane = new JScrollPane(privateKeyInput);
		privateKeyInput.setRows(5);
		privateKeyInput.setColumns(40);
		privateKeyInput.setWrapStyleWord(true);
		privateKeyInput.setLineWrap(true);
		privateKeyPanel.add(privScrollPane, BorderLayout.CENTER);
		
		
		JPanel publicKeyPanel = new JPanel();
		add(publicKeyPanel);
		publicKeyPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel publicKey = new JLabel("Public Key");
		publicKeyPanel.add(publicKey, BorderLayout.NORTH);
		
		JTextArea publicKeyInput = new JTextArea();
		JScrollPane pubScrollPane = new JScrollPane(publicKeyInput);
		publicKeyInput.setRows(5);
		publicKeyInput.setColumns(45);
		publicKeyInput.setWrapStyleWord(true);
		publicKeyInput.setLineWrap(true);
		publicKeyPanel.add(pubScrollPane, BorderLayout.CENTER);
		
		
		JPanel actionPanel = new JPanel();
		add(actionPanel);
		
		JButton loadKey = new JButton("Load Key");
		loadKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyPairHandler.loadKey(publicKeyInput.getText(), privateKeyInput.getText());
				saveToFile();
			}
		});
		actionPanel.add(loadKey);
		
		JButton genKey = new JButton("Gen Key");
		genKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyPairHandler.genKey();
				publicKeyInput.setText(Base64.getEncoder().encodeToString(Store.publicKey.getEncoded()));
				privateKeyInput.setText(Base64.getEncoder().encodeToString(Store.privateKey.getEncoded()));
				saveToFile();
			}
		});
		actionPanel.add(genKey);

	}
	
	private void saveToFile() {
		try {
			File folder = new File(Store.configs.get("keyFolder"));
			
			if(!folder.exists()) {
				folder.mkdir();
			}
			
			File pubFile = new File(folder.getPath() + "/" + Store.algorithm + ".pub");
			File privFile = new File(folder.getPath() + "/" + Store.algorithm + ".key");
			
			if(Store.publicKey != null) {
				FileOutputStream pubOut = new FileOutputStream(pubFile);
				pubOut.write(Base64.getEncoder().encode(Store.publicKey.getEncoded()));
				pubOut.close();
			} else pubFile.delete();
			
			if(Store.privateKey != null) {
				FileOutputStream privOut = new FileOutputStream(privFile);
				privOut.write(Base64.getEncoder().encode(Store.privateKey.getEncoded()));
				privOut.close();	
			} else privFile.delete();
			
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
	}

}

package org.example.finalProject;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.example.Config.AlgorithmConfig;
import org.example.Config.DSAConfig;
import org.example.Config.RSAConfig;
import org.example.Key.KeyPanelAsync;
import org.example.Key.KeyUtils;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new RSAConfig();
		new DSAConfig();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		KeyUtils.readConfig();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		Store.algorithm = Algorithm.RSA;
		JMenuBar menuBar = new MenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JPanel agorithmPanel = new JPanel();
		header.add(agorithmPanel);
		agorithmPanel.setLayout(new BorderLayout(0, 0));
		
		SizePanel sizePanel = new SizePanel();
		AlgorithmConfig config = AlgorithmConfig.configs.get(Store.algorithm);
		sizePanel.keySizeSelection.setModel(new DefaultComboBoxModel<Integer>(config.size));
		Store.keySize = (int) sizePanel.keySizeSelection.getSelectedItem();
		
		SignPanel signPanel = new SignPanel();
		signPanel.signSelection.setModel(new DefaultComboBoxModel<String>(config.signatures));
		Store.signature = (String) signPanel.signSelection.getSelectedItem();
		header.add(signPanel);
		header.add(sizePanel);
		
		JComboBox<Algorithm> algorithmSelection = new JComboBox<Algorithm>();
		algorithmSelection.addActionListener((ActionEvent e) -> {
			Store.algorithm = (Algorithm) algorithmSelection.getSelectedItem();
			sizePanel.keySizeSelection.setModel(new DefaultComboBoxModel<Integer>(AlgorithmConfig.configs.get(Store.algorithm).size));
			Store.keySize = (int) sizePanel.keySizeSelection.getSelectedItem();
			signPanel.signSelection.setModel(new DefaultComboBoxModel<String>(AlgorithmConfig.configs.get(Store.algorithm).signatures));
			Store.signature = (String) signPanel.signSelection.getSelectedItem();
			KeyUtils.readKey();
		});
		agorithmPanel.add(algorithmSelection, BorderLayout.CENTER);
		algorithmSelection.setModel(new DefaultComboBoxModel<Algorithm>(Algorithm.values()));
		
		JLabel algorithmLabel = new JLabel("Agorithm");
		agorithmPanel.add(algorithmLabel, BorderLayout.NORTH);
		
		JPanel body = new JPanel();
		contentPane.add(body);
		body.add(new MessageSignPanel());
		body.add(new FileSignPanel());
		
		KeyPanelAsync keyPanel = new KeyPanelAsync();
		contentPane.add(keyPanel, BorderLayout.SOUTH);
	}

}

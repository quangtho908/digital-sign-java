package org.example.finalProject;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MessageSignPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MessageSignPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblMessageSign = new JLabel("Message Sign");
		add(lblMessageSign, BorderLayout.NORTH);
		
		JPanel body = new JPanel();
		add(body, BorderLayout.CENTER);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		JPanel messagePanel = new JPanel();
		body.add(messagePanel);
		messagePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel messLabel = new JLabel("Message");
		messagePanel.add(messLabel, BorderLayout.NORTH);
		
		JTextArea messageInput = new JTextArea();
		messageInput.setColumns(45);
		messageInput.setRows(4);
		
		JScrollPane scrollPane = new JScrollPane(messageInput);
		messagePanel.add(scrollPane);
		
		
		JPanel hashPanel = new JPanel();
		body.add(hashPanel);
		hashPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel hashLabel = new JLabel("Sign");
		hashPanel.add(hashLabel, BorderLayout.NORTH);
		
		JTextArea hashInput = new JTextArea();
		hashInput.setColumns(45);
		hashInput.setRows(4);
		
		JScrollPane hashPane = new JScrollPane(hashInput);
		hashPanel.add(hashPane);
		
		JPanel action = new JPanel();
		body.add(action);
		

		JButton hashBtn = new JButton("Sign");
		hashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign = Store.handler.sign(messageInput.getText());
				hashInput.setText(sign);
			}
		});
		
		JButton verifyBtn = new JButton("Verify");
		verifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean verify = Store.handler.verify(messageInput.getText(), hashInput.getText());
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

	}

}

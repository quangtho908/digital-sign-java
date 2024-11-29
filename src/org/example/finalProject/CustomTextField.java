package org.example.finalProject;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class CustomTextField extends JTextField{

	private static final long serialVersionUID = 1L;
	
	public CustomTextField(String placeHodler) {
		super();
		setText(placeHodler);
		addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(getText().isEmpty()) {
					setText(placeHodler);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(getText().equals(placeHodler)) {
					setText("");
				}
				
			}
		});
	}

}

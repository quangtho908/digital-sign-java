package org.example.finalProject;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.example.Key.KeyUtils;
import org.example.dialog.FolderChoosen;

public class MenuBar extends JMenuBar{
	
	private JFileChooser fileChooser;

	private static final long serialVersionUID = 1L;
	
	public MenuBar() {
		FolderChoosen folderChoosen = new FolderChoosen();
		fileChooser = folderChoosen.fileChooser;
		if(Store.configs.get("keyFolder") == null) {
			initKeyFolder();
			KeyUtils.updateConfig();	
		}
		fileChooser.setSelectedFile(new File(Store.configs.get("keyFolder")));
		KeyUtils.readKey();
		fileChooser.addActionListener((ActionEvent e) -> {
			String action = e.getActionCommand();
			switch(action) {
				case "ApproveSelection":
					Store.configs.put("keyFolder", fileChooser.getSelectedFile().getPath());
					KeyUtils.updateConfig();
					KeyUtils.readKey();
					break;
				default:
					break;
			}
			folderChoosen.setVisible(false);
		});
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Change Folder Hash");
		fileMenu.add(menuItem);
		menuItem.addActionListener((ActionEvent e) -> {
			folderChoosen.setVisible(true);
		});
		add(fileMenu);
	}
	
	private void initKeyFolder() {
		Store.configs.put("keyFolder", fileChooser.getSelectedFile().getPath() + "/keys");
		File folder = new File(Store.configs.get("keyFolder"));
		
		if(!folder.exists()) {
			folder.mkdir();
		}
		fileChooser.setSelectedFile(folder);
	}
}

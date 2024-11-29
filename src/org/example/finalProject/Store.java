package org.example.finalProject;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;

import org.example.Key.KeyPairHandler;
import org.example.dialog.Notice;

public class Store {
	public static Algorithm algorithm;
//	public static String keyFolder;
	
	public static HashMap<String, String> configs = new HashMap<String, String>();
	
	public static String chooseFile;
	
	public static PublicKey publicKey;
	public static PrivateKey privateKey;
	
	public static int keySize;
	public static String signature;
	
	public static Notice notice = new Notice();
	
	public static DS handler = new DS();
	public static KeyPairHandler keyPairHandler = new KeyPairHandler();
}

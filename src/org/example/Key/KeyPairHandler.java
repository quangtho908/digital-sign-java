package org.example.Key;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.example.finalProject.Store;

public class KeyPairHandler {

	public void loadKey(String publicKey, String privateKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(Store.algorithm.toString());
			if(publicKey != null && !publicKey.isBlank()) {
				X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
				PublicKey pubKey = keyFactory.generatePublic(publicSpec);
				Store.publicKey = pubKey;

			} else Store.publicKey = null;
			
			if(privateKey != null && !privateKey.isBlank()) {
				PKCS8EncodedKeySpec privateSpec= new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
				PrivateKey privKey = keyFactory.generatePrivate(privateSpec);
				Store.privateKey = privKey;	
			} else Store.privateKey = null;
			
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		} 
	}

	public void genKey() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Store.algorithm.toString());
			keyGen.initialize(Store.keySize);
			KeyPair keyPair = keyGen.generateKeyPair();
			Store.publicKey = keyPair.getPublic();
			Store.privateKey = keyPair.getPrivate();
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
	}
}

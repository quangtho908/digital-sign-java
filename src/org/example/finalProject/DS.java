package org.example.finalProject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class DS {
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	public DS() {
		
	}
	
	private void loadKey() {
		this.publicKey = Store.publicKey;
		this.privateKey = Store.privateKey;
	}
	
	public String sign(String mes) {
		try {
			loadKey();
			Signature signature = Signature.getInstance(Store.signature);
			byte[] data = mes.getBytes();
			signature.initSign(privateKey);
			signature.update(data);
			byte[] sign = signature.sign();
			
			return Base64.getEncoder().encodeToString(sign);
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		return "";
	}
	
	public boolean verify(String mes, String sign) {
		try {
			loadKey();
			Signature signature = Signature.getInstance(Store.signature);
			signature.initVerify(publicKey);
			byte[] data = mes.getBytes();
			byte[] signValue = Base64.getDecoder().decode(sign);
			
			signature.update(data);
			
			return signature.verify(signValue);
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public String signFile(String src) {
		try {
			loadKey();
			Signature signature = Signature.getInstance(Store.signature);
			signature.initSign(privateKey);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
			byte[] buff = new byte[1024];
			int read;
			while((read = bis.read(buff)) != -1) {
				signature.update(buff, 0, read);
			}
			
			bis.close();
			byte[] sign = signature.sign();
			
			return Base64.getEncoder().encodeToString(sign);
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		return "";
	}
	
	public boolean verifyFile(String src, String sign) {
		try {
			loadKey();
			Signature signature = Signature.getInstance(Store.signature);
			signature.initVerify(publicKey);
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
			byte[] buff = new byte[1024];
			int read;
			while((read = bis.read(buff)) != -1) {
				signature.update(buff, 0, read);
			}
			bis.close();
			byte[] signValue = Base64.getDecoder().decode(sign);
			
			return signature.verify(signValue);
			
		} catch (Exception e) {
			return false;
		}
		
	}
}

package org.example.Key;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.example.finalProject.Store;

public class KeyUtils {
	public static void readKey() {
		try {
			
			File pubFile = new File(Store.configs.get("keyFolder") + "/" + Store.algorithm + ".pub");
			File privFile = new File(Store.configs.get("keyFolder") + "/" + Store.algorithm + ".key");
			String pubKey = null;
			String privKey = null;
			if(pubFile.exists()) {
				try (FileInputStream pubIn = new FileInputStream(pubFile)) {
					byte[] pubBytes = pubIn.readAllBytes();
					pubKey = new String(pubBytes, StandardCharsets.UTF_8).replaceAll("\\s+", "");
				}

			} else Store.publicKey = null;
			
			if(privFile.exists()){
				try (FileInputStream privIn = new FileInputStream(privFile)) {
					byte[] privBytes = privIn.readAllBytes();
					privKey = new String(privBytes, StandardCharsets.UTF_8).replaceAll("\\s+", "");
				}
			} else Store.privateKey = null;
			
		   if(pubKey == null && privKey == null) return;
			
			Store.keyPairHandler.loadKey(pubKey, privKey);
		} catch (IOException e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
	}
	
	public static void updateConfig() {
		try {
			File configFile = new File(new File(".").getPath() + "/config.json" );
			Gson gson = new Gson();
		    Type typeObject = new TypeToken<HashMap<String, String>>() {}.getType();
		    String gsonData = gson.toJson(Store.configs, typeObject);
			try (FileOutputStream fos = new FileOutputStream(configFile)) {
				fos.write(gsonData.getBytes(StandardCharsets.UTF_8));
			}
			return;
			
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
	}
	
	public static void readConfig() {
		try {
			File configFile = new File(new File(".").getPath() + "/config.json" );
			if(!configFile.exists()) {
				return;
			}
			
			try (FileInputStream fis = new FileInputStream(configFile)) {
				byte[] data = fis.readAllBytes();
				
				String json = new String(data);
				Gson gson = new Gson();
				Type typeObject = new TypeToken<HashMap<String, String>>() {}.getType();
				Store.configs = gson.fromJson(json, typeObject);
			}
			
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
	}
}

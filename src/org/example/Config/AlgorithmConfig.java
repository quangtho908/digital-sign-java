package org.example.Config;

import java.util.HashMap;

import org.example.finalProject.Algorithm;

public abstract class AlgorithmConfig {
	
	public static HashMap<Algorithm, AlgorithmConfig> configs = new HashMap<Algorithm, AlgorithmConfig>();
	public Integer[] size;
	public String[] signatures;
	public AlgorithmConfig() {
		configs.put(getType(), this);
	}
	
	public abstract Algorithm getType();
}

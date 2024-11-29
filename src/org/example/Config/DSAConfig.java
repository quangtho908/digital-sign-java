package org.example.Config;

import org.example.finalProject.Algorithm;

public class DSAConfig extends AlgorithmConfig {
	public DSAConfig() {
		super();
		size = new Integer[] {512, 1024, 2048, 3072};
		signatures = new String[] {"SHA1withDSA", "SHA224withDSA", "SHA256withDSA", "SHA384withDSA", "SHA512withDSA"};
	}

	@Override
	public Algorithm getType() {
		
		return Algorithm.DSA;
	}
}

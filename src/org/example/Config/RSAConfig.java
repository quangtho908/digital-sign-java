package org.example.Config;

import org.example.finalProject.Algorithm;

public class RSAConfig extends AlgorithmConfig{
	
	public RSAConfig() {
		super();
		size = new Integer[] {1024, 2048, 4096};
		signatures = new String[] {"SHA1withRSA", "SHA256withRSA", "SHA384withRSA", "SHA512withRSA"};
	}

	@Override
	public Algorithm getType() {
		
		return Algorithm.RSA;
	}

}

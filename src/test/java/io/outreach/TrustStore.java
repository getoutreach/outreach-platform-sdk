package io.outreach;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Arrays;

public class TrustStore {

	public static KeyStore get() {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS");
			char[] password = "outreach".toCharArray();
			keyStore.load(stream(), password);
			Arrays.fill(password, '\0');
			return keyStore;
		} catch (Exception ignore) {
			return null;
		}
	}

	private static InputStream stream() throws IOException {
		return new FileInputStream("/Users/dc/Projects/outreach-platform-sdk/src/test/resources/outreach.truststore");
	}

}

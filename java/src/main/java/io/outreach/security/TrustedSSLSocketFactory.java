package io.outreach.security;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class TrustedSSLSocketFactory {

	private static final String SSL_CONTEXT_TLS = "TLS";

	public static SSLSocketFactory get(KeyStore trustStore) throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustManagers = new TrustManager[] { new TrustStoreTrustManager(trustStore) };
		SSLContext context = SSLContext.getInstance(SSL_CONTEXT_TLS);
		KeyManager[] keyManagers = new KeyManager[0];
		SecureRandom random = new SecureRandom();
		context.init(keyManagers, trustManagers, random);
		return (SSLSocketFactory) context.getSocketFactory();
	}

}

package io.outreach.security;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Enumeration;

import javax.net.ssl.X509TrustManager;

public class TrustStoreTrustManager implements X509TrustManager {

	private final KeyStore trustStore;

	public TrustStoreTrustManager(KeyStore trustStore) {
		this.trustStore = trustStore;
	}

	@Override
	public void checkClientTrusted(X509Certificate[] peerCertificates, String authType) throws CertificateException {
		checkPeerCertificateChainTrusted(peerCertificates);
	}

	private void checkPeerCertificateChainTrusted(X509Certificate[] peerCertificates) throws CertificateException {
		if (peerCertificates == null || peerCertificates.length < 1) {
			throw new CertificateException();
		}
		X509Certificate peerCertificate = peerCertificates[0];
		checkPeerCertificateTrusted(peerCertificate);
	}

	private void checkPeerCertificateTrusted(X509Certificate peerCertificate) throws CertificateException {
		PublicKey peerPublicKey = peerCertificate.getPublicKey();
		try {
			checkPublicKeyTrusted(peerPublicKey);
		} catch (KeyStoreException exception) {
			throw new CertificateException(exception);
		}
	}

	private void checkPublicKeyTrusted(PublicKey peerPublicKey) throws KeyStoreException, CertificateException {
		Enumeration<String> aliases = trustStore.aliases();
		while (aliases.hasMoreElements()) {
			String alias = aliases.nextElement();
			Certificate trustedCertificate = trustStore.getCertificate(alias);
			PublicKey trustedPublicKey = trustedCertificate.getPublicKey();
			if (equals(peerPublicKey, trustedPublicKey)) {
				return;
			}
		}
		throw new CertificateException();
	}

	@Override
	public void checkServerTrusted(X509Certificate[] peerCertificates, String authType) throws CertificateException {
		checkPeerCertificateChainTrusted(peerCertificates);
	}

	private boolean equals(PublicKey a, PublicKey b) {
		return Arrays.equals(a.getEncoded(), b.getEncoded());
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}

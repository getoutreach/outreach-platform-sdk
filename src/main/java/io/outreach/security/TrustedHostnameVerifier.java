package io.outreach.security;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

import io.outreach.exception.OutreachSecurityException;

public class TrustedHostnameVerifier implements HostnameVerifier {

	private final KeyStore trustStore;
	
	public TrustedHostnameVerifier(KeyStore trustStore) {
		this.trustStore = trustStore;
	}
	
	@Override
	public boolean verify(String hostname, SSLSession session) {
		try {
			Certificate trustedCertificate = trustStore.getCertificate(hostname);
			if(trustedCertificate == null) {
				return false;
			}
			Certificate [] peerCertificates = session.getPeerCertificates();
			if(peerCertificates == null || peerCertificates.length < 1) {
				return false;
			}
			Certificate peerCertificate = peerCertificates[0];
			if(trustedCertificate.equals(peerCertificate)) {
				return true;
			}
		} catch (KeyStoreException | SSLPeerUnverifiedException exception) {
			throw new OutreachSecurityException(exception);
		}
		return false;
	}

}

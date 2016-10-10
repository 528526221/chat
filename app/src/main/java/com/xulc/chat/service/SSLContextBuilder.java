package com.xulc.chat.service;

import java.io.ByteArrayInputStream;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 安全连接帮助类，方便获取SSLContext对象。
 *
 * @author chensijiang 2016年3月28日 下午5:15:44
 *
 */
public class SSLContextBuilder {
	public static final SSLContext create(final boolean veriCertificate, final String crt) throws Exception {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		final X509Certificate hostCertificate = (veriCertificate
				? (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(crt.getBytes())) : null);
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(null, new TrustManager[] { new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}

			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				System.out.println();
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String type) throws CertificateException {
				if (!veriCertificate) {
					return;
				}
				if (certs == null || certs.length < 3) {
					throw new CertificateException("证书链数量错误。");
				}
				certs[0].checkValidity();
//				if (!certs[0].equals(hostCertificate)) {
//					throw new CertificateException("证书不匹配，SSL认证失败。");
//				}
			}
		} }, new SecureRandom());
		return ctx;
	}

}

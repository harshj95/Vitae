package com.application.job.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.xerces.impl.dv.util.Base64;

public class CryptoHelper {
	private static final String defaultToken = "1682ea8a66fx552";
	private static final String defaultSecret = "249704e0xdra4fa";

	public String encrypt(String blobToEncrypt, String clientToken, String clientSecret) throws Exception {
		return Base64
				.encode(buildCypher(Cipher.ENCRYPT_MODE, clientToken, clientSecret).doFinal(blobToEncrypt.getBytes()));
	}

	public String decrypt(String blobToDecrypt, String clientToken, String clientSecret) throws Exception {
		return new String(
				buildCypher(Cipher.DECRYPT_MODE, clientToken, clientSecret).doFinal(Base64.decode(blobToDecrypt)));
	}

	private Cipher buildCypher(int mode, String clientToken, String clientSecret) throws Exception {
		if (clientToken == null && clientSecret == null) {
			clientToken = defaultToken;
			clientSecret = defaultSecret;
		}
		String keyBase = clientSecret + clientToken;
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(keyBase.getBytes());
		SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
		Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
		aes.init(mode, key);
		return aes;
	}
}

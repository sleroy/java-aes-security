package io.github.sleroy.aes;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Karthik M'lore
 * @author Sylvain Leroy
 * @version 1.1
 * 
 *          This program encrypts and decrypts strings using AES (Advanced
 *          Encryption Standard) 128 bit algorithm and using the UTF-8 charset.
 */

@Service
public final class Encryption {
	private static final String CIPHER_ALGO_NAME = "AES";

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static final String CIPHER_ALGO = "AES/ECB/PKCS5Padding";

	private static final Logger LOGGER = LoggerFactory.getLogger(Encryption.class);

	private static byte[] encodingKey = { 0x2d, 0x2a, 0x2d, 0x42, 0x55, 0x49, 0x4c, 0x44, 0x41, 0x43, 0x4f, 0x44, 0x45,
			0x2d, 0x2a, 0x2d };

	/**
	 * Encrypt a plain text using the default to a base-64 text.
	 * 
	 * @param plainText
	 *            the plain text
	 * @return the Base64 encrypted text
	 */
	public String encrypt(String plainText) {
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGO);
			SecretKey secretKey = new SecretKeySpec(encodingKey, CIPHER_ALGO_NAME);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] cipherText = cipher.doFinal(plainText.getBytes(DEFAULT_ENCODING));
			String encryptedString = new String(Base64.getEncoder().encode(cipherText), DEFAULT_ENCODING);
			return encryptedString;
		} catch (Exception e) {
			LOGGER.error("Could not encrypt a encodingKey for the reason {}", e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Decrypt a base-64 encrypted text toa plain text,
	 * 
	 * @param the
	 *            Base64 encrypted text
	 * @return plainText the plain text
	 */
	public String decrypt(String encryptedText) {
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGO);
			SecretKey secretKey = new SecretKeySpec(encodingKey, CIPHER_ALGO_NAME);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] cipherText = Base64.getDecoder().decode(encryptedText.getBytes(DEFAULT_ENCODING));
			String decryptedString = new String(cipher.doFinal(cipherText), DEFAULT_ENCODING);
			return decryptedString;
		} catch (Exception e) {
			LOGGER.error("Could not decrypt a encodingKey for the reason {}", e.getMessage(), e);
		}
		return null;
	}

}
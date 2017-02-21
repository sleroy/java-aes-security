package io.github.sleroy.aes;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncryptionTest {

	@Test
	public void testEncrypt_emptyString() {
		Encryption encryption = new Encryption();
		assertEquals("o/12Iu71Q+x7xtIxRk2rAw==", encryption.encrypt(""));
		
	}
	
	@Test
	public void testEncrypt_nullString() {
		Encryption encryption = new Encryption();
		assertEquals(null, encryption.encrypt(null));
		
	}
	@Test
	public void testEncryptDecrypt() {
		Encryption encryption = new Encryption();
		String encrypt = encryption.encrypt("Very long string");
		assertEquals("O+mPB1GOtevFpMZCH4EwbqP9diLu9UPse8bSMUZNqwM=", encrypt);
		assertEquals("Very long string", encryption.decrypt("O+mPB1GOtevFpMZCH4EwbqP9diLu9UPse8bSMUZNqwM="));
		
	}

	
	

	@Test
	public void testDecrypt_emptyString() {
		Encryption encryption = new Encryption();
		assertEquals("", encryption.decrypt(""));
		
	}
	
	@Test
	public void testDecrypt_nullString() {
		Encryption encryption = new Encryption();
		assertEquals(null, encryption.decrypt(null));
		
	}


}

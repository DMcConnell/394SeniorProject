package com.eat.support;




import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class Support {
 
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }


public static String encryptPass(String Password) {
	final String secretKey = "Arm@dillo_%62Tiger457Team!";

	return Support.encrypt(Password, secretKey);
	
}


public static String decryptPass (String EncryptedPassword) {
	final String secretKey = "Arm@dillo_%62Tiger457Team!"; 
	return Support.decrypt(EncryptedPassword, secretKey);
	}

public static void main(String[]args) {
	String a = encryptPass("ILikeDonuts");
	System.out.println(a);
	String b = decryptPass(a);
    System.out.println(b);

}

}

/**package com.eat.support;

import java.security.*;
import java.security.spec.*;
import java.io.*;
import java.nio.file.NoSuchFileException;

import javax.crypto.*;
import javax.crypto.spec.*;

public class Support{
	static Cipher passwordCipher;
	private final static String ALGO = "DES";
	private final static String MODE = "ECB";
	private final static String PADDING = "PCKS5Padding";
	private static byte[] cipherText=null;

	
	public static String passEnc(String password) {
		String input = password;
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance(ALGO);
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.init(56, random);
			Key sharedKey = keyGen.generateKey();
			
			passwordCipher = Cipher.getInstance(ALGO);
			passwordCipher.init(Cipher.ENCRYPT_MODE, sharedKey);
			
			cipherText = passwordCipher.doFinal(input.getBytes());
			
			
			//Add a statement to keep a copy of the key to decrypt the password			

		save(sharedKey.getEncoded(), "sharedkey.txt");
		
		
		} catch (NoSuchAlgorithmException | 
				NoSuchPaddingException | NoSuchProviderException | 
				InvalidKeyException | IllegalBlockSizeException | 
				BadPaddingException e) {
			// TODO Auto-generated catch block
			
			
			e.printStackTrace();
		}
		
		return (new String(cipherText));}


	
	public static String passDecrypt(String encryptedPassword) {
		byte[] encoded = load ("sharedkey.txt");
		byte[] plainText=null;
		try {
			SecretKeyFactory keyFact = SecretKeyFactory.getInstance(ALGO);
			KeySpec keySpec = new DESKeySpec(encoded);
			SecretKey key = keyFact.generateSecret(keySpec);
			
			byte [] cipherText = load (encryptedPassword);
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.DECRYPT_MODE, key);
			
			plainText = c.doFinal(cipherText);
		
		} catch (InvalidKeyException | NoSuchAlgorithmException | 
				InvalidKeySpecException | NoSuchPaddingException |
				IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (new String (plainText));	}
	
	
	private static void save(byte[] buffer, String file) {
		try {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(buffer);
		fos.close();}
		
		catch (IOException e) {
			e.printStackTrace();}
		}
	
	private static byte[] load(String file) {
		FileInputStream fis;
		byte[] buf = null;
		try {
			fis = new FileInputStream(file);
			buf = new byte[fis.available()];
			fis.read();
			fis.close();
			return buf;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return buf;
		
	}
	
	public static void main(String[]args) {

		System.out.println(passDecrypt(passEnc("Mustafa Had a little lamb")));
		}
	
}**/
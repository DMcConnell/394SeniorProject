package com.eat.support;




import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class Support {
 
    private static SecretKeySpec secretKey;
    private static byte[] key;
	private static String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    
    
    //set key Function allows us to later make a secret ket to encryt the string with
    //Set key uses built in MessageDIggest function converts the string into UTF-8 and sets the KeySpec to AES type encryption
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
 
    
    //Here we take the String we are encrypting and the secret key we create with the function above create a CIPHER 
    //which is built into Java for encryption purposes
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            //create a Cipher to get the instance of how we will be encrypting mode AES
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            //initialize into Encrypt mode and input the secret key we created
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            //the actual action of encryption done here with cipher.dofinal, we later cast this result intoa string and return it
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

    
    //using function before we create a final string to hold our secret String for encryption and Decrypt process

public static String encryptPass(String Password) {
	final String secretKey = "Arm@dillo_%62Tiger457Team!";

	return Support.encrypt(Password, secretKey);
	
}


public static String decryptPass (String EncryptedPassword) {
	final String secretKey = "Arm@dillo_%62Tiger457Team!"; 
	return Support.decrypt(EncryptedPassword, secretKey);
	}


public static String generatePassword() {
	StringBuilder newPassword = new StringBuilder();
	Random randomCharIndex = new Random();
	char character ;
	int i =0;
	for (i =0; i<8; i++) {
		character = allowedCharacters.charAt(randomCharIndex.nextInt(allowedCharacters.length()));
		newPassword.append(character);
	}
	
return newPassword.toString();}

public static void main(String[]args) {
	String a = encryptPass("ILikeDonuts");
	System.out.println(a);
	String b = decryptPass(a);
    System.out.println(b);
    System.out.println("\n\n");

	
    System.out.println(generatePassword());

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
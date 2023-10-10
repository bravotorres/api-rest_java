package com.quironlabs.api.utils;

//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//import javax.crypto.AEADBadTagException;
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.GCMParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;
//
//import org.apache.commons.codec.binary.Base64;
//
//public class AESEncriptacionGCM {
//	
//	private static final String ALGORITMO = "AES/GCM/NoPadding";
//	  
//	  private static final String CODIFICACION = "UTF-8";
//	  
//	  public static String encrypt(String paramString1, String paramString2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, AEADBadTagException {
//	    byte[] arrayOfByte1 = paramString1.getBytes();
//	    byte[] arrayOfByte2 = DatatypeConverter.parseHexBinary(paramString2);
//	    SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte2, "AES");
//	    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//	    cipher.init(1, secretKeySpec);
//	    byte[] arrayOfByte3 = cipher.getIV();
//	    byte[] arrayOfByte4 = cipher.doFinal(arrayOfByte1);
//	    assert arrayOfByte4.length == arrayOfByte1.length + 16;
//	    byte[] arrayOfByte5 = new byte[12 + arrayOfByte1.length + 16];
//	    System.arraycopy(arrayOfByte3, 0, arrayOfByte5, 0, 12);
//	    System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 12, arrayOfByte4.length);
//	    return Base64.encodeBase64String(arrayOfByte5);
//	  }
//	  
//	  public static String encrypt(String paramString1, String paramString2, String paramString3) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, AEADBadTagException {
//	    byte[] arrayOfByte1 = paramString1.getBytes();
//	    byte[] arrayOfByte2 = DatatypeConverter.parseHexBinary(paramString2);
//	    byte[] arrayOfByte3 = paramString3.getBytes();
//	    SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte2, "AES");
//	    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//	    cipher.init(1, secretKeySpec);
//	    byte[] arrayOfByte4 = cipher.getIV();
//	    cipher.updateAAD(arrayOfByte3);
//	    byte[] arrayOfByte5 = cipher.doFinal(arrayOfByte1);
//	    assert arrayOfByte5.length == arrayOfByte1.length + 16;
//	    byte[] arrayOfByte6 = new byte[12 + arrayOfByte1.length + 16];
//	    System.arraycopy(arrayOfByte4, 0, arrayOfByte6, 0, 12);
//	    System.arraycopy(arrayOfByte5, 0, arrayOfByte6, 12, arrayOfByte5.length);
//	    return Base64.encodeBase64String(arrayOfByte6);
//	  }
//	  
//	  public static byte[] encryptBytes(byte[] paramArrayOfbyte, String paramString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, AEADBadTagException {
//	    byte[] arrayOfByte1 = DatatypeConverter.parseHexBinary(paramString);
//	    SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte1, "AES");
//	    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//	    cipher.init(1, secretKeySpec);
//	    byte[] arrayOfByte2 = cipher.getIV();
//	    byte[] arrayOfByte3 = cipher.doFinal(paramArrayOfbyte);
//	    assert arrayOfByte3.length == paramArrayOfbyte.length + 16;
//	    byte[] arrayOfByte4 = new byte[12 + paramArrayOfbyte.length + 16];
//	    System.arraycopy(arrayOfByte2, 0, arrayOfByte4, 0, 12);
//	    System.arraycopy(arrayOfByte3, 0, arrayOfByte4, 12, arrayOfByte3.length);
//	    return arrayOfByte4;
//	  }
//	  
//	  public static byte[] encryptBytes(byte[] paramArrayOfbyte, String paramString1, String paramString2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, AEADBadTagException {
//	    byte[] arrayOfByte1 = DatatypeConverter.parseHexBinary(paramString1);
//	    byte[] arrayOfByte2 = paramString2.getBytes();
//	    SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte1, "AES");
//	    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//	    cipher.init(1, secretKeySpec);
//	    byte[] arrayOfByte3 = cipher.getIV();
//	    cipher.updateAAD(arrayOfByte2);
//	    byte[] arrayOfByte4 = cipher.doFinal(paramArrayOfbyte);
//	    assert arrayOfByte4.length == paramArrayOfbyte.length + 16;
//	    byte[] arrayOfByte5 = new byte[12 + paramArrayOfbyte.length + 16];
//	    System.arraycopy(arrayOfByte3, 0, arrayOfByte5, 0, 12);
//	    System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 12, arrayOfByte4.length);
//	    return arrayOfByte5;
//	  }
//	  
//	  public static String decrypt(String paramString1, String paramString2) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, AEADBadTagException {
//	    byte[] arrayOfByte1 = Base64.decodeBase64(paramString1.getBytes("UTF-8"));
//	    if (arrayOfByte1.length < 28)
//	      throw new IllegalArgumentException(); 
//	    byte[] arrayOfByte2 = DatatypeConverter.parseHexBinary(paramString2);
//	    SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte2, "AES");
//	    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//	    GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, arrayOfByte1, 0, 12);
//	    cipher.init(2, secretKeySpec, gCMParameterSpec);
//	    return new String(cipher.doFinal(arrayOfByte1, 12, arrayOfByte1.length - 12));
//	  }
//	  
//	  public static String decrypt(String paramString1, String paramString2, String paramString3) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, AEADBadTagException {
//	    byte[] arrayOfByte1 = Base64.decodeBase64(paramString1.getBytes("UTF-8"));
//	    byte[] arrayOfByte2 = paramString3.getBytes();
//	    if (arrayOfByte1.length < 28)
//	      throw new IllegalArgumentException(); 
//	    byte[] arrayOfByte3 = DatatypeConverter.parseHexBinary(paramString2);
//	    SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte3, "AES");
//	    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//	    GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, arrayOfByte1, 0, 12);
//	    cipher.init(2, secretKeySpec, gCMParameterSpec);
//	    cipher.updateAAD(arrayOfByte2);
//	    return new String(cipher.doFinal(arrayOfByte1, 12, arrayOfByte1.length - 12));
//	  }
//	  
//	  public static byte[] decryptBytes(byte[] paramArrayOfbyte, String paramString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidAlgorithmParameterException, AEADBadTagException {
//	    if (paramArrayOfbyte.length < 28)
//	      throw new IllegalArgumentException(); 
//	    byte[] arrayOfByte = DatatypeConverter.parseHexBinary(paramString);
//	    SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte, "AES");
//	    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//	    GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, paramArrayOfbyte, 0, 12);
//	    cipher.init(2, secretKeySpec, gCMParameterSpec);
//	    return cipher.doFinal(paramArrayOfbyte, 12, paramArrayOfbyte.length - 12);
//	  }
//	  
//	  public static byte[] decryptBytes(byte[] paramArrayOfbyte, String paramString1, String paramString2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidAlgorithmParameterException, AEADBadTagException {
//	    byte[] arrayOfByte1 = paramString2.getBytes();
//	    if (paramArrayOfbyte.length < 28)
//	      throw new IllegalArgumentException(); 
//	    byte[] arrayOfByte2 = DatatypeConverter.parseHexBinary(paramString1);
//	    SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte2, "AES");
//	    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//	    GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, paramArrayOfbyte, 0, 12);
//	    cipher.init(2, secretKeySpec, gCMParameterSpec);
//	    cipher.updateAAD(arrayOfByte1);
//	    return cipher.doFinal(paramArrayOfbyte, 12, paramArrayOfbyte.length - 12);
//	  }
//	  
//	  public static void main(String[] paramArrayOfString) throws Exception {
//		  
//		  	final String key = "20D628A29F3F39249F3F39249F3F3924";
//			
//		    String originalString = "3l8uj8a9c6gu65qn3e3jqcol4t";
//		  
//		    try {
//		      String str3 = encrypt(originalString, key);
//		      System.out.println("Encrypted Text : " + str3);
//		      
//		      String str4 = decrypt(str3, key);
//		      System.out.println("Decrypted Text : " + str4);
//		    } catch (Exception exception) {
//		      exception.printStackTrace();
//		    } 
//		  }
//
//}

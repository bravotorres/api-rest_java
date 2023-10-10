package com.quironlabs.api.utils;
//
//import java.io.*;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//
//import javax.crypto.*;
//import javax.crypto.spec.*;
//
//import org.apache.commons.codec.*;
//import org.apache.commons.codec.binary.*;
//import org.apache.log4j.Logger;
//import com.l2b.LeerProperties;
//
//public class AESEncriptacionCBC {
//
//	private static Logger log = Logger.getLogger(AESEncriptacionCBC.class);
//	public static final LeerProperties lp = new LeerProperties();
//	
//	
//	private static final String ALGORITMO = "AES";
//	private static final String CODIFICACION = "UTF-8";
//	private static final String MODE = "CBC";
//	
//
//	public static String encriptar(String cadena) throws NoSuchAlgorithmException, NoSuchPaddingException,
//			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, DecoderException {
//		
//		String llave = obtenerLLave();
//		//System.out.println("llave = " + llave);
//
//		byte[] raw = Hex.decodeHex(llave.toCharArray());
//		String result = null;
//		try {
//			result = toolEncrypt(cadena, raw, MODE, null, new Base64());
//		} catch (IOException e) {
//			log.error(e, e);
//			System.out.println(e);
//		} catch (EncoderException e) {
//			log.error(e, e);
//			System.out.println(e);
//		}
//		
//		//Encripta a base 64
//		//byte[] encodedBytes = Base64.encodeBase64(result.getBytes()); ya no va
//		//System.out.println("encodedBytes " + new String(encodedBytes));
//		
//		//return new String(encodedBytes); ya no va
//		return result;
//	}
//	
//	
//	public static String desencriptar(String encriptado) throws InvalidKeyException, IllegalBlockSizeException,
//			BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, DecoderException {
//		
//		String llave = obtenerLLave();
//		
//		//desencripta base 64 y lo pasa a bytes
//		//byte[] decodedBytes = Base64.decodeBase64(encriptado.getBytes()); Ya no va
//		
//		
//		//System.out.println("decodedBytes " + new String(decodedBytes));
//		
//		
//		
//		//encriptado = new String(decodedBytes); Ya no va
//		byte[] raw = Hex.decodeHex(llave.toCharArray());
//		
//		String originalString = null;
//		try {
//			//originalString = toolDesenc(encriptado, raw, MODE, null, new Hex());
//			originalString = toolDesenc(encriptado, raw, MODE, null, new Base64());
//		} catch (InvalidAlgorithmParameterException e) {
//			log.error(e, e );
//			System.out.println(e);
//		}
//		return originalString;
//		}
//	
//	
//	
//	public static String desencriptarBytes(String encriptado, String llave) throws InvalidKeyException, IllegalBlockSizeException,
//				BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, DecoderException {
//
//
//		byte[] raw = Hex.decodeHex(llave.toCharArray());
//		
//		String originalString = null;
//		try {
//			originalString = toolDesenc(encriptado, raw, MODE, null, new Hex());
//		} catch (InvalidAlgorithmParameterException e) {
//			log.error(e, e );
//			System.out.println(e);
//		}
//		return originalString;
//	}
//
//	
//	public static String toolEncrypt(String cadena, byte[] llave, String mode,
//			String padding, BinaryEncoder encoder)
//			throws NoSuchAlgorithmException, NoSuchPaddingException,
//			InvalidKeyException, IllegalBlockSizeException,
//			BadPaddingException, DecoderException, IOException,
//			EncoderException {
//
//		SecretKeySpec skeySpec = new SecretKeySpec(llave, ALGORITMO);
//
//		String transformation = loadTransformation(mode, padding);
//
//		Cipher cipher = Cipher.getInstance(transformation);
//		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//		byte[] encrypted = cipher.doFinal(cadena.getBytes(CODIFICACION));
//
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		if (cipher.getIV() != null) {
//			outputStream.write(cipher.getIV());
//		}
//		outputStream.write(encrypted);
//		encrypted = outputStream.toByteArray();
//		outputStream.close();
//
//		encrypted = encoder.encode(encrypted);
//		return new String(encrypted);
//	}
//
//	
//	public static String toolDesenc(String encriptado, byte[] llave,
//			String mode, String padding, BinaryDecoder decoder)
//			throws InvalidKeyException, IllegalBlockSizeException,
//			BadPaddingException, UnsupportedEncodingException,
//			NoSuchAlgorithmException, NoSuchPaddingException, DecoderException,
//			InvalidAlgorithmParameterException {
//		SecretKeySpec skeySpec = new SecretKeySpec(llave, ALGORITMO);
//
//		String transformation = loadTransformation(mode, padding);
//
//		byte[] initialData = decoder.decode(encriptado.getBytes());
//
//		IvParameterSpec ivSpecs = null;
//		if (MODE.equalsIgnoreCase(mode)) {
//			byte[] iv = Arrays.copyOfRange(initialData, 0, 16);
//			ivSpecs = new IvParameterSpec(iv);
//			initialData = Arrays.copyOfRange( initialData, 16, initialData.length );
//		}
//
//		Cipher cipher = Cipher.getInstance(transformation);
//		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpecs);
//		byte[] original = cipher.doFinal(initialData);
//		String originalString = new String(original);
//		return originalString;
//	}
//
//	private static String loadTransformation(String mode, String padding) {
//		String transformation = ALGORITMO;
//		transformation += mode == null ? "/ECB" : "/" + mode;
//		transformation += padding == null ? "/PKCS5PADDING" : "/" + padding;
//		return transformation;
//	}
//	
//	
//	
//	 public static String readBynary(String fileName) {
//	    	
//	    	String PrivKeyEnc = "";
//
//	        try {
//	            byte[] buffer = new byte[256];
//
//	            FileInputStream inputStream =  new FileInputStream(fileName);
//
//	            int total = 0;
//	            int nRead = 0;
//	            while((nRead = inputStream.read(buffer)) != -1) {
//	            	PrivKeyEnc = new String(buffer);
//	                System.out.println(new String(buffer));
//	                total += nRead;
//	            }
//	            
//	            inputStream.close();       
//
//	        }
//	        catch(FileNotFoundException ex) {
//	            System.out.println( "Error al leer el archivo '" +  fileName + "'");               
//	        }
//	        catch(IOException ex) {
//	            System.out.println( "Error al leyendo '" + fileName + "'");                   
//	             ex.printStackTrace();
//	        }
//			return PrivKeyEnc;
//	    }
//	 
//	 
//	 
//	 
//	 
//	 public static void escribeFichero(String fileName) {
//	    	
//		 File f = new File(fileName);
//
//		 //Escritura
//		 try{
//		 FileWriter w = new FileWriter(f);
//		 BufferedWriter bw = new BufferedWriter(w);
//		 PrintWriter wr = new PrintWriter(bw);	
//		 wr.write("Esta es una linea de codigo");//escribimos en el archivo 		               
//		 wr.close();
//		 bw.close();
//		 }catch(IOException e){
//			 e.printStackTrace();
//		 }
//		 
//	    }
//	 
//	 
//	 public static String obtenerLLave() {
//		String llave_Decrypted="";
//	 	
//	 	String key_a = leerficheros5(lp.szLeerPropiedad("ruta.A") + "KeyA_PGS");
//	 	//System.out.println("key_a " + key_a);
//		
//		String key_b = leerficheros5(lp.szLeerPropiedad("ruta.B") + "KeyB_PGS");
//		//System.out.println("key_b " + key_b);
//		
//		//procedemos a dencriptar la llave A de 128 bits  con la llave B de 256 bits
//		try {
//			llave_Decrypted = desencriptarBytes(key_a, key_b);
//		} catch (InvalidKeyException e)          { e.printStackTrace();
//		} catch (IllegalBlockSizeException e)    { e.printStackTrace();
//		} catch (BadPaddingException e)          { e.printStackTrace();
//		} catch (UnsupportedEncodingException e) { e.printStackTrace();
//		} catch (NoSuchAlgorithmException e)     { e.printStackTrace();
//		} catch (NoSuchPaddingException e)       { e.printStackTrace();
//		} catch (DecoderException e)             { e.printStackTrace();
//	    }
//		
//		//System.out.println("llave_Decrypted = " + llave_Decrypted);
//	 
//    	return llave_Decrypted;
//	 
//    }
//	 
//	 	 
//	 
//	 public static String leerficheros5 (String filename) {
//		 	String salida ="";
//			File file = new File(filename);
//			FileInputStream fin = null;
//			try {
//				fin = new FileInputStream(file);
//
//				byte fileContent[] = new byte[(int)file.length()];
//				
//				// Reads up to certain bytes of data from this input stream into an array of bytes.
//				fin.read(fileContent);
//				//create string from byte array
//				String s = new String(fileContent);
//				salida = new String(Hex.encodeHex(fileContent));
//				//System.out.println("Salida = " + salida);
//			}
//			catch (FileNotFoundException e) {
//				System.out.println("File not found" + e);
//			}
//			catch (IOException ioe) {
//				System.out.println("Exception while reading file " + ioe);
//			}
//			finally {
//				// close the streams using close method
//				try {
//					if (fin != null) {
//						fin.close();
//					}
//				}
//				catch (IOException ioe) {
//					System.out.println("Error while closing stream: " + ioe);
//				}
//			}
//			return salida;
//		}
//
//
//
//	 
//	 
//	 
//		public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, DecoderException {
//					
//			
//			String original = "MENSAJE DE PRUEBA";
//			System.out.println("Cadena original = " + original);
//	    	
//			String Encriptado = encriptar(original);
//	    	System.out.println("Encriptado = " + Encriptado);
//	    	
//			String Desencriptado = desencriptar(Encriptado);
//			System.out.println("Desencriptado = " + Desencriptado);
//			
////			String Desencriptado2 = desencriptar("NzhlNWYwOWY1MmI5YTYyMjE3MmExZTM4OTJjN2RhNWIyOGZhYzY3OGRjOGJkNTBkYjE4NGI0YmFhOTMxYzE3ZWYzY2Y0MWIwNTA0MzgwMWYxMjRmMmE2MDExNjA5OWY3");
////			System.out.println("Desencriptado2 = " + Desencriptado2);
//			
//		}
//
//}

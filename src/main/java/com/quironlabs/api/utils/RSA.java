package com.quironlabs.api.utils;


import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RSA {
    private static final Logger logger = LoggerFactory.getLogger(RSA.class);
    private String privateKey;
    private String publicKey;

    public RSA() {
        try {
            Encoder encoder = Base64.getEncoder();
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair pair = generator.generateKeyPair();
            
            PrivateKey privateK = pair.getPrivate();
            String b64 = Base64.getEncoder().encodeToString("Hola, mundo!".getBytes());
            byte[] bytes = privateK.getEncoded();
            logger.info("b64: {}", privateK);
            
            String b = encoder.encodeToString(bytes);
            logger.info("b64: {}", b);
            
//            String b64 = Base64.getEncoder().encodeToString("Hola, mundo!".getBytes());
//            byte[] bytes = privateK.getEncoded();
            PublicKey publicK = pair.getPublic();
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
        }
    }

    public RSA(String privateKex, String publicKey) {
        this.privateKey = privateKex;
        this.publicKey = publicKey;
    }

    public String encrypt(String message) {
        return null;
    }

    public String decrpyt(String encryptedMessage) {
        return null;
    }
    
    public String createKeyPair() {
        try {
            String id;
            Encoder encoder = Base64.getEncoder();
            
            // Generate a KeyPair
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            
            KeyPair pair = generator.generateKeyPair();
            
            PrivateKey privateK = pair.getPrivate();
            String private_b64 = encoder.encodeToString(privateK.getEncoded());

            logger.info("Private: {}", private_b64);
            
            PublicKey publicK = pair.getPublic();
            String public_b64 = encoder.encodeToString(publicK.getEncoded());

            logger.info("Public: {}", public_b64);
            
            // Transfor into a Base64 text
            
            // Save into a Table in DB.
            
            // Return a id to return a KeyPair info
            
            
            return null;
        } catch (Exception e) {
            logger.error("Error to create or save a KeyPair: {}", e.getMessage());
            return null;
        }
    }

//    public static void main(String[] args) {
//        RSA cypher = new RSA();
//        String message = "Hola, mundo de Java + RSA!!!";
//        
//        String encrypted = cypher.encrypt(message);
//        logger.info("Encrypted: {}", encrypted);
//        
//        String decrypted = cypher.decrpyt(encrypted);
//        logger.info("Decrypted: {}", decrypted);
//        
//        assert(message.equals(decrypted));
//    }
}

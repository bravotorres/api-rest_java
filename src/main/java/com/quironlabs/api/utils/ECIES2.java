package com.quironlabs.api.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;

import javax.crypto.Cipher;

import org.bouncycastle.jce.spec.IEKeySpec;
import org.bouncycastle.jce.spec.IESParameterSpec;


public class ECIES2 {
    private SecureRandom random;
    private int keySize;
    private KeyPair akey;
    private KeyPair bkey;

    public ECIES2() throws Exception {
        this.random = new SecureRandom();
    }

    public void establishKeys(String keysize) throws Exception {

        ECGenParameterSpec ecGenSpec = new ECGenParameterSpec(keysize);
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");

        keyGen.initialize(ecGenSpec, random);

        this.akey = keyGen.generateKeyPair();
        this.bkey = keyGen.generateKeyPair();
        this.keySize = Integer.valueOf((ecGenSpec.getName().substring(4, 7))).intValue();
    }

    public byte[] encrypt(byte[] plainText) throws Exception {

        // get ECIES cipher objects
        Cipher acipher = Cipher.getInstance("ECIES");

        // generate derivation and encoding vectors
        byte[] d = new byte[] {1, 2, 3, 4, 5, 6, 7, 8};
        byte[] e = new byte[] {8, 7, 6, 5, 4, 3, 2, 1};
        IESParameterSpec param = new IESParameterSpec(d, e, 256);

        // encrypt the plaintext using the public key
        acipher.init(Cipher.ENCRYPT_MODE, new IEKeySpec(akey.getPrivate(), bkey.getPublic()), param);
        return acipher.doFinal(plainText);
    }

    public byte[] decrypt(byte[] cipherText) throws Exception {

        // get ECIES cipher objects
        Cipher bcipher = Cipher.getInstance("ECIES");

        // generate derivation and encoding vectors
        byte[] d = new byte[] {1, 2, 3, 4, 5, 6, 7, 8};
        byte[] e = new byte[] {8, 7, 6, 5, 4, 3, 2, 1};
        IESParameterSpec param = new IESParameterSpec(d, e, 256);

        // decrypt the text using the private key
        bcipher.init(Cipher.DECRYPT_MODE, new IEKeySpec(bkey.getPrivate(), akey.getPublic()), param);
        return bcipher.doFinal(cipherText);
    }

    public byte[] sign(byte[] plainText) throws Exception {

        Signature sig = Signature.getInstance("SHA1WithECDSA");
        sig.initSign(akey.getPrivate());
        sig.update(plainText);
        return sig.sign();
    }

    public boolean verify(byte[] plainText, byte[] signature) throws Exception {

        Signature sig = Signature.getInstance("SHA1WithECDSA");
        sig.initVerify(akey.getPublic());
        sig.update(plainText);

        try {

            if (sig.verify(signature)) {
                return true;
            } else
                return false;
        } catch (SignatureException se) {
            System.out.println("Signature failed");
        }
        return false;
    }

    public int getKeySize() {
        return keySize;
    }
    
}

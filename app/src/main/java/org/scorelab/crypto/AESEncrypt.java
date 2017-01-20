package org.scorelab.crypto;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by kasun on 1/20/17.
 */
public class AESEncrypt {
    private static Key key=null;
    private static Cipher cipher = null;

    public AESEncrypt(int size) throws NoSuchAlgorithmException, NoSuchPaddingException {
        //Generate a key
        if (key==null){
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(size);
            key = generator.generateKey();
        }
        if(cipher==null) cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    public byte[] encrypt(String msg) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException{
        // encryption pass
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(msg.getBytes());
    }

    public byte[] decrypt(byte[] cipherText) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException{
        // decryption pass
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }
}
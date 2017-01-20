package org.scorelab.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by kasun on 1/20/17.
 */
public class Random {
    private static SecureRandom sr=null;

    public byte[] getNumber(String pass) throws NoSuchAlgorithmException {
        //Create secure number generators with the same seed
        // Get 256 random bits
        sr=SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(pass.getBytes());
        byte[] data = new byte[256/8];
        sr.nextBytes(data);
        return data;
    }
    public byte[] getNextNumber() throws NoSuchAlgorithmException {
        //Create secure number generators with the same seed
        // Get 256 random bits
        byte[] data = new byte[256/8];
        sr.nextBytes(data);
        return data;
    }
}

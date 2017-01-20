package org.scorelab.crypto;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by kasun on 1/20/17.
 */
public class SimpleHash {
    private static MessageDigest md=null;

    public SimpleHash() throws NoSuchAlgorithmException {
        if(md==null)  md = MessageDigest.getInstance("sha1");
    }

    public byte[] makeHash(String msg){
        md.update(msg.getBytes());
        return md.digest();
    }

    public boolean verifyHash(String msg,byte[] digest){
        md.update(msg.getBytes());
        byte[] newDigest=md.digest();
        if(MessageDigest.isEqual(digest,newDigest)) return true;
        else return false;
    }

}
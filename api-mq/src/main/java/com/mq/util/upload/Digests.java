package com.mq.util.upload;

import com.mq.util.exception.BlException;
import com.mq.util.exception.ExceptionStatus;
import com.mq.util.idGenerate.IdGenerator;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class Digests {
    private static final String SHA1 = "SHA-1";
    private static final String MD5 = "MD5";
    public static final String ENCODING = "UTF-8";
    private static SecureRandom random = new SecureRandom();
    private static char[] hexChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public Digests() {
    }

    public static String sha1(String input) {
        byte[] digest = sha1(input.getBytes(Charset.forName("UTF-8")));
        return toHexString(digest);
    }

    public static String sha1(String input, byte[] salt) {
        byte[] digest = sha1(input.getBytes(Charset.forName("UTF-8")), salt);
        return toHexString(digest);
    }

    public static String sha1(String input, byte[] salt, int iterations) {
        byte[] digest = sha1(input.getBytes(Charset.forName("UTF-8")), salt, iterations);
        return toHexString(digest);
    }

    public static byte[] sha1(byte[] input) {
        return digest(input, "SHA-1", (byte[])null, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, "SHA-1", salt, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, "SHA-1", salt, iterations);
    }

    public static String md5(String input) {
        byte[] digest = md5(input.getBytes(Charset.forName("UTF-8")));
        return toHexString(digest);
    }

    public static String md5(String input, byte[] salt) {
        byte[] digest = md5(input.getBytes(Charset.forName("UTF-8")), salt);
        return toHexString(digest);
    }

    public static String md5(String input, byte[] salt, int iterations) {
        byte[] digest = md5(input.getBytes(Charset.forName("UTF-8")), salt, iterations);
        return toHexString(digest);
    }

    public static byte[] md5(byte[] input) {
        return digest(input, "MD5", (byte[])null, 1);
    }

    public static byte[] md5(byte[] input, byte[] salt) {
        return digest(input, "MD5", salt, 1);
    }

    public static byte[] md5(byte[] input, byte[] salt, int iterations) {
        return digest(input, "MD5", salt, iterations);
    }

    private static String toHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder(digest.length * 2);
        byte[] arr$ = digest;
        int len$ = digest.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte aDigest = arr$[i$];
            sb.append(hexChar[(aDigest & 240) >>> 4]);
            sb.append(hexChar[aDigest & 15]);
        }

        return sb.toString();
    }

    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for(int i = 1; i < iterations; ++i) {
                digest.reset();
                result = digest.digest(result);
            }

            return result;
        } catch (GeneralSecurityException var7) {
            throw new BlException(ExceptionStatus.SERVER_ERROR);
        }
    }

    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", (long)numBytes);
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] md5(InputStream input) throws IOException {
        return digest(input, "MD5");
    }

    public static byte[] sha1(InputStream input) throws IOException {
        return digest(input, "SHA-1");
    }

    private static byte[] digest(InputStream input, String algorithm) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8192;
            byte[] buffer = new byte[bufferLength];

            for(int read = input.read(buffer, 0, bufferLength); read > -1; read = input.read(buffer, 0, bufferLength)) {
                messageDigest.update(buffer, 0, read);
            }

            return messageDigest.digest();
        } catch (GeneralSecurityException var6) {
            throw new BlException(ExceptionStatus.SERVER_ERROR);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(md5(IdGenerator.getID()));
    }
}

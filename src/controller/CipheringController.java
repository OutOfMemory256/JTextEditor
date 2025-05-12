package controller;

import controller.encryption.AbstractEncryptor;
import controller.encryption.CaesarEncryptor;
import controller.encryption.EncryptingAlgorithmType;
import controller.encryption.XOREncryptor;

import java.util.HashMap;

public class CipheringController {
    private static final HashMap<EncryptingAlgorithmType, AbstractEncryptor> encryptors = new HashMap<>();

    static  {
        encryptors.put(EncryptingAlgorithmType.CAESAR, new CaesarEncryptor());
        encryptors.put(EncryptingAlgorithmType.XOR, new XOREncryptor());
    }

    public static String encrypt(String algorithmType, String text, String key) {
        AbstractEncryptor encryptor = encryptors.get(EncryptingAlgorithmType.valueOf(algorithmType));
        return encryptor.encrypt(text, key);
    }

    public static String decrypt(String algorithmType, String text, String key) {
        AbstractEncryptor encryptor = encryptors.get(EncryptingAlgorithmType.valueOf(algorithmType));
        return encryptor.decrypt(text, key);
    }
}

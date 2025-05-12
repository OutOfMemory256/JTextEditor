package controller.encryption;

public abstract class AbstractEncryptor {
    public abstract String encrypt(String plainText, String key);
    public abstract String decrypt(String cipheredText, String key);
}

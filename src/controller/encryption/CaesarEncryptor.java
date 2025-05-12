package controller.encryption;

public class CaesarEncryptor extends AbstractEncryptor {
    @Override
    public String encrypt(String plainText, String key) {
        return process(plainText, Integer.parseInt(key));
    }

    @Override
    public String decrypt(String cipheredText, String key) {
        return process(cipheredText, -Integer.parseInt(key));
    }

    private static String process(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append((char) (c + shift));
        }
        return result.toString();
    }
}

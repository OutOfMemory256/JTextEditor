package controller.encryption;

public class XOREncryptor extends AbstractEncryptor {
    @Override
    public String encrypt(String text, String key) {
        return process(text, key);
    }

    @Override
    public String decrypt(String text, String key) {
        return process(text, key);
    }

    private String process(String text, String key) {
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i % key.length());
            result.append((char) (textChar ^ keyChar));
        }
        return result.toString();
    }
}
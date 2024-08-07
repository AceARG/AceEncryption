package ace.encrypt.encryptservice.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
@Service
public class EncryptionServiceImp implements EncryptionService {
    private final SecretKey secretKey;

    // Constructor to generate a new AES key
    public EncryptionServiceImp() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // Use 128, 192, or 256 bits based on your security requirements
        this.secretKey = keyGen.generateKey();
    }

    // Encrypts the given data
    @Override
    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted); // Return encrypted data as a Base64 string
    }

    // Decrypts the given Base64 encoded encrypted data
    @Override
    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decrypted); // Return decrypted string
    }

    // Optional: Getter for the secret key if needed for other purposes
    public SecretKey getSecretKey() {
        return secretKey;
    }
}


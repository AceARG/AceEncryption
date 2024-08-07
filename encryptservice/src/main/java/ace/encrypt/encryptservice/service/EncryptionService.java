package ace.encrypt.encryptservice.service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

// EncryptionService interface definition
interface EncryptionService {
    String encrypt(String data) throws Exception;
    String decrypt(String encryptedData) throws Exception;
}




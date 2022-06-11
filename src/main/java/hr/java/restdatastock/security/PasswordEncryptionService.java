package hr.java.restdatastock.security;

public interface PasswordEncryptionService {
    String createMD5(String password);
}

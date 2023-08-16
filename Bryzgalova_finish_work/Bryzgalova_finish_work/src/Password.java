import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

/**
 * class for check password
 */
public class Password {
    public boolean checkPassword(String s) throws NoSuchAlgorithmException {
        boolean flag = false;
        byte[] salt = new byte[16];

        KeySpec spec = new PBEKeySpec(s.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = new byte[0];
        try {
            hash = f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        Base64.Encoder enc = Base64.getEncoder();
        if(enc.encodeToString(hash).equals("g4MCaU7GcQ4oc9qsvsjD7Q==")) {
            flag = true;
        }
        return flag;
    }
}

package idk;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import jakarta.xml.bind.DatatypeConverter; //Ikke med lenger i JDK 9+ !!!

import org.springframework.stereotype.Service;

@Service
public class PassordService {

	/**
	 * @return et tilfeldig generert 16 bytes salt ved å bruke SHA1PRNG
	 */
	public String genererTilfeldigSalt() {
	    SecureRandom sr;
	    byte[] salt = new byte[16];
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		    sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    return DatatypeConverter.printHexBinary(salt);
	}

	/**
	 * Genererer en kryptografisk hash for gitt passord og salt.
	 * 
	 * Algoritmen som brukes er PBKDF2WithHmacSHA1
	 * 	- PBKDF2: Password-Based-Key-Derivation-Function
	 *  - WithHmac: HMAC står for Keyed-Hash Message Authentication Code
	 *  - SHA1: SHA1 hashing-algoritme
	 * 
	 * Det itereres 1000 ganger.
	 * Output fra denne algoritmen er 256 bits, dvs. 32 bytes.
	 * 
	 * Se https://en.wikipedia.org/wiki/PBKDF2 for mer info om virkemåte.
	 * 
	 * Til slutt omgjøres byte-tabellen til en HEX-streng på 64 tegn/siffer.
	 *  
	 * @param passord
	 * @param salt
	 * @return en 64 tegn lang HEX-streng som representerer en 32 byte/256 bit hash.
	 */
	public String hashMedSalt(String passord, String salt) { 
		
		if (passord == null || salt == null) { //Burde validert skikkelig!!
			throw new IllegalArgumentException();
		}
		
		char[] passchar = passord.toCharArray();
		byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);
		
		byte[] keyhash = null;
		try {
			PBEKeySpec pks = new PBEKeySpec(passchar, saltbytes, 1000, 256);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			keyhash = skf.generateSecret(pks).getEncoded();
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return DatatypeConverter.printHexBinary(keyhash);
	}
	
	/**
	 * Sjekker om et passord matcher en hash generert med korresponderende 
	 * hashMedSalt(). 
	 * 
	 * @param passord - Passord som skal sjekkes
	 * @param salt - Saltet som ble brukt ved generering av passordhash
	 * @param hash - Det "lagrete" passordet 
	 * @return om passordet matcher
	 */
	public boolean erKorrektPassord(
			String passord, String salt, String hash) {
		
		if (passord == null || salt == null || hash == null) { //Burde validert skikkelig!!
			throw new IllegalArgumentException();
		}
		
		return hash.equals(hashMedSalt(passord, salt));
	}
}

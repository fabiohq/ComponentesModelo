package co.com.test;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;


public class Encripcion {
//HG58YZ3CR9012345678901234567890123456789
	public static String decrypt(byte[] message, String publicKey)
			/*    */     throws Exception
			/*    */   {
			/* 49 */     MessageDigest md = MessageDigest.getInstance("md5");
			/* 50 */     byte[] digestOfPassword = md.digest(publicKey.getBytes("UTF-8"));
			/* 51 */     byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			/* 52 */     int j = 0;
			/* 52 */     for (int k = 16; j < 8;) {
			/* 53 */       keyBytes[(k++)] = keyBytes[(j++)];
			/*    */     }
			/* 56 */     SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			/* 57 */     IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			/* 58 */     Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			/* 59 */     decipher.init(2, key, iv);			
			/* 61 */     byte[] plainText = decipher.doFinal(message);
			/*    */     
			/* 63 */     return new String(plainText, "UTF-8");
			/*    */   }
	
	
	public static byte[] decode(String value) throws Exception {
		byte[] decodeResult = new BASE64Decoder().decodeBuffer(value);
		System.out.println(new String(new String(decodeResult)));
		
		return decodeResult;
	   }
	
	public static void main (String args[]){
		
		
		try {
			
			//byte[] CDRIVES = new byte[1];
			String CDRIVES = "HG58YZ3CR9012345678901234567890123456789";
			byte[] pin = decode("QIoEpdwZw8G/reAxj6L48w==");
			//byte[] myvar = "HG58YZ3CR9012345678901234567890123456789".getBytes();
			//byte[] CDRIVES = javax.xml.bind.DatatypeConverter.parseHexBinary("HG58YZ3CR9012345678901234567890123456789");
			String response = decrypt(pin,CDRIVES);
			System.out.println("response "+response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}




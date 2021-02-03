package top.fuguijar.utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * AES 加密工具类
 */
public class AesUtil {

    //密钥 (需要前端和后端保持一致)十六位作为密钥
    private static final String KEY = "ihaierForTodoKey";
    //密钥偏移量 (需要前端和后端保持一致)十六位作为密钥偏移量
    private static final String IV = "ihaierForTodo_Iv";
    //算法
    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";

    /**加密
     * @param encryptBytes
     * @return
     * @throws Exception
     */
    public static String aesEncryption(String encryptBytes) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        byte[] temp = IV.getBytes(StandardCharsets.UTF_8);
        IvParameterSpec iv = new IvParameterSpec(temp);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"), iv);
        byte[] decryptBytes = cipher.doFinal(encryptBytes.getBytes());
        return Base64.encodeBase64String(decryptBytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    private static byte[] base64Decode(String base64Code) throws Exception{
        /**sun.misc.BASE64Decoder是java内部类，有时候会报错，
         * 用org.apache.commons.codec.binary.Base64替代，效果一样。
         */
        return StringUtils.isEmpty(base64Code) ? null : new Base64().decodeBase64(new String(base64Code).getBytes());
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @return 解密后的String
     * @throws Exception
     */
    private static String aesDecryptByBytes(byte[] encryptBytes) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        byte[] temp = IV.getBytes(StandardCharsets.UTF_8);
        IvParameterSpec iv = new IvParameterSpec(temp);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"), iv);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr));
    }


}
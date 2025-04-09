package com.monolhybrid.est256.common.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ECKeyUtil {

    public static ECPrivateKey getPrivateKey() throws Exception {
        String key = Files.readString(Path.of("src/main/resources/keys/ec-private.pem"));

        key = key.replaceAll("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        return (ECPrivateKey) KeyFactory.getInstance("EC").generatePrivate(keySpec);
    }


    public static ECPublicKey getPublicKey() throws Exception{
        String key = Files.readString(Path.of("src/main/resources/keys/ec-public.pem"));
        key = key.replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        return (ECPublicKey) KeyFactory.getInstance("EC").generatePublic(keySpec);
    }
}

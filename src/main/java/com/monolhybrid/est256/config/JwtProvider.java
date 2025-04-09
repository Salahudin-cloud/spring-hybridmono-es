package com.monolhybrid.est256.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.monolhybrid.est256.common.util.ECKeyUtil;
import com.auth0.jwt.JWT;


import java.security.interfaces.ECPrivateKey;
import java.util.Date;

public class JwtProvider {
    public static String generateToken(String username, String role) throws Exception {
        ECPrivateKey privateKey = ECKeyUtil.getPrivateKey();

        return JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withIssuer("jwt-es256")
                .withExpiresAt(new Date(System.currentTimeMillis() + 360_000))
                .sign(Algorithm.ECDSA256(null, privateKey));

    }
}

package com.monolhybrid.est256.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.monolhybrid.est256.common.util.ECKeyUtil;

import java.security.interfaces.ECPublicKey;

public class JwtValidator {

    public static DecodedJWT verifyToken(String token) throws Exception {
        ECPublicKey publicKey = ECKeyUtil.getPublicKey();

        Algorithm algorithm = Algorithm.ECDSA256(publicKey, null);

        return JWT.require(algorithm)
                .withIssuer("jwt-es256")
                .build()
                .verify(token);
    }

}

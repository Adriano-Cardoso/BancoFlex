package com.bankflex.accountservice.utils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String BEARER = "bearer";
    public static final String ISSUER = "l2m-travelex-api";
    public static final String PROFILES_CLAIM = "profiles";
}

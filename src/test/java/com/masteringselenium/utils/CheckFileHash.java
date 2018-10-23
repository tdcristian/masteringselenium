package com.masteringselenium.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

public class CheckFileHash {

    public static String generateHashForFileOfType(File fileToCheck, HashType hashtype) throws IOException {
        if (!fileToCheck.exists()) throw new FileNotFoundException(fileToCheck+" doesn't exist!");
        
        switch (hashtype) {
            case MD5: return DigestUtils.md5Hex(new FileInputStream(fileToCheck));
            case SHA1: return DigestUtils.sha1Hex(new FileInputStream(fileToCheck));
            default: throw new UnsupportedOperationException(hashtype.toString()+" hash type is not supported");
        }
    }
}

package util;

import java.util.UUID;

public class UuidGen {

    public static String codeGen(){
        return UUID.randomUUID().toString();
    }
}

package org.big.common;

import java.util.UUID;

/**
 * Created by Tianshan on 17/5/31.
 */
public class UUIDUtils {
    public static String getUUID32(){
        UUID uuid = UUID.randomUUID();
        String uuidStr =  uuid.toString();
        uuidStr = uuidStr.replace("-", "");
        return uuidStr;
    }
}

package org.omgcms.kernel.util;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取小写字符UUID不带横线
     *
     * @return UUID
     */
    public static String getUuidLowerCaseWithoutDash() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        uuidStr = uuidStr.replace("-", "");
        return uuidStr;
    }

    /**
     * 获取UUID带横线小写
     *
     * @return uuid
     */
    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        return uuidStr;
    }

    /**
     * 获取大写字符UUID不带横线
     *
     * @return UUID
     */
    public static String getUuidUpperCaseWithoutDash() {
        return getUuidLowerCaseWithoutDash().toUpperCase();
    }

}

package com.youkeda.dewu.util;

import java.util.UUID;

/**
 * @Author songchuanming
 * @DATE 2020/6/11.
 */
public class UUIDUtils {
    public static final String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

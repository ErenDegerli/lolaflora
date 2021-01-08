package com.lolaflora.utils;

import com.lolaflora.core.PropKey;
import com.lolaflora.core.PropertyReader;

import java.io.IOException;

public class TimeUtil {

    public static Long getImplicitWait() throws IOException {
        return Long.parseLong(PropertyReader.getInstance().getProperty(PropKey.IMPLICIT_WAIT.getPropVal()));
    }

    public static Long getExplicitWait() throws IOException {
        return Long.parseLong(PropertyReader.getInstance().getProperty(PropKey.EXPLICIT_WAIT.getPropVal()));
    }
}

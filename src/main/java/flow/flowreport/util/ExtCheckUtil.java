package flow.flowreport.util;

import flow.flowreport.api.exception.ApiException;

import java.util.Arrays;

public class ExtCheckUtil {
    public static void checkExistInFavoriteExt(String newExt) {
        boolean isExist = Arrays.stream(favoriteExts)
                .anyMatch(favoriteExt -> favoriteExt.equals(newExt));

        if (isExist)
            throw new ApiException("고정 확장자에 등록되어 있어서 사용할수 없습니다.");
    }

    private static final String[] favoriteExts = {
            "bat",
            "cmd",
            "com",
            "cpl",
            "exe",
            "js",
            "scr"
    };
}

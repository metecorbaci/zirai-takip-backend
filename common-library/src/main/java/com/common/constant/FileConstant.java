package com.common.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileConstant {
    public static final String FILE_UPLOAD_DEST = "file-service/src/main/java/com/fileservice/upload/";

    public static final Map<String, String> ALLOWED_TYPES = new HashMap<>();

    static {
        ALLOWED_TYPES.put("image/jpeg", "jpg");
        ALLOWED_TYPES.put("image/png", "png");
        ALLOWED_TYPES.put("application/pdf", "pdf");
        // Diğer MIME türleri ve uzantıları ekleyin
    }

    public static final String PROFILE_PHOTO_TYPE = "PP";
}

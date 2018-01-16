package za.co.command_quality.util;

import java.io.File;

/**
 * Created by Azael on 2018-01-16.
 */
public class FileUtil {

    public static void createFolder(String folderName) {
        try {
            new File(folderName).mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

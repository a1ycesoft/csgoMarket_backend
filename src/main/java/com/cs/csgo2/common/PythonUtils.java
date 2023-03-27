package com.cs.csgo2.common;

import java.io.IOException;

public class PythonUtils {
    public static void getHistory(String source,String goodsId){
        String path = System.getProperty("user.dir") + "/src/main/resources/utils/main.exe";
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(path+' '+source+' '+goodsId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

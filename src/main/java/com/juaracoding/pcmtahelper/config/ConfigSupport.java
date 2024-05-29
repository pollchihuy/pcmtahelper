package com.juaracoding.pcmtahelper.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigSupport {

    public static void getArgsRunner(String[] args){
        Map<String,String> map = new HashMap<>();
        if(args.length!=0){
            String[] strArr = new String[2];
            for (int i = 0; i < args.length; i++) {
                strArr = args[i].split("=");
                if(strArr.length!=2){
                    System.out.println("Terdapat Kesalahan dalam penulisan Parameter !! Error-X001");
                    System.exit(1);
                }
                if(strArr[1]==null || strArr.equals("")){
                    System.out.println("Seluruh Parameter Wajib Diisi !! Error-X002");
                    System.exit(1);
                }
                map.put(strArr[0],strArr[1]);
            }

//            /** java -jar paulsqa.one-jar.jar URL=localhost:8084 DELAY=2 ISDELAY=Y TIMEOUT=10 BROWSER=Firefox DELAY_PARAM=y*/
//            public BaseConfig(int xTimeout,int xDelay,String xBaseUrl,String xBrowser,String xDelayParam) {
            BaseConfig baseConfig = new BaseConfig(
                    Integer.parseInt(map.get("TIMEOUT").toString()),
                    Integer.parseInt(map.get("DELAY").toString()),
                    map.get("URL").toString(),
                    map.get("BROWSER").toString(),
                    map.get("DELAY_PARAM").toString()
            );
            /** untuk print isi dari map */
//            for (Map.Entry<String,String> x:
//                    map.entrySet()) {
//                System.out.println("Key : "+x.getKey()+" Value : "+x.getValue());
//            }
        } else {
            System.out.println("Parameter Tidak Terpenuhi Error-X003");
            System.exit(1);
        }
    }
}
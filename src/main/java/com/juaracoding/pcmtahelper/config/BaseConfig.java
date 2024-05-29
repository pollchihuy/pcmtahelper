package com.juaracoding.pcmtahelper.config;

public class BaseConfig {

    private static int timeout;
    private static int delay;
    private static String baseUrl;
    private static String browser;
    private static String delayParam;

    public BaseConfig(int xTimeout,int xDelay,String xBaseUrl,String xBrowser,String xDelayParam) {
        timeout = xTimeout;
        delay = xDelay;
        baseUrl = xBaseUrl;
        browser = xBrowser;
        delayParam = xDelayParam;
    }

    public static int getTimeout() {
        return timeout;
    }

    public static int getDelay() {
        return delay;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getBrowser() {
        return browser;
    }

    public static String getDelayParam() {
        return delayParam;
    }
}

package com.github.galcyurio;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author galcyurio
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String apiKey = loadApiKey();
        boolean checkResult = checkApiKey(apiKey);
        System.out.println("checkResult = " + checkResult);
    }

    /** 비밀번호를 불러오는 함수 */
    static String loadApiKey() throws IOException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("secret.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties.getProperty("apiKey");
    }

    /** 가짜 체크 함수 */
    static boolean checkApiKey(String password) {
        return password.equals("96fc4075-24d5-4990-850e-526a2745311e");
    }
}

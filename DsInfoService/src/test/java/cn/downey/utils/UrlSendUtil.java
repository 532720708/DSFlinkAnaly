package cn.downey.utils;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2018/10/14 0014.
 */
public class UrlSendUtil {
    public static void sendmessage(String address, String message) {
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setAllowUserInteraction(true);
            conn.setUseCaches(false);
            conn.setReadTimeout(6 * 1000);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            OutputStream outputStream = conn.getOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(outputStream);
            out.write(message.getBytes());
            out.flush();
            String temp = "";
            InputStream in = conn.getInputStream();
            byte[] tempBytes = new byte[1024];
            while (in.read(tempBytes, 0, 1024) != -1) {
                temp += new String(tempBytes);
            }
            System.out.println(conn.getResponseCode());
            System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.atguigu.mp.play;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Pattern;

/**
 * @Author: 李开鹏
 * @Date: 2020/5/23 18:21
 * @Version 1.0
 * @Description
 **/
@Data
public class ConnectionDanMu {
    private String request_url = "";
    private String room_id = "";
    private HttpURLConnection connection;

    //TODO：构造方法
    public ConnectionDanMu() {
    }

    //连接指定地址
    public boolean getConnection() {
        try {
            //TODO：设置连接URL
            URL url = new URL(request_url);
            //TODO：获取连接
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            //TODO：设置请求方式
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.connect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //提交表单发送数据
    public String getDanMuData() {

        try {
            OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
            String post_value1 = "roomid=" + URLEncoder.encode(room_id, "UTF-8");
            String post_value2 = "&csrf_token=" + URLEncoder.encode("", "UTF-8");
            String post_value3 = "&csrf=" + URLEncoder.encode("", "UTF-8");
            String post_value4 = "&visit_id=" + URLEncoder.encode("", "UTF-8");

            String post_value = post_value1 + post_value2 + post_value3 + post_value4;

//            把数据写到服务器
            outputWriter.write(post_value);
//            刷新
            outputWriter.flush();
//            关闭流
            outputWriter.close();

            //TODO：获取服务器返回的数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String rowLine = "";
            String data = "";
            while ((rowLine = reader.readLine()) != null) {
                data = data + rowLine;
            }
            reader.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    // 格式化数据
    public static ObservableList<DanMuDataFormat> dataFormat(String data) {

        String str_value = data.replaceAll("vip", "\r\n");
//        System.out.println(str_value);
        String regex = "text(.*)isadmin";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str_value);
        //TODO：创建一个可观察的列表
        ObservableList<DanMuDataFormat> list = FXCollections.observableArrayList();
        while (matcher.find()) {
            DanMuDataFormat dmdf = new DanMuDataFormat();
            //TODO：格式弹幕信息
            String temp_data = matcher.group();
            String text_regex = "text(.*)nickname";
            Pattern text_pattern = Pattern.compile(text_regex);
            Matcher text_matcher = text_pattern.matcher(temp_data);
            while (text_matcher.find()) {
                String temp_text = text_matcher.group();
                String text = temp_text.substring(7, temp_text.length() - 11);
//                System.out.println(text);
                dmdf.setText(text);
            }

            //TODO：格式发送者
            String nickname_regex = "nickname(.*)uname_color";
            Pattern nickname_pattern = Pattern.compile(nickname_regex);
            Matcher nickname_matcher = nickname_pattern.matcher(temp_data);
            while (nickname_matcher.find()) {
                String temp_nickname = nickname_matcher.group();
                String nickname = temp_nickname.substring(11, temp_nickname.length() - 14);
//                System.out.println(nickname);
                dmdf.setNickname(nickname);
            }

            //TODO：格式化发送时间
            String timeline_regex = "timeline(.*)isadmin";
            Pattern timeline_pattern = Pattern.compile(timeline_regex);
            Matcher timeline_matcher = timeline_pattern.matcher(temp_data);
            while (timeline_matcher.find()) {
                String temp_timeline = timeline_matcher.group();
                String timeline = temp_timeline.substring(11, temp_timeline.length() - 10);
//                System.out.println(timeline);
                dmdf.setTimeline(timeline);
            }
            //TODO：把数据放到可观察列表中
            list.add(dmdf);
        }

        return list;
    }

    public void close(){
        if(connection!=null){
            connection.disconnect();
        }
    }
}

package com.itwyx.classroomsearch.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleData {
    // 处理得到的json数据，返回空教室集合
    public static List<String> handleData(String dataJson) {
        String pattern = "\"cdbh\":\"G\\d+\"";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(dataJson);
        List<String> list = new ArrayList<>();
        while (m.find()) {
//            System.out.println(m.group());
            String classroomNumber = m.group().substring(8, 12);
            list.add(classroomNumber);
        }
        return list;
    }
}

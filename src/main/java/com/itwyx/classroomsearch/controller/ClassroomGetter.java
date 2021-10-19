package com.itwyx.classroomsearch.controller;


import com.itwyx.classroomsearch.pojo.DataParam;
import com.itwyx.classroomsearch.utils.HandleData;
import com.itwyx.classroomsearch.utils.ObtainData;

import java.util.List;

public class ClassroomGetter {
    public static void main(String[] args) {
        DataParam dataParam = new DataParam(8, 2, 10);
        String cookies = "JSESSIONID=2C746A09A2C08D9D8C12D2A35BE575AF";
        String json = ObtainData.getData(dataParam, cookies);
        List<String> strings = HandleData.handleData(json);
        strings.forEach(System.out::println);
//        System.out.println(json);
    }

}

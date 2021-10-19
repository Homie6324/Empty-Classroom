package com.itwyx.classroomsearch.utils;


import com.itwyx.classroomsearch.pojo.DataParam;
import okhttp3.FormBody;

import java.io.IOException;

public class ObtainData {
    private static final OkHttpUtil OK_HTTP_UTIL = OkHttpUtil.getInstance();
    public static String getData(DataParam dataParam, String cookies){
        FormBody formBody = new FormBody.Builder()
                .add("fwzt", "cx")
                .add("xqh_id", "3D669E6DAB06A186E053AB14CECA64B4")//写死了就是本学期号不会变
                .add("cdlb_id", "")//空为全部教室
                .add("cdejlb_id", "")//空为全部教室
                .add("xnm", "2021")//学年名
                .add("xqm", "3")//学期名也写死了其他不让请求接口
                .add("lh", "01")//工学馆
                .add("jyfs", "0")
                .add("nd", "")
                .add("zcd", (new Double(Math.pow(2, dataParam.getZcd()-1))).toString())//抓包分析一下就知道了
                .add("xqj", dataParam.getXqj().toString())//抓包分析一下就知道了
                .add("jcd", (new Double(Math.pow(2, dataParam.getJcd()-1))).toString())//抓包分析一下就知道了
                .add("_search", "false")
                .add("queryModel.showCount", "1000")
                .add("queryModel.currentPage", "1")
                .add("queryModel.sortName", "cdbh")
                .add("queryModel.sortOrder", "asc")
                .build();
        StringBuffer strResult;
        try {
            strResult = OK_HTTP_UTIL.post("http://jwxt.neuq.edu.cn/jwglxt/cdjy/cdjy_cxKxcdlb.html?doType=query&gnmkdm=N2155", formBody, cookies);
//            System.out.println(strResult);
        } catch (IOException e) {
            strResult = null;
        }
        assert strResult != null;
        return strResult.toString();
    }
}

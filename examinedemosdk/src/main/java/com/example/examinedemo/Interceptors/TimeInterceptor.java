package com.example.examinedemo.Interceptors;

import com.alibaba.fastjson.JSONObject;
import com.example.examinedemo.entity.ExceptionResult;
import com.example.examinedemo.utils.R;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

@Component
public class TimeInterceptor implements HandlerInterceptor {

    static final long overTime = 1672502399000L;

    private Long getNetTime(){
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection urlConnection = url.openConnection();
            urlConnection.getContent();
            long date = urlConnection.getDate();
            return date;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ExceptionResult result;
        Long netTime = getNetTime();
        if (netTime==null){
            response.setCharacterEncoding("UTF-8");
            result = R.customReturn("1099", "请连接网络后重试!",null);
            PrintWriter pw = response.getWriter();
            pw.write(JSONObject.toJSONString(result));
            return false;
        }
        if (netTime>overTime){
            response.setCharacterEncoding("UTF-8");
            result = R.failed("","服务已过期，接口不可用");
            PrintWriter pw = response.getWriter();
            pw.write(JSONObject.toJSONString(result));
            return false;
        }
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }
}

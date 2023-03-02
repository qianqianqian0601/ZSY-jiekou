package com.example.examinedemo;

import com.example.examinedemo.controller.ExamineController;
import com.example.examinedemo.entity.ConstantData;
import com.example.examinedemo.serviceImpl.ExamineDemo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;

@SpringBootTest
class ExamineDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        ExamineController.initService("/home/user/IdeaProjects/ExamineDemo/src/main/java/com/example/examinedemo/models");
        int[] actionResults = new int[14];
        int[] diagnosisResults = new int[24];
        ExamineDemo.DLibray.INSTANCE.dd_getActionResults(actionResults);
        ExamineDemo.DLibray.INSTANCE.dd_getDiagnosisResults(diagnosisResults);
        System.out.println(Arrays.toString(actionResults));
        System.out.println(Arrays.toString(diagnosisResults));
        HashMap<String, String> actionResultsMap = new HashMap<>();
        String[] actionOrder = ConstantData.actionOrder;
        for (int i = 0; i < actionOrder.length; i++) {
            switch (actionResults[i]){
                case 0:
                    actionResultsMap.put(actionOrder[i],"动作错误");
                    break;
                case 1:
                    actionResultsMap.put(actionOrder[i],"无动作");
                    break;
                case 2:
                    actionResultsMap.put(actionOrder[i],"动作未完成");
                    break;
                case 3:
                    actionResultsMap.put(actionOrder[i],"动作完成");
                    break;
                case 4:
                    actionResultsMap.put(actionOrder[i],"超出范围");
                    break;
                default:
                    actionResultsMap.put(actionOrder[i],"未知");
            }
        }
        HashMap<String, String> diagnosisResultsMap = new HashMap<>();
        String[] diagnosisOrder = ConstantData.diagnosisOrder;
        for (int i = 0; i < diagnosisOrder.length-2; i++) {
            switch (diagnosisResults[i]){
                case 0:
                    diagnosisResultsMap.put(diagnosisOrder[i],"错误(相应的动作)");
                    break;
                case 1:
                    diagnosisResultsMap.put(diagnosisOrder[i],"损伤");
                    break;
                case 2:
                    diagnosisResultsMap.put(diagnosisOrder[i],"部分损伤");
                    break;
                case 3:
                    diagnosisResultsMap.put(diagnosisOrder[i],"正常");
                    break;
                case 4:
                    diagnosisResultsMap.put(diagnosisOrder[i],"超出范围");
                    break;
                default:
                    diagnosisResultsMap.put(diagnosisOrder[i],"未知");
            }
        }
        for (int i = 22; i < diagnosisOrder.length; i++) {
            switch (diagnosisResults[i]){
                case 0:
                    diagnosisResultsMap.put(diagnosisOrder[i],"不健康");
                    break;
                case 1:
                    diagnosisResultsMap.put(diagnosisOrder[i],"健康");
                    break;
                default:
                    diagnosisResultsMap.put(diagnosisOrder[i],"未知");
            }
        }
        System.out.println(" ");
    }

    @Test
    public void end(){
        ExamineDemo.CLibray.INSTANCE.poseDiagnoserClose();
        ExamineDemo.DLibray.INSTANCE.diseaseDiagnoserClose();
    }

    @Test
    public void array(){
        int[] ints = new int[14];
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void st2() {
        String modelPath = "/home/user/IdeaProjects/ExamineDemo/src/main/java/com/example/examinedemo/models";
        ExamineDemo.CLibray.INSTANCE.poseDiagnoserInitial(modelPath);
        ExamineDemo.DLibray.INSTANCE.diseaseDiagnoserInitial();
        String imgPathStart = "/home/user/wyx/img.jpg";
        String imgPathEnd = "/home/user/wyx/img.jpg";
        String savePath = "/home/user/wyx/out/";
        File file = new File(savePath);
        if(!file.exists()){
            file.mkdirs();
        }
        ExamineDemo.CLibray.INSTANCE.pd_shoulderAbduction(imgPathStart,imgPathEnd,savePath);
        float sa_leftInit = ExamineDemo.CLibray.INSTANCE.pd_getSALeftInitAngle();
        float sa_rightInit = ExamineDemo.CLibray.INSTANCE.pd_getSARightInitAngle();
        float sa_leftEnd = ExamineDemo.CLibray.INSTANCE.pd_getSALeftEndAngle();
        float sa_rightEnd = ExamineDemo.CLibray.INSTANCE.pd_getSARightEndAngle();
        ExamineDemo.DLibray.INSTANCE.dd_shoulderAbduction(sa_leftInit,sa_rightInit,sa_leftEnd,sa_rightEnd);
    }

    @Test
    public void time(){
        long nowTime = System.currentTimeMillis();
        long overTime = 1672502399000L;
        if (nowTime<overTime){
            System.out.println("ing");
        }else {
            System.out.println("ed");
        }
    }

    @Test
    public void getNetTime(){
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection urlConnection = url.openConnection();
            urlConnection.getContent();
            long date = urlConnection.getDate();
            System.out.println(date);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("null");
        }
    }
}

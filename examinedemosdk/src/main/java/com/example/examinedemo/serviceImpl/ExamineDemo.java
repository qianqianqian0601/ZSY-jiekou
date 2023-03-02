package com.example.examinedemo.serviceImpl;

import com.sun.jna.Library;
import com.sun.jna.Native;

import java.io.File;

public class ExamineDemo {

    /**
     *检模块函数映射
     */
    public interface CLibray extends Library{
        CLibray INSTANCE =
                Native.loadLibrary(
                        "libPoseDiagnoser.so",
                        CLibray.class);
        //初始化检测模块
        void poseDiagnoserInitial(String modelPath);

        //传入开始和结束图片进行检测 并输出

        //1.肩外展，输入初始动作图片路径和检测动作图片路径。
        void pd_shoulderAbduction(String imgPathStart,String imgPathEnd,String savePath);
        float pd_getSALeftInitAngle();
        float pd_getSARightInitAngle();
        float pd_getSALeftEndAngle();
        float pd_getSARightEndAngle();

        //2.肩上举，输入动作图片路径。
        void pd_shoulderLifting(String imagePath , String savePath);
        float pd_getSLLeftAngle();
        float pd_getSLRightAngle();

        //3.肘关节屈曲，输入左相机图片路径和右相机图片路径。
        void pd_elbowFlexion(String imgPathLeft,String imgPathRight, String savePath);
        float pd_getEFLeftAngle();
        float pd_getEFRightAngle();

        //4.肘关节伸直，输入初始动作图片路径和检测动作图片路径。
        void pd_elbowExtension(String imgPathStart,String imgPathEnd,String savePath);
        float pd_getEELeftInitAngle();
        float pd_getEERightInitAngle();
        float pd_getEELeftEndAngle();
        float pd_getEERightEndAngle();

        //5.腕关节背伸，输入动作图片路径。
        void pd_wristDorsiflexion(String imagePath,String savePath);
        float pd_getWDLeftAngle();
        float pd_getWDRightAngle();

        //6.腕关节掌曲，输入动作图片路径。
        void pd_wristPalmarFlexion(String imagePath , String savePath);
        float pd_getWPLeftAngle();
        float pd_getWPRightAngle();

        //7.左小臂外旋检测
        void pd_leftForearmExternalRotation(String initImgPath, String endImgPath, String savePath);
        float pd_getERLeftAngle();
        //8.左小臂内旋检测
        void pd_leftForearmInternalRotation(String initImgPath, String endImgPath, String savePath);
        float pd_getIRLeftAngle();
        //9.右小臂外旋检测
        void pd_rightForearmExternalRotation(String initImgPath, String endImgPath, String savePath);
        float pd_getERRightAngle();
        //10.右小臂内旋检测
        void pd_rightForearmInternalRotation(String initImgPath, String endImgPath, String savePath);
        float pd_getIRRightAngle();

        //关闭检测模块。
        void poseDiagnoserClose();
    }

    /**
     * 诊断模块函数映射
     */
    public interface DLibray extends Library{
        DLibray INSTANCE =
                Native.loadLibrary(
                        "libDiseaseDiagnoser.so",
                        DLibray.class);
        //初始化诊断模块
        void diseaseDiagnoserInitial();

        //1.肩外展诊断
        void dd_shoulderAbduction(float sa_leftInit,float sa_rightInit,float sa_leftEnd,float sa_rightEnd);

        //2.肩上举诊断
        void dd_shoulderLifting(float sl_left,float sl_right);

        //3.肘关节屈曲诊断
        void dd_elbowFlexion(float ef_left,float ef_right);

        //4.肘关节伸直诊断
        void dd_elbowExtension(float ee_leftInit,float ee_rightInit,float ee_leftEnd,float ee_rightEnd);

        //5.腕关节背伸诊断
        void dd_wristDorsiflexion(float wd_left,float wd_right);

        //6.腕关节曲掌诊断
        void dd_wristPalmarFlexion(float wp_left,float wp_right);

        //7.所有动作完成 综合诊断
        void dd_totalDiagnosis();

        //8.获取所有诊断结果。
        void dd_getActionResults(int[] array);// 获取所有动作的完成情况。
        void dd_getDiagnosisResults(int[] array);// 获取所有诊断结果。

        //关闭诊断模块。
        void diseaseDiagnoserClose();
    }

    public static void main(String[] args) {
        String modelPath = "/home/user/IdeaProjects/ExamineDemo/src/main/java/com/example/examinedemo/models";
        CLibray.INSTANCE.poseDiagnoserInitial(modelPath);
        DLibray.INSTANCE.diseaseDiagnoserInitial();
        String imgPathStart = "/home/user/IdeaProjects/ExamineDemo/src/main/java/com/example/examinedemo/images/img.jpg";
        String imgPathEnd = "/home/user/IdeaProjects/ExamineDemo/src/main/java/com/example/examinedemo/images/img.jpg";
        String savePath = "/home/user/wyx/out/";
        File file = new File(savePath);
        if(!file.exists()){
            file.mkdirs();
        }
        CLibray.INSTANCE.pd_shoulderAbduction(imgPathStart,imgPathEnd,savePath);
        float sa_leftInit = CLibray.INSTANCE.pd_getSALeftInitAngle();
        float sa_rightInit = CLibray.INSTANCE.pd_getSARightInitAngle();
        float sa_leftEnd = CLibray.INSTANCE.pd_getSALeftEndAngle();
        float sa_rightEnd = CLibray.INSTANCE.pd_getSARightEndAngle();
        DLibray.INSTANCE.dd_shoulderAbduction(sa_leftInit,sa_rightInit,sa_leftEnd,sa_rightEnd);

    }
}

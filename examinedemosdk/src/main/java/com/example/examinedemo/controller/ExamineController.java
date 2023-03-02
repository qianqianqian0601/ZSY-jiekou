package com.example.examinedemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.examinedemo.entity.ConstantData;
import com.example.examinedemo.entity.ExceptionResult;
import com.example.examinedemo.serviceImpl.ExamineDemo;
import com.example.examinedemo.utils.R;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;

@RestController
@RequestMapping("/examine")
public class ExamineController {


    static ExamineDemo.CLibray checkModule =  ExamineDemo.CLibray.INSTANCE;
    static ExamineDemo.DLibray diagnosisModule =  ExamineDemo.DLibray.INSTANCE;

    /**
     * 初始化服务
     *
     * @param modelsPath 模型路径
     * @return
     * wyx
     */
    @PostMapping("/initService")
    public static ExceptionResult initService(String modelsPath) {
        String action = "初始化服务";
        if (modelsPath == null || modelsPath.equals("")) return R.parameterError("modelsPath");
        try {
            checkModule.poseDiagnoserInitial(modelsPath);
            diagnosisModule.diseaseDiagnoserInitial();
            return R.success(action);
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed(action);
        }
    }


    /**
     * 肩外展诊断
     *
     * @param /imgPathStart 动作开始图片路径
     * @param /imgPathEnd   动作结束图片路径
     * @param /savePath     保存路径
     * @return
     * wyx
     */
    @PostMapping("/shoulderAbduction")
    public static ExceptionResult shoulderAbduction(String imgPathStart,String imgPathEnd,String savePath) {
        String action = "肩外展诊断";
        if (imgPathStart==null || imgPathStart.equals("")) return R.parameterError("imgPathStart");
        File imgFile = new File(imgPathStart);
        if (!imgFile.exists()) return R.fileIsNotExist(imgPathStart);

        if (imgPathEnd == null || imgPathEnd.equals("")) return R.parameterError("imgPathEnd");
        File imgFile2 = new File(imgPathEnd);
        if (!imgFile2.exists()) return R.fileIsNotExist(imgPathEnd);

        if (savePath == null || savePath.equals("")) return R.parameterError("savePath");
        File file = new File(savePath);
        //文件夹不存在就创建
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            checkModule.pd_shoulderAbduction(imgPathStart,imgPathEnd,savePath);
            float sa_leftInit = checkModule.pd_getSALeftInitAngle();
            float sa_rightInit = checkModule.pd_getSARightInitAngle();
            float sa_leftEnd = checkModule.INSTANCE.pd_getSALeftEndAngle();
            float sa_rightEnd = checkModule.INSTANCE.pd_getSARightEndAngle();
            diagnosisModule.dd_shoulderAbduction(sa_leftInit,sa_rightInit,sa_leftEnd,sa_rightEnd);
            JSONObject result = new JSONObject();
            result.put("SALeftInitAngle",sa_leftInit);
            result.put("SARightInitAngle",sa_rightInit);
            result.put("SALeftEndAngle",sa_leftEnd);
            result.put("SARightEndAngle",sa_rightEnd);
            return R.success(action,result);
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed(action);
        }
    }


    /**
     * 肩上举诊断
     *
     * @param imgPath  检测图片路径
     * @param savePath 保存路径
     * @return
     * wyx
     */
    @PostMapping("/shoulderLifting")
    public static ExceptionResult shoulderLifting(String imgPath, String savePath) {
        String action = "肩上举诊断";
        if (imgPath == null || imgPath.equals("")) return R.parameterError("imgPath");
        File imgFile = new File(imgPath);
        if (!imgFile.exists()) return R.fileIsNotExist(imgPath);

        if (savePath == null || savePath.equals("")) return R.parameterError("savePath");
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            checkModule.pd_shoulderLifting(imgPath, savePath);//传入图片路径进行检测
            float sl_left = checkModule.pd_getSLLeftAngle();
            float sl_right = checkModule.pd_getSLRightAngle();
            diagnosisModule.dd_shoulderLifting(sl_left, sl_right);//诊断
            JSONObject result = new JSONObject();
            result.put("SLLeftAngle",sl_left);
            result.put("SLRightAngle",sl_right);
            return R.success(action,result);
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed(action);
        }
    }


    /**
     * 肘关节屈曲诊断
     *
     * @param imgPathLeft  左相机图片路径
     * @param imgPathRight 右相机图片路径
     * @param savePath     保存路径
     * @return
     * wyx
     */
    @PostMapping("/elbowFlexion")
    public static ExceptionResult elbowFlexion(String imgPathLeft, String imgPathRight, String savePath) {
        String action = "肘关节屈曲诊断";
        if ( imgPathLeft == null || imgPathLeft.equals("")) return R.parameterError("imgPathLeft");
        File imgFile = new File(imgPathLeft);
        if (!imgFile.exists()) return R.fileIsNotExist(imgPathLeft);

        if (imgPathRight == null || imgPathRight.equals("")) return R.parameterError("imgPathRight");
        File imgFile2 = new File(imgPathRight);
        if (!imgFile2.exists()) return R.fileIsNotExist(imgPathRight);

        if (savePath == null || savePath.equals("")) return R.parameterError("savePath");
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            checkModule.pd_elbowFlexion(imgPathLeft, imgPathRight, savePath);//传入图片路径进行检测
            float ef_left = checkModule.pd_getEFLeftAngle();
            float ef_right = checkModule.pd_getEFRightAngle();
            diagnosisModule.dd_elbowFlexion(ef_left, ef_right);//诊断
            JSONObject result = new JSONObject();
            result.put("EFLeftAngle",ef_left);
            result.put("EFRightAngle",ef_right);
            return R.success(action,result);
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed(action);
        }
    }


    /**
     * 肘关节伸直诊断
     *
     * @param imgPathStart 动作开始图片路径
     * @param imgPathEnd   动作结束图片路径
     * @param savePath     保存路径
     * @return
     * wyx
     */
    @PostMapping("/elbowExtension")
    public static ExceptionResult elbowExtension(String imgPathStart, String imgPathEnd, String savePath){
        String action = "肘关节伸直诊断";
        if (imgPathStart==null || imgPathStart.equals("")) return R.parameterError("imgPathStart");
        File imgFile = new File(imgPathStart);
        if (!imgFile.exists()) return R.fileIsNotExist(imgPathStart);

        if (imgPathEnd==null || imgPathEnd.equals("")) return R.parameterError("imgPathEnd");
        File imgFile2 = new File(imgPathStart);
        if (!imgFile2.exists()) return R.fileIsNotExist(imgPathEnd);

        if (savePath.equals("") || savePath==null) return R.parameterError("savePath");
        File file = new File(savePath);
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            checkModule.pd_elbowExtension(imgPathStart,imgPathEnd,savePath);//传入图片路径进行检测
            float ee_leftInit = checkModule.pd_getEELeftInitAngle();
            float ee_rightInit = checkModule.pd_getEERightInitAngle();
            float ee_leftEnd = checkModule.pd_getEELeftEndAngle();
            float ee_rightEnd = checkModule.pd_getEERightEndAngle();
            diagnosisModule.dd_elbowExtension(ee_leftInit,ee_rightInit,ee_leftEnd,ee_rightEnd);//诊断
            JSONObject result = new JSONObject();
            result.put("EELeftInitAngle",ee_leftInit);
            result.put("EERightInitAngle",ee_rightInit);
            result.put("EELeftEndAngle",ee_leftEnd);
            result.put("EERightEndAngle",ee_rightEnd);
            return R.success(action,result);
        }catch (Exception e){
            e.printStackTrace();
            return R.failed(action);
        }
    }


    /**
     * 腕关节背伸诊断
     *
     * @param imgPath  动作图片路径
     * @param savePath 保存路径
     * @return
     * wyx
     */
    @PostMapping("/wristDorsiflexion")
    public static ExceptionResult wristDorsiflexion(String imgPath, String savePath){
        String action = "腕关节背伸诊断";
        if (imgPath==null || imgPath.equals("")) return R.parameterError("imgPath");
        File imgFile = new File(imgPath);
        if (!imgFile.exists()) return R.fileIsNotExist(imgPath);

        if (savePath==null || savePath.equals("")) return R.parameterError("savePath");
        File file = new File(savePath);
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            checkModule.pd_wristDorsiflexion(imgPath,savePath);//传入图片路径进行检测
            float wd_left = checkModule.pd_getWDLeftAngle();
            float wd_right = checkModule.pd_getWDRightAngle();
            diagnosisModule.dd_wristDorsiflexion(wd_left,wd_right);//诊断
            JSONObject result = new JSONObject();
            result.put("WDLeftAngle",wd_left);
            result.put("WDRightAngle",wd_right);
            return R.success(action,result);
        }catch (Exception e){
            e.printStackTrace();
            return R.failed(action);
        }
    }


    /**
     * 腕关节掌曲诊断
     *
     * @param imgPath  动作图片路径
     * @param savePath 保存路径
     * @return
     * wyx
     */
    @PostMapping("/wristPalmarFlexion")
    public static ExceptionResult wristPalmarFlexion(String imgPath, String savePath){
        String action = "腕关节掌曲诊断";
        if (imgPath==null || imgPath.equals("")) return R.parameterError("imgPath");
        File imgFile = new File(imgPath);
        if (!imgFile.exists()) return R.fileIsNotExist(imgPath);

        if (savePath==null || savePath.equals("")) return R.parameterError("savePath");
        File file = new File(savePath);
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            checkModule.pd_wristPalmarFlexion(imgPath,savePath);//传入图片路径进行检测
            float wp_left = checkModule.pd_getWPLeftAngle();
            float wp_right = checkModule.pd_getWPRightAngle();
            diagnosisModule.dd_wristPalmarFlexion(wp_left,wp_right);//诊断
            JSONObject result = new JSONObject();
            result.put("WPLeftAngle",wp_left);
            result.put("WPRightAngle",wp_right);
            return R.success(action,result);
        }catch (Exception e){
            e.printStackTrace();
            return R.failed(action);
        }
    }


    /**
     * 获取诊断结果
     *
     * @return
     * wyx
     */
    @GetMapping("/getResult")
    public static ExceptionResult getResult(){
        String action = "获取诊断结果";
        int[] actionResults = new int[14];
        int[] diagnosisResults = new int[24];
        try {
            diagnosisModule.dd_totalDiagnosis();//所有动作完成 综合诊断
            diagnosisModule.dd_getActionResults(actionResults);
            diagnosisModule.dd_getDiagnosisResults(diagnosisResults);
            HashMap<String, String> actionResultsMap = new HashMap<>();
            String[] actionOrder = ConstantData.actionOrder;
            for (int i = 0; i < actionOrder.length; i++) {
                switch (actionResults[i]){
                    case 0:
                        actionResultsMap.put(actionOrder[i],"动作错误(图像未检测出该部位或者没有图像)");
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
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ActionResults",actionResultsMap);
            jsonObject.put("diagnosisResults",diagnosisResultsMap);
            return R.success(action,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed(action);
        }
    }


    /**
     * 获取左小臂外旋检测角度
     *
     * @param imgPathStart 动作开始图片路径
     * @param imgPathEnd   动作结束图片路径
     * @param savePath     保存路径
     * @return
     * wyx
     */
    @PostMapping("/getERLeftAngle")
    public ExceptionResult getERLeftAngle(String imgPathStart, String imgPathEnd, String savePath){
        String action = "获取左小臂外旋检测角度";
        if (imgPathStart==null || imgPathStart.equals("")) return R.parameterError("imgPathStart");
        File imgFile = new File(imgPathStart);
        if (!imgFile.exists()) return R.fileIsNotExist(imgPathStart);

        if (imgPathEnd == null || imgPathEnd.equals("")) return R.parameterError("imgPathEnd");
        File imgFile2 = new File(imgPathEnd);
        if (!imgFile2.exists()) return R.fileIsNotExist(imgPathEnd);

        if (savePath == null || savePath.equals("")) return R.parameterError("savePath");
        File file = new File(savePath);
        //文件夹不存在就创建
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            checkModule.pd_leftForearmExternalRotation(imgPathStart,imgPathEnd,savePath);
            float Angle = checkModule.pd_getERLeftAngle();
            return R.success(action,Angle);
        }catch (Exception e){
            e.printStackTrace();
            return R.failed(action);
        }
    }


    /**
     * 获取左小臂内旋检测角度
     *
     * @param imgPathStart 动作开始图片路径
     * @param imgPathEnd   动作结束图片路径
     * @param savePath     保存路径
     * @return
     * wyx
     */
    @PostMapping("/getIRLeftAngle")
    public ExceptionResult getIRLeftAngle(String imgPathStart, String imgPathEnd, String savePath){
        String action = "获取左小臂内旋检测角度";
        if (imgPathStart==null || imgPathStart.equals("")) return R.parameterError("imgPathStart");
        File imgFile = new File(imgPathStart);
        if (!imgFile.exists()) return R.fileIsNotExist(imgPathStart);

        if (imgPathEnd == null || imgPathEnd.equals("")) return R.parameterError("imgPathEnd");
        File imgFile2 = new File(imgPathEnd);
        if (!imgFile2.exists()) return R.fileIsNotExist(imgPathEnd);

        if (savePath == null || savePath.equals("")) return R.parameterError("savePath");
        File file = new File(savePath);
        //文件夹不存在就创建
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            checkModule.pd_leftForearmInternalRotation(imgPathStart, imgPathEnd, savePath);
            float Angle = checkModule.pd_getIRLeftAngle();
            return R.success(action,Angle);
        }catch (Exception e){
            e.printStackTrace();
            return R.failed(action);
        }
    }

    /**
     * 获取右小臂外旋检测角度
     *
     * @param imgPathStart 动作开始图片路径
     * @param imgPathEnd   动作结束图片路径
     * @param savePath     保存路径
     * @return
     * wyx
     */
    @PostMapping("/getERRightAngle")
    public ExceptionResult getERRightAngle(String imgPathStart, String imgPathEnd, String savePath){
        String action = "获取右小臂外旋检测角度";
        if (imgPathStart==null || imgPathStart.equals("")) return R.parameterError("imgPathStart");
        File imgFile = new File(imgPathStart);
        if (!imgFile.exists()) return R.fileIsNotExist(imgPathStart);

        if (imgPathEnd == null || imgPathEnd.equals("")) return R.parameterError("imgPathEnd");
        File imgFile2 = new File(imgPathEnd);
        if (!imgFile2.exists()) return R.fileIsNotExist(imgPathEnd);

        if (savePath == null || savePath.equals("")) return R.parameterError("savePath");
        File file = new File(savePath);
        //文件夹不存在就创建
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            checkModule.pd_rightForearmExternalRotation(imgPathStart, imgPathEnd, savePath);
            float Angle = checkModule.pd_getERRightAngle();
            return R.success(action,Angle);
        }catch (Exception e){
            e.printStackTrace();
            return R.failed(action);
        }
    }


    /**
     * 获取右小臂内旋检测角度
     *
     * @param imgPathStart 动作开始图片路径
     * @param imgPathEnd   动作结束图片路径
     * @param savePath     保存路径
     * @return
     * wyx
     */
    @PostMapping("/getIRRightAngle")
    public ExceptionResult getIRRightAngle(String imgPathStart, String imgPathEnd, String savePath){
        String action = "获取右小臂内旋检测角度";
        if (imgPathStart==null || imgPathStart.equals("")) return R.parameterError("imgPathStart");
        File imgFile = new File(imgPathStart);
        if (!imgFile.exists()) return R.fileIsNotExist(imgPathStart);

        if (imgPathEnd == null || imgPathEnd.equals("")) return R.parameterError("imgPathEnd");
        File imgFile2 = new File(imgPathEnd);
        if (!imgFile2.exists()) return R.fileIsNotExist(imgPathEnd);

        if (savePath == null || savePath.equals("")) return R.parameterError("savePath");
        File file = new File(savePath);
        //文件夹不存在就创建
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            checkModule.pd_rightForearmInternalRotation(imgPathStart, imgPathEnd, savePath);
            float Angle = checkModule.pd_getIRRightAngle();
            return R.success(action,Angle);
        }catch (Exception e){
            e.printStackTrace();
            return R.failed(action);
        }
    }

    /**
     * 服务关闭
     *
     * @return
     * wyx
     */
    @GetMapping("/closeService")
    public static ExceptionResult closeService(){
        String action = "服务关闭";
        try {
            checkModule.poseDiagnoserClose();//关闭检测模块
            diagnosisModule.diseaseDiagnoserClose();//关闭诊断模块
            return R.success(action);
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed(action);
        }
    }
}
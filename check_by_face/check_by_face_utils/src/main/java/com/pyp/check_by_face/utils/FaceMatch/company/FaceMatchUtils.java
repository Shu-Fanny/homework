package com.pyp.check_by_face.utils.FaceMatch.company;

import org.json.JSONObject;

import java.io.File;

public class FaceMatchUtils {
    /**
     * 1、获取需要比较的图片路径以及图片名
     * 2、获取图片库的路径
     * 3、遍历图片库文件
     * 4、开启线程，将该班级的学生的图片取出与需要比较的图片路径进行对比
     * 5、如果找到相似度分数大于80，返回true;否则返回false
     */

    //ak-- 百度云官网获取的 API Key
    static String ak = "PSce6S7M7WVRVyIux15iDToC";
    //sk--  百度云官网获取的 Securet Key
    static String sk = "fvzwcYociG2GYnsZppKqEbSlUDQaQ9Sd";

    /*
    public boolean comparePicture(String fileLibraryPath,String fileImagePath){
         boolean flag = false;
        //1、获取需要比较的图片路径以及图片名
        String faceImgPath = fileImagePath + imgName;
        //2、获取图片库的路径-- 路径格式：根路径/学院名/专业名/班级名
        File[] fileArray = new File(fileLibraryPath).listFiles();
                //3、开始遍历照片库中的各个文件
        for (int i = 0; i < fileArray.length; i++) {
            //获取第i张照片的名字
            String personImg = fileArray[i].getName();
            String storeImgPath = fileLibraryPath + personImg;
            String result = FaceMatch.match(ak, sk, faceImgPath, storeImgPath);
             //String score = new JSONObject(result).getJSONObject("result").getString("score");
            JSONObject resultArray = new JSONObject(result).getJSONObject("result");
            String[] names  = JSONObject.getNames(resultArray);
            String score = resultArray.get(names[0]).toString();
            //比较分数大于75，判断为同一人，返回true
            if (Double.parseDouble(score) >= 75.00) {
                flag = true;
                System.out.println(Double.parseDouble(score));
                break;
            }
        }
        return flag;
    }
    */

    /**
     *
     * @param fileLibraryPath  学生采集的人脸图片
     * @param fileImagePath    摄像头上传的人脸图片
     * @return
     */
    public boolean comparePicture(String fileLibraryPath,String fileImagePath){
        boolean flag = false;
            String result = FaceMatch.match(ak, sk, fileImagePath, fileLibraryPath);
            //String score = new JSONObject(result).getJSONObject("result").getString("score");
            JSONObject resultArray = new JSONObject(result);
            String error_msg = resultArray.getString("error_msg");
            System.out.println(error_msg);
            if (!"SUCCESS".equals(error_msg)){
                return false;
            }
            JSONObject jResult = resultArray.getJSONObject("result");
            String[] names  = JSONObject.getNames(jResult);
            String score = jResult.get(names[0]).toString();
            //比较分数大于75，判断为同一人，返回true
            if (Double.parseDouble(score) >= 75.00) {
                flag = true;
                System.out.println(Double.parseDouble(score));
            }
        return flag;
    }
}

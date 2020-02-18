package com.pyp.check_by_face.utils.FaceMatch.company;

import javafx.embed.swing.SwingFXUtils;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //获取人脸库中的图片本地路径
    static String fileLibraryPath = new File(System.getProperty("user.dir")).getParent()
            + File.separator + "check_by_face\\check_by_face_controller\\src\\main\\webapp\\img\\test" + File.separator ;
    //需要对比的人脸照片路径
    static String fileImagePath = new File(System.getProperty("user.dir")).getParent()
            + File.separator +  "faceImage" + File.separator ;

    //ak-- 百度云官网获取的 API Key
    static String ak = "PSce6S7M7WVRVyIux15iDToC";
    //sk--  百度云官网获取的 Securet Key
    static String sk = "fvzwcYociG2GYnsZppKqEbSlUDQaQ9Sd";

    public static void main(String[] args) {
        //String filePath = new File("").getAbsolutePath() + "/src/userimage/";
        //faceMatchOneToOne();
        faceMatchOneToMore();
    }


    protected static void faceMatchOneToMore() {
        //String imgPath1 = fileLibraryPath + "2.png";
        //摄像头传过来需要对比的照片
        String faceImgPath = fileImagePath + "3.jpg";

       // File file = new File(imgPath2);
        //拿到照片库中的所有人的文件
        File[] fileArray = new File(fileLibraryPath).listFiles();
      //  List<String> faceMathPersonNameList = new ArrayList<>();
        //初始弹窗为：在拍照存储中没有匹配者
        String alertContent = "在拍照存储中没有匹配者！";

        //开始遍历照片库中的各个文件
        for (int i = 0; i < fileArray.length; i++) {
            //获取第i张照片的名字
            String personImg = fileArray[i].getName();
            String storeImgPath = fileLibraryPath + personImg;
            System.out.println("文件" + i + ":" + storeImgPath);

            String result = FaceMatch.match(ak, sk, faceImgPath, storeImgPath);

            try {
               // String score = new JSONObject(result).getJSONObject("result").getString("score");
                JSONObject resultArray = new JSONObject(result).getJSONObject("result");
                String[] names  = JSONObject.getNames(resultArray);
                String score = resultArray.get(names[0]).toString();
                System.out.println("相似得分为：" + score);
                // 阈值为75，高于75分判断为同一人
                if (Double.parseDouble(score) >= 75) {
                    System.out.println("相似得分为：" + score);
                    alertContent = "在拍照存储中找到匹配者！";
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        System.out.println("判断为：" + alertContent);

        /*for (int i = 0; i < faceMathPersonNameList.size(); i++) {
            String nameAbout = i < faceMathPersonNameList.size() - 1 ? (faceMathPersonNameList.get(i) + "、") : (faceMathPersonNameList.get(i) + "。");
            alertContent = i == 0 ? ("在拍照存储中找到匹配者，姓名为：" + nameAbout) : (alertContent + nameAbout);
        }*/
    }


    public static void faceMatchOneToOne(){
        //获得两张照片的路径
        String imgPath1 = fileLibraryPath + "1.png";
        String imgPath2 = fileLibraryPath + "3.jpg";
        String result = FaceMatch.match(ak, sk, imgPath1, imgPath2);


        System.out.println(System.getProperty("user.dir"));
        System.out.println("路径：" + fileLibraryPath);
        System.out.println("对比结果为：" + result);

        try {
            JSONObject resultArray = new JSONObject(result).getJSONObject("result");
            String[] names  = JSONObject.getNames(resultArray);
            String score = resultArray.get(names[0]).toString();
            //String score = new JSONObject(result).getJSONObject("result").getString("score");
            System.out.println("相似得分为：" + score);
            String judge = "不是同一人";
            // 阈值为80，高于80分判断为同一人
            if(Double.parseDouble(score) >= 75){
                judge = "同一人";
            }

            //80.35440063
            System.out.println("判断为：" + judge);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


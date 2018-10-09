package com.example.administrator.shaxuan.util;

import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/30.
 */

public class SaveDataToFile {


    //保存数据到SD卡文件
    public static boolean saveDataToSDcard(String fileName, List<Map<String, Object>> list, int pos) {
        boolean isAvailable = false;    //SD是否可读
        FileOutputStream fileOutputStream = null;
        //创建File对象
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (file.exists()) {
            mapList = getDataFromSDcard("shaXuan.txt");

            if (pos >= 0) {
                mapList.remove(pos);
            }
            for (int i = 0; i < mapList.size(); i++) {
                list.add(mapList.get(i));
            }
        }
        //将list转成String类型
        List<String> cache = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            // 取出当前的Map，转化为JSONObject
            JSONObject obj = new JSONObject(list.get(i));
            // 转化为字符串并添加进新的List中
            cache.add(obj.toString());
        }
        // 可存储的字符串数据
        String listStr = cache.toString();

        //判断SD卡是否可读写
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            isAvailable = true;
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(listStr.getBytes());
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return isAvailable;
    }

    public static void delFile(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        file.delete();
    }


    //读取文件内容，并将String转成List<>
    public static List<Map<String, Object>> getDataFromSDcard(String fileName) {
        //读取文件内容保存到resultStr
        String resultStr = null;
        //将读取的String结果转化成List<>
        List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] b = new byte[fileInputStream.available()];
                fileInputStream.read(b);
                resultStr = new String(b);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("读文件出错");
            }

            try {
                JSONArray jsonArray = new JSONArray(resultStr);
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("name", jsonObject.get("name"));
                        map.put("phoneNumber", jsonObject.get("phoneNumber"));
                        map.put("totalCount", jsonObject.get("totalCount"));
                        map.put("describe", jsonObject.get("describe"));
                        map.put("ysyCount", jsonObject.get("ysyCount"));
                        tempList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("转化list出错");
            }
        }
        return tempList;
    }

    //判断文件是否存在
    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }


    /**
     * 更新list内的元素。
     *
     * @param objList
     * @param oldObj  旧对象
     * @param newObj  要更新的对象
     * @return
     */
    public static List<Map<String, Object>> updateElement(List<Map<String, Object>> objList, Map<String, Object> oldObj, Map<String, Object> newObj) {
        int position = objList.indexOf(oldObj);
        objList.remove(position);
        objList.add(position, newObj);

        return objList;
    }


    //清除数据
    public static boolean delDataToSDcard(String fileName, List<Map<String, Object>> list) {
        boolean isAvailable = false;    //SD是否可读
        FileOutputStream fileOutputStream = null;
        //创建File对象
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList = getDataFromSDcard("shaXuan.txt");
        for (int i = 0; i < mapList.size(); i++) {
            list.add(mapList.get(i));
        }
        //将list转成String类型
        List<String> cache = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            // 取出当前的Map，转化为JSONObject
            JSONObject obj = new JSONObject(list.get(i));
            // 转化为字符串并添加进新的List中
            cache.add(null);
        }
        // 可存储的字符串数据
        String listStr = cache.toString();

        //判断SD卡是否可读写
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            isAvailable = true;
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(listStr.getBytes());
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return isAvailable;
    }


    //清除数据
    public static boolean delDataForPos(String fileName, List<Map<String, Object>> list, int pos) {
        boolean isAvailable = false;    //SD是否可读
        FileOutputStream fileOutputStream = null;
        //创建File对象
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList = getDataFromSDcard("shaXuan.txt");
        for (int i = 0; i < mapList.size(); i++) {
            list.add(mapList.get(i));
        }
        list.remove(pos);
        //将list转成String类型
        List<String> cache = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            // 取出当前的Map，转化为JSONObject
            JSONObject obj = new JSONObject(list.get(i));
            // 转化为字符串并添加进新的List中
            cache.add(obj.toString());
        }
        // 可存储的字符串数据
        String listStr = cache.toString();

        //判断SD卡是否可读写
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            isAvailable = true;
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(listStr.getBytes());
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return isAvailable;
    }
}

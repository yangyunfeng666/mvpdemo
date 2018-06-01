package com.yunsoft.mvpdemo.reactnative.hotupdate;

import android.content.Context;
import android.util.Log;


import com.yunsoft.mvpdemo.reactnative.FileConstant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 文件工具类
 * Created by Song on 2017/2/15.
 */
public class FileUtils {

    /**
     * 解压 ZIP 包
     */
    public static void decompression(String filePath) {
        try {
            ZipInputStream inZip = new ZipInputStream(new FileInputStream(FileConstant.JS_PATCH_LOCAL_PATH));
            ZipEntry zipEntry;
            String szName;
            try {

                while((zipEntry = inZip.getNextEntry()) != null) {

                    szName = zipEntry.getName();
                    if(zipEntry.isDirectory()) {
                        szName = szName.substring(0,szName.length()-1);
                        File folder = new File(filePath + File.separator + szName);
                        folder.mkdirs();
                    }else{
                        File file1 = new File(filePath + File.separator + szName);
                        file1.createNewFile();
                        FileOutputStream fos = new FileOutputStream(file1);
                        int len;
                        byte[] buffer = new byte[1024];
                        while((len = inZip.read(buffer)) != -1) {
                            fos.write(buffer, 0 , len);
                            fos.flush();
                        }
                        fos.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            inZip.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Assets目录下的bundle文件
     * @return
     */
    public static String getJsBundleFromAssets(Context context) {

        String result = "";
        try {
            InputStream is = context.getAssets().open(FileConstant.JS_BUNDLE_LOCAL_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            result = new String(buffer,"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析SD卡下的bundle文件
     * @param filePath
     * @return
     */
    public static String getJsBundleFromSDCard(String filePath) {

        String result = "";
        try {
            InputStream is = new FileInputStream(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            result = new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将.pat文件转换为String
     * @param patPath 下载的.pat文件所在目录
     * @return
     */
    public static String getStringFromPat(String patPath) {

        FileReader reader = null;
        String result = "";
        try {
            reader = new FileReader(patPath);
            int ch = reader.read();
            StringBuilder sb = new StringBuilder();
            while (ch != -1) {
                sb.append((char)ch);
                ch  = reader.read();
            }
            reader.close();
            result = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将图片复制到bundle所在文件夹下的drawable-mdpi
     * @param srcFilePath
     * @param destFilePath
     */
    public static void copyPatchImgs(String srcFilePath,String destFilePath) {

        File root = new File(srcFilePath);
        File dest = new File(destFilePath);
        if(!dest.exists()){
            dest.mkdirs();
        }
        File[] files;
        if(root.exists() && root.listFiles() != null) {
            files = root.listFiles();
            for (File file : files) {
                Log.e("show","file:"+srcFilePath+":"+destFilePath+file.getName());
                    if(!file.isDirectory()){
                        copyFile(srcFilePath+File.separator+file.getName(),destFilePath+File.separator+file.getName());
                    }
            }
        }
    }

    public static void copyFile(String src, String target){
        try
        {
            File fOldFile = new File(src);
            if (fOldFile.exists())
            {
                int byteread = 0;
                InputStream inputStream = new FileInputStream(fOldFile);
                FileOutputStream fileOutputStream = new FileOutputStream(target);
                byte[] buffer = new byte[1444];
                while ( (byteread = inputStream.read(buffer)) != -1)
                {
                    fileOutputStream.write(buffer, 0, byteread);//三个参数，第一个参数是写的内容，
                    //第二个参数是从什么地方开始写，第三个参数是需要写的大小
                }
                inputStream.close();
                fileOutputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("复制单个文件出错");
            e.printStackTrace();
        }

    }


    /**
     * 遍历删除文件夹下所有文件
     * @param filePath
     */
    public static void traversalFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if(f.isDirectory()) {
                    traversalFile(f.getAbsolutePath());
                } else {
                    f.delete();
                }
            }
            file.delete();
        }
    }

    /**
     * 删除指定File
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        File patFile = new File(filePath);
        if(patFile.exists()) {
            patFile.delete();
        }
    }

}

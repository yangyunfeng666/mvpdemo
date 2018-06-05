package com.yunsoft.mvpdemo.reactnative.hotupdate;

import android.content.Context;
import android.util.Log;

import com.yunsoft.mvpdemo.reactnative.FileConstant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.LinkedList;

/**
 * 热修复
 * Created by Song on 2017/5/12.
 */
public class HotUpdate {

    private static final String TAG ="show" ;

    public static void checkVersion() {
        // 检查版本是否需要更新
    }

    public static void checkPackage(Context context,String filePath) {
        // 1.下载前检查SD卡是否存在更新包文件夹,FIRST_UPDATE来标识是否为第一次下发更新包
        File bundleFile = new File(filePath);
        if(bundleFile != null && bundleFile.exists()) {
            ACache.get(context).put(FileConstant.FIRST_UPDATE,false);
        } else {
            ACache.get(context).put(FileConstant.FIRST_UPDATE,true);
        }
    }

    /**
     * 拷贝drawable目录下图片到指定jsbundle加载的图片目录下
     * @param context
     * @param PicID
     */
    public void copyImage2Data(Context context,Integer PicID,String LogoFilePath)
    {
        Log.d(TAG, "mythou copyImage2Data----->Enter PicID="+PicID);
        try
        {
            //计算图片存放全路径
            File dir = new File(FileConstant.DRAWABLE_PATH);
            //如果文件夹不存在，创建一个（只能在应用包下面的目录，其他目录需要申请权限 OWL）
            if(!dir.exists()){
                Log.d(TAG, "mythou copyImage2Data----->dir not exist");
                boolean result = dir.mkdirs();
            }
            // 获得封装  文件的InputStream对象
            InputStream is = context.getResources().openRawResource(PicID);

            Log.d(TAG, "copyImage2Data----->InputStream open");

            FileOutputStream fos = new FileOutputStream(LogoFilePath);

            byte[] buffer = new byte[1024];
            System.out.println("3");
            int count = 0;

            // 开始复制Logo图片文件
            while((count=is.read(buffer)) > 0)
            {
                fos.write(buffer, 0, count);
                System.out.println("4");
            }
            fos.close();
            is.close();

        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void handleZIP(final Context context,String newVersion,String olderVersion,boolean allUpdate) {

        // 开启单独线程，解压，合并。
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (allUpdate) {//如果是全量更新
                    ACache.get(context).put(FileConstant.FIRST_UPDATE,true);
                    // 解压到根目录
                    FileUtils.decompression(FileConstant.LOCAL_FOLDER);
                    mergeAllUpdate(newVersion);
                } else {
                    //如果是第一次更新 这里以是否存在以前的版本的jsbundle文件为判断依据
                    boolean result = true;
                    File file = new File(FileConstant.JS_BUNDLE_LOCAL_PATH+olderVersion+FileConstant.SPLEX+FileConstant.JS_BUNDLE_LOCAL_FILE);
                    if(file!=null&&file.exists()){
                        result = false;
                    }
//                    boolean result = (Boolean) ACache.get(context).getAsObject(FileConstant.FIRST_UPDATE);
                    if (result) {//如果是第一次更新
                        Log.e("show", "result" + result);
                        // 解压到根目录
                        FileUtils.decompression(FileConstant.LOCAL_FOLDER);
                        //com.yunsoft.mvpdemp/bundle.pat drawable-mdpi
                        // 合并
                        mergePatAndAsset(context,newVersion);
                        //判断是否有图片
                    } else { //如果有文件 那么
                        Log.e("show", "result" + result);
                        // 解压到future目录
                        FileUtils.decompression(FileConstant.LOCAL_FOLDER);
                        // 合并
                        mergePatAndBundle(newVersion,olderVersion);
                    }
                }
                // 删除ZIP压缩包
                FileUtils.traversalFile(FileConstant.LOCAL_FOLDER);
                FileUtils.deleteFile(FileConstant.JS_PATCH_LOCAL_PATH);
            }
        }).start();
    }

    /**
     * 与Asset资源目录下的bundle进行合并 然后在生成xxx_index.android.bundle文件
     */
    private static void mergePatAndAsset(Context context,String newVersion) {
        // 1.解析Asset目录下的bundle文件
        String assetsBundle = FileUtils.getJsBundleFromAssets(context);
        // 2.解解压后的bundle当前目录下.pat文件
        String patcheStr = FileUtils.getStringFromPat(FileConstant.JS_PATCH_LOCAL_FILE);
        // 3.合并 /wan/xxxx_index.android.bundle 文件
        merge(patcheStr,assetsBundle,FileConstant.JS_BUNDLE_LOCAL_PATH+newVersion+FileConstant.SPLEX+FileConstant.JS_BUNDLE_LOCAL_FILE);
        File file = new File(FileConstant.FUTURE_DRAWABLE_PATH);
        Log.e("show","FUTURE_DRAWABLE_PATH:"+FileConstant.FUTURE_DRAWABLE_PATH);
        //如果有网络图片
        if(file!=null&&file.exists()) {
            //拷贝图片文件
            Log.e("show","haveImage");
            FileUtils.copyPatchImgs(FileConstant.FUTURE_DRAWABLE_PATH, FileConstant.DRAWABLE_PATH);
        }else{
            Log.e("show","not haveImage");
        }
    }

    /**
     * 与SD卡下的bundle进行合并
     */
    private static void mergePatAndBundle(String newVersion,String oldVersion) {
        // 1.解析sd卡目录下的bunlde war/xxx_index.android.bundle文件
        String assetsBundle = FileUtils.getJsBundleFromSDCard(FileConstant.JS_BUNDLE_LOCAL_PATH+oldVersion+FileConstant.SPLEX+FileConstant.JS_BUNDLE_LOCAL_FILE);
        // 2.解析最新下发的.pat文件字符串
        String patcheStr = FileUtils.getStringFromPat(FileConstant.JS_PATCH_LOCAL_FILE);
        // 3.合并
        merge(patcheStr,assetsBundle,FileConstant.JS_BUNDLE_LOCAL_PATH+newVersion+FileConstant.SPLEX+FileConstant.JS_BUNDLE_LOCAL_FILE);
        //4.删除原来的jsbundle文件，然后把临时合并文件拷贝过来
//        FileUtils.deleteFile(FileConstant.JS_BUNDLE_LOCAL_PATH);
        //5.拷贝合并jsbundle文件到目的文件
       // FileUtils.copyFile(FileConstant.JS_BUNDLE_FUTURE_LOCAL_PATH,FileConstant.JS_BUNDLE_LOCAL_PATH);
        //6.拷贝图片文件到
        File file = new File(FileConstant.FUTURE_DRAWABLE_PATH);
        Log.e("show","FUTURE_DRAWABLE_PATH:"+FileConstant.FUTURE_DRAWABLE_PATH);
        //如果有网络图片
        if(file!=null&&file.exists()) {
            //拷贝图片文件
            Log.e("show","haveImage");
            FileUtils.copyPatchImgs(FileConstant.FUTURE_DRAWABLE_PATH, FileConstant.DRAWABLE_PATH);
        }else{
            Log.e("show"," not haveImage");
        }
    }

    /**
     * 全量更新 拷贝 jsbundle文件到某个目录 然后在拷贝/war/xxxjsbundle 和图片到/war/drawable-mdpi 目录
     */
    private static void mergeAllUpdate(String newVersion) {

        //4 删除原来的jsbundle文件，然后把临时合并文件拷贝过来
//        FileUtils.deleteFile(FileConstant.JS_BUNDLE_LOCAL_PATH);
        //5 拷贝合并jsbundle文件到目的文件
        FileUtils.copyFile(FileConstant.ALL_UPDATE_JS_LOCAL_FILE,FileConstant.JS_BUNDLE_LOCAL_PATH+newVersion+FileConstant.SPLEX+FileConstant.JS_BUNDLE_LOCAL_FILE);
        //6 拷贝图片文件到
        File file = new File(FileConstant.FUTURE_DRAWABLE_PATH);
        Log.e("show","FUTURE_DRAWABLE_PATH:"+FileConstant.FUTURE_DRAWABLE_PATH);
        //如果有网络图片
        if(file!=null&&file.exists()) {
            //拷贝图片文件
            Log.e("show","haveImage");
            FileUtils.copyPatchImgs(FileConstant.FUTURE_DRAWABLE_PATH, FileConstant.DRAWABLE_PATH);
        }else{
            Log.e("show"," not haveImage");
        }
    }

    /**
     * 合并,生成新的bundle文件
     */
    private static void merge(String patcheStr, String bundle,String savebundleFilePath) {

        // 3.初始化 dmp
        diff_match_patch dmp = new diff_match_patch();
        // 4.转换pat
        LinkedList<diff_match_patch.Patch> pathes = (LinkedList<diff_match_patch.Patch>) dmp.patch_fromText(patcheStr);
        // 5.pat与bundle合并，生成新的bundle
        Object[] bundleArray = dmp.patch_apply(pathes,bundle);
        // 6.保存新的bundle文件
        try {
            Writer writer = new FileWriter(savebundleFilePath);
            String newBundle = (String) bundleArray[0];
            writer.write(newBundle);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

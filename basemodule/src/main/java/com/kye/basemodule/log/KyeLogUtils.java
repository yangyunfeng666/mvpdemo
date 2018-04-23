package com.kye.basemodule.log;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.kye.basemodule.BuildConfig;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yyf on 2018-04-11 14:33.
 */

public class KyeLogUtils {

    /**
     * 初始化 必须做的事情
     * @param context
     * @param bugly_appid
     */
    public static void init(Application context,String bugly_appid,String logger_tag){
//        //崩溃的采集
//        CrashHander crashHander = CrashHander.getsInstance();
//        crashHander.init(context);
        //设置日志打印的tag标识
        Logger.init(logger_tag);
        //bugly渠道 bugly的初始化
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        ApplicationInfo info = null;
        String ochainal = BuildConfig.FLAVOR;//渠道号
        try {
            info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            ochainal = info.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName =getProcessName(android.os.Process.myPid());
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        strategy.setAppChannel(ochainal);
        //初始化Bugly
//        CrashReport.initCrashReport(context, BuildConfig.BUGLY_ID, BuildConfig.DEBUG,strategy);
        //设置开发设备 用于测试使用 在打relase 包时候 去掉
        if(BuildConfig.DEBUG) {
            Bugly.setIsDevelopmentDevice(context, true);
        }
        //使用配置参数动态设置值
        Bugly.init(context, bugly_appid, BuildConfig.DEBUG,strategy);
    }
    /**
     * 采集错误到服务器
     * @param message
     * @param e
     */
    public static void postLog(String message,Throwable e){
        if(BuildConfig.DEBUG){
            Logger.e(e,message);
        }else{
            CrashReport.postCatchedException(e);
        }
    }

    /**
     * 采集错误服务器
     * @param
     * @param e
     */
    public static void postLog(Throwable e){
        if(BuildConfig.DEBUG){
            Logger.e(e,"ReportLogUtil");
        }else{
            CrashReport.postCatchedException(e);
        }
    }



    public static void i(String message, Object... args) {
        if (BuildConfig.DEBUG)
            Logger.i(message, args);
    }

    public static void v(String message, Object... args) {
        if (BuildConfig.DEBUG) {
            Logger.v(message, args);
        }
    }

    public static void d(String message, Object... args) {
        if (BuildConfig.DEBUG) {
            Logger.d(message, args);
        }
    }

    public static void w(String message, Object... args) {
        if (BuildConfig.DEBUG)
            Logger.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        if (BuildConfig.DEBUG)
            Logger.wtf(message, args);
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        if (BuildConfig.DEBUG)
            Logger.json(json);
    }

    public static void e(String message,Object ...arg) {
        if (BuildConfig.DEBUG) {
            Logger.e(message, arg);
        }else{
            CrashReport.postCatchedException(new Throwable(message));
        }
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        if (BuildConfig.DEBUG)
            Logger.xml(xml);
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}

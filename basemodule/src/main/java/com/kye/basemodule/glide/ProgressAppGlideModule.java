package com.kye.basemodule.glide;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * Created by sunfusheng on 2017/6/14.
 */
@GlideModule
public class ProgressAppGlideModule extends AppGlideModule {

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(ProgressManager.getOkHttpClient()));
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //重新设置内存限制
        builder.setMemoryCache(new LruResourceCache(getTotalMemorySize("MemFree") / 8));
//        Log.e("getMemFreeMemorySize/8:", getTotalMemorySize("MemFree")/8  + "");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.DATA)
                .skipMemoryCache(false);
        builder.setDefaultRequestOptions(requestOptions);
    }

    /**
     * 读取总的内存大小
     *
     * @return
     */
    public long getTotalMemorySize(String type) {
        long size = 0;
        try {
            FileReader fileReader = new FileReader("/proc/meminfo");
            BufferedReader bufferedReader = new BufferedReader(fileReader, 4 * 1024);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.contains(type)) {
                    break;
                }
            }
            bufferedReader.close();
            /* \\s表示   空格,回车,换行等空白符,
            +号表示一个或多个的意思     */
            String[] array = str.split("\\s+");
            // 获得系统总内存，单位是KB，乘以1024转换为Byte
            int length = Integer.valueOf(array[1]).intValue()*1024 ;
            size = length;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }
}
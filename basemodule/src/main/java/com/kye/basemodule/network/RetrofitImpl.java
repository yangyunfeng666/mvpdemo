package com.kye.basemodule.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yyf on 2018-04-11 15:01.
 */

public abstract class RetrofitImpl {
    /**
     * host
     * @return
     */
    public abstract String callHostAddress();

    /**
     * null 转换器
     * @return
     */
    public Converter.Factory callConverterFactory() {
        return GsonConverterFactory.create(new GsonBuilder()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                .create());
    }


    /**
     * adapterFactor
     */
    public CallAdapter.Factory callAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    /**
     * okhttp
     * @return
     */
    public okhttp3.Call.Factory callClient(Interceptor ...arg) {
        return new OkHttpClient();
    }


    public class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();        if (rawType != String.class) {
                return null;        }
            return (TypeAdapter<T>) new StringNullAdapter();    }
    }


    public class StringNullAdapter extends TypeAdapter<String> {
        @Override    public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();            return "";        }
            return reader.nextString();    }

        @Override    public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.value("");            return;        }
            writer.value(value);    }
    }


}

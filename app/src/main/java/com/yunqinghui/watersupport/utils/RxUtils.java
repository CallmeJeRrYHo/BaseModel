package com.yunqinghui.watersupport.utils;

import android.content.Context;

import com.yunqinghui.watersupport.model.bean.Result;
import com.yunqinghui.watersupport.model.http.MyHttpException;
import com.yunqinghui.watersupport.model.http.api.RetrofitHelper;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;


/**
 * Created by HJH
 * Data 2017/11/15.
 */

public class RxUtils {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T>ObservableTransformer<T,T> rxSchedulerHelper(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<Result<T>, T> handleResult() {   //compose判断结果
        return new ObservableTransformer<Result<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<Result<T>> upstream) {
                return upstream.map(new Function<Result<T>, T>() {
                    @Override
                    public T apply(@NonNull Result<T> tResult) throws Exception {
                        if (tResult.getCode().equals("0"))
                            return tResult.getResult();
                        else
                            throw new MyHttpException(tResult.getMessage());
                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     * @return
     */
    public static ObservableTransformer<Result, Result> handleNoResult() {   //compose判断结果
        return new ObservableTransformer<Result, Result>() {
            @Override
            public ObservableSource<Result> apply(@NonNull Observable<Result> upstream) {
                return upstream.map(new Function<Result, Result>() {
                    @Override
                    public Result apply(@NonNull Result result) throws Exception {
                        if (result.getCode().equals("0"))
                            return result;
                        else
                            throw new MyHttpException(result.getMessage());
                    }
                });
            }
        };
    }


    /**
     * 使用时先进行修改！！！
     * @param file
     * @param context
     * @param retrofitHelper
     * @return
     */
    public static Observable<Result> getUploadPicObservable(final File file, final Context context, final RetrofitHelper retrofitHelper){
        return  Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<File> e) throws Exception {
                e.onNext(file);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .flatMap(new Function<File, ObservableSource<Result>>() {
                    @Override
                    public ObservableSource<Result> apply(@NonNull File file) throws Exception {
                        LogUtils.d(file.getName() + " " + FileUtils.getFileSize(file));
                        List<File> list = Luban.with(context).load(file).get();
                        if (EmptyUtils.isEmpty(list))
                            throw new MyHttpException("压缩错误！");
                        File compressFile = list.get(0);
                        RequestBody requestFile =
                                RequestBody.create(MediaType.parse("multipart/form-data"), compressFile);
                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("uploaded_file", compressFile.getName(), requestFile);
                        String descriptionString = "hello, 这是文件描述";
                        RequestBody description =
                                RequestBody.create(
                                        MediaType.parse("multipart/form-data"), descriptionString);
                        //return retrofitHelper.getApi().uploadPic(body, description);
                        return null;
                    }
                });
    }
}

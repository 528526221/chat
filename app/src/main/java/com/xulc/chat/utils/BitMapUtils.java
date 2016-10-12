package com.xulc.chat.utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.xulc.chat.R;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by 徐椋春 on 2016/8/21.
 */
public class BitMapUtils {
    private static ImageOptions options;
    public static void displayImg(ImageView view,String url){
        if (options==null){
            options = new ImageOptions.Builder()
                    // 是否忽略GIF格式的图片
                    .setIgnoreGif(false)
                    // 下载中显示的图片
                    .setLoadingDrawableId(R.mipmap.ic_launcher)
                    // 下载失败显示的图片
                    .setFailureDrawableId(R.mipmap.ic_launcher)
                    // 得到ImageOptions对象
                    .setAutoRotate(true)
                    //设置图片的质量
                    .setConfig(Bitmap.Config.RGB_565)
                    //设置淡入效果
                    .setFadeIn(true)
                     //待展示图片 将旋转图片
                    .setAutoRotate(true)
                    .build();
        }
        x.image().bind(view,url,options);
    }

    public static DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher) // resource or drawable
            .showImageForEmptyUri(R.mipmap.ic_launcher) // resource or drawable
            .showImageOnFail(R.mipmap.ic_launcher) // resource or drawable
            .considerExifParams(false) // default
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
            .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
            .displayer(new SimpleBitmapDisplayer()) // default
            .handler(new Handler()) // default
            .build();
    public static DisplayImageOptions headOptions = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher) // resource or drawable
            .showImageForEmptyUri(R.mipmap.ic_launcher) // resource or drawable
            .showImageOnFail(R.mipmap.ic_launcher) // resource or drawable
            .considerExifParams(false) // default
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
            .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
            .displayer(new SimpleBitmapDisplayer()) // default
            .handler(new Handler()) // default
            .build();
}

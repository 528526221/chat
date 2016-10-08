package com.xulc.chat.utils;

import android.widget.ImageView;


import com.xulc.chat.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by 徐椋春 on 2016/8/21.
 */
public class BitMapUtils {
    private static ImageOptions options;
    public void displayImg(ImageView view,String url){
        if (options==null){
            options = new ImageOptions.Builder()
                    // 是否忽略GIF格式的图片
                    .setIgnoreGif(false)
                    // 图片缩放模式
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    // 下载中显示的图片
                    .setLoadingDrawableId(R.mipmap.ic_launcher)
                    // 下载失败显示的图片
                    .setFailureDrawableId(R.mipmap.ic_launcher)
                    // 得到ImageOptions对象
                    .build();
        }
        x.image().bind(view,url);
    }
}

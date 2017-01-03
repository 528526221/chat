package com.xulc.chat.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by xlc on 2016/4/11.
 */
public class Resolution {
    /*
    效果图分辨率
     */
    public static float width = 720.0f;
    public  static float height = 1280.0f;
    /*
    屏幕的分辨率信息
     */
    public  static int screenW;
    public  static int screenH;
    public  static float density;
    public  static int statusBarHeight;
    public static float scaledDensity;

    /**
     * 屏幕分辨率数据初始化
     * @param screenW 屏幕宽
     * @param screenH 高
     * @param density 屏幕密度
     */
    public static void init(int screenW, int screenH, float density,float scaledDensity) {
        Resolution.screenW = screenW;
        Resolution.screenH = screenH;
        Resolution.density = density;
        Resolution.scaledDensity = scaledDensity;
    }

    /**
     * 计算宽比例
     * @return
     */
    public  static float getRateOfWidth(){
        return screenW/width;
    }

    /**
     * 计算高比例
     * @return
     */
    public  static float getRateOfHeight(){
        return screenH/height;
    }

    /**
     * 得到计算后的宽
     * @param width 效果图中宽
     * @return
     */
    public  static int getRateWidth(int width){
        if (width==RelativeLayout.LayoutParams.WRAP_CONTENT||width==RelativeLayout.LayoutParams.MATCH_PARENT){
            return width;
        }
        return (int) (width*getRateOfWidth());
    }

    /**
     * 得到计算后的高
     * @param height 效果图中高
     * @return
     */
    public  static int getRateHeight(int height){
        if (height == RelativeLayout.LayoutParams.WRAP_CONTENT||height==RelativeLayout.LayoutParams.MATCH_PARENT){
            return height;
        }
        return (int) (height*getRateOfHeight());
    }

    /**
     * 重设view的宽高
     * @param view 控件
     * @param width 效果图中宽
     * @param height 效果图中高
     */
    public  static void setViewParms(View view,int width,int height){
        LayoutParams params  = view.getLayoutParams();
        params.width = getRateWidth(width);
        params.height =  getRateHeight(height);
    }

    /**
     * 重设view的margin值
     * @param view 控件
     * @param left 距离左
     * @param top 距离上
     * @param right 距离右
     * @param bottom 距离下
     */
    public  static void setViewMargin(View view,int left,int top,int right,int bottom){
        LayoutParams params = view.getLayoutParams();
        if (params instanceof RelativeLayout.LayoutParams){
            ((RelativeLayout.LayoutParams)params).setMargins(getRateWidth(left),getRateHeight(top),getRateWidth(right),getRateHeight(bottom));
        }else if (params instanceof LinearLayout.LayoutParams){
            ((LinearLayout.LayoutParams)params).setMargins(getRateWidth(left),getRateHeight(top),getRateWidth(right),getRateHeight(bottom));
        }
        view.setLayoutParams(params);

    }

    public static void setViewMarginTop(View view, int top) {
        if (view != null) {
            LayoutParams params = view.getLayoutParams();
            if (null != params && params instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams)params).setMargins(0, Resolution.getRateHeight(top), 0, 0);
            }
        }
    }

    public static void setViewMarginLeft(View view, int left) {
        if (view != null) {
            LayoutParams params = view.getLayoutParams();
            if (null != params && params instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams)params).setMargins(Resolution.getRateHeight(left), 0, 0, 0);
            }
        }
    }
    public static void setViewMarginRight(View view, int right) {
        if (view != null) {
            LayoutParams params = view.getLayoutParams();
            if (null != params && params instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams)params).setMargins(0, 0, Resolution.getRateHeight(right), 0);
            }
        }
    }

    /**
     * view转Bitmap
     *
     * @param view 视图
     * @return bitmap
     */
    public static Bitmap view2Bitmap(View view) {
        if (view == null) return null;
        Bitmap ret = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(ret);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return ret;
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(float dpValue) {
        final float scale = density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(float pxValue) {
        final float scale = density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(float spValue) {
        final float fontScale = scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(float pxValue) {
        final float fontScale = scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 重设view的自定义高度
     * 这里需要setLayoutParams方法 控件已渲染
     * @param view
     * @param height
     */
    public static void setCustomHeight(View view,int height){
        LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }
}

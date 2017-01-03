package com.xulc.chat.utils;

import android.os.Environment;

import com.xulc.chat.table.TableChat;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import org.xutils.common.util.KeyValue;
import java.io.File;
import java.util.UUID;

import okhttp3.Call;

/**
 * Created by xuliangchun on 2017/1/3.
 */
public class DownFileManager {
    public static void downVoice(String url, final long id){
        OkHttpUtils.get().url(url).build().execute(new FileCallBack(Environment.getExternalStorageDirectory() + "/cody_audios", UUID.randomUUID().toString() + ".amr") {
            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(File file, int i) {
                KeyValue keyValue = new KeyValue("localAudioUrl",file.getAbsolutePath());
                DbUtils.getInstance().updateById(TableChat.class,id,keyValue);
            }
        });
    }
}

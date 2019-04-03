package com.xm.framework.media.download;

import android.content.Context;

/**
 * Created by Mouse on 2019/4/3.
 */
public class TipMediaManager {

    private XmMediaplayer yxMediaplayer;
    private static TipMediaManager mediaplayerManager;
    private Context context;

    private TipMediaManager(Context context) {
        this.context = context.getApplicationContext();
        yxMediaplayer = new XmMediaplayer(this.context);
    }

    public static TipMediaManager getInstance(Context context) {
        if (mediaplayerManager == null) {
            mediaplayerManager = new TipMediaManager(context);
        }
        return mediaplayerManager;
    }

    public void play(IDownloadConfig iDownloadConfig) {
        String name = iDownloadConfig.getAudioName();
        if (yxMediaplayer.isPlaying()) {
            yxMediaplayer.stop();
        } else {
            yxMediaplayer.play(name, iDownloadConfig, null);
        }
    }

    public void play(String name) {
        if (yxMediaplayer.isPlaying()) {
            yxMediaplayer.stop();
        } else {
            yxMediaplayer.play(name, XmMediaplayer.AUDIO_FILE_TYPE_ASSETS);
        }
    }
}

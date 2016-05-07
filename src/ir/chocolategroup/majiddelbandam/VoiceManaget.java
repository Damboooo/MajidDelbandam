package ir.chocolategroup.majiddelbandam;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by mohammad hosein on 17/04/2016.
 */
public class VoiceManaget {
    public static void play(Context context,int code)
    {
        MediaPlayer mp = MediaPlayer.create(context, code);
        mp.start();
    }
}

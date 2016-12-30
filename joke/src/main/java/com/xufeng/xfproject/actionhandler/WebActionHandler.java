package com.xufeng.xfproject.actionhandler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by xufeng on 2016/12/29.
 */

public class WebActionHandler {

    public static void openWeb(Context context, String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

}

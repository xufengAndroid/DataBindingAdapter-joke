package com.xufeng.xfproject.utils;

import android.view.View;

import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.google.common.base.Strings;
import com.stfalcon.frescoimageviewer.ImageViewer;
import com.stfalcon.frescoimageviewer.OnControllerListener;
import com.stfalcon.frescoimageviewer.drawee.ZoomableDraweeView;

/**
 * Created by xufeng on 2016/12/21.
 */

public class ImageViewerUtils {

    public static void showBigImage(View view, String url){
        if(!Strings.isNullOrEmpty(url)){
            new ImageViewer.Builder(view.getContext(), new String[]{url})
                    .setStartPosition(0)
                    .setOnControllerListener(new OnControllerListener() {
                        @Override
                        public void setController(PipelineDraweeControllerBuilder pipelineDraweeControllerBuilder, String s, ZoomableDraweeView zoomableDraweeView) {
                            pipelineDraweeControllerBuilder.setAutoPlayAnimations(true);
                        }
                    })
                    .show();
        }
    }

}

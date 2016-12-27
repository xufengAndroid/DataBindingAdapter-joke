/*
 * Copyright (C) 2016 MarkZhai (http://zhaiyifan.cn).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xufeng.xfproject.manage;


import android.databinding.BindingAdapter;
import android.graphics.PointF;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class ViewBindingAdapter {

    @BindingAdapter({"url"})
    public static void setUrl(SimpleDraweeView view, String url) {
        Uri imageUri = Uri.parse(url);
        PointF focusPoint = new PointF(0f,0f);
        view.getHierarchy().setActualImageFocusPoint(focusPoint);
        //创建DraweeController
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //加载的图片URI地址
                .setUri(imageUri)
                //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                // 设置加载图片完成后是否直接进行播放
                .setAutoPlayAnimations(true)
                //设置旧的Controller
                .setOldController(view.getController())
                //构建
                .build();
        view.setController(controller);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager manager) {
        view.setLayoutManager(manager);
    }

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

}

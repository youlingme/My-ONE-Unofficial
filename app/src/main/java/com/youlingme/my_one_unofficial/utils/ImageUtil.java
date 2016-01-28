package com.youlingme.my_one_unofficial.utils;

import android.content.Context;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

/**
 * User: youlingme
 * Date: 2016-01-28 18:02
 * Fresco配置和初始化
 */
public class ImageUtil {

    public static void init(Context context) {
        File cacheDir = new File(FileManager.getAppDir());

        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder().setBaseDirectoryPath(cacheDir).setBaseDirectoryName(FileManager.DIR_PIC_CACHE).build();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(context).setMainDiskCacheConfig(diskCacheConfig).build();
        Fresco.initialize(context, imagePipelineConfig);
    }

}

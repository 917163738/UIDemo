package com.xmnode.demo.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import java.io.ByteArrayOutputStream;

/**
 * image
 */
public class ImgUtils {

    /**
     * 图片转二进制
     *
     * @param bmp
     * @return
     */
    public static byte[] bmpToByte(Bitmap bmp) {

        if (bmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bmpByte = baos.toByteArray();
            return bmpByte;
        }
        return null;
    }

    /**
     * 按正方形裁切图片
     */
    public static Bitmap ImageCrop(Bitmap bitmap) {
        int w = bitmap.getWidth();
        return Bitmap.createBitmap(bitmap, 0, 0, w, w, null, false);
    }

    /**
     * 获取圆角图片
     *
     * @param bitmap
     * @param roundPx
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     *
     * @param bmp
     * @return
     */
    public static Bitmap getSmallBitmap(byte[] bmp) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bmp, 0, bmp.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 100, 100);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeByteArray(bmp, 0, bmp.length, options);
    }


    /**
     * 设置单个图片（使用缓存） 有默认图
     *
     * @param imgurl
     * @param iv
     * @param pre_id
     */
    public static void setImageIconAnsyCachePre(
            String imgurl, ImageView iv, int pre_id) {
        DisplayImageOptions display_options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .showImageOnLoading(pre_id).showImageOnFail(pre_id)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Config.RGB_565)
                .showImageForEmptyUri(pre_id).considerExifParams(true).build();
        ImageLoader loader = ImageLoader.getInstance();
        loader.resume();
        loader.displayImage(imgurl, iv, display_options);
    }


    /**
     * 设置单个图片（使用缓存） 有默认图
     *
     * @param imgurl
     * @param iv
     * @param pre_id
     */
    public static void setImageIconAnsyCachePreInSamplePower(
            String imgurl, ImageView iv, int pre_id) {
        DisplayImageOptions display_options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .showImageOnLoading(pre_id).showImageOnFail(pre_id)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Config.RGB_565)
                .showImageForEmptyUri(pre_id).considerExifParams(true).build();
        ImageLoader loader = ImageLoader.getInstance();
        loader.resume();
        loader.displayImage(imgurl, iv, display_options);
    }

    /**
     * 设置单个图片（使用缓存） 有默认图带事件监听
     *
     * @param imgurl
     * @param iv
     * @param pre_id
     */
    public static void setImageIconAnsyCachePreWithListener(
            String imgurl, ImageView iv, int pre_id, ImageLoadingListener listener) {
        DisplayImageOptions display_options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .showImageOnLoading(pre_id).showImageOnFail(pre_id)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Config.RGB_565)
                .showImageForEmptyUri(pre_id).considerExifParams(true).build();
        ImageLoader loader = ImageLoader.getInstance();
        loader.resume();
        loader.displayImage(imgurl, iv, display_options, listener);
    }

    /**
     * 从drawable获取获取单个图片bitmap
     *
     * @param res_id
     */
    public static Bitmap getBitmapFromDrawable(int res_id, ImageScaleType scaleType) {

        DisplayImageOptions display_options;
        if (scaleType == null) {
            display_options = new DisplayImageOptions.Builder()
                    .bitmapConfig(Config.RGB_565)
                    .cacheInMemory(false).cacheOnDisc(false)
                    .build();
        } else {
            display_options = new DisplayImageOptions.Builder()
                    .bitmapConfig(Config.RGB_565)
                    .cacheInMemory(false).cacheOnDisc(false)
                    .imageScaleType(scaleType)
                    .build();
        }
        ImageLoader loader = ImageLoader.getInstance();
        String uri = "drawable://" + res_id;
        MemoryCacheUtils.removeFromCache(uri, loader.getMemoryCache());
        DiskCacheUtils.removeFromCache(uri, loader.getDiskCache());
        return loader.loadImageSync(uri, display_options);
    }

}

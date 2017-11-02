package com.qwm.androidreview.gildedemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.qwm.androidreview.R;
import com.qwm.androidreview.utils.DimenUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.bumptech.glide.request.RequestOptions.overrideOf;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/11/1<br>
 * <b>Author:</b> q<br>
 * <b>Description:</b> <br>
 */
public class GlideAdapter  extends RecyclerView.Adapter<GlideAdapter.ViewHolder>  {
    private Context context;
    private List<Type> dataSet;

    enum Type {
        Mask,
        NinePatchMask,
        CropTop,
        CropCenter,
        CropBottom,
        CropSquare,
        CropCircle,
        ColorFilter,
        Grayscale,
        RoundedCorners,
        Blur,
        Toon,
        Sepia,
        Contrast,
        Invert,
        Pixel,
        Sketch,
        Swirl,
        Brightness,
        Kuawahara,
        Vignette
    }

    public GlideAdapter(Context context, List<Type> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @Override public GlideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.glide_transfrom2_item, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(GlideAdapter.ViewHolder holder, int position) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        switch (dataSet.get(position)) {
            case Mask: {
                int width = DimenUtils.dip2px(context, 133.33f);
                int height = DimenUtils.dip2px(context, 126.33f);
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(overrideOf(width, height))
                        .apply(bitmapTransform(new MultiTransformation<>(new CenterCrop(), new MaskTransformation(R.mipmap.mask_starfish))))
                        .into(holder.image);
                break;
            }
            case NinePatchMask: {
                int width = DimenUtils.dip2px(context, 150.0f);
                int height = DimenUtils.dip2px(context, 100.0f);
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(overrideOf(width, height))
                        .apply(bitmapTransform(new MultiTransformation<>(new CenterCrop(), new MaskTransformation(R.mipmap.mask_chat_right))))
                        .into(holder.image);
                break;
            }
            case CropTop:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_demo)
                        .apply(bitmapTransform(
                                new CropTransformation(DimenUtils.dip2px(context, 300), DimenUtils.dip2px(context, 100),
                                        CropTransformation.CropType.TOP)))
                        .into(holder.image);
                break;
            case CropCenter:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_demo)
                        .apply(bitmapTransform(
                                new CropTransformation(DimenUtils.dip2px(context, 300), DimenUtils.dip2px(context, 100))))
                        .into(holder.image);
                break;
            case CropBottom:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_demo)
                        .apply(bitmapTransform(
                                new CropTransformation(DimenUtils.dip2px(context, 300), DimenUtils.dip2px(context, 100),
                                        CropTransformation.CropType.BOTTOM)))
                        .into(holder.image);

                break;
            case CropSquare:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_demo)
                        .apply(bitmapTransform(new CropSquareTransformation()))
                        .into(holder.image);
                break;
            case CropCircle:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_demo)
                        .apply(bitmapTransform(new CropCircleTransformation()))
                        .into(holder.image);
                break;
            case ColorFilter:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_demo)
                        .apply(bitmapTransform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0))))
                        .into(holder.image);
                break;
            case Grayscale:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_demo)
                        .apply(bitmapTransform(new GrayscaleTransformation()))
                        .into(holder.image);
                break;
            case RoundedCorners:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_demo)
                        .apply(bitmapTransform(new RoundedCornersTransformation(45, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM)))
                        .into(holder.image);
                break;
            case Blur:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(bitmapTransform(new BlurTransformation(25)))
                        .into(holder.image);
                break;
            case Toon:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_demo)
                        .apply(bitmapTransform(new ToonFilterTransformation()))
                        .into(holder.image);
                break;
            case Sepia:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(bitmapTransform(new SepiaFilterTransformation()))
                        .into(holder.image);
                break;
            case Contrast:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(bitmapTransform(new ContrastFilterTransformation(2.0f)))
                        .into(holder.image);
                break;
            case Invert:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(bitmapTransform(new InvertFilterTransformation()))
                        .into(holder.image);
                break;
            case Pixel:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(bitmapTransform(new PixelationFilterTransformation(20)))
                        .into(holder.image);
                break;
            case Sketch:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(bitmapTransform(new SketchFilterTransformation()))
                        .into(holder.image);
                break;
            case Swirl:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(bitmapTransform(
                                new SwirlFilterTransformation(0.5f, 1.0f, new PointF(0.5f, 0.5f))).dontAnimate())
                        .into(holder.image);
                break;
            case Brightness:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(bitmapTransform(new BrightnessFilterTransformation(0.5f)).dontAnimate())
                        .into(holder.image);
                break;
            case Kuawahara:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(bitmapTransform(new KuwaharaFilterTransformation(25)).dontAnimate())
                        .into(holder.image);
                break;
            case Vignette:
                Glide.with(context)
                        .load(R.mipmap.ic_glide_tr_check)
                        .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                                new float[] { 0.0f, 0.0f, 0.0f }, 0f, 0.75f)).dontAnimate())
                        .into(holder.image);
                break;
        }
        holder.title.setText(dataSet.get(position).name());
    }

    @Override public int getItemCount() {
        return dataSet.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}

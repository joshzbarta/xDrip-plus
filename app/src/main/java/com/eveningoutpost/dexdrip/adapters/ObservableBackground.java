package com.eveningoutpost.dexdrip.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import lombok.Setter;

@Setter
public final class ObservableBackground extends BaseObservable {
    @Nullable
    private Integer drawableResource;

    @Nullable
    private Integer colorResource;
    @Nullable
    private Integer colorValue;
    @Nullable
    private Drawable drawable;
    @Nullable
    private Bitmap bitmap;

    private void reset() {
        this.drawableResource = null;
        this.colorResource = null;
        this.colorValue = null;
        this.drawable = null;
    }

    public final void setDrawable(Drawable drawable) {
        this.reset();
        this.drawable = drawable;
        this.notifyChange();
    }

    public final void setBitmap(Bitmap bitmap) {
        this.reset();
        this.bitmap = bitmap;
        this.notifyChange();
    }

    public final void clear() {
        this.reset();
        this.notifyChange();
    }

    @BindingAdapter(value = "background")
    public static void setBackground(View view, ObservableBackground observable) {
        Integer resource;
        if (observable.getDrawableResource() != null) {
            resource = observable.getDrawableResource();
            if (resource != null) {
                view.setBackgroundResource(resource);
            }
        } else if (observable.getColorResource() != null) {
            resource = observable.getColorResource();
            if (resource != null) {
                final int mcolor = ContextCompat.getColor(view.getContext(), resource);
                view.setBackgroundColor(mcolor);
            }
        } else if (observable.getColorValue() != null) {
            final Integer colorVal = observable.getColorValue();
            if (colorVal != null) {
                view.setBackgroundColor(colorVal);
            }
        } else if (observable.getDrawable() != null) {
            final Drawable drawable = observable.getDrawable();
            if (drawable != null) {
                view.setBackground(drawable);
            }
        } else if (observable.getBitmap() != null) {
            Bitmap bitmap = observable.getBitmap();
            if (bitmap != null) {
                view.setBackground((new BitmapDrawable(view.getContext().getResources(), bitmap)));
            }
        } else {
            view.setBackgroundResource(0);
        }
    }

    public final void setDrawableResource(@DrawableRes int drawableResource) {
        this.reset();
        this.drawableResource = drawableResource;
        this.notifyChange();
    }

    public final void setColorResource(@ColorRes int colorResource) {
        this.reset();
        this.colorResource = colorResource;
        this.notifyChange();
    }

    public final void setColorValue(int colorValue) {
        this.reset();
        this.colorValue = colorValue;
        this.notifyChange();
    }

    @Nullable
    public Integer getDrawableResource() {
        return this.drawableResource;
    }

    @Nullable
    public Integer getColorResource() {
        return this.colorResource;
    }

    @Nullable
    public Integer getColorValue() {
        return this.colorValue;
    }

    @Nullable
    public Drawable getDrawable() {
        return this.drawable;
    }

    @Nullable
    public Bitmap getBitmap() {
        return this.bitmap;
    }
}

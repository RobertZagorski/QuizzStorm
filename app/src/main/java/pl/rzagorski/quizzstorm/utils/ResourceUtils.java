package pl.rzagorski.quizzstorm.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.model.ui.BlendColor;

/**
 * Created by Robert Zagórski on 29.07.2016.
 */
public class ResourceUtils {

    @Inject
    public ResourceUtils() {
    }

    public List<BlendColor> getBlendColors(@NonNull Context context) {
        final int PERCENTAGE_FROM = 0;
        final int PERCENTAGE_TO = 1;
        final int COLOR_HEX = 2;

        TypedArray blends = context.getResources().obtainTypedArray(R.array.blends);
        if (blends == null) {
            return Collections.emptyList();
        }
        List<BlendColor> blendColorList = new ArrayList<>();
        for (int i = 0; i < blends.length(); ++i) {
            int blendToParse = blends.getResourceId(i, 0);
            TypedArray rawBlend = context.getResources().obtainTypedArray(blendToParse);

            BlendColor blendColor = new BlendColor();

            int percentageFrom = rawBlend.getInteger(PERCENTAGE_FROM, 0);
            int percentageTo = rawBlend.getInteger(PERCENTAGE_TO, 0);
            String color = rawBlend.getString(COLOR_HEX);

            blendColor.setPercentageFrom(percentageFrom);
            blendColor.setPercentageTo(percentageTo);
            blendColor.setColor(Color.parseColor(color));

            blendColorList.add(blendColor);
        }
        return blendColorList;
    }
}

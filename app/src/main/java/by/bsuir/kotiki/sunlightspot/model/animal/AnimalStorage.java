package by.bsuir.kotiki.sunlightspot.model.animal;

import android.content.Context;
import android.graphics.drawable.Drawable;

public final class AnimalStorage {
    private static final AnimalStorage instance = new AnimalStorage();
    private String activeAnimal = "shunia";

    private AnimalStorage() {
    }

    public static AnimalStorage getInstance() {
        return instance;
    }

    public Drawable getAnimal(int code, Context context) {
        StringBuilder builder = new StringBuilder(activeAnimal).append('_');

        if (code == 800) {
            builder.append(code);
        } else {
            builder.append(code / 100);
        }

        builder.append("_f");
        String path = builder.toString();

        return context.getResources().getDrawable(context.getResources().getIdentifier(path, "mipmap", context.getPackageName()));
    }
}

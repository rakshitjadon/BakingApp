package example.rakshit.android.baking.utils;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import example.rakshit.android.baking.widget.BakeryService;

/**
 * Created by rakshit on 25/03/18.
 */

@SuppressLint("Registered")
public class SetThings extends IntentService{
    public SetThings(String name) {
        super(name);
    }
    public static void makeWidget(Context context, ArrayList<String> things) {
        Intent intent = new Intent(context, BakeryService.class);
        intent.putExtra("Things", things);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}

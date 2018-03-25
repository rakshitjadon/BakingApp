package example.rakshit.android.baking.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.ArrayList;
/**
 * Created by rakshit on 25/03/18.
 */
public class BakeryService extends IntentService {
    public BakeryService() {
        super("BakeryService");
    }
    public void setThings(ArrayList<String> things) {
        Intent intent = new Intent("android.appwidget.action.WIDGET");
        intent.setAction("android.appwidget.action.WIDGET");
        intent.putExtra("Things",things);
        sendBroadcast(intent);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent doSomething) {
        if (doSomething != null) {
            ArrayList<String> things = doSomething.getExtras().getStringArrayList("Things");
            setThings(things);
        }
    }


}

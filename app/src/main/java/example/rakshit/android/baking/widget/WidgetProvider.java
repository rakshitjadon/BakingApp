package example.rakshit.android.baking.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.ArrayList;

import example.rakshit.android.baking.R;
import example.rakshit.android.baking.DetailActivity;
/**
 * Created by rakshit on 25/03/18.
 */

public class WidgetProvider extends AppWidgetProvider {

    static ArrayList<String> thingsList = new ArrayList<>();
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        makeCategories(context);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, DetailActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        rv.setPendingIntentTemplate(R.id.widget_view, pendingIntent);
        rv.setRemoteAdapter(R.id.widget_view, new Intent(context, WidgetBakery.class));
        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }

    public void widgetlist(Context context, Intent intent) {
        int[] appWidgetId = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, WidgetProvider.class));
        final String action = intent.getAction();
        assert action != null;
        if (action.equals("android.appwidget.action.WIDGET")) {
            getIntent(intent);
            AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_view);
            WidgetProvider.updateBakingWidgets(context, AppWidgetManager.getInstance(context), appWidgetId);
        }
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }

    public static void updateBakingWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        widgetlist(context, intent);
        super.onReceive(context, intent);
    }
    public void getIntent(Intent intent)
    {
        thingsList = intent.getExtras().getStringArrayList("Things");
    }
    public static void makeCategories(Context context)
    {
        new Intent(context, DetailActivity.class).addCategory(Intent.ACTION_MAIN);
        new Intent(context, DetailActivity.class).addCategory(Intent.CATEGORY_LAUNCHER);
        new Intent(context, DetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }

}



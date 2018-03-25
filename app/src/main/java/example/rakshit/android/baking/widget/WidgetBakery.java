package example.rakshit.android.baking.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

import example.rakshit.android.baking.R;

import static example.rakshit.android.baking.widget.WidgetProvider.thingsList;
/**
 * Created by rakshit on 25/03/18.
 */
public class WidgetBakery extends RemoteViewsService {

    List<String> thingsRemoteList;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ThingsRemoteViewsFactory(this.getApplicationContext(), intent);
    }private class ThingsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        Context mContext = null;
        private ThingsRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
        }
        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
            rv.setTextViewText(R.id.widget_item, thingsRemoteList.get(position));
            rv.setOnClickFillInIntent(R.id.widget_item, new Intent());
            return rv;
        }
        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            thingsRemoteList = thingsList;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return thingsRemoteList.size();
        }


        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}

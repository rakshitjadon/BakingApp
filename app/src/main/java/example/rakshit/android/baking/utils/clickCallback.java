package example.rakshit.android.baking.utils;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
/**
 * Created by rakshit on 21/03/18.
 */
public class clickCallback implements RecyclerView.OnItemTouchListener {

    private Interface.OnItemClickListener onItemClick;
    private GestureDetector singleTap;
    public clickCallback(Context context, Interface.OnItemClickListener clickListener) {
        onItemClick = clickListener;
        singleTap = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View view = rv.findChildViewUnder(e.getX(), e.getY());
        if (view != null && onItemClick != null && singleTap.onTouchEvent(e)) {
            onItemClick.onItemClick(view, rv.getChildAdapterPosition(view));
        }
        return false;
    }
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}

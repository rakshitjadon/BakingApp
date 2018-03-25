// Generated code from Butter Knife. Do not modify!
package example.rakshit.android.baking.Adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import example.rakshit.android.baking.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ThingAdapter$ThingsViewHolder_ViewBinding implements Unbinder {
  private ThingAdapter.ThingsViewHolder target;

  @UiThread
  public ThingAdapter$ThingsViewHolder_ViewBinding(ThingAdapter.ThingsViewHolder target,
      View source) {
    this.target = target;

    target.ingredient = Utils.findRequiredViewAsType(source, R.id.ingredient, "field 'ingredient'", TextView.class);
    target.quantity = Utils.findRequiredViewAsType(source, R.id.ing_quantity, "field 'quantity'", TextView.class);
    target.measure = Utils.findRequiredViewAsType(source, R.id.ing_measure, "field 'measure'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ThingAdapter.ThingsViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ingredient = null;
    target.quantity = null;
    target.measure = null;
  }
}

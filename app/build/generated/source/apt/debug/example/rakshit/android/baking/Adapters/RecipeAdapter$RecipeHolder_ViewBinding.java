// Generated code from Butter Knife. Do not modify!
package example.rakshit.android.baking.Adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import example.rakshit.android.baking.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecipeAdapter$RecipeHolder_ViewBinding implements Unbinder {
  private RecipeAdapter.RecipeHolder target;

  @UiThread
  public RecipeAdapter$RecipeHolder_ViewBinding(RecipeAdapter.RecipeHolder target, View source) {
    this.target = target;

    target.dish_name = Utils.findRequiredViewAsType(source, R.id.dish_name, "field 'dish_name'", TextView.class);
    target.thumbnail = Utils.findRequiredViewAsType(source, R.id.image_view, "field 'thumbnail'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecipeAdapter.RecipeHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dish_name = null;
    target.thumbnail = null;
  }
}

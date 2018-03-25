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

public class RecipeAdapter$RecipeViewHolder_ViewBinding implements Unbinder {
  private RecipeAdapter.RecipeViewHolder target;

  @UiThread
  public RecipeAdapter$RecipeViewHolder_ViewBinding(RecipeAdapter.RecipeViewHolder target,
      View source) {
    this.target = target;

    target.dish_name = Utils.findRequiredViewAsType(source, R.id.dish_name, "field 'dish_name'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecipeAdapter.RecipeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dish_name = null;
  }
}

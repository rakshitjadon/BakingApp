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

public class StoneAdapter$StoneViewHolder_ViewBinding implements Unbinder {
  private StoneAdapter.StoneViewHolder target;

  @UiThread
  public StoneAdapter$StoneViewHolder_ViewBinding(StoneAdapter.StoneViewHolder target,
      View source) {
    this.target = target;

    target.stepParagraph = Utils.findRequiredViewAsType(source, R.id.step_para, "field 'stepParagraph'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StoneAdapter.StoneViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.stepParagraph = null;
  }
}

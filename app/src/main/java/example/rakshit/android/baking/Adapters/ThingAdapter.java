package example.rakshit.android.baking.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.rakshit.android.baking.R;
import example.rakshit.android.baking.Model.Ingredient;
/**
 * Created by rakshit on 21/03/18.
 */
public class ThingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Ingredient> mThings;
    private Context mContext;
    public ThingAdapter(List<Ingredient> things, Context context) {
        mThings = things;
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ingredient_item, parent, false);
        return new ThingsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        callinAdapter((ThingsViewHolder) holder, position);
    }
    @Override
    public int getItemCount() {
        return mThings.size();
    }
    private void callinAdapter(ThingsViewHolder thingsViewHolder, int position) {
        thingsViewHolder.ingredient.setText(mThings.get(position).getIngredient());
        thingsViewHolder.quantity.setText(String.valueOf(mThings.get(position).getQuantity()));
        thingsViewHolder.measure.setText(mThings.get(position).getMeasure());
    }
    public class ThingsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredient)
        TextView ingredient;
        @BindView(R.id.ing_quantity)
        TextView quantity;
        @BindView(R.id.ing_measure)
        TextView measure;
        ThingsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

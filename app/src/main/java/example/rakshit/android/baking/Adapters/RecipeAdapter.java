package example.rakshit.android.baking.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.rakshit.android.baking.Model.Dish;
import example.rakshit.android.baking.R;
/**
 * Created by rakshit on 21/03/18.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Dish[] mDishes;
    private Context mContext;
    public RecipeAdapter(Dish[] dishes, Context context) {
        mDishes = dishes;
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_item, parent, false);
        return new RecipeHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        callinAdapter((RecipeHolder) holder, position);
    }
    @Override
    public int getItemCount() {
        return  mDishes.length;
    }
    private void callinAdapter(RecipeHolder recipeViewHolder, int position) {
        recipeViewHolder.dish_name.setText(mDishes[position].getName());
    }
    @SuppressWarnings("WeakerAccess")
    public class RecipeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dish_name)
        TextView dish_name;
        private RecipeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

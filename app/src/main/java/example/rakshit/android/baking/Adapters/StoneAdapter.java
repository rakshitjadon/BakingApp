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
import example.rakshit.android.baking.Model.Stones;
/**
 * Created by rakshit on 21/03/18.
 */
public class StoneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Stones> mStones;
    private onClick onClick;
    private Context mContext;
    public interface onClick {
        void onItemClick(List<Stones> stonesList, int afterClick);
    }
    public StoneAdapter(List<Stones> stones, onClick listener, Context context) {
        mStones = stones;
        onClick = listener;
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.step_item, parent, false);
        return new StoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        callInAdapter((StoneViewHolder) holder, position);
    }

    @Override
    public int getItemCount() { return mStones.size(); }

    private void callInAdapter(StoneViewHolder stepViewHolder, int position) {
        stepViewHolder.stepParagraph.setText(mStones.get(position).getShortDescription());
    }

    public class StoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.step_para)
        TextView stepParagraph;
        private StoneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onClick.onItemClick(mStones, getAdapterPosition());
        }
    }
}

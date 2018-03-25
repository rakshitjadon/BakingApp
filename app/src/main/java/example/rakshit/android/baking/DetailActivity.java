package example.rakshit.android.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import example.rakshit.android.baking.Adapters.StoneAdapter;
import example.rakshit.android.baking.Model.Dish;
import example.rakshit.android.baking.Model.Stones;
import example.rakshit.android.baking.Display.fragments.ExoPlayerFragment;
import example.rakshit.android.baking.Display.fragments.DetailFragment;
/**
 * Created by rakshit on 20/03/18.
 */
public class DetailActivity extends AppCompatActivity implements StoneAdapter.onClick {

    List<Stones> stones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Dish dish = getIntent().getParcelableExtra("dish");
        stones = dish.getSteps();
        if (savedInstanceState == null) {
            DetailFragment detailFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putParcelable("Stones", dish);
            detailFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.stone, detailFragment)
                    .commit();
            if (findViewById(R.id.tablet) != null) {
                Stones getStones = stones.get(0);
                ExoPlayerFragment exoPlayerFragment = new ExoPlayerFragment();
                Bundle stepArgs = new Bundle();
                stepArgs.putParcelable("Stones", getStones);
                exoPlayerFragment.setArguments(stepArgs);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.stone_detail, exoPlayerFragment)
                        .commit();
            }
            //getActionBar().setDisplayShowHomeEnabled(false);
        }

    }

    @Override
    public void onItemClick(List<Stones> stonesList, int afterClick) {
        Stones listStones = stones.get(afterClick);
        ExoPlayerFragment fragment = new ExoPlayerFragment();
        Bundle args = new Bundle();
        args.putParcelable("Stones", listStones);
        fragment.setArguments(args);
        if (findViewById(R.id.simple)!= null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.stone, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.stone_detail, fragment)
                    .addToBackStack(null)
                    .commit();
        }

    }
}

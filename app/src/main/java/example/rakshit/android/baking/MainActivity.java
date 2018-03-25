package example.rakshit.android.baking;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import example.rakshit.android.baking.Display.fragments.MainFragment;
import example.rakshit.android.baking.IdlingResource.SimpleIdlingResource;
/**
 * Created by rakshit on 20/03/18.
 */
public class MainActivity extends AppCompatActivity {

    @Nullable
    private SimpleIdlingResource mIdlingResource;
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        if (findViewById(R.id.recipe_main) != null) {
            args.putBoolean(getString(R.string.two_pane), false);
            mainFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_main, mainFragment)
                    .commit();
        } else {
            args.putBoolean(getString(R.string.two_pane), true);
            mainFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_main_pane, mainFragment)
                    .commit();
        }
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getIdlingResource();
    }
}

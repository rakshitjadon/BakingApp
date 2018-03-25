package example.rakshit.android.baking.Display.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.rakshit.android.baking.Model.Dish;
import example.rakshit.android.baking.R;
import example.rakshit.android.baking.DetailActivity;
import example.rakshit.android.baking.Adapters.RecipeAdapter;
import example.rakshit.android.baking.utils.Interface;
import example.rakshit.android.baking.utils.JsonParser;
import example.rakshit.android.baking.utils.NetworkUtils;
import example.rakshit.android.baking.utils.clickCallback;

import static android.support.test.InstrumentationRegistry.getContext;
/**
 * Created by rakshit on 21/03/18.
 */
public class MainFragment extends Fragment {

    Dish[] mDish;
    boolean isTwoPane;
    FetchDishes fetchdishes;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_interface, container, false);
    }
    @SuppressLint("StaticFieldLeak")
    public class FetchDishes extends android.os.AsyncTask<URL, Void, Dish[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Dish[] doInBackground(URL... params) {
            URL url = params[0];
            String response ;
            try {
                response = NetworkUtils.getResponseFromHttp(url);
                return JsonParser.getdata(response);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Dish[] dishes) {
            super.onPostExecute(dishes);
            progressBar.setVisibility(View.INVISIBLE);
            setAdapter(dishes);
        }
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        URL url = NetworkUtils.buildUrl();
        if (getArguments() != null) {
            isTwoPane = getArguments().getBoolean(getResources().getString(R.string.two_pane));}
        if (!isTwoPane) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }
        recyclerView.addOnItemTouchListener(new clickCallback(getContext(), new Interface.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), DetailActivity.class).putExtra("dish", mDish[position]));
            }
        }));
        new FetchDishes().execute(url);
    }
    public void setAdapter(Dish[] dishes)
    {
        mDish = dishes;
        recyclerView.setAdapter(new RecipeAdapter(dishes, getContext()));
    }
}




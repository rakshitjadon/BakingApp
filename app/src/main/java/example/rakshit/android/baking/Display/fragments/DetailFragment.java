package example.rakshit.android.baking.Display.fragments;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.rakshit.android.baking.Model.Dish;
import example.rakshit.android.baking.R;
import example.rakshit.android.baking.Model.Ingredient;
import example.rakshit.android.baking.Model.Stones;
import example.rakshit.android.baking.DetailActivity;
import example.rakshit.android.baking.Adapters.ThingAdapter;
import example.rakshit.android.baking.Adapters.StoneAdapter;
import example.rakshit.android.baking.utils.SetThings;

/**
 * Created by rakshit on 21/03/18.
 */
public class DetailFragment extends Fragment {

    Dish mDish;
    Parcelable stoneList;
    RecyclerView rv_ingredient;
    RecyclerView rv_stone;
    List<Ingredient> ingredient = null;
    StoneAdapter stoneAdapter;
    ThingAdapter thingAdapter;
    Parcelable thingList;
    List<Stones> stones = null;
    public DetailFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.steps_interface, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            mDish = savedInstanceState.getParcelable("dish");
            thingList = savedInstanceState.getParcelable("ingredient");
            stoneList = savedInstanceState.getParcelable("step");
        } else {
            if (getArguments()!= null) {
                mDish = getArguments().getParcelable("Stones");
            }
        }
        if (mDish!= null) {
            ingredient = mDish.getIngredients();
            stones = mDish.getSteps();
        }
        rv_ingredient = (RecyclerView) view.findViewById(R.id.rv_ingredient);
        thingAdapter = new ThingAdapter(ingredient,getContext());
        rv_ingredient.setAdapter(thingAdapter);
        rv_ingredient.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_stone = (RecyclerView) view.findViewById(R.id.rv_stone);
        stoneAdapter = new StoneAdapter(stones, (DetailActivity) getActivity(),getContext());
        rv_stone.setAdapter(stoneAdapter);
        rv_stone.setLayoutManager(new LinearLayoutManager(getActivity()));
        widget();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("dish", mDish);
        outState.putParcelable("ingredient", rv_ingredient.getLayoutManager().onSaveInstanceState());
        outState.putParcelable("step", rv_stone.getLayoutManager().onSaveInstanceState());
    }
    @Override
    public void onResume() {
        super.onResume();
        rv_ingredient.getLayoutManager().onRestoreInstanceState(thingList);
        rv_stone.getLayoutManager().onRestoreInstanceState(stoneList);
    }
    public void widget()
    {
        final ArrayList<String> widhet = new ArrayList<>();
        for (int i = 0; i < ingredient.size(); i++) {
            String thingName = ingredient.get(i).getIngredient();
            float quantity = ingredient.get(i).getQuantity();
            String measure = ingredient.get(i).getMeasure();
            widhet.add(thingName + "\t\t\t"  + quantity + "\t" + measure + "\n");
        }
        SetThings.makeWidget(getContext(), widhet);

    }



}

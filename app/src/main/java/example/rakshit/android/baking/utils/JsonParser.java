package example.rakshit.android.baking.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import example.rakshit.android.baking.Model.Dish;
import example.rakshit.android.baking.Model.Ingredient;
import example.rakshit.android.baking.Model.Stones;

/**
 * Created by rakshit on 21/03/18.
 */

public class JsonParser {
    public static Dish[] getdata(String dishJSON) throws JSONException {
        JSONArray dishArray = new JSONArray(dishJSON);
        Dish[] dishes = new Dish[dishArray.length()];
        for (int a = 0; a < dishArray.length(); a++) {
            dishes[a] = new Dish();
            JSONObject dishinfo = dishArray.getJSONObject(a);
            dishes[a].setName(dishinfo.getString("name"));
            dishes[a].setImage(dishinfo.getString("image"));
            dishes[a].setServings(dishinfo.getString("servings"));
            List<Ingredient> inList = new ArrayList<>();
            JSONArray inArray = new JSONArray(dishinfo.getString("ingredients"));
            Ingredient[] things = new Ingredient[inArray.length()];
            for (int b = 0; b < inArray.length(); b++) {
                things[b] = new Ingredient();
                JSONObject detail = inArray.getJSONObject(b);
                things[b].setQuantity(detail.getLong("quantity"));
                things[b].setMeasure(detail.getString("measure"));
                things[b].setIngredient(detail.getString("ingredient"));
                inList.add(things[b]);
            }
            dishes[a].setIngredients(inList);
            List<Stones> stList = new ArrayList<>();
            JSONArray stArray = new JSONArray(dishinfo.getString("steps"));
            Stones[] stones = new Stones[stArray.length()];
            for (int c = 0; c < stArray.length(); c++) {
                stones[c] = new Stones();
                JSONObject stoneDetail = stArray.getJSONObject(c);
                stones[c].setId(stoneDetail.getInt("id"));
                stones[c].setShortDescription(stoneDetail.getString("shortDescription"));
                stones[c].setDescription(stoneDetail.getString("description"));
                stones[c].setVideoURL(stoneDetail.getString("videoURL"));
                stones[c].setThumbnailURL(stoneDetail.getString("thumbnailURL"));
                stList.add(stones[c]);
            }
            dishes[a].setSteps(stList);
        }
        return dishes;
    }
}

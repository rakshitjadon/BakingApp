package example.rakshit.android.baking.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by rakshit on 21/03/18.
 */
public class Dish implements Parcelable {

    private String name;
    private List<Ingredient> ingredients;
    private List<Stones> stones;
    private String servings;
    private String image;

    public Dish() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Stones> getSteps() {
        return stones;
    }

    public void setSteps(List<Stones> stones) {
        this.stones = stones;
    }


    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeList(ingredients);
        dest.writeList(stones);
        dest.writeString(servings);
        dest.writeString(image);
    }

    private Dish(Parcel parcel) {
        name = parcel.readString();
        ingredients = new ArrayList<>();
        parcel.readList(ingredients, Ingredient.class.getClassLoader());
        stones = new ArrayList<>();
        parcel.readList(stones, Stones.class.getClassLoader());
        servings = parcel.readString();
        image = parcel.readString();
    }

    public static final Parcelable.Creator<Dish> CREATOR = new Parcelable.Creator<Dish>() {
        public Dish createFromParcel(Parcel source) {
            return new Dish(source);
        }
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };
}

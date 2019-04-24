package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * convert from Json to object Sandwich
     *
     * @param json Input data as JSON string format
     * @return @Sandwich Output
     */
    public static Sandwich parseSandwichJson(String json) {
        if (json.isEmpty()) return null;

        Sandwich sandwich = new Sandwich();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");
            sandwich.setMainName(name.getString("mainName"));
            sandwich.setAlsoKnownAs(jsonArrayToList(name.getJSONArray("alsoKnownAs")));
            sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));
            sandwich.setDescription(jsonObject.getString("description"));
            sandwich.setImage(jsonObject.getString("image"));
            sandwich.setIngredients(jsonArrayToList(jsonObject.getJSONArray("ingredients")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    /**
     * Convert from JSonArray object to List
     *
     * @param jsonArray Input
     * @return List Output
     * @throws JSONException exception
     */
    private static List<String> jsonArrayToList(JSONArray jsonArray) throws JSONException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(String.valueOf(jsonArray.get(i)));
        }
        return result;
    }
}

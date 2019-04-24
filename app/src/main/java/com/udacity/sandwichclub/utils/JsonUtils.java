package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.constants.SandwichConst;
import com.udacity.sandwichclub.models.SandwichModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * convert from Json to object SandwichModel
     *
     * @param json Input data as JSON string format
     * @return @SandwichModel Output
     */
    public static SandwichModel parseSandwichJson(String json) {
        if (json.isEmpty()) return null;

        SandwichModel model = new SandwichModel();
        try {
            JSONObject object = new JSONObject(json);
            JSONObject name = object.getJSONObject(SandwichConst.NAME);
            model.setMainName(name.getString(SandwichConst.MAIN_NAME));
            model.setAlsoKnownAs(jsonArrayToList(name.getJSONArray(SandwichConst.ALSO_KNOWN_AS)));
            model.setPlaceOfOrigin(object.getString(SandwichConst.PLACE_OF_ORIGIN));
            model.setDescription(object.getString(SandwichConst.DESCRIPTION));
            model.setImage(object.getString(SandwichConst.IMAGE));
            model.setIngredients(jsonArrayToList(object.getJSONArray(SandwichConst.INGREDIENTS)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return model;
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

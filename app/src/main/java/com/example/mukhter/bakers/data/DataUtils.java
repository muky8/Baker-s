package com.example.mukhter.bakers.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
/**
 * Created by MUKHTER on 10/07/2017.
 */

public class DataUtils {

    public static final String BASE_URL = "http://go.udacity.com/android-baking-app-json";

    public static URL buildBaseUrl() {

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static ArrayList<Ingredient> extractIngredientsFromJson(String ingredientJson){

        ArrayList<Ingredient> jsonResult = new ArrayList<>();

        try {
            JSONArray ingredientArray = new JSONArray(ingredientJson);
            for(int i = 0; i < ingredientArray.length(); i++) {
                JSONObject ingredientObject = ingredientArray.getJSONObject(i);
                String quantity = ingredientObject.getString("quantity");
                String measure = ingredientObject.getString("measure");
                String ingredient = ingredientObject.getString("ingredient");

                jsonResult.add(new Ingredient(quantity, measure, ingredient));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    public static ArrayList<Step> extractStepsFromJson(String stepJson){

        ArrayList<Step> jsonResult = new ArrayList<>();

        try {
            JSONArray stepArray = new JSONArray(stepJson);
            for(int i = 0; i < stepArray.length(); i++) {
                JSONObject stepsObject = stepArray.getJSONObject(i);
                String id = stepsObject.getString("id");
                String sDesc = stepsObject.getString("shortDescription");
                String desc = stepsObject.getString("description");
                String video = stepsObject.getString("videoURL");
                String thumb = stepsObject.getString("thumbnailURL");

                jsonResult.add(new Step(id,sDesc,desc,video,thumb));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    public static ArrayList<Recipe> allJson(String recipeJson){

        ArrayList<Recipe> resultRecipe = new ArrayList<>();

        try {
            JSONArray recipeArray = new JSONArray(recipeJson);
            for(int i = 0; i < recipeArray.length(); i++){
                JSONObject recipeObject = recipeArray.getJSONObject(i);
                String name = recipeObject.getString("name");
                String ingredients = recipeObject.getJSONArray("ingredients").toString();
                String steps = recipeObject.getJSONArray("steps").toString();

                resultRecipe.add(new Recipe(name,ingredients,steps));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultRecipe;
    }

    public static String ingredientToString(ArrayList<Ingredient> list){
        String mList = "";
        for(Ingredient a: list){
            mList += "o \t" + a.getmIngredient() + " (" + a.getmQty() + " " + a.getmMeasure() + ")\n";
        }
        return mList;
    }

    //pass data here before recipe bulk insert
    public static ContentValues[] getRecipeCvArray(ArrayList<Recipe> recipeArrayList){

        ContentValues[] result = new ContentValues[recipeArrayList.size()];

        ContentValues contentValues;

        int count = 0;
        for(Recipe a : recipeArrayList){
            contentValues = new ContentValues();
            contentValues.put(BakingContract.BakingEntry.RECIPE,a.getmName());
            contentValues.put(BakingContract.BakingEntry.INGREDIENTS,a.getmIngredients());
            contentValues.put(BakingContract.BakingEntry.STEPS,a.getmSteps());
            result[count] = contentValues;
            count++;
        }
        return result;
    }

    public static ArrayList<Recipe> cursorToArrayListRecipe(Cursor cursor){

        ArrayList<Recipe> result = new ArrayList<>();
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String name = cursor.getString(cursor.getColumnIndex(BakingContract.BakingEntry.RECIPE));
            String ingredient = cursor.getString(cursor.getColumnIndex(BakingContract.BakingEntry.INGREDIENTS));
            String step = cursor.getString(cursor.getColumnIndex(BakingContract.BakingEntry.STEPS));

            result.add(new Recipe(name,ingredient,step));

        }
        cursor.close();

        return result;
    }

}

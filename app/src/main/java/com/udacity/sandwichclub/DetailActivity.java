package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.models.SandwichModel;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView mIngredientsIv;
    private TextView mOriginTv;
    private TextView mDescriptionTv;
    private TextView mIngredientsTv;
    private TextView mAlsoKnownTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mIngredientsIv = findViewById(R.id.image_iv);
        mOriginTv = findViewById(R.id.origin_tv);
        mDescriptionTv = findViewById(R.id.description_tv);
        mIngredientsTv = findViewById(R.id.ingredients_tv);
        mAlsoKnownTv = findViewById(R.id.also_known_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        SandwichModel model = JsonUtils.parseSandwichJson(json);
        if (model == null) {
            // SandwichModel data unavailable
            closeOnError();
            return;
        }

        populateUI(model);

        Picasso.with(this)
                .load("https://upload.wikimedia.org/wikipedia/commons/4/48/Chivito1.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(mIngredientsIv);

        setTitle(model.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(SandwichModel model) {
        mOriginTv.setText(model.getPlaceOfOrigin());
        mDescriptionTv.setText(model.getDescription());

        List<String> ingredients = model.getIngredients();
        if (!ingredients.isEmpty()) mIngredientsTv.setText(String.valueOf(ingredients));
        List<String> alsoKnownAs = model.getAlsoKnownAs();
        if (!alsoKnownAs.isEmpty()) mAlsoKnownTv.setText(String.valueOf(alsoKnownAs));
    }
}

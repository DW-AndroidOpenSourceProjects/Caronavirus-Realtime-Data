package developers.work.coronavirusrealtimeanalytics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CountriesActivity extends AppCompatActivity implements CallbackHandler {
    Context context;
    Middleware actions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        context = getApplicationContext();
        actions = new Middleware(this);
        actions.fetchCountries();
    }

    @Override
    public void callback(JSONObject json){

        List<Countries> mCountries = new ArrayList<Countries>();
        try{
            final JSONArray array = (JSONArray) json.get("data");
            for(int i=0;i<array.length();i++){
                if(array.getString(i).compareTo("GLOBAL") != 0)
                    mCountries.add(new Countries(array.getString(i)));
            }
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.countries_recyclerview);
            RecyclerViewAdapterCountries adapter = new RecyclerViewAdapterCountries(this,mCountries);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            recyclerView.setAdapter(adapter);

            findViewById(R.id.progressBar).setVisibility(View.GONE);
        }catch(Exception e){}

    }


}

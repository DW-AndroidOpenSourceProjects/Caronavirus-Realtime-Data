package developers.work.coronavirusrealtimeanalytics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Inflater;

public class ReportsActivity extends AppCompatActivity implements CallbackHandler {
    Middleware actions = null;
    Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        context = getApplicationContext();

        actions = new Middleware(this);
        Boolean isGlobal = (Boolean) getIntent().getBooleanExtra("global",false);
        if(isGlobal){
           actions.fetchGlobal();
           TextView title = (TextView) findViewById(R.id.title);
           title.setText("GLOBAL STATUS");
        }else{
            String countryName = (String) getIntent().getStringExtra("country");

            TextView title = (TextView) findViewById(R.id.title);
            title.setText(countryName+" STATUS");
            actions.fetchCountry(countryName);
        }

    }
    public void callback(JSONObject data){
        Log.d("callback",data.toString());
        try{
            if(data.has("code")){
                // Handle Error
                throw new Exception();
            }
            List<Reports> mReports = new ArrayList<Reports>();
            JSONArray jsonArray = data.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++){
                Reports report = new Reports(jsonArray.getJSONObject(i));
                mReports.add(report);
            }

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reports_recyclerview);
            RecyclerViewAdapterReports adapter = new RecyclerViewAdapterReports(this,mReports);
            recyclerView.setLayoutManager(new GridLayoutManager(this,1));
            recyclerView.setAdapter(adapter);

            findViewById(R.id.progressBar).setVisibility(View.GONE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

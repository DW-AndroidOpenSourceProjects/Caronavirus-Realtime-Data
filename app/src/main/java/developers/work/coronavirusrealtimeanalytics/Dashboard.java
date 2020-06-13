package developers.work.coronavirusrealtimeanalytics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Log.d("Dashboard","CALLING GLOBAL FETCH");
    }

    public void changeActivityToReports(View v){
        Intent activity = new Intent(this,ReportsActivity.class);
        activity.putExtra("global",true);
        startActivity(activity);
    }
    public void changeActivityToCountries(View v){
        Intent activity = new Intent(this,CountriesActivity.class);
        startActivity(activity);
    }
}

package developers.work.coronavirusrealtimeanalytics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent dashboard = new Intent(this,Dashboard.class);
        if(this.checkNetworkConnectivity())
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(3000);
                        startActivity(dashboard);
                        finish();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();

    }

    private boolean checkNetworkConnectivity(){
        ConnectivityManager manager  = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo network = manager.getActiveNetworkInfo();

        if(network != null){
            if(network.getType() == ConnectivityManager.TYPE_WIFI){
                return true;
            }
            if(network.getType() == ConnectivityManager.TYPE_MOBILE){
                return true;
            }
            return true;
        }else{
            Toast.makeText(this,"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
        }

        return false;
    }
}

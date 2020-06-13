package developers.work.coronavirusrealtimeanalytics;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Property;

import androidx.annotation.RequiresApi;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Middleware{
    private RequestQueue queue = null;
//    final String host = "http://localhost/developerswork/coronavirus";
    private final String host = "https://coronavirus.developerswork.online";
    private String global,countries;
    private HashMap<String,String> country = new HashMap<String,String>();
    private Context activity = null;

    public Middleware(Context activity){
        this.queue = Volley.newRequestQueue(activity);
        this.activity = activity;
    }

    public JSONObject getGlobal(){
        JSONObject json = null;
        try{
            json = new JSONObject(this.global);
        }catch (Exception e){}
        return json;
    }
    public JSONArray getCountries(){
        JSONArray json = null;
        try{
            json = new JSONArray(this.countries);
        }catch (Exception ignored){}
        return json;
    }
    public JSONObject getCountry(String countryCode){
        JSONObject json = null;
        try{
            json = new JSONObject(country.get(countryCode));
        }catch(Exception e){}
        return json;
    }

    public void fetchGlobal(){
        // Instantiate the RequestQueue.
        String url = host + "/api/v1/global";
        Log.d("global",url);
        final Middleware scope = this;
        final CallbackHandler activity = (CallbackHandler)this.activity;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        scope.global = response.toString();
//                        Log.d("global",response.toString());
                        activity.callback(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("global",error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    public void fetchCountries(){
        // Instantiate the RequestQueue.
        String url = host + "/api/v1/list_countries";
        Log.d("global",url);
        final Middleware scope = this;
        final CallbackHandler activity = (CallbackHandler)this.activity;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        scope.countries = response.toString();
                        Log.d("list countries",response.toString());
                        activity.callback(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("global",error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    public void fetchAllCountries(){
        JSONArray countries = null;
        try{
            countries = new JSONArray(this.countries);
            for(int i=0;i<countries.length();i++){
                try {
                    JSONObject object = countries.getJSONObject(i);
                    String countryName = object.get("alpha2Code").toString();
                    this.country.put(countryName,object.toString());
                    this.fetchCountry(countryName);
                    Log.d("fetchAllCountries",countryName);
                }catch (Exception e){}
            }
        }catch(Exception e){}
    }
    public void fetchCountry(final String countryName){
        // Instantiate the RequestQueue.
        String url = host + "/api/v1/country?name="+countryName.replaceAll(" ","%20");
//        Log.d("countries",url);
        final Middleware scope = this;
        final CallbackHandler activity = (CallbackHandler) this.activity;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            JSONObject object = new JSONObject(scope.country.get(countryName));
//                            object.put("stats",response);
                            scope.country.put(countryName,response.toString());
                            activity.callback(response);
                        }catch (Exception e){}
                        Log.d("country",response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("country",error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    // https://dzone.com/articles/url-encoding-and-decoding-using-java
    public static String encode(String url)
    {
        try {
            String encodedURL = URLEncoder.encode( url, "UTF-8" );
            return encodedURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" +e.getMessage();
        }
    }

}

interface CallbackHandler{
    public void callback(JSONObject json);
}
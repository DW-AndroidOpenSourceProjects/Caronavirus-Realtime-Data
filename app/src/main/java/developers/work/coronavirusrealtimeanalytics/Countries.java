package developers.work.coronavirusrealtimeanalytics;

import org.json.JSONObject;

public class Countries {
    private String title;

    Countries(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}

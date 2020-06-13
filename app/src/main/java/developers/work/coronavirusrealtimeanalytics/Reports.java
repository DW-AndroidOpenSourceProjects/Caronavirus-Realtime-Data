package developers.work.coronavirusrealtimeanalytics;

import org.json.JSONObject;

public class Reports {
    private String title,total_cases,total_deaths,total_recovered,total_new_cases_today,total_new_deaths_today,recorded_on;

    Reports(JSONObject jsonObject){
        try {
            this.title = jsonObject.getString("title");
        }catch (Exception e){}
        try {
            this.total_cases = jsonObject.getString("total_cases");
        }catch (Exception e){}
        try {
            this.total_deaths = jsonObject.getString("total_deaths");
        }catch (Exception e){}
        try {
            this.total_recovered = jsonObject.getString("total_recovered");
        }catch (Exception e){}
        try {
            this.total_new_cases_today = jsonObject.getString("total_new_cases_today");
        }catch (Exception e){}
        try {
            this.total_new_deaths_today = jsonObject.getString("total_new_deaths_today");
        }catch (Exception e){}
        try {
            this.recorded_on = jsonObject.getString("recorded_on");
        }catch (Exception e){}
    }

    public String getTitle(){
        if(!title.isEmpty())
            return title;
        return "---";
    }
    public String getTotalCases(){
        if(!total_cases.isEmpty())
            return total_cases;
        return "---";
    }
    public String getTotalDeaths(){
        if(!total_deaths.isEmpty())
            return total_deaths;
        return "---";
    }
    public String getTotalRecovered(){
        if(!total_recovered.isEmpty())
            return total_recovered;
        return "---";
    }
    public String getTotalNewCasesToday(){
        if(!total_new_cases_today.isEmpty())
            return total_new_cases_today;
        return "---";
    }
    public String getTotalNewDeathsToday(){
        if(!total_new_deaths_today.isEmpty())
            return total_new_deaths_today;
        return "---";
    }
    public String getRecordedOn(){
        if(recorded_on != null)
        if(!recorded_on.isEmpty())
            return recorded_on;
        return "---";
    }
}

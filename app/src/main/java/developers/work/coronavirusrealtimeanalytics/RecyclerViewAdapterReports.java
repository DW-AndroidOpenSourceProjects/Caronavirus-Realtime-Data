package developers.work.coronavirusrealtimeanalytics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterReports extends RecyclerView.Adapter<RecyclerViewAdapterReports.MyViewHolder>{

    private Context mContext;
    private List<Reports> mData;

    RecyclerViewAdapterReports(Context context,List<Reports> data){
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.reports_template,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
        holder.total_cases.setText(mData.get(position).getTotalCases());
        holder.total_deaths.setText(mData.get(position).getTotalDeaths());
        holder.total_recovered.setText(mData.get(position).getTotalRecovered());
        holder.total_new_deaths_today.setText(mData.get(position).getTotalNewDeathsToday());
        holder.total_new_cases_today.setText(mData.get(position).getTotalNewCasesToday());
        holder.recorded_on.setText(mData.get(position).getRecordedOn());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,recorded_on,total_cases,total_deaths,total_recovered,total_new_cases_today,total_new_deaths_today;
        public MyViewHolder(View view){
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            recorded_on = (TextView) view.findViewById(R.id.recorded_on);
            total_cases = (TextView) view.findViewById(R.id.total_cases);
            total_deaths = (TextView) view.findViewById(R.id.total_deaths);
            total_recovered = (TextView) view.findViewById(R.id.total_recovered);
            total_new_cases_today = (TextView) view.findViewById(R.id.total_new_cases_today);
            total_new_deaths_today = (TextView) view.findViewById(R.id.total_new_deaths_today);
        }
    }

}

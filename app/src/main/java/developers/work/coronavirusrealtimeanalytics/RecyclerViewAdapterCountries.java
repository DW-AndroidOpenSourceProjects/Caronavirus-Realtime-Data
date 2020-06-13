package developers.work.coronavirusrealtimeanalytics;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterCountries extends RecyclerView.Adapter<RecyclerViewAdapterCountries.MyViewHolder>{

    private Context mContext;
    private List<Countries> mData;

    RecyclerViewAdapterCountries(Context context, List<Countries> data){
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.countries_template,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
        final int index = position;
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent activity = new Intent(mContext,ReportsActivity.class);
                    activity.putExtra("country",mData.get(index).getTitle());
                    mContext.startActivity(activity);
                }catch (Exception e){}
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        CardView card;
        public MyViewHolder(View view){
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            card = (CardView) view.findViewById(R.id.countries_template_cardview);

        }
    }

}

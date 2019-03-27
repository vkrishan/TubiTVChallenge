package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.vaibhavkrishan.tubitvchallenge.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import models.MovieRowModel;

public class MoviesCustomAdapter extends RecyclerView.Adapter<MoviesCustomAdapter.MyViewHolder> {

        private Context mContext;
        private ArrayList<MovieRowModel> dataSet;

        // Constructor
        public MoviesCustomAdapter(Context context, ArrayList<MovieRowModel> data) {
            this.mContext = context;
            this.dataSet = data;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
            View v = LayoutInflater.from(mContext).inflate(R.layout.custom_row, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position){
            holder.itemView.setTag(dataSet.get(position));

            MovieRowModel currentMovieItem = dataSet.get(position);

            // Get views data
            String imageUrl = currentMovieItem.getmImg();
            Log.e("MyAdapter:imageURL:", imageUrl);
            String movieName = currentMovieItem.getmName();
            String movieID = currentMovieItem.getmID();

            // Set the views
            holder.m_NameTextView.setText(movieName);
            holder.m_idTextView.setText(movieID);
  //        Picasso.get().load(imageUrl).fit().centerInside().into(holder.m_logoImageView);
            Picasso.get().load(imageUrl).into(holder.m_logoImageView);

        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }


        // View Holder
        public static class MyViewHolder extends RecyclerView.ViewHolder{

            TextView m_NameTextView;
            TextView m_idTextView;
            ImageView m_logoImageView;

            public MyViewHolder(View itemView){
                super(itemView);
                m_NameTextView = itemView.findViewById(R.id.movieTitle);
                m_logoImageView = itemView.findViewById(R.id.movieImg);
                m_idTextView =  itemView.findViewById(R.id.movieID);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MovieRowModel cpu = (MovieRowModel) view.getTag();
                        Toast.makeText(view.getContext(), cpu.getmName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

}

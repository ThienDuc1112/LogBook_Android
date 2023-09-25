package com.example.logbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logbook.models.Profile;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>{
    private List<Profile> listProfile;
    private Context context;

    public ProfileAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Profile> listProfile){
        this.listProfile = listProfile;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_infor,parent,false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        final Profile profile = listProfile.get(position);
        if(profile ==null){
            return;
        }
        holder.name.setText(profile.getName());
        holder.DOB.setText(profile.getDob());
        holder.email.setText(profile.getEmail());
        holder.profileImage.setImageResource(profile.getIdImage());
    }

    @Override
    public int getItemCount() {
        if(listProfile!=null){
            return listProfile.size();
        }
        return 0;
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        private TextView name,DOB,email;
        private ImageView profileImage;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameInformationText);
            DOB = itemView.findViewById(R.id.DOBInformationText);
            email = itemView.findViewById(R.id.emailInformationText);
            profileImage = itemView.findViewById(R.id.imageProfile);
        }
    }
}

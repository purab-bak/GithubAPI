package com.example.splashscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReposAdapter extends  RecyclerView.Adapter<ReposAdapter.ReposViewHolder>{

    private List<GithubRepo> repos;
    private int rowLayout;
    private Context context;


    public List<GithubRepo> getRepos() {
        return repos;
    }

    public void setRepos(List<GithubRepo> repos) {
        this.repos = repos;
    }

    public int getRowLayout() {
        return rowLayout;
    }

    public void setRowLayout(int rowLayout) {
        this.rowLayout = rowLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ReposAdapter(List<GithubRepo> repos, int rowLayout, Context context) {
        this.repos = repos;
        this.rowLayout = rowLayout;
        this.context = context;
    }
    public static class ReposViewHolder extends  RecyclerView.ViewHolder{

        RelativeLayout actionContainer;
        ImageView imageView;

        LinearLayout reposLayout;
        TextView repoName;
        TextView repoDesc;
        TextView repoLanguage;


        public ReposViewHolder(@NonNull View itemView) {
            super(itemView);

            actionContainer = itemView.findViewById(R.id.relativeLayout);
            imageView = itemView.findViewById(R.id.imageRV);
            reposLayout = itemView.findViewById(R.id.recyclerViewRepo);
            repoName = itemView.findViewById(R.id.repoName);
            repoDesc = itemView.findViewById(R.id.repoDesc);
            repoLanguage = itemView.findViewById(R.id.repoLang);
        }
    }

    @NonNull
    @Override
    public ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(rowLayout, parent, false);
        return  new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposViewHolder holder, int position) {

        //animation stuff will be added here

        holder.actionContainer.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_anim));
        holder.imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_side));


        holder.repoName.setText(repos.get(position).getName());
        holder.repoDesc.setText(repos.get(position).getDescription());
        holder.repoLanguage.setText(repos.get(position).getLanguage());

    }

    @Override
    public int getItemCount() {
        return repos.size();
    }


}

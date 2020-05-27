package com.example.splashscreen;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder>{

    //variable declaration
    private List<GithubUser> followersGithubUser;
    private int rowLayout;
    private Context context;



    //getters and setters
    public List<GithubUser> getFollowersGithubUser() {
        return followersGithubUser;
    }

    public void setFollowersGithubUser(List<GithubUser> followersGithubUser) {
        this.followersGithubUser = followersGithubUser;
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


    //Constructor
    public FollowersAdapter(List<GithubUser> followersGithubUser, int rowLayout, Context context) {
        this.followersGithubUser = followersGithubUser;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public static class FollowersViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayoutF;
        ImageView avatarF;
        TextView usernameF;
        Button viewProfileF;
        Button loadRepositoriesF;
        RelativeLayout expandableLayout;
        RelativeLayout nonExpandableLayout;



        //String imgurl;
        //View view;


        public FollowersViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayoutF = itemView.findViewById(R.id.relativeLayoutF);
            avatarF = itemView.findViewById(R.id.avatarF);
            usernameF = itemView.findViewById(R.id.usernameF);
            viewProfileF = itemView.findViewById(R.id.viewProfileF);
            loadRepositoriesF = itemView.findViewById(R.id.loadRepositoriesF);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            nonExpandableLayout = itemView.findViewById(R.id.nonExpandableLayout);

        }
    }

    @NonNull
    @Override
    public FollowersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(rowLayout, parent, false);
        return  new FollowersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FollowersViewHolder holder, final int position) {

        holder.usernameF.setText(followersGithubUser.get(position).getLogin());
        PicassoClient.downloadImage(context, followersGithubUser.get(position).getAvatar(), holder.avatarF);

        //onClickListener for RV
         final boolean isExpanded = followersGithubUser.get(position).isExpanded();

        holder.nonExpandableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followersGithubUser.get(position).setExpanded(!isExpanded);
                notifyItemChanged(position);
            }
        });

        holder.expandableLayout.setVisibility(isExpanded? View.VISIBLE : View.GONE);




        holder.viewProfileF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserActivity.class);
                intent.putExtra("usernameIntent", followersGithubUser.get(position).getLogin());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.loadRepositoriesF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Repos.class);
                intent.putExtra("username", followersGithubUser.get(position).getLogin());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return followersGithubUser.size();
    }

}

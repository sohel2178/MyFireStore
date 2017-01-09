package com.baudiabatash.myfirestore.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baudiabatash.myfirestore.Model.User;
import com.baudiabatash.myfirestore.R;

import java.util.List;

/**
 * Created by Sohel on 1/9/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyUserHolder>{

    private Context context;
    private List<User> userList;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        this.inflater= LayoutInflater.from(context);
    }

    @Override
    public MyUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_user,parent,false);

        MyUserHolder holder = new MyUserHolder(view);
        return holder;
    }

    public void addUser(User user){
        userList.add(user);
        int position = userList.indexOf(user);
        notifyItemInserted(position);
    }

    @Override
    public void onBindViewHolder(MyUserHolder holder, int position) {

        User user = userList.get(position);

        holder.tvName.setText(user.getName());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyUserHolder extends RecyclerView.ViewHolder{

        TextView tvName;

        public MyUserHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.user_name);
        }
    }
}

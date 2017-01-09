package com.baudiabatash.myfirestore.Navigation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baudiabatash.myfirestore.Adapter.UserAdapter;
import com.baudiabatash.myfirestore.Listener.MyUserValueChangeClass;
import com.baudiabatash.myfirestore.Model.User;
import com.baudiabatash.myfirestore.R;
import com.baudiabatash.myfirestore.Utility.MyDatabaseRef;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements MyUserValueChangeClass.UserUpdateListener{

    private RecyclerView rvUser;

    MyDatabaseRef myDatabaseRef;
    private UserAdapter adapter;
    private List<User> userList;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDatabaseRef = new MyDatabaseRef();
        userList= new ArrayList<>();
        adapter = new UserAdapter(getActivity(),userList);

        MyUserValueChangeClass myUserValueChangeClass = new MyUserValueChangeClass(myDatabaseRef.getUserRef());
        myUserValueChangeClass.setUserUpdateListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        rvUser = (RecyclerView) view.findViewById(R.id.rv_users);
        rvUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvUser.setAdapter(adapter);
    }

    @Override
    public void getUser(User user) {
        adapter.addUser(user);
    }
}

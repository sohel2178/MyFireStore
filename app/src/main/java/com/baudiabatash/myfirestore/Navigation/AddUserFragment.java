package com.baudiabatash.myfirestore.Navigation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.baudiabatash.myfirestore.Listener.MyUserValueChangeClass;
import com.baudiabatash.myfirestore.Model.User;
import com.baudiabatash.myfirestore.R;
import com.baudiabatash.myfirestore.Utility.MyDatabaseRef;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment implements View.OnClickListener,MyUserValueChangeClass.UserUpdateListener{

    private EditText etName;

    private Button btnAdd;

    MyDatabaseRef myDatabaseRef;
    private int last_id;


    public AddUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        last_id=0;

        myDatabaseRef = new MyDatabaseRef();

        MyUserValueChangeClass muvL = new MyUserValueChangeClass(myDatabaseRef.getUserRef());
        muvL.setUserUpdateListener(this);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        etName = (EditText) view.findViewById(R.id.user_name);
        btnAdd= (Button) view.findViewById(R.id.btn_add);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnAdd.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        String name = etName.getText().toString().trim();

        addUserToTheRef(myDatabaseRef.getUserRef(),new User(name));

    }

    @Override
    public void getUser(User user) {
        last_id=user.getId();
    }


    private void addUserToTheRef(DatabaseReference ref, User user){
        String key = String.valueOf(last_id+1);
        user.setId(last_id+1);
        ref.child(key).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                getFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();
            }
        });
    }
}

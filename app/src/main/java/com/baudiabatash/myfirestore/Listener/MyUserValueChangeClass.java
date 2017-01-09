package com.baudiabatash.myfirestore.Listener;

import android.util.Log;

import com.baudiabatash.myfirestore.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Sohel on 1/9/2017.
 */

public class MyUserValueChangeClass {

    private DatabaseReference myRef;

    private UserUpdateListener listener;

    public MyUserValueChangeClass(DatabaseReference myRef) {
        this.myRef = myRef;

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot x:dataSnapshot.getChildren()){
                    User user = x.getValue(User.class);

                    if(listener!=null){
                        listener.getUser(user);
                    }

                    Log.d("YYYY",user.getName());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setUserUpdateListener(UserUpdateListener listener){
        this.listener =listener;
    }


    public interface UserUpdateListener{
        public void getUser(User user);
    }
}

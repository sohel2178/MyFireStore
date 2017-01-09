package com.baudiabatash.myfirestore.Utility;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Sohel on 1/9/2017.
 */

public class MyDatabaseRef {

    private static final String USER_REF="users";
    private static final String STORE_REF="stores";
    private static final String CUSTOMER_REF="customers";

    private FirebaseDatabase database;
    private DatabaseReference rootRef;

    public MyDatabaseRef(){
        this.database = FirebaseDatabase.getInstance();
        this.rootRef = database.getReference();
    }


    public DatabaseReference getUserRef(){
        DatabaseReference userRef =rootRef.child(USER_REF);

        return userRef;
    }

    public DatabaseReference getStoreRef(){
        DatabaseReference storeRef =rootRef.child(STORE_REF);

        return storeRef;
    }
}

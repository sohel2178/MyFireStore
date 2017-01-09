package com.baudiabatash.myfirestore;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;

import com.baudiabatash.myfirestore.Listener.MyUserValueChangeClass;
import com.baudiabatash.myfirestore.Model.User;
import com.baudiabatash.myfirestore.Navigation.HomeFragment;
import com.baudiabatash.myfirestore.Utility.Constant;
import com.baudiabatash.myfirestore.Utility.MyDatabaseRef;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyUserValueChangeClass.UserUpdateListener{

    private List<User> userList;
    private int last_id;

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Iconify
                .with(new FontAwesomeModule());

        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        //Toolbar Code
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);




        //Drawer Layout Code
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

       // userLocalStore = new UserLocalStore(this);








        NavigationDrawer drawerFragment =
                (NavigationDrawer) manager.findFragmentById(R.id.fragment_navigation_drawer);


        drawerFragment.setUp(R.id.fragment_navigation_drawer, mDrawerLayout, toolbar);
        getSupportActionBar().setTitle(Constant.HOME);

        mDrawerLayout.closeDrawer(Gravity.LEFT);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment())
                .commit();

        /*userList= new ArrayList<>();

        last_id=-1;

        MyDatabaseRef myDatabaseRef = new MyDatabaseRef();

        DatabaseReference userRef = myDatabaseRef.getUserRef();
        DatabaseReference storeRef = myDatabaseRef.getStoreRef();


        MyUserValueChangeClass myUserValueChangeClass = new MyUserValueChangeClass(userRef);
        myUserValueChangeClass.setUserUpdateListener(this);*/



        /*if(userRef==null){
            Log.d("NULLTESt","UserREFNull");
        }

        if(storeRef==null){
            Log.d("NULLTESt","StoreREFNull");
        }*/

    }


    private void pushUserToTheRef(DatabaseReference databaseReference, User user){
        String key=databaseReference.push().getKey();
        databaseReference.child(key).setValue(user);
    }

    private void addUserToTheRef(DatabaseReference ref, User user){
        String key = String.valueOf(user.getId());
        ref.child(key).setValue(user);
    }


    @Override
    public void getUser(User user) {
        userList.add(user);
        Log.d("YYYYYYYY",userList.size()+"");
        last_id=user.getId();
    }
}

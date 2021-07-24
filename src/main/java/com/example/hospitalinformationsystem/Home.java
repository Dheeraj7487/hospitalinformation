package com.example.hospitalinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    MenuItem home_db,share,logout;

    String [] name={"Profile","Hospital Info","Request Result","Contact Us","Feedback"};
    int [] image={R.drawable.profile,R.drawable.hospital,R.drawable.beds,R.drawable.contactus,R.drawable.feedback};
    GridView gridView;

    UserDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myDB = new UserDB(Home.this);

        navigationView = findViewById(R.id.navigationview_home);
        drawerLayout = findViewById(R.id.drawer_home);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView=findViewById(R.id.home_grid);
        Home_Adapter aabbAdapter=new Home_Adapter(Home.this,name,image);
        gridView.setAdapter(aabbAdapter);

        View headerViewiew =  navigationView.inflateHeaderView(R.layout.header);
        TextView tvname = headerViewiew.findViewById(R.id.dashboard_name);
        TextView tvemail = headerViewiew.findViewById(R.id.dashboard_email);

        Cursor cursor = myDB.selectEmailIdName();
        if (cursor.moveToFirst() ) {
            tvname.setText(cursor.getString(0));
            tvemail.setText(cursor.getString(1));
        } else {
            tvemail.setText("Ooops no data extracted");
        }
        cursor.close();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.db:
                        Intent intent = new Intent(Home.this,Home.class);
                        startActivity(intent);
                        break;
                    case R.id.share:
                        Intent intentInvite = new Intent(Intent.ACTION_SEND);
                        intentInvite.setType("text/plain");
                        String body = "Link to your app";
                        String subject = "Your Subject";
                        intentInvite.putExtra(Intent.EXTRA_SUBJECT, subject);
                        intentInvite.putExtra(Intent.EXTRA_TEXT, body);
                        startActivity(Intent.createChooser(intentInvite, "Share using"));
                        break;
                    case R.id.logout:
                        logout();
                        break;
                    default:
                        return onOptionsItemSelected(item);
                }
                return true;
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void logout()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle("Hospital Information System");
        builder.setMessage("Are You Want To Exit This App");
        builder.setIcon(R.drawable.hospital);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(Home.this,Home.class);
                startActivity(intent);
            }
        });
        builder.show();
    }

}




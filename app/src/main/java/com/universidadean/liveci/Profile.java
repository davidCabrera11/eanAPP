package com.universidadean.liveci;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    //TextView textName, textEmail;
    FirebaseAuth mAuth;

    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //To sign out gmail user account completely
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        //Get id tags of xml
        mAuth = FirebaseAuth.getInstance();
      //  textName = findViewById(R.id.textName);
       // textEmail = findViewById(R.id.textEmail);

        //Get Info of Current User
        FirebaseUser user = mAuth.getCurrentUser();
       // textName.setText(user.getDisplayName());
       // textEmail.setText(user.getEmail());


        //Sign Out User

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
     }

    //Items in the menu on the top left
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()){
                case R.id.menuLogout:
                    //Logs out of Firebase but not Gmail
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(this, MainActivity.class));
                    signOut();
                break;

            }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

    }

    //Signs Out of Gmail
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
}

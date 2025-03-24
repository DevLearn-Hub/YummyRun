package com.shop.yummyrun.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shop.yummyrun.R;
import com.shop.yummyrun.ui.cart.CartActivity;
import com.shop.yummyrun.ui.home.HomeActivity;
import com.shop.yummyrun.ui.login.LoginActivity;

public class AccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();
        TextView emailTextView = findViewById(R.id.emailTextView);
        Button signOutButton = findViewById(R.id.signOutButton);

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            emailTextView.setText(user.getEmail());
        }

        signOutButton.setOnClickListener(v -> {
            mAuth.signOut();
            finish();
            startActivity(new Intent(AccountActivity.this, LoginActivity.class));
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    startActivity(new Intent(AccountActivity.this, HomeActivity.class));
                    finish();
                    return true;
                } else if (itemId == R.id.account) {
                    return true;
                } else if (itemId == R.id.cart) {
                    startActivity(new Intent(AccountActivity.this, CartActivity.class));
                    finish();
                    return true;
                }

                return false;
            }
        });
    }
}

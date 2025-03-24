package com.shop.yummyrun.ui.account;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shop.yummyrun.R;
import com.shop.yummyrun.adapter.OrderAdapter;
import com.shop.yummyrun.model.Order;
import com.shop.yummyrun.ui.cart.CartActivity;
import com.shop.yummyrun.ui.home.HomeActivity;
import com.shop.yummyrun.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends AppCompatActivity {

    private List<Order> orders = new ArrayList<>();
    private OrderAdapter orderAdapter;
    private FirebaseAuth mAuth;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();

        TextView emailTextView = findViewById(R.id.emailTextView);
        Button signOutButton = findViewById(R.id.signOutButton);
        FirebaseUser user = mAuth.getCurrentUser();
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        if (user != null) {
            emailTextView.setText("Email: " + user.getEmail());
        }else{
            String userEmail = preferences.getString("email", "Гость");
            emailTextView.setText("Email: " + userEmail);
        }

        signOutButton.setOnClickListener(v -> {
            mAuth.signOut();
            preferences.edit().clear().apply();
            startActivity(new Intent(AccountActivity.this, LoginActivity.class));
            finish();
        });

        RecyclerView ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new OrderAdapter(orders);
        ordersRecyclerView.setAdapter(orderAdapter);

        loadOrders();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.account) {
                return true;
            } else if (itemId == R.id.cart) {
                startActivity(new Intent(this, CartActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadOrders() {
        SharedPreferences preferences = getSharedPreferences("orders", MODE_PRIVATE);
        orders.clear();

        for (String orderId : preferences.getAll().keySet()) {
            String orderDetails = preferences.getString(orderId, "");
            if (!orderDetails.isEmpty()) {
                orders.add(new Order(orderId, orderDetails, "В обработке"));
            }
        }

        orderAdapter.notifyDataSetChanged();
    }
}

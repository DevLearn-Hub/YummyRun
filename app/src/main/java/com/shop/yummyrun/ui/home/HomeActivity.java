package com.shop.yummyrun.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.shop.yummyrun.R;
import com.shop.yummyrun.adapter.ProductAdapter;
import com.shop.yummyrun.model.Product;
import com.shop.yummyrun.ui.account.AccountActivity;
import com.shop.yummyrun.ui.cart.CartActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ProductAdapter productAdapter;
    private List<Product> productList;
    private List<Product> filteredProductList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int numberOfColumns = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        productList = new ArrayList<>();

        productList.add(new Product("Мини-чиабатта с куриной ветчиной", "Описание", 298, R.drawable.chibata, "Сэндвичи"));
        productList.add(new Product("Пицца с ветчиной", "Описание", 499, R.drawable.pizza, "Пицца"));
        productList.add(new Product("Салат Цезарь", "Описание", 150, R.drawable.salad, "Салаты"));
        productList.add(new Product("Пицца Маргарита", "Описание", 350, R.drawable.pizza, "Пицца"));
        productList.add(new Product("Цезарь с курицей", "Описание", 220, R.drawable.salad, "Салаты"));

        filteredProductList = new ArrayList<>(productList);

        productAdapter = new ProductAdapter(HomeActivity.this, filteredProductList);
        recyclerView.setAdapter(productAdapter);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterProducts(s, null, null);
                return false;
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    return true;
                } else if (itemId == R.id.account) {
                    startActivity(new Intent(HomeActivity.this, AccountActivity.class));
                    return true;
                } else if (itemId == R.id.cart) {
                    startActivity(new Intent(HomeActivity.this, CartActivity.class));
                    return true;
                }
                return false;
            }
        });

        @SuppressLint("WrongViewCast") ChipGroup chipGroup = findViewById(R.id.filterChipGroup);
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnClickListener(view -> {
                String category = null;
                String price = null;
                if (chip.getId() == R.id.chipAll) {
                    // Show all products
                    filterProducts(null, null, null);
                } else if (chip.getId() == R.id.chipCategory1) {
                    category = "Сэндвичи"; // Category 1
                } else if (chip.getId() == R.id.chipCategory2) {
                    category = "Пицца"; // Category 2
                } else if (chip.getId() == R.id.chipCategory3) {
                    category = "Салаты"; // Category 2
                } else if (chip.getId() == R.id.chipPriceLow) {
                    price = "low";  // Low price filter
                } else if (chip.getId() == R.id.chipPriceHigh) {
                    price = "high";  // High price filter
                }
                filterProducts(null, category, price);
            });
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filterProducts(String query, String category, String price) {
        if (query == null && category == null && price == null) {
            filteredProductList = new ArrayList<>(productList);
        } else {
            List<Product> tempFilteredList = new ArrayList<>(filteredProductList);

            if (query != null || category != null) {
                tempFilteredList = new ArrayList<>();
                for (Product product : productList) {
                    boolean matchesQuery = query == null || product.getName().toLowerCase().contains(query.toLowerCase());
                    boolean matchesCategory = category == null || product.getCategory().equalsIgnoreCase(category);

                    if (matchesQuery && matchesCategory) {
                        tempFilteredList.add(product);
                    }
                }
            }

            if (price != null) {
                if (price.equals("low")) {
                    tempFilteredList.sort(Comparator.comparingDouble(Product::getPrice));
                } else if (price.equals("high")) {
                    tempFilteredList.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
                }
            }

            filteredProductList = tempFilteredList;
        }

        productAdapter.filter(filteredProductList);
        productAdapter.notifyDataSetChanged();
    }
}

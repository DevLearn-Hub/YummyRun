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

        productList.add(new Product("Мини-чиабатта с куриной ветчиной", "Сытный завтрак в удобном формате сэндвича. В наборе 2 мини-чиабатты с классическим для утра набором: нежным омлетом, ломтиками ветчины и сыра, свежими овощами и соусом.", 298, R.drawable.chibata, "Сэндвичи"));
        productList.add(new Product("Пицца римская «Овощная»", "В этой пицце нет мяса, яиц и молочных продуктов, зато есть насыщенный пряно-овощной вкус. Воздушное тесто покрыто густым томатным соусом с ароматным орегано. Начинка собрана из цукини, сладкого перца, черри и оливок халкидики.", 470, R.drawable.pizza, "Пицца"));
        productList.add(new Product("Салат «Витаминный» с лимонной заправкой", "Полезный летний салат из свежих овощей: капусты, огурцов, моркови и сладкого перца. Чтобы внести во вкус яркие нотки, мы выбрали заправку из растительного масла с ароматным цветочным мёдом.", 208, R.drawable.salad1, "Салаты"));
        productList.add(new Product("Салат «Цезарь»", "Один из самых популярных салатов с классическим составом. Заботливо приготовлен из ломтиков нежного куриного филе, сладких томатов черри и упругих салатных дистьев.", 328, R.drawable.salad2, "Салаты"));
        productList.add(new Product("Пицца 4 сыра", "Аппетитная пицца для любителей сыра. В начинку входит нежная моцарелла, чеддер с ореховым послевкусием, терпкий маасдам и сладковато-острый пармезан.", 488, R.drawable.pizza2, "Пицца"));
        productList.add(new Product("Пицца «Пепперони Салями»", "Почти готовая пицца с двумя видами сырокопчёной колбасы, нежной моцареллой и пряным томатным соусом. Нужно лишь отправить на несколько минут в духовку или СВЧ-печь. Когда сыр станет аппетитно тягучим — пиццу можно подавать к столу!", 488, R.drawable.pizza3, "Пицца"));
        productList.add(new Product("Салат с тунцом и горчичным соусом", "урманский салат с консервированным тунцом, сладкими томатами черри и свежим редисом на подушке из упругой свежей зелени. Чтобы сделать блюдо ещё более сочным, а вкус — многогранным.", 320, R.drawable.salad3, "Салаты"));
        productList.add(new Product("Клаб-сэндвич с курицей и яйцом", "Мы не устаём пополнять ассортимент сэндвичами — чтобы ваши завтраки и перекусы были вкусными, питательными и разнообразными! В данном случае в качестве начинки выступают аппетитная копчёно-варёная курица, яйца, сыр.", 218, R.drawable.sendvich1, "Сэндвичи"));
        productList.add(new Product("Клаб-сэндвич с курицей и беконом на хлебе со злаками", "Запечённая куриная грудка, хрустящий бекон, свежие овощи и лёгкий соус на основе майонеза и горчицы создают идеальное сочетание текстур и ароматов. Хлеб с семенами льна, подсолнечника и кунжута.", 228, R.drawable.sendvich2, "Сэндвичи"));
        productList.add(new Product("Бутерброд с тунцом и базиликом", "Ломтики мягкого ржано-пшеничного хлеба с нежным тунцом, свежим базиликом и сочным сельдереем. Добавили много начинки, чтобы вы точно остались сытыми.", 258, R.drawable.sendvich3, "Сэндвичи"));

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
                    filterProducts(null, null, null);
                } else if (chip.getId() == R.id.chipCategory1) {
                    category = "Сэндвичи";
                } else if (chip.getId() == R.id.chipCategory2) {
                    category = "Пицца";
                } else if (chip.getId() == R.id.chipCategory3) {
                    category = "Салаты";
                } else if (chip.getId() == R.id.chipPriceLow) {
                    price = "low";
                } else if (chip.getId() == R.id.chipPriceHigh) {
                    price = "high";
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

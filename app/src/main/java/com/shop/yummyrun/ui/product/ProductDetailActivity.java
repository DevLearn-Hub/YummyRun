package com.shop.yummyrun.ui.product;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shop.yummyrun.R;
import com.shop.yummyrun.model.CartItem;
import com.shop.yummyrun.model.Product;
import com.shop.yummyrun.ui.account.AccountActivity;
import com.shop.yummyrun.ui.cart.CartActivity;
import com.shop.yummyrun.ui.home.HomeActivity;

public class ProductDetailActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageView productImage = findViewById(R.id.productImage);
        TextView productName = findViewById(R.id.productName);
        TextView productDescription = findViewById(R.id.productDescription);
        TextView productPrice = findViewById(R.id.productPrice);
        Button addToCartButton = findViewById(R.id.addToCartButton);

        Product product = (Product) getIntent().getSerializableExtra("product");
        if(product != null){
            productImage.setImageResource(product.getImageResId());
            productName.setText(product.getName());
            productDescription.setText(product.getDescription());
            productPrice.setText(product.getPrice() + " ₽");
        }

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());

        addToCartButton.setOnClickListener(v -> {
            assert product != null;
            showAddToCartDialog(product);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                startActivity(new Intent(ProductDetailActivity.this, HomeActivity.class));
                return true;
            } else if (itemId == R.id.account) {
                startActivity(new Intent(ProductDetailActivity.this, AccountActivity.class));
                return true;
            } else if (itemId == R.id.cart) {
                startActivity(new Intent(ProductDetailActivity.this, CartActivity.class));
                return true;
            }
            return false;
        });
    }

    @SuppressLint("SetTextI18n")
    private void showAddToCartDialog(Product product){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_to_cart);
        dialog.show();

        TextView dialogProductName = dialog.findViewById(R.id.dialogProductName);
        TextView dialogProductPrice = dialog.findViewById(R.id.dialogProductPrice);
        ImageView dialogProductImage = dialog.findViewById(R.id.dialogProductImage);

        dialogProductName.setText(product.getName());
        dialogProductPrice.setText(product.getPrice()  + " ₽");
        dialogProductImage.setImageResource(product.getImageResId());

        Button confirmButton = dialog.findViewById(R.id.confirmAddButton);
        confirmButton.setOnClickListener(v -> {
            CartItem newItem = new CartItem(product.getName(), product.getImageResId(), product.getPrice(), 1);

            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            intent.putExtra("cart_item", newItem);
            startActivity(intent);
            dialog.dismiss();
        });
    }
}

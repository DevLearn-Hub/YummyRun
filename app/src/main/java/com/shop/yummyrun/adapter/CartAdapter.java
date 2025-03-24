package com.shop.yummyrun.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.yummyrun.R;
import com.shop.yummyrun.model.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private OnRemoveItemListener onRemoveItemListener;

    public interface OnRemoveItemListener {
        void onRemoveItem(int position);
    }

    public CartAdapter(List<CartItem> cartItems, OnRemoveItemListener onRemoveItemListener) {
        this.cartItems = cartItems;
        this.onRemoveItemListener = onRemoveItemListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.productImage.setImageResource(cartItem.getProductImageResId());
        holder.productName.setText(cartItem.getProductName());
        holder.productPrice.setText(String.format("%.2f ₽", cartItem.getProductPrice()));
        holder.productQuantity.setText(String.valueOf(cartItem.getQuantity()));

        holder.removeButton.setOnClickListener(v -> onRemoveItemListener.onRemoveItem(position));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, productQuantity;
        Button removeButton;

        public CartViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }
}
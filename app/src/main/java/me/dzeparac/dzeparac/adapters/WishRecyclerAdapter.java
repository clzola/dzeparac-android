package me.dzeparac.dzeparac.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dzeparac.dzeparac.R;
import me.dzeparac.dzeparac.models.Transaction;
import me.dzeparac.dzeparac.models.Wish;
import me.dzeparac.dzeparac.utils.GlideApp;

public class WishRecyclerAdapter extends RecyclerView.Adapter<WishRecyclerAdapter.WishViewHolder> {

    public interface WishItemListener {
        void onBuyWishButtonClick(Wish wish);
    }

    private ArrayList<Wish> mWishes;
    private WishItemListener mWishItemListener;

    public WishRecyclerAdapter() {
        mWishes = new ArrayList<Wish>();
    }

    @Override
    public WishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_wish, parent, false);
        return new WishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WishViewHolder holder, int position) {
        holder.bind(mWishes.get(position));
    }

    @Override
    public int getItemCount() {
        return mWishes.size();
    }

    public void setData(ArrayList<Wish> wishes) {
        mWishes = wishes;
        notifyDataSetChanged();
    }

    public void setWishItemListener(WishItemListener listener) {
        mWishItemListener = listener;
    }

    public class WishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_name_display) TextView mNameDisplay;
        @BindView(R.id.tv_amount_display) TextView mAmountDisplay;
        @BindView(R.id.btn_wish_buy) ImageButton mBuyWishButton;

        private Wish mWish;

        public WishViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            mBuyWishButton.setOnClickListener(this);
        }

        public void bind(Wish wish) {
            mWish = wish;
            mNameDisplay.setText(wish.name);
            mAmountDisplay.setText(Double.toString(wish.amount) + " €");
        }

        @Override
        public void onClick(View v) {
            if( v.getId() == R.id.btn_wish_buy )
                mWishItemListener.onBuyWishButtonClick(mWish);
        }
    }

}

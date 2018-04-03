package me.dzeparac.dzeparac.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dzeparac.dzeparac.R;
import me.dzeparac.dzeparac.models.Transaction;
import me.dzeparac.dzeparac.utils.GlideApp;

public class TransactionsRecyclerAdapter extends RecyclerView.Adapter<TransactionsRecyclerAdapter.TransactionViewHolder> {

    private ArrayList<Transaction> mTransactions;

    public TransactionsRecyclerAdapter() {
        mTransactions = new ArrayList<Transaction>();
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        holder.bind(mTransactions.get(position));
    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }

    public void setData(ArrayList<Transaction> transactions) {
        mTransactions = transactions;
        notifyDataSetChanged();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_category_icon) ImageView mCategoryIcon;
        @BindView(R.id.tv_name_display) TextView mNameDisplay;
        @BindView(R.id.tv_amount_display) TextView mAmountDisplay;
        @BindView(R.id.tv_created_at_display) TextView mCreatedAtDisplay;

        private Transaction mTransaction;

        public TransactionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(Transaction transaction) {
            mTransaction = transaction;
            mNameDisplay.setText(transaction.name);
            mCreatedAtDisplay.setText(transaction.getCreatedAtHumanString());

            if( transaction.category.id == -1 )
                mCategoryIcon.setImageResource(R.drawable.money);
            else GlideApp.with(mCategoryIcon.getContext()).load(transaction.category.iconUrl).into(mCategoryIcon);

            if( transaction.amount > 0 ) {
                mAmountDisplay.setText("+" + Double.toString(transaction.amount) + " €");
                mAmountDisplay.setTextColor(ContextCompat.getColor(mAmountDisplay.getContext(), R.color.colorPrimaryDark));
            } else {
                mAmountDisplay.setText(Double.toString(transaction.amount) + " €");
                mAmountDisplay.setTextColor(ContextCompat.getColor(mAmountDisplay.getContext(), R.color.colorAmountNegative));

            }

        }
    }

}

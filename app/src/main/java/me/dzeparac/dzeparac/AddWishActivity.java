package me.dzeparac.dzeparac;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dzeparac.dzeparac.models.Category;
import me.dzeparac.dzeparac.repositories.CategoriesRepository;
import me.dzeparac.dzeparac.repositories.TransactionsRepository;
import me.dzeparac.dzeparac.repositories.WishesRepository;

public class AddWishActivity extends AppCompatActivity implements WishesRepository.OnCreateWishListener {

    @BindView(R.id.input_category)
    Spinner mCategoriesSpinner;
    @BindView(R.id.input_name)
    EditText mNameEdit;
    @BindView(R.id.input_amount) EditText mAmountEdit;
    @BindView(R.id.btn_submit)
    Button mSubmitButton;
    @BindView(R.id.btn_cancel) Button mCancelButton;

    private DzeparacApp mApp;
    private WishesRepository mWishesRepository;
    private CategoriesRepository mCategoriesRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);
        ButterKnife.bind(this);
        mApp = (DzeparacApp) getApplication();
        mWishesRepository = mApp.getWishesRepository();
        mCategoriesRepository = mApp.getCategoriesRepository();

        ArrayList<Category> categories = mCategoriesRepository.categories;


        ArrayAdapter<Category> categoryArrayAdapter =
                new ArrayAdapter<Category>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        categories.subList(1, categories.size()));

        mCategoriesSpinner.setAdapter(categoryArrayAdapter);

        ActionBar actionBar = getSupportActionBar();
        if( actionBar != null) {
            actionBar.setTitle(R.string.add_wish_title);
        }
    }

    @OnClick(R.id.btn_submit)
    public void onSubmitButtonClick() {
        Category category = (Category) mCategoriesSpinner.getSelectedItem();
        double amount = Double.parseDouble(mAmountEdit.getText().toString());
        if( category.id == -1 )
            amount *= -1;

        mWishesRepository.create(
                mApp.getId(),
                mNameEdit.getText().toString(),
                category.id,
                amount,
                this
        );
    }

    @OnClick(R.id.btn_cancel)
    public void onCancelButtonClick() {
        finish();
    }

    @Override
    public void onWishCreated() {
        finish();
    }

    @Override
    public void onWishCreatedError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

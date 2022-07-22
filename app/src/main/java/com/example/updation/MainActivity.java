package com.example.updation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.updation.entities.Product;
import com.example.updation.api.ProductAPI;
import com.example.updation.api.APIClient;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editTextId, editTextName, editTextQuantity, editTextPrice, editTextDescription;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });
    }

    private void buttonSave_onClick(View view) {
        Product product = new Product();
        product.setId(Integer.parseInt(editTextId.getText().toString()));
        product.setName(editTextName.getText().toString());
        product.setQuantity(Integer.parseInt(editTextQuantity.getText().toString()));
        product.setPrice(Double.parseDouble(editTextPrice.getText().toString()));
        product.setDescription(editTextDescription.getText().toString());
        ProductAPI productAPI = APIClient.getClient().create(ProductAPI.class);
        productAPI.save(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                try {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), getString(R.string.update_successful), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
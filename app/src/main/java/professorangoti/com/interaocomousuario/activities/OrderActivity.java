package professorangoti.com.interaocomousuario.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import professorangoti.com.interaocomousuario.R;
import professorangoti.com.interaocomousuario.activities.domain.ApiInterface;
import professorangoti.com.interaocomousuario.activities.domain.Pojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String pedido;
    private int entrega;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        pedido = intent.getStringExtra("mensagem");
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(pedido);

        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }


        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        Retrofit retrofit = new Retrofit.Builder() .baseUrl("http://provaddm2018.atwebpages.com/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        ApiInterface apiService = retrofit.create( ApiInterface.class);
        Call<Pojo> call = apiService.obterProduto();
        call.enqueue(new Callback<Pojo>() {
            //chamada assíncrona
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                int statusCode = response.code();
                Pojo user = response.body();
                //Log.i("teste","statuscode: " + statusCode);
                Log.i("teste", "Cidade do usuário:------------------------------------------------ " + user);

            }
            public void onFailure(Call<Pojo> call, Throwable t) {
                // Log error here since request failed Log.i("teste",t.toString()); }});}
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    // Same day service
                    entrega = R.string.entrega_no_dia_seguinte;
                    displayToast(getString(entrega));
                break;
            case R.id.nextday:
                if (checked)
                    // Next day delivery
                    entrega = R.string.entrega_no_dia_seguinte;
                    displayToast(getString(entrega));
                break;
            case R.id.pickup:
                if (checked)
                    // Pick up
                    entrega = R.string.retirar_na_loja;
                    displayToast(getString(entrega));
                break;
            default:
                // Do nothing.
                break;
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        displayToast(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void fecharConta(View view) {
        Intent intent = new Intent(OrderActivity.this, PedidoActivity.class);
        //intent.putExtra("mensagem", mensagem);
        startActivity(intent);
    }

}

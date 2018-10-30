package professorangoti.com.interaocomousuario.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

public class PedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);



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
}

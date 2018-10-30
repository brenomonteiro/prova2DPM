package professorangoti.com.interaocomousuario.activities.domain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("precos")
    Call <Pojo> obterProduto();

}

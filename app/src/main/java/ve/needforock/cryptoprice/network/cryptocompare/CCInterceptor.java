package ve.needforock.cryptoprice.network.cryptocompare;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Soporte on 30-Sep-17.
 */

public class CCInterceptor {

    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/";

    public CryptoRequests getBasic(){

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return interceptor.create(CryptoRequests.class);
    }

}

package ve.needforock.cryptoprice.network.cryptocompare;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import ve.needforock.cryptoprice.models.RequestedCurrency;

/**
 * Created by Soporte on 30-Sep-17.
 */

public class GetCrypto extends AsyncTask<String, Void, RequestedCurrency> {
    @Override
    protected RequestedCurrency doInBackground(String... strings) {
        CryptoRequests cryptoRequests = new CCInterceptor().getBasic();
        Call<RequestedCurrency> value = cryptoRequests.getPrice(strings[0], strings[1]);

        try{
            Response<RequestedCurrency> response = value.execute();
            if(response.code() == 200 && response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

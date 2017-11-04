package ve.needforock.cryptoprice.network.cryptocompare;


import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import ve.needforock.cryptoprice.models.Fullinfo;


/**
 * Created by Soporte on 02-Oct-17.
 *//**/


public class GetFullInfo extends AsyncTask<String, Void, Fullinfo> {



    @Override
    protected Fullinfo doInBackground(String... strings) {
        CryptoRequests cryptoRequests = new CCInterceptor().getBasic();
        Call<Fullinfo> value = cryptoRequests.getFullInfo(strings[0], strings[1]);

        try{
            Response<Fullinfo> response = value.execute();
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


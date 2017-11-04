package ve.needforock.cryptoprice.network.cryptocompare;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ve.needforock.cryptoprice.models.Fullinfo;
import ve.needforock.cryptoprice.models.RequestedCurrency;

/**
 * Created by Soporte on 30-Sep-17.
 */

public interface CryptoRequests {


    @GET("price")
    Call<RequestedCurrency> getPrice(@Query("fsym") String fsym, @Query("tsyms") String tsyms);

    /*@GET("pricemulti")
    Call<Currency> getfMulti(@Query("fsyms") String fsyms, @Query("tsyms") String tsyms);*/

    @GET("pricemultifull")
    Call<Fullinfo> getFullInfo(@Query("fsyms") String fsyms, @Query("tsyms") String tsyms);


}

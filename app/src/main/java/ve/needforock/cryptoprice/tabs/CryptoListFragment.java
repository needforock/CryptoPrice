package ve.needforock.cryptoprice.tabs;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ve.needforock.cryptoprice.R;
import ve.needforock.cryptoprice.adapters.CurrencyAdapterUsd;
import ve.needforock.cryptoprice.models.CurrencyTo;
import ve.needforock.cryptoprice.models.Fullinfo;
import ve.needforock.cryptoprice.network.cryptocompare.GetFullInfo;

public class CryptoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private String currencyToDisplay;


    public CryptoListFragment() {
    }

    public static CryptoListFragment newInstance() {
        return new CryptoListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crypto_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.ccurrencyRv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        refreshList("USD");

    }

    public void refreshList(String localCurrency){
        currencyToDisplay = localCurrency;
        new BackgroundFullInfo().execute("BTC,LTC,ETH,DASH,BCH","USD,EUR,CLP");
    }


    private  class BackgroundFullInfo extends GetFullInfo {
        private ProgressDialog progressDialog;
        private CurrencyAdapterUsd currencyAdapterUsd, currencyAdapterEur, currencyAdapterClp;


        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Fullinfo data) {
            if (data!=null){

                switch (currencyToDisplay){
                    case "USD":
                        ArrayList<CurrencyTo> currencyListUsd = new ArrayList<>();
                        currencyListUsd.add(data.getDISPLAY().getBTC().getUSD());
                        currencyListUsd.add(data.getDISPLAY().getLTC().getUSD());
                        currencyListUsd.add(data.getDISPLAY().getBCH().getUSD());
                        currencyListUsd.add(data.getDISPLAY().getDASH().getUSD());
                        currencyListUsd.add(data.getDISPLAY().getETH().getUSD());
                        currencyAdapterUsd = new CurrencyAdapterUsd(currencyListUsd, getContext());
                        recyclerView.setAdapter(currencyAdapterUsd);
                        progressDialog.dismiss();
                        break;

                    case "EUR":
                        ArrayList<CurrencyTo> currencyListEur = new ArrayList<>();
                        currencyListEur.add(data.getDISPLAY().getBTC().getEUR());
                        currencyListEur.add(data.getDISPLAY().getLTC().getEUR());
                        currencyListEur.add(data.getDISPLAY().getBCH().getEUR());
                        currencyListEur.add(data.getDISPLAY().getDASH().getEUR());
                        currencyListEur.add(data.getDISPLAY().getETH().getEUR());
                        currencyAdapterEur = new CurrencyAdapterUsd(currencyListEur, getContext());
                        recyclerView.setAdapter(currencyAdapterEur);
                        progressDialog.dismiss();
                        break;

                    case "CLP":
                        ArrayList<CurrencyTo> currencyListClp = new ArrayList<>();
                        currencyListClp.add(data.getDISPLAY().getBTC().getCLP());
                        currencyListClp.add(data.getDISPLAY().getLTC().getCLP());
                        currencyListClp.add(data.getDISPLAY().getBCH().getCLP());
                        currencyListClp.add(data.getDISPLAY().getDASH().getCLP());
                        currencyListClp.add(data.getDISPLAY().getETH().getCLP());
                        currencyAdapterClp = new CurrencyAdapterUsd(currencyListClp, getContext());
                        recyclerView.setAdapter(currencyAdapterClp);
                        progressDialog.dismiss();
                        break;
                }



            }

        }
    }
}



package ve.needforock.cryptoprice.tabs;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ve.needforock.cryptoprice.R;
import ve.needforock.cryptoprice.models.RequestedCurrency;
import ve.needforock.cryptoprice.network.cryptocompare.GetCrypto;


/**
 * A simple {@link Fragment} subclass.
 */
public class CryptoCalculatorFragment extends Fragment {


    private Spinner fromSpinner,toSpinner1, toSpinner2,toSpinner3,toSpinner4;
    private LinearLayout secondLL,thirdLL,fourthLL;
    private EditText fromEt;
    private EditText result1;
    private EditText result2;
    private EditText result3;
    private EditText result4;
    private String first, second, third, fourth;
    private double quantity;
    private String currencyFrom;
    private RequestedCurrency conversion;

    public CryptoCalculatorFragment() {
        // Required empty public constructor
    }

    public static CryptoCalculatorFragment newInstance(){
        return new CryptoCalculatorFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crypto_calculator, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        secondLL = (LinearLayout) view.findViewById(R.id.secondToLL);
        thirdLL = (LinearLayout) view.findViewById(R.id.thirdToLL);
        fourthLL = (LinearLayout) view.findViewById(R.id.fourthToLL);

        fromSpinner = (Spinner) view.findViewById(R.id.fromSpn);

        fromEt = (EditText) view.findViewById(R.id.quantityFrom);
       result1 = (EditText) view.findViewById(R.id.resultEt);
        result2 = (EditText) view.findViewById(R.id.result2Et);
        result3 = (EditText) view.findViewById(R.id.result3Et);
        result4 = (EditText) view.findViewById(R.id.result4Et);

        List<String> fromCurrencies = new ArrayList<>();
        fromCurrencies.add("From");
        fromCurrencies.add("USD");
        fromCurrencies.add("EUR");
        fromCurrencies.add("BTC");
        fromCurrencies.add("LTC");
        fromCurrencies.add("ETH");
        fromCurrencies.add("DASH");
        fromCurrencies.add("BCH");
        fromCurrencies.add("VEF");
        fromCurrencies.add("CLP");
        fromCurrencies.add("CNY");
        fromCurrencies.add("JPY");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, fromCurrencies);
        fromSpinner.setAdapter(adapter);

        toSpinner1 = (Spinner) view.findViewById(R.id.toSpn);
        List<String> toCurrencies1 = new ArrayList<>();
        toCurrencies1.add("To");
        toCurrencies1.add("USD");
        toCurrencies1.add("EUR");
        toCurrencies1.add("BTC");
        toCurrencies1.add("LTC");
        toCurrencies1.add("ETH");
        toCurrencies1.add("DASH");
        toCurrencies1.add("BCH");
        toCurrencies1.add("VEF");
        toCurrencies1.add("CLP");
        toCurrencies1.add("CNY");
        toCurrencies1.add("JPY");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, toCurrencies1);
        toSpinner1.setAdapter(adapter1);


        toSpinner2 = (Spinner) view.findViewById(R.id.to2Spn);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, toCurrencies1);
        toSpinner2.setAdapter(adapter2);

        toSpinner3 = (Spinner) view.findViewById(R.id.to3Spn);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, toCurrencies1);
        toSpinner3.setAdapter(adapter3);

        toSpinner4 = (Spinner) view.findViewById(R.id.to4Spn);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, toCurrencies1);
        toSpinner4.setAdapter(adapter4);



    }

    public void addField(int count){
        switch(count){
            case 1:
               secondLL.setVisibility(View.VISIBLE);
                break;
            case 2:
                thirdLL.setVisibility(View.VISIBLE);
                break;
            case 3:
                fourthLL.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void calculate(){


        String quantity = fromEt.getText().toString();

        if (quantity.trim().length()>0) {

            if (fromSpinner.getSelectedItemPosition() != 0) {
                currencyFrom = fromSpinner.getSelectedItem().toString();
                String first = toSpinner1.getSelectedItem().toString();
                String second = toSpinner2.getSelectedItem().toString();
                String third = toSpinner3.getSelectedItem().toString();
                String fourth = toSpinner4.getSelectedItem().toString();
                if (toSpinner1.getSelectedItemPosition() != 0) {
                    new BackgroundCrypto().execute(currencyFrom,first + "," + second + "," + third + "," + fourth);
                    //new BackgroundVef().execute();
                }

            }else{
                Toast.makeText(getContext(), "Select Currency", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "Enter Quantity", Toast.LENGTH_SHORT).show();
        }
    }

    private class BackgroundCrypto extends GetCrypto {
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(RequestedCurrency data) {
            conversion = data;
            if (data!=null){
                quantity = Double.parseDouble(fromEt.getText().toString());
                first = toSpinner1.getSelectedItem().toString();
                second = toSpinner2.getSelectedItem().toString();
                third = toSpinner3.getSelectedItem().toString();
                fourth = toSpinner4.getSelectedItem().toString();

                if(fromEt.getText().toString().trim().length()>0) {
                    if (first.equals("USD")) {
                        result1.setText(String.format("%.4f",data.getUSD() * quantity));
                    } else if (first.equals("EUR")) {
                        result1.setText(String.format("%.4f",data.getEUR() * quantity));
                    } else if (first.equals("LTC")) {
                        result1.setText(String.format("%.4f",data.getLTC() * quantity));
                    } else if (first.equals("ETH")) {
                        result1.setText(String.format("%.4f",data.getETH() * quantity));
                    } else if (first.equals("DASH")) {
                        result1.setText(String.format("%.4f",data.getDASH() * quantity));
                    } else if (first.equals("BCH")) {
                        result1.setText(String.format("%.4f",data.getBCH() * quantity));
                    } else if (first.equals("BTC")) {
                        result1.setText(String.format("%.4f",data.getBTC() * quantity));
                    }else if (first.equals("VEF")){
                        result1.setText(String.format("%.4f",data.getVEF() * quantity));
                    }else if (first.equals("CLP")) {
                        result1.setText(String.format("%.4f", data.getCLP() * quantity));
                    }else if (first.equals("CNY")) {
                        result1.setText(String.format("%.4f", data.getCNY() * quantity));
                    }else if (first.equals("JPY")) {
                        result1.setText(String.format("%.4f", data.getJPY() * quantity));
                    }


                    if (second.equals("USD")) {
                        result2.setText(String.format("%.4f",data.getUSD() * quantity));
                    } else if (second.equals("EUR")) {
                        result2.setText(String.format("%.4f",data.getEUR() * quantity));
                    } else if (second.equals("LTC")) {
                        result2.setText(String.format("%.4f",data.getLTC() * quantity));
                    } else if (second.equals("ETH")) {
                        result2.setText(String.format("%.4f",data.getETH() * quantity));
                    } else if (second.equals("DASH")) {
                        result2.setText(String.format("%.4f",data.getDASH() * quantity));
                    } else if (second.equals("BCH")) {
                        result2.setText(String.format("%.4f",data.getBCH() * quantity));
                    } else if (second.equals("BTC")) {
                        result2.setText(String.format("%.4f",data.getBTC() * quantity));
                    }else if (second.equals("VEF")){
                        result2.setText(String.format("%.4f",data.getVEF() * quantity));
                    }else if (second.equals("CLP")) {
                        result2.setText(String.format("%.4f", data.getCLP() * quantity));
                    }else if (second.equals("CNY")) {
                        result2.setText(String.format("%.4f", data.getCNY() * quantity));
                    }else if (second.equals("JPY")) {
                        result2.setText(String.format("%.4f", data.getJPY() * quantity));
                    }


                    if (third.equals("USD")) {
                        result3.setText(String.format("%.4f",data.getUSD() * quantity));
                    } else if (third.equals("EUR")) {
                        result3.setText(String.format("%.4f",data.getEUR() * quantity));
                    } else if (third.equals("LTC")) {
                        result3.setText(String.format("%.4f",data.getLTC() * quantity));
                    } else if (third.equals("ETH")) {
                        result3.setText(String.format("%.4f",data.getETH() * quantity));
                    } else if (third.equals("DASH")) {
                        result3.setText(String.format("%.4f",data.getDASH() * quantity));
                    } else if (third.equals("BCH")) {
                        result3.setText(String.format("%.4f",data.getBCH() * quantity));
                    } else if (third.equals("BTC")) {
                        result3.setText(String.format("%.4f",data.getBTC() * quantity));
                    }else if (third.equals("VEF")){
                        result3.setText(String.format("%.4f",data.getVEF() * quantity));
                    }else if (third.equals("CLP")) {
                        result3.setText(String.format("%.4f", data.getCLP() * quantity));
                    }else if (third.equals("CNY")) {
                        result3.setText(String.format("%.4f", data.getCNY() * quantity));
                    }else if (third.equals("JPY")) {
                        result3.setText(String.format("%.4f", data.getJPY() * quantity));
                    }

                    if (fourth.equals("USD")) {
                        result4.setText(String.format("%.4f",data.getUSD() * quantity));
                    } else if (fourth.equals("EUR")) {
                        result4.setText(String.format("%.4f",data.getEUR() * quantity));
                    } else if (fourth.equals("LTC")) {
                        result4.setText(String.format("%.4f",data.getLTC() * quantity));
                    } else if (fourth.equals("ETH")) {
                        result4.setText(String.format("%.4f",data.getETH() * quantity));
                    } else if (fourth.equals("DASH")) {
                        result4.setText(String.format("%.4f",data.getDASH() * quantity));
                    } else if (fourth.equals("BCH")) {
                        result4.setText(String.format("%.4f",data.getBCH() * quantity));
                    } else if (fourth.equals("BTC")) {
                        result4.setText(String.format("%.4f",data.getBTC() * quantity));
                    }else if (fourth.equals("VEF")){
                        result4.setText(String.format("%.4f",data.getVEF() * quantity));
                    }else if (fourth.equals("CLP")) {
                        result4.setText(String.format("%.4f", data.getCLP() * quantity));
                    }else if (fourth.equals("CNY")) {
                        result4.setText(String.format("%.4f", data.getCNY() * quantity));
                    }else if (fourth.equals("JPY")) {
                        result4.setText(String.format("%.4f", data.getJPY() * quantity));
                    }

                }else{
                    Toast.makeText(getContext(), "Enter Quantity", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }
        }
    }



}

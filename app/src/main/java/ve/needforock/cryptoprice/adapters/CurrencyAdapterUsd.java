package ve.needforock.cryptoprice.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ve.needforock.cryptoprice.R;
import ve.needforock.cryptoprice.models.CurrencyTo;

/**
 * Created by Soporte on 03-Oct-17.
 */

public class CurrencyAdapterUsd extends RecyclerView.Adapter<CurrencyAdapterUsd.ViewHolder> {

    ArrayList<CurrencyTo> currencyList;
    Context context;

    public CurrencyAdapterUsd(ArrayList<CurrencyTo> currencyList, Context context) {
        this.currencyList = currencyList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_currency, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CurrencyTo currencyTo = currencyList.get(position);
        String fromSymbol = currencyTo.getFROMSYMBOL();
        if (fromSymbol.equals("Ƀ")){
            fromSymbol = "Ƀ" + " Bitcoin";
        }else if(fromSymbol.equals("BCH")) {
            fromSymbol = "BCH" + " Bitcoin Cash";
        }
        else if(fromSymbol.equals("Ł")) {
            fromSymbol = "Ł" + " Litecoin";
        }
        else if(fromSymbol.equals("Ξ")) {
            fromSymbol = "Ξ" + " Ethereum";
        }
        holder.name.setText(fromSymbol);

        holder.price.setText(currencyTo.getPRICE());
        String change24H = currencyTo.getCHANGEPCT24HOUR();
        if(change24H.contains("-")){
            /*int color = R.color.colorRed;
            holder.change.setTextColor(color);
*/        }
        if(change24H.contains("-")) {
            holder.change.setText(change24H + " %");
            holder.change.setTextColor(context.getResources().getColor(R.color.colorRed));
        }else{
            holder.change.setText("+" + change24H + " %");
            holder.change.setTextColor(context.getResources().getColor(R.color.colorPositive));
        }


    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name, exchangeCurrency, price, change;

        public ViewHolder (View itemView){
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameTv);

            price = (TextView) itemView.findViewById(R.id.change24HTv);
            change = (TextView) itemView.findViewById(R.id.percentChangeTv);

        }


    }
}

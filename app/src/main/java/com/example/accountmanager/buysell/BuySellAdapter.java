package com.example.accountmanager.buysell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.accountmanager.R;
import com.example.accountmanager.customersupplier.CustomerAndSupplier;

import java.util.List;

public class BuySellAdapter extends BaseAdapter {
    Context context;
    List<BuySell> list;
    LayoutInflater inflater;

    public BuySellAdapter(Context context, List<BuySell> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.buyselllist,null);
        TextView identity = view.findViewById(R.id.identity);
        TextView amount = view.findViewById(R.id.amount);
        TextView productname = view.findViewById(R.id.productname);
        TextView date = view.findViewById(R.id.date);
        TextView month = view.findViewById(R.id.month);

        identity.setText(list.get(i).getIdentity());
        amount.setText(String.valueOf(list.get(i).getAmount()));
        productname.setText(list.get(i).getProductname());
        date.setText(list.get(i).getDate());
        month.setText(list.get(i).getMonth());


        return view;
    }
}

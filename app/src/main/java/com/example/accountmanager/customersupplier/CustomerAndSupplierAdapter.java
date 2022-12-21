package com.example.accountmanager.customersupplier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.accountmanager.R;



import java.util.List;

public class CustomerAndSupplierAdapter extends BaseAdapter {
    Context context;
    List<CustomerAndSupplier> list;
    LayoutInflater inflater;

    public CustomerAndSupplierAdapter(Context context, List<CustomerAndSupplier> list) {
        this.context=context;
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
        view=inflater.inflate(R.layout.customerandsupplierlist,null);
        TextView identity = view.findViewById(R.id.identity);
        TextView name = view.findViewById(R.id.name);
        TextView number = view.findViewById(R.id.number);
        TextView amount = view.findViewById(R.id.amount);
        //TextView date = view.findViewById(R.id.date);

        identity.setText(list.get(i).getIdentity());
        name.setText(list.get(i).getName());
        number.setText(String.valueOf(list.get(i).getNumber()));
        amount.setText(String.valueOf(list.get(i).getAmount()));
        //date.setText(list.get(i).getDate());

        return view;
    }
}

package com.example.onlinebankingappproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.model.base_models.AccountModel;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<AccountModel> {

    private List<AccountModel> accountList = new ArrayList<>();

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<AccountModel> objects) {
        super(context, resource, objects);

    }

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Nullable
    @Override
    public AccountModel getItem(int position) {
        return accountList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initSpinnerView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initSpinnerView(position, convertView, parent);
    }

    private View initSpinnerView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        }

        TextView accountNameTextView = convertView.findViewById(R.id.accountNameTextView);

        AccountModel account = getItem(position);
        if (account != null) {
            accountNameTextView.setText(account.getAccount_name());
        }

        return convertView;
    }

    // DashboardAdapter'den alınan setData fonksiyonu
    public void setData(List<AccountModel> accounts) {
        accountList.clear();
        accountList.addAll(accounts);
        notifyDataSetChanged();
    }
}

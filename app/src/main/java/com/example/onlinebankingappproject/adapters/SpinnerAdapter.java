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

    // Constructor
    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<AccountModel> objects) {
        super(context, resource, objects);
    }

    // Öğe sayısını döndüren metot
    @Override
    public int getCount() {
        return accountList.size();
    }

    // Belirli bir pozisyondaki öğeyi döndüren metot
    @Nullable
    @Override
    public AccountModel getItem(int position) {
        return accountList.get(position);
    }

    // Spinner'ın açık durumundaki görünümü oluşturan metot
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initSpinnerView(position, convertView, parent);
    }

    // Spinner'ın kapalı durumundaki (dropdown) görünümü oluşturan metot
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initSpinnerView(position, convertView, parent);
    }

    // Spinner görünümünü başlatan ve döndüren metot
    private View initSpinnerView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        }

        // Spinner öğesindeki TextView'i bulma
        TextView accountNameTextView = convertView.findViewById(R.id.accountNameTextView);

        // Belirli bir pozisyondaki hesap öğesini al
        AccountModel account = getItem(position);
        if (account != null) {
            // Hesap adını TextView'e set etme
            accountNameTextView.setText(account.getAccount_name());
        }

        return convertView;
    }

    // DashboardAdapter'den alınan setData fonksiyonu
    // Bu metot, Spinner'ın veri setini günceller ve değişiklikleri uygular
    public void setData(List<AccountModel> accounts) {
        accountList.clear();
        accountList.addAll(accounts);
        notifyDataSetChanged();
    }
}

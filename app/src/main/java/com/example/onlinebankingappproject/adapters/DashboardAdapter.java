package com.example.onlinebankingappproject.adapters;// DashboardAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.model.ResponseModels.AccountModel;
import java.util.ArrayList;
import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.AccountViewHolder> {

    private List<AccountModel> accountList = new ArrayList<>();

    public void setData(List<AccountModel> accounts) {
        accountList.clear();
        accountList.addAll(accounts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        AccountModel account = accountList.get(position);
        holder.bind(account);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder {

        private TextView accountNumberTextView;
        private TextView accountNameTextView;
        private TextView accountTypeTextView;
        private TextView balanceTextView;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            accountNumberTextView = itemView.findViewById(R.id.accountNumberTextView);
            accountNameTextView = itemView.findViewById(R.id.accountNameTextView);
            accountTypeTextView = itemView.findViewById(R.id.accountTypeTextView);
            balanceTextView = itemView.findViewById(R.id.balanceTextView);
        }

        public void bind(AccountModel account) {
            accountNumberTextView.setText("Account Number: " + account.getAccount_number());
            accountNameTextView.setText("Account Name: " + account.getAccount_name());
            accountTypeTextView.setText("Account Type: " + account.getAccount_type());
            balanceTextView.setText("Balance: " + account.getBalance());
        }
    }
}

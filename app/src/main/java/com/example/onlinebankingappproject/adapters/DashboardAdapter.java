package com.example.onlinebankingappproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.model.base_models.AccountModel;
import com.example.onlinebankingappproject.model.base_models.SerializableAccountModel;
import com.example.onlinebankingappproject.view.AccountOperationsActivity;

import java.util.ArrayList;
import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.AccountViewHolder> {

    private List<AccountModel> accountList = new ArrayList<>();
    private Context context;
    private int currentPosition = 0;

    public DashboardAdapter(Context context) {
        this.context = context;
    }

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
        holder.bind(account, position);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {

        private TextView accountNumberTextView;
        private TextView accountNameTextView;
        private TextView accountIdTextView;
        private TextView accountTypeTextView;
        private TextView balanceTextView;
        private Button accountOperationsButton;
        private ImageView leftArrowImageView;
        private ImageView rightArrowImageView;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            accountNumberTextView = itemView.findViewById(R.id.accountNumberTextView);
            accountNameTextView = itemView.findViewById(R.id.accountNameTextView);
            accountIdTextView = itemView.findViewById(R.id.accountIdTextView);
            accountTypeTextView = itemView.findViewById(R.id.accountTypeTextView);
            balanceTextView = itemView.findViewById(R.id.balanceTextView);
            accountOperationsButton = itemView.findViewById(R.id.accountOperationsButton);
            leftArrowImageView = itemView.findViewById(R.id.leftArrow);
            rightArrowImageView = itemView.findViewById(R.id.rightArrow);

            // RecyclerView üzerinde scroll işlemlerini engellemek için bir OnTouchListener ekleyin
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true; // Olayı tüket ve işleme devam etme
                }
            });
        }

        public void bind(AccountModel account, int position) {
            accountNumberTextView.setText(account.getAccount_number());
            accountNameTextView.setText(account.getAccount_name());
            accountIdTextView.setText(String.valueOf(account.getAccount_id()));
            accountTypeTextView.setText(account.getAccount_type());
            balanceTextView.setText(account.getBalance().toString());

            leftArrowImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentPosition > 0) {
                        currentPosition--;
                        AccountModel newAccount = accountList.get(currentPosition);
                        bind(newAccount, currentPosition);
                    }
                }
            });

            rightArrowImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentPosition < accountList.size() - 1) {
                        currentPosition++;
                        AccountModel newAccount = accountList.get(currentPosition);
                        bind(newAccount, currentPosition);
                    }
                }
            });

            accountOperationsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAccountOperationsActivity(account);
                }
            });
        }
    }

    private void startAccountOperationsActivity(AccountModel selectedAccount) {
        try {
            Intent intent = new Intent(context, AccountOperationsActivity.class);
            intent.putExtra("selectedAccount", new SerializableAccountModel(selectedAccount));
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e("DashboardAdapter", "Error starting AccountOperationsActivity", e);
        }
    }
}

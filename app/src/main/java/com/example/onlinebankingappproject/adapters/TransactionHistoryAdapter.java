package com.example.onlinebankingappproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.model.base_models.TransactionHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.TransactionViewHolder> {

    private List<TransactionHistoryModel> transactionHistoryList = new ArrayList<>();
    private Context context;

    // Constructor, adaptörün bağlı olduğu bağlamı (context) belirler
    public TransactionHistoryAdapter(Context context) {
        this.context = context;
    }

    // Veri setini güncellemek için kullanılan metot
    public void setData(List<TransactionHistoryModel> transactionHistoryList) {
        this.transactionHistoryList.clear();
        this.transactionHistoryList.addAll(transactionHistoryList);
        notifyDataSetChanged();
    }

    // ViewHolder oluşturmak için kullanılan metot
    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_history_item, parent, false);
        return new TransactionViewHolder(view);
    }

    // ViewHolder'ı bind etmek için kullanılan metot
    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        TransactionHistoryModel transaction = transactionHistoryList.get(position);
        holder.bind(transaction);
    }

    // Veri setinin eleman sayısını döndüren metot
    @Override
    public int getItemCount() {
        return transactionHistoryList.size();
    }

    // Inner class: ViewHolder sınıfı
    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        private TextView transactionIdTextView;
        private TextView transactionTypeTextView;
        private TextView amountTextView;
        private TextView sourceTextView;
        private TextView statusTextView;
        private TextView reasonCodeTextView;
        private TextView createdAtTextView;

        // ViewHolder'ı oluşturan constructor
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionIdTextView = itemView.findViewById(R.id.transaction_id);
            transactionTypeTextView = itemView.findViewById(R.id.transactionTypeTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            sourceTextView = itemView.findViewById(R.id.sourceTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            reasonCodeTextView = itemView.findViewById(R.id.reasonCodeTextView);
            createdAtTextView = itemView.findViewById(R.id.createdAtTextView);
        }

        // ViewHolder'ı bind etmek için kullanılan metot
        public void bind(TransactionHistoryModel transaction) {
            transactionIdTextView.setText("İşlem Kimliği: " + transaction.getTransactionId());
            transactionTypeTextView.setText("İşlem Türü: " + transaction.getTransactionType());
            amountTextView.setText("Tutar: $" + transaction.getAmount());
            sourceTextView.setText("Kaynak: " + transaction.getSource());
            statusTextView.setText("Durum: " + transaction.getStatus());
            reasonCodeTextView.setText("Neden Kodu: " + transaction.getReasonCode());
            createdAtTextView.setText("Oluşturulma Tarihi: " + transaction.getCreatedAt());
        }
    }
}

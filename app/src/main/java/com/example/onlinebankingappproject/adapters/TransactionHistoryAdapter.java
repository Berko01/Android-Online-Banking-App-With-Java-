package com.example.onlinebankingappproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.model.ResponseModels.TransactionHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.TransactionViewHolder> {

    private List<TransactionHistoryModel> transactionHistoryList = new ArrayList<>();
    private Context context;

    public TransactionHistoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<TransactionHistoryModel> transactionHistoryList) {
        this.transactionHistoryList.clear();
        this.transactionHistoryList.addAll(transactionHistoryList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_history_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        TransactionHistoryModel transaction = transactionHistoryList.get(position);
        holder.bind(transaction);
    }

    @Override
    public int getItemCount() {
        return transactionHistoryList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        private TextView transactionIdTextView;
        private TextView transactionTypeTextView;
        private TextView amountTextView;
        private TextView sourceTextView;
        private TextView statusTextView;
        private TextView reasonCodeTextView;
        private TextView createdAtTextView;

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

        public void bind(TransactionHistoryModel transaction) {
            transactionIdTextView.setText("Transaction ID: " + transaction.getTransactionId());
            transactionTypeTextView.setText("Transaction Type: " + transaction.getTransactionType());
            amountTextView.setText("Amount: $" + transaction.getAmount());
            sourceTextView.setText("Source: " + transaction.getSource());
            statusTextView.setText("Status: " + transaction.getStatus());
            reasonCodeTextView.setText("Reason Code: " + transaction.getReasonCode());
            createdAtTextView.setText("Created At: " + transaction.getCreatedAt());
        }
    }
}
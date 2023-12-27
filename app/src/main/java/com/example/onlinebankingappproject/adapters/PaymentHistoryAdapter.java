package com.example.onlinebankingappproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.model.base_models.PaymentHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.PaymentViewHolder> {

    private List<PaymentHistoryModel> paymentHistoryList = new ArrayList<>();
    ;
    private Context context;

    public PaymentHistoryAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<PaymentHistoryModel> payments) {
        paymentHistoryList.clear();
        paymentHistoryList.addAll(payments);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        PaymentHistoryModel paymentHistory = paymentHistoryList.get(position);
        holder.bind(paymentHistory);
    }

    @Override
    public int getItemCount() {
        return paymentHistoryList.size();
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {
        TextView beneficiaryTextView, accountNumberTextView, amountTextView, referenceNoTextView, statusTextView;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            beneficiaryTextView = itemView.findViewById(R.id.beneficiaryTextView);
            accountNumberTextView = itemView.findViewById(R.id.accountNumberTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            referenceNoTextView = itemView.findViewById(R.id.referenceNoTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }

        public void bind(PaymentHistoryModel paymentHistoryModel) {
            beneficiaryTextView.setText(paymentHistoryModel.getBeneficiary());
            accountNumberTextView.setText(paymentHistoryModel.getBeneficiaryAccNo());
            amountTextView.setText(String.valueOf(paymentHistoryModel.getAmount()));
            referenceNoTextView.setText(paymentHistoryModel.getReferenceNo());
            statusTextView.setText(paymentHistoryModel.getStatus());

        }
    }
}

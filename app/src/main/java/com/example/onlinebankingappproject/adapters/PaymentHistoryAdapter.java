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
    private Context context;

    // Constructor, adaptörün bağlı olduğu bağlamı (context) belirler
    public PaymentHistoryAdapter(Context context) {
        this.context = context;
    }

    // Veri setini güncellemek için kullanılan metot
    public void setData(List<PaymentHistoryModel> payments) {
        paymentHistoryList.clear();
        paymentHistoryList.addAll(payments);
        notifyDataSetChanged();
    }

    // ViewHolder oluşturmak için kullanılan metot
    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_item, parent, false);
        return new PaymentViewHolder(view);
    }

    // ViewHolder'ı bind etmek için kullanılan metot
    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        PaymentHistoryModel paymentHistory = paymentHistoryList.get(position);
        holder.bind(paymentHistory);
    }

    // Veri setinin eleman sayısını döndüren metot
    @Override
    public int getItemCount() {
        return paymentHistoryList.size();
    }

    // Inner class: ViewHolder sınıfı
    public class PaymentViewHolder extends RecyclerView.ViewHolder {
        TextView beneficiaryTextView, accountNumberTextView, amountTextView, referenceNoTextView, statusTextView;

        // ViewHolder'ı oluşturan constructor
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            beneficiaryTextView = itemView.findViewById(R.id.beneficiaryTextView);
            accountNumberTextView = itemView.findViewById(R.id.accountNumberTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            referenceNoTextView = itemView.findViewById(R.id.referenceNoTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }

        // ViewHolder'ı bind etmek için kullanılan metot
        public void bind(PaymentHistoryModel paymentHistoryModel) {
            beneficiaryTextView.setText(paymentHistoryModel.getBeneficiary());
            accountNumberTextView.setText(paymentHistoryModel.getBeneficiaryAccNo());
            amountTextView.setText(String.valueOf(paymentHistoryModel.getAmount()));
            referenceNoTextView.setText(paymentHistoryModel.getReferenceNo());
            statusTextView.setText(paymentHistoryModel.getStatus());
        }
    }
}

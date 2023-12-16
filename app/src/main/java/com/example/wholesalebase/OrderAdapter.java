package com.example.wholesalebase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class OrderAdapter extends FirebaseRecyclerAdapter<OrdersModel, OrderAdapter.myViewHolder>{


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderAdapter(@NonNull FirebaseRecyclerOptions<OrdersModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull OrdersModel model) {
        holder.name.setText(model.getName());
        holder.articul.setText(model.getArticul());
        holder.quantity.setText(model.getQuantity());
        holder.warehouse.setText(model.getWarehouse());
        holder.price.setText(model.getPrice());


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.price.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_order))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.txtName);
                EditText articul = view.findViewById(R.id.txtArt);
                EditText quantity = view.findViewById(R.id.txtQuantity);
                EditText warehouse = view.findViewById(R.id.txtWarehouse);
                EditText price = view.findViewById(R.id.txtPrice);

                Button btnUpdate= view.findViewById(R.id.btnUpdate);

                name.setText(model.getName());
                articul.setText(model.getArticul());
                quantity.setText(model.getQuantity());
                warehouse.setText(model.getWarehouse());
                price.setText(model.getPrice());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name", name.getText().toString());
                        map.put("articul", articul.getText().toString());
                        map.put("quantity", quantity.getText().toString());
                        map.put("warehouse", warehouse.getText().toString());
                        map.put("price", price.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Orders")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Информация изменена!", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Информация не изменена...", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Вы уверены?");
                builder.setMessage("Данные будут удалены безвозвратно");

                builder.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Orders")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Отменено!!!!!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView name, articul, quantity,warehouse, price;

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            articul = (TextView)itemView.findViewById(R.id.articul);
            quantity = (TextView)itemView.findViewById(R.id.quantity);
            warehouse = (TextView)itemView.findViewById(R.id.warehouse);
            price = (TextView)itemView.findViewById(R.id.price);

            btnEdit=(Button) itemView.findViewById(R.id.btnEdit);
            btnDelete=(Button) itemView.findViewById(R.id.btnDelete);
        }
    }



}

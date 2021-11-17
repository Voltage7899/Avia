package com.company.buytickey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class list_view extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static  View.OnClickListener listener;


    private DatabaseReference database;
    private ItemViewHolder itemViewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        recyclerView=findViewById(R.id.recyclerView_User);

        initRecyclerView();
    }
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    protected void onStart() {
        super.onStart();

        database= FirebaseDatabase.getInstance().getReference().child("Flight");
        FirebaseRecyclerOptions<Flight> options=new FirebaseRecyclerOptions.Builder<Flight>()
                .setQuery(database,Flight.class).build();
        FirebaseRecyclerAdapter<Flight, ItemViewHolder> adapter = new FirebaseRecyclerAdapter<Flight, ItemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull Flight model) {

                holder.price.setText("Цена: "+model.getPrice());
                holder.from.setText("Из "+model.getFrom());
                holder.to.setText("В "+model.getTo());
                holder.imageView.setImageURI(Uri.parse(model.getImage()));//тут мы конвертируем стринговую переменную в URI,это для картинки,так как мы не можем хранить в бд ничего кроме стрингов и интов,то мы конвертировали URI в стринг при добавление в бд,в активити  Add_Sweat

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String flight_id=getRef(position).getKey();
                        Toast.makeText(list_view.this, "asdfsdaf", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(list_view.this,Item_Flight.class);
                        intent.putExtra("flight_id",flight_id);
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element,parent,false);
                itemViewHolder=new ItemViewHolder(view);
                return itemViewHolder;
            }
        };
        recyclerView.setAdapter(adapter);//Устанавливаем адаптер
        adapter.startListening();

    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView price,from,to;

        ImageView imageView;

        public ItemViewHolder(View view) {
            super(view);
            price = view.findViewById(R.id.price_el);
            from=view.findViewById(R.id.from_el);
            to=view.findViewById(R.id.to_el);
            imageView = view.findViewById(R.id.image_el);


        }
    }
}
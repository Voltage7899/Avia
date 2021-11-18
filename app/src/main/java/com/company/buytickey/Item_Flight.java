package com.company.buytickey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Item_Flight extends AppCompatActivity {
    private String id_from_intent;
    private DatabaseReference databaseReference;
    private TextView name,departure,landing,from,to;
    private ImageView image;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__flight);
        //Привязываем поля к переменным
        name=findViewById(R.id.name_item);
        departure=findViewById(R.id.departure_item);
        landing=findViewById(R.id.landing_item);
        from=findViewById(R.id.from_item);
        to=findViewById(R.id.to_item);
        image=findViewById(R.id.image_item);
        button=findViewById(R.id.buy_item);

        //Получаем данные из интента
        id_from_intent=getIntent().getExtras().get("flight_id").toString();
        //получаем ссылку
        databaseReference= FirebaseDatabase.getInstance().getReference();
        //слушатель на взаимодействие с базой данных
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Flight").child(id_from_intent).exists()){
                    Flight flight=snapshot.child("Flight").child(id_from_intent).getValue(Flight.class);
                    name.setText(flight.getPrice());
                    departure.setText(flight.getDeparture());
                    landing.setText(flight.getLanding());
                    from.setText(flight.getFrom());
                    to.setText(flight.getTo());
                    image.setImageURI(Uri.parse(flight.getImage()));
                }
                else {
                    //Выводит всплывающее сообщение
                    Toast.makeText(Item_Flight.this, "Рейса не существует", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Выводит всплывающее сообщение
                Toast.makeText(Item_Flight.this, "Данные высланы в смс", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
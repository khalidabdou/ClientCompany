package comproducts.company.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import comproducts.company.Adapters.ServicesAdapter;
import comproducts.company.DBConnect;
import comproducts.company.Modul.Service;
import comproducts.company.R;

public class ListActivity extends AppCompatActivity {
    DBConnect dbConnect;
    private RecyclerView recyclerViewitems;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Service> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbConnect=new DBConnect(this);
        recyclerViewitems=findViewById(R.id.recycleview);
        layoutManager=new LinearLayoutManager(this);
        recyclerViewitems.setHasFixedSize(true);
        adapter=new ServicesAdapter(this,dbConnect.getAllServices());
        recyclerViewitems.setLayoutManager(layoutManager);
        recyclerViewitems.setAdapter(adapter);


    }
}

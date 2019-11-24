package comproducts.company.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import comproducts.company.DBConnect;
import comproducts.company.R;
import comproducts.company.Adapters.SliderAdapterExample;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    SliderView sliderView;
    private DBConnect dbConnect;
    public static int langue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderView = findViewById(R.id.imageSlider);
        dbConnect=new DBConnect(this);
        sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        langue=sharedPreferences.getInt("lange",1);
        if (!sharedPreferences.getBoolean("choseLang",false)){
        ShowDialog();
        }

        final SliderAdapterExample adapter = new SliderAdapterExample(this);
        adapter.setCount(dbConnect.getAllServices().size());
        sliderView.setSliderAdapter(adapter);


        sliderView.setIndicatorAnimation(IndicatorAnimations.DROP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });
    }

    public void clickservice(View view) {
        startActivity(new Intent(MainActivity.this,ListActivity.class));
    }

    public void ShowDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("chose lange")
                .setTitle("langue")
                .setPositiveButton("Francais", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor.putInt("lange",1);
                        editor.putBoolean("choseLang",true);
                        editor.commit();
                        langue=1;
                    }
                })
                .setNeutralButton("Arabic", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor.putInt("lange",2);
                        editor.putBoolean("choseLang",true);
                        editor.commit();
                        langue=2;
                    }
                })
                .setCancelable(false);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

         super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
        int id =item.getItemId();
        switch (id){
            case R.id.lagng:
                Toast.makeText(this, "lang", Toast.LENGTH_SHORT).show();
                break;


        }
        return true;
    }
}

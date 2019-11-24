package comproducts.company.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comproducts.company.Controller.appFounction;
import comproducts.company.Modul.Service;
import comproducts.company.R;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {


    public ArrayList<Service> arrayServices;
    public Context context;
    public static class ServiceViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public ImageView ImageService;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.serviceTitle);
            ImageService=itemView.findViewById(R.id.imageservice);
        }
    }


    public ServicesAdapter(Context context,ArrayList<Service> arrayServices){
        this.arrayServices=arrayServices;
        this.context=context;
    }
    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemservice,parent,false);
        ServiceViewHolder svh=new ServiceViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service currentService=arrayServices.get(position);
        holder.title.setText(currentService.titleFr);
        try {
            holder.ImageService.setImageBitmap(appFounction.getLogoFromAsset(context, currentService.image));
        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public int getItemCount() {
        return arrayServices.size();
    }


}

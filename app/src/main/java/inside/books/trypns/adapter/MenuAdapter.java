package inside.books.trypns.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import inside.books.trypns.DetailLapangan;
import inside.books.trypns.R;
import inside.books.trypns.helper.Session;
import inside.books.trypns.interfaces.SendInteger;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<String> mArrayList;
    private Context mContex;
    Intent i;



    public MenuAdapter(List<String> mArrayList, Context mContex) {
        this.mArrayList = mArrayList;
        this.mContex = mContex;


    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuAdapter.ViewHolder(LayoutInflater.from(mContex).inflate
                (R.layout.detail_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        String[] current = String.valueOf(mArrayList.get(position)).split("-");
        holder.bindTO(current);
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView lapangan, jenis, bintang, nama, harga;
        ImageView imageLapangan, imageLove;

        public ViewHolder(View itemView) {
            super(itemView);
            lapangan= itemView.findViewById(R.id.detailLapangan);
            jenis = itemView.findViewById(R.id.detailJenis);
            bintang = itemView.findViewById(R.id.detailBintang);
            nama = itemView.findViewById(R.id.detailNama);
            harga = itemView.findViewById(R.id.detailHarga);
            imageLapangan = itemView.findViewById(R.id.detailImage);
            imageLove = itemView.findViewById(R.id.detailLove);
            itemView.setOnClickListener(this);

        }

        public void bindTO(String[] current) {
            lapangan.setText(current[1]);
            jenis.setText(current[2]);
            bintang.setText(current[3]);
            nama.setText(current[4]);
            harga.setText(current[5]);
            Glide.with(mContex).load(getImage(current[6])).into(imageLapangan);


        }


        @Override
        public void onClick(View view) {

            i = new Intent(mContex, DetailLapangan.class);
            i.putExtra("lapangan", mArrayList.get(getAdapterPosition()));
            mContex.startActivity(i);

            /*int clickSoal = mArrayList.get(getAdapterPosition());

            notifyItemChanged(selectedPos);
            selectedPos = getAdapterPosition();
            notifyItemChanged(selectedPos);

            //session.saveSPInt(Session.NO_SOAL, clickSoal);
            sendInteger.value(clickSoal);*/

        }

        public int getImage(String imageName) {

            int drawableResourceId = mContex.getResources().getIdentifier(imageName, "drawable", mContex.getPackageName());

            return drawableResourceId;
        }
    }


}

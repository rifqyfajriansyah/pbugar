package inside.books.trypns.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

import inside.books.trypns.R;
import inside.books.trypns.helper.Session;
import inside.books.trypns.interfaces.SendDate;

public class TanggalAdapter extends RecyclerView.Adapter<TanggalAdapter.ViewHolder> {
    private ArrayList<Date> mArrayList;
    private Context mContex;
    int indexDay, paramMonth;
    SendDate sendDate;
    Session session;



    public TanggalAdapter(ArrayList<Date> mArrayList, Context mContex, int indexDay, int paramMonth, SendDate sendDate) {
        this.mArrayList = mArrayList;
        this.mContex = mContex;
        this.paramMonth = paramMonth;
        this.sendDate = sendDate;

        session = new Session(mContex);

        if(session.getIndexTanggal()==0) {
            this.indexDay = indexDay - 1;
        }else {
            this.indexDay = session.getIndexTanggal();
        }



    }

    @Override
    public TanggalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TanggalAdapter.ViewHolder(LayoutInflater.from(mContex).inflate
                (R.layout.detail_tanggal, parent, false));
    }

    @Override
    public void onBindViewHolder(TanggalAdapter.ViewHolder holder, int position) {
        Date current = mArrayList.get(position);
        holder.hari.setTextColor(indexDay == position ? ContextCompat.getColor(mContex, R.color.colorLight) : ContextCompat.getColor(mContex, R.color.colorBlack));
        holder.tanggal.setTextColor(indexDay == position ? ContextCompat.getColor(mContex, R.color.colorLight) : ContextCompat.getColor(mContex, R.color.colorBlack));
        holder.cardView.setCardBackgroundColor(indexDay == position ? ContextCompat.getColor(mContex, R.color.colorPrimary) : ContextCompat.getColor(mContex, R.color.colorAccent));

        holder.bindTO(current);
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView tanggal, hari;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            tanggal= itemView.findViewById(R.id.detailTanggal);
            hari = itemView.findViewById(R.id.detailHari);
            cardView = itemView.findViewById(R.id.detailKotak);

            itemView.setOnClickListener(this);

        }

        public void bindTO(Date current) {

            if(paramMonth == 0){
                setCard("Lewat", tanggal, hari, cardView);
            }

            tanggal.setText(String.valueOf(current.getDate()));
            hari.setText(getHari(current.getDay()));


        }


        @Override
        public void onClick(View view) {

            if (paramMonth==1){
                session.saveSPInt(Session.INDEXTANGGAL, getAdapterPosition());
                sendDate.value(mArrayList.get(getAdapterPosition()));
            }else{
                Toast.makeText(mContex, "Tidak bisa memilih pada hari ini", Toast.LENGTH_SHORT).show();
            }




        }

        public String getHari(int index) {

            switch (index){

                case 1 : return "SEN";

                case 2 : return "SEL";


                case 3 : return "RAB";


                case 4 : return  "KAM";


                case 5 : return "JUM";


                case 6 : return "SAB";


                default: return "MIN";

            }
        }

        private void setCard(String tioe, TextView t1, TextView t2, CardView c){

            switch (tioe){

                case "Select" : t1.setTextColor(Color.WHITE);
                    t2.setTextColor(Color.WHITE);
                    c.setCardBackgroundColor(Color.parseColor("#983732"));
                    break;

                case "Lewat" : t1.setTextColor(Color.WHITE);
                    t2.setTextColor(Color.WHITE);
                    c.setCardBackgroundColor(Color.parseColor("#3A3A50"));
                    break;

                    default:t1.setTextColor(Color.BLACK);
                        t2.setTextColor(Color.BLACK);
                        c.setCardBackgroundColor(Color.parseColor("#339599A3"));

            }

        }

    }


}

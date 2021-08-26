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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import inside.books.trypns.R;
import inside.books.trypns.helper.Session;
import inside.books.trypns.interfaces.SendDate;
import inside.books.trypns.interfaces.SendInteger;

public class JamAdapter extends RecyclerView.Adapter<JamAdapter.ViewHolder> {
    private List<String> mArrayList;
    private Context mContex;
    private SendInteger sendInteger;

    public JamAdapter(List<String> mArrayList, Context mContex, SendInteger sendInteger) {
        this.mArrayList = mArrayList;
        this.mContex = mContex;
        this.sendInteger = sendInteger;


    }

    @Override
    public JamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JamAdapter.ViewHolder(LayoutInflater.from(mContex).inflate
                (R.layout.detail_jam, parent, false));
    }

    @Override
    public void onBindViewHolder(JamAdapter.ViewHolder holder, int position) {
        String current = mArrayList.get(position);
        holder.bindTO(current);
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView jam;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            jam= itemView.findViewById(R.id.detailJam);
            cardView = itemView.findViewById(R.id.detailKotakJam);

            itemView.setOnClickListener(this);

        }

        public void bindTO(String current) {

            if(Integer.valueOf(current)>9){
                jam.setText(current+":00");
            }else{
                jam.setText("0"+current+":00");
            }


        }


        @Override
        public void onClick(View view) {

                String cardviewku = cardView.getCardBackgroundColor().toString();

                if(cardviewku.substring(cardviewku.length()-7).equals("801614}")){
                    setCard("waduh", jam, cardView);
                    sendInteger.value(0);
                }else{
                    setCard("Select", jam, cardView);
                    sendInteger.value(1);
                }




                //sendInteger.value(1);

            //-6801614


        }



        private void setCard(String tioe, TextView t1, CardView c){

            switch (tioe){

                case "Select" : t1.setTextColor(Color.WHITE);
                    c.setCardBackgroundColor(Color.parseColor("#983732"));
                    break;

                case "Lewat" : t1.setTextColor(Color.WHITE);
                    c.setCardBackgroundColor(Color.parseColor("#3A3A50"));
                    break;

                default:

                    c.setCardBackgroundColor(Color.parseColor("#D1CCCC"));
                    t1.setTextColor(Color.parseColor("#737373"));

            }

        }

    }


}

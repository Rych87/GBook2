package rych.gbook2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class GbAdapter extends RecyclerView.Adapter<GbAdapter.ViewHolde>{

    private ArrayList<Book> mBl;
    private Context mContext;

    public GbAdapter(ArrayList<Book> bl, Context ctx){
        mBl=bl;
        mContext=ctx;
    }
    @Override
    public GbAdapter.ViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {

        /*LinearLayout ll = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        ViewHolder vh=new ViewHolder(ll);*/
        CardView cw=(CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frame, parent, false);
        return new ViewHolde(cw);
    }

    @Override
    public void onBindViewHolder(ViewHolde holder, int position) {
        holder.mAuth.setText(mBl.get(position).mAuth);
        holder.mTitle.setText(mBl.get(position).mTitle);
        holder.mBook=mBl.get(position);
        holder.itemView.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return mBl.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mAuth;
        private TextView mTitle;
        private Book mBook;

        public ViewHolde(CardView cw){
            super(cw);
            mTitle= cw.findViewById(R.id.textView3);
            mAuth= cw.findViewById(R.id.textView2);
            mBook=null;
            //mInfo=(String)ll.getTag();
        }

        @Override
        public void onClick(View view) {
            Intent startAct2=new Intent(mContext, Desc_act.class);
            startAct2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startAct2.putExtra("Book",mBook);
            mContext.startActivity(startAct2);
        }
    }
}

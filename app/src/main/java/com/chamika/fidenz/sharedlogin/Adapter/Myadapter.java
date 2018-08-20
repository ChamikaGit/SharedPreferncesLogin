package com.chamika.fidenz.sharedlogin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chamika.fidenz.sharedlogin.R;
import com.chamika.fidenz.sharedlogin.models.Student;

import java.util.List;

/**
 * Created by fidenz on 2/6/18.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>{



    private List<Student> studentList;

    private Context context;

    Listener mListner;


    public interface Listener {

        public void onClick(int position);
        public void onClicknew(int position);

    }
//
//    public void setListner(Listener listner){
//
//        mListner = listner;
//    }


    private Button btnedit;

    public Myadapter(List<Student> studentList, Context context, Listener listner) {
        this.studentList = studentList;
        this.context = context;
        this.mListner = listner;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        Student student = studentList.get(position);
        holder.textViewid.setText(String.valueOf(student.getId()));
        holder.textViewFname.setText(student.getFirstName());
        /*
        holder.textViewLname.setText(student.getLastName());
        holder.textViewaddress.setText(student.getAddress());
        holder.textViewphoneno.setText(student.getMobileNumber());

        */

        holder.brnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,String.valueOf(position),Toast.LENGTH_LONG).show();

                mListner.onClick(position);

            }
        });

        holder.buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListner.onClicknew(position);
            }
        });




    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView textViewid;
        public TextView textViewFname;
        public TextView textViewLname;
        public TextView textViewaddress;
        public TextView textViewphoneno;
        public Button brnedit;

        public Button buttondelete;






        public ViewHolder(View itemView) {
            super(itemView);


            textViewid = (TextView)itemView.findViewById(R.id.tvid);
            textViewFname =(TextView)itemView.findViewById(R.id.tvFname);

           /*
            textViewLname =(TextView) itemView.findViewById(R.id.tvLastname);
            textViewaddress=(TextView) itemView.findViewById(R.id.tvaddress);
            textViewphoneno=(TextView) itemView.findViewById(R.id.tvPhoneno);
            */

            brnedit =(Button)itemView.findViewById(R.id.btnedit);

            buttondelete = itemView.findViewById(R.id.btndelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"dwewewe",Toast.LENGTH_LONG).show();
                }
            });






        }
    }

}

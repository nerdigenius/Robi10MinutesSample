package com.example.robi10minutessample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class myAdapter extends RecyclerView.Adapter<myAdapter.CustomViewHolder> {
    public ArrayList<SampleItem> listItems;

    public myAdapter(ArrayList<SampleItem> listItems) {
        this.listItems = listItems;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView,RightArrow;
        public TextView textView,author;
        public ConstraintLayout expandableLayout,mainLayout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            expandableLayout=itemView.findViewById(R.id.expandable_view);
            mainLayout=itemView.findViewById(R.id.main_layout);
            RightArrow=itemView.findViewById(R.id.RightArrow);
            author=itemView.findViewById(R.id.author);




            RightArrow.setVisibility(View.GONE);
            author.setText("");



            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SampleItem sampleItem= listItems.get(getAdapterPosition());
                    sampleItem.setExpanded(!sampleItem.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });



        }

        public void setChilditems(ArrayList<ChildItems> childitems){
            LinearLayout childList=itemView.findViewById(R.id.child_item_list);
            childList.removeAllViews();
            if(listItems.get(getAdapterPosition()).isChapter())
            {
                for (int i=0;i<childitems.size();i++){
                    final View childListItem= View.inflate(childList.getContext(), R.layout.child_item_layout, null);
                    final ImageView childImageView=childListItem.findViewById(R.id.childImage);
                    final TextView childTextView=childListItem.findViewById(R.id.childText);

                    if(childitems.get(i).getType().equals("audio"))
                    {
                        childImageView.setImageResource(R.drawable.ic_audio);
                    }
                    else if(childitems.get(i).getType().equals("video"))
                    {
                        childImageView.setImageResource(R.drawable.ic_video);
                    }
                    else
                    {
                        childImageView.setImageResource(R.drawable.ic_document);
                    }


                    childTextView.setText(childitems.get(i).getName());
                    childListItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(childListItem.getContext(),childTextView.getText(),Toast.LENGTH_SHORT).show();
                        }
                    });

                    ((LinearLayout)childList).addView(childListItem);
                }
            }

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        CustomViewHolder view = new CustomViewHolder(v);
        return view;

    }



    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        final SampleItem sampleItem = listItems.get(position);
        holder.imageView.setImageResource(sampleItem.getImageResource());
        holder.textView.setText(sampleItem.getMtext1());
        if(sampleItem.getType().equals("document"))
        {
            holder.author.setText("Author: "+sampleItem.getAuthor());
            holder.author.setVisibility(View.VISIBLE);
        }

        if(sampleItem.isChapter())
        {
            holder.setChilditems(sampleItem.getchildItemsArrayList());
            holder.RightArrow.setVisibility(View.VISIBLE);
            if(sampleItem.isExpanded()){
                holder.expandableLayout.setVisibility(View.VISIBLE);
                holder.RightArrow.animate().rotation(90).start();

            }
            else {
                holder.expandableLayout.setVisibility(View.GONE);
                holder.RightArrow.animate().rotation(0).start();


            }

        }
        else{
            holder.setChilditems(null);
            holder.RightArrow.setVisibility(View.GONE);
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),sampleItem.getMtext1(),Toast.LENGTH_SHORT).show();
                }
            });
        }





    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
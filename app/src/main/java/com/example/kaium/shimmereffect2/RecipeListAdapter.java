package com.example.kaium.shimmereffect2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.MyViewHolder> {

    private Context context;
    private List<Recipe> cartList;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        public TextView name, description, price, chef, timestamp;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);

            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            chef = view.findViewById(R.id.chef);
            price = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.thumbnail);
            timestamp = view.findViewById(R.id.timestamp);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            showPopup(v);
            return false;
        }

        public void showPopup(View v) {
            PopupMenu popup = new PopupMenu(context, v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.popup_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    switch (menuItem.getItemId()){
                        case R.id.upload:
                            Toast.makeText(context, "Uploaded", Toast.LENGTH_LONG).show();
                            break;
                        case R.id.share:
                            Toast.makeText(context, "Shared", Toast.LENGTH_LONG).show();
                            break;
                        case R.id.mail:
                            Toast.makeText(context, "Mail sent", Toast.LENGTH_LONG).show();
                            break;
                    }

                    return true;
                }
            });
            popup.show();
        }


//        public void showMenu(View v) {
//            PopupMenu popup = new PopupMenu(context, v);
//            popup.setOnMenuItemClickListener(context);
//            popup.inflate(R.menu.actions);
//            popup.show();
//        }
    }

    public RecipeListAdapter(Context context, List<Recipe> cartList){
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_list_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Recipe recipe = cartList.get(i);
        myViewHolder.name.setText(recipe.getName());
        myViewHolder.chef.setText("By " + recipe.getChef());
        myViewHolder.description.setText(recipe.getDescription());
        myViewHolder.price.setText("Price: $" + recipe.getPrice());
        myViewHolder.timestamp.setText(recipe.getTimestamp());

        Glide.with(context)
                .load(recipe.getThumbnail())
                .into(myViewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}

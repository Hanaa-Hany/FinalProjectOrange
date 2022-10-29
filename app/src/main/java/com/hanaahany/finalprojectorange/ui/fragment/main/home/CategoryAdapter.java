package com.hanaahany.finalprojectorange.ui.fragment.main.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.hanaahany.finalprojectorange.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryVH> {
    ArrayList<List<String>>arrayList;

    public CategoryAdapter(ArrayList<List<String>>arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item,parent,false);
        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {
        holder.textViewCategoryName.setText(arrayList.get(position).get(position));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavController navController= Navigation.findNavController(view);
//                navController.navigate(R.id.action_homeFragment_to_exploreCategoryProductFragment);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CategoryVH extends RecyclerView.ViewHolder {
        TextView textViewCategoryName;
        public CategoryVH(@NonNull View itemView) {
            super(itemView);
            textViewCategoryName=itemView.findViewById(R.id.name_category_item);
        }
    }
}

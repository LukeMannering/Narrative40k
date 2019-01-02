package uk.co.lukemannering.narrative40k.databinding;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomDataBinding {
    private Picasso mPicasso;

    @Inject
    public CustomDataBinding(Picasso picasso) {
        this.mPicasso = picasso;
    }

    @BindingAdapter({"custom_divider_color"})
    public void bind(RecyclerView recyclerView, int colorResId){
        Context context = recyclerView.getContext();
        DividerItemDecoration divider = new DividerItemDecoration(context, LinearLayoutManager.VERTICAL);
        divider.setDrawable(new ColorDrawable(colorResId));
        recyclerView.addItemDecoration(divider);
    }

    @BindingAdapter({"custom_src"})
    public void bind(ImageView imageView, String url){
        mPicasso.load(url).into(imageView);
    }
}

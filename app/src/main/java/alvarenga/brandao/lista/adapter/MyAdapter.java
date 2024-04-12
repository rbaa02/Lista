package alvarenga.brandao.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alvarenga.brandao.lista.R;
import alvarenga.brandao.lista.activity.MainActivity;
import alvarenga.brandao.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(mainActivity);
        View v = inf.inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myitem = itens.get(position);
        View v = holder.itemView;

        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageURI(myitem.photo);

        TextView tvtitle = v.findViewById(R.id.tvTitle);
        tvtitle.setText(myitem.title);

        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myitem.description);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}

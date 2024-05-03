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
    // setando a mainActivity
    MainActivity mainActivity;
    // setando a lista de itens
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        // inicializando as variaveis
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // obtendo inflator para ler XML e criar os elementos
        LayoutInflater inf = LayoutInflater.from(mainActivity);
        // criar os elementos de interface referentes a um item e guardar em um view
        View v = inf.inflate(R.layout.item_list, parent, false);
        // retornando o view guardado em um myviewholder
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // pega o item pela posicao dele
        MyItem myitem = itens.get(position);

        // atribuindo a visualização atual ah variável 'v'
        View v = holder.itemView;

        // pegando os elementos pelo id e setando e vinculando ah elementos
        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageBitmap(myitem.photo);
        TextView tvtitle = v.findViewById(R.id.tvTitle);
        tvtitle.setText(myitem.title);
        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myitem.description);
    }

    @Override
    public int getItemCount() {
        // Retorna a quantidade de itens que estao na lista
        return itens.size();
    }
}

package alvarenga.brandao.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.List;

import alvarenga.brandao.lista.R;
import alvarenga.brandao.lista.adapter.MyAdapter;
import alvarenga.brandao.lista.model.MainActivityViewModel;
import alvarenga.brandao.lista.model.MyItem;
import alvarenga.brandao.lista.util.Util;

public class MainActivity extends AppCompatActivity {

    // ID chamada
    static int NEW_ITEM_REQUEST = 1;

    // cria o myAdapter
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pega o floating button pelo id
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        //adiciona um ouvidor de clicks
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cria um intent para a NewItemActivity
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                // Abre a activity e busca pelo resultado
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        // Pega o Recycler pelo id
        RecyclerView rvItens = findViewById(R.id.rvItens);

        // Obtem dados do view model e guarda
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        List<MyItem> itens = vm.getItens();

        // Cria e seta o myAdapter
        myAdapter = new MyAdapter(this, itens);
        rvItens.setAdapter(myAdapter);
        // Indica que nao existe variacao de tamanho
        rvItens.setHasFixedSize(true);
        // Cria um gerenciador de layout do tipo linear
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // Setar ele no RecycleView
        rvItens.setLayoutManager(layoutManager);
        // Criar um decorador para cada item, separando cada um em uma linha
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        // Adiciona o decorador no recycler view
        rvItens.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // verifica se o ID de request eh igual
        if (requestCode != NEW_ITEM_REQUEST) return;
        // verifica se abriu sem erros
        if (resultCode != Activity.RESULT_OK) return;

        // cria um novo item
        MyItem myItem = new MyItem();
        // pega o titulo, descricao e foto do EXTRA e do DATA e adiciona no item
        myItem.title = data.getStringExtra("title");
        myItem.description = data.getStringExtra("description");
        Uri selectedPhotoURI = data.getData();

        // Tenta abrir a foto
        try{
            // carrega a imagem e a guarda dentro de um Bitmap
            Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoURI, 100, 100);
            // guarda dentro do myitem
            myItem.photo = photo;
        } catch (FileNotFoundException e){
            // se nao achar a imagem mostra o erro
            e.printStackTrace();
        }
        // Obtem dados do view model e guarda
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        List<MyItem> itens = vm.getItens();
        // Notifica o adapter para mostrar o novo item
        myAdapter.notifyItemInserted(itens.size()-1);
    }
}
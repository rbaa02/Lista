package alvarenga.brandao.lista.activity;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import alvarenga.brandao.lista.R;
import alvarenga.brandao.lista.model.MainActivityViewModel;
import alvarenga.brandao.lista.model.MyItem;
import alvarenga.brandao.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {
    // id da chamada
    static int PHOTO_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // Obtem dados do view model e guarda
        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);

        // pega a foto do VM
        Uri selectedPhotoLoc = vm.getSelectPhotoLoc();
        // verifica se selecionou uma foto
        if(selectedPhotoLoc != null){
            // procura e seta a imagem
            ImageView imvfotoPreview = findViewById(R.id.imvfotoPreview);
            imvfotoPreview.setImageURI(selectedPhotoLoc);
        }

        // Procurar botao pelo ID
        ImageButton imgCI = findViewById(R.id.imbCl);
        // Evento de click do botao
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar uma intent de abrir documento
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                // Somente imagens
                i.setType("image/*");
                // Abre a activity e espera um resultado
                startActivityForResult(i, PHOTO_PICKER_REQUEST);
            }
        });

        // Achar botao pelo id
        Button btnAddItem = findViewById(R.id.btnAddItem);
        // Adiciona um ouvidor de clicks
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri selectedPhotoLoc = vm.getSelectPhotoLoc();

                // Procura o texto pelo ID e pega o texto
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                // Verifica se o texto e vazio
                if (title.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título!", Toast.LENGTH_LONG).show();
                    return;
                }

                // procura a descricao pelo id e pega o texto
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                // Verifica se a descricao e vazio
                if (description.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Cria a intent
                Intent i = new Intent();
                // Seta as datas e os Extra
                i.setData(selectedPhotoLoc);
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK, i);

                // Finaliza a tarefa
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // verifica se o ID de request eh igual
        if (requestCode != PHOTO_PICKER_REQUEST) return;
        // verifica se abriu sem erros
        if (resultCode != Activity.RESULT_OK) return;

        // pega a foto do DATA
        Uri selectedPhotoLoc = data.getData();
        // pega o imv pelo id
        ImageView imvPhotoPreview = findViewById(R.id.imvfotoPreview);
        // seta a foto no imageView
        imvPhotoPreview.setImageURI(selectedPhotoLoc);

        // obtemos o ViewModel e guardamos dentro do ViewModel o endereço URI da imagem escolhida pelo usuário
        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
        vm.setSelectPhotoLoc(selectedPhotoLoc);
    }
}
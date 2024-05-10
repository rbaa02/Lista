package alvarenga.brandao.lista.activity;

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
import alvarenga.brandao.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;

    // Uri vai guardar o endereco da imagem, nao a imagem em si
    Uri photoSelected = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
        Uri selectedPhotoLocation = vm.getSelectPhotoLocation();

        if (selectedPhotoLocation != null){
            ImageView imgfotoPreview = findViewById(R.id.imvPhotoPreview);
            imgfotoPreview.setImageURI(selectedPhotoLocation);
        }

        ImageButton imgCI = findViewById(R.id.imbCl);
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intenção de abrir um documento
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                // Selecionando somente as imagens
                photoPickerIntent.setType("image/*");

                // Intenção que espera um resultado (a foto)
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        // Obtem botao que adiciona o item
        Button btnAddItem =  findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Se nao for selecionada uma imagem
                if (photoSelected == null){
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Obtem os dados que o usuario digitou
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();

                //Casos em que o usuario deixou algum campo vazio
                if (title.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();

                if (description.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                // Intencao para guardar os resultados a serem enviados
                Intent i = new Intent();

                // Setando os dados
                i.setData(photoSelected);
                i.putExtra("title", title);
                i.putExtra("description", description);

                // Indica que ha dados no retorno
                setResult(Activity.RESULT_OK, i);

                // Finaliza a tela, voltando para a tela que chamou
                finish();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica a chamada do StartActivityForResult, se e a mesma referente a escolha de imagem
        if (requestCode == PHOTO_PICKER_REQUEST) {

            // Verifica se a Activity de destino retornou com sucesso ou nao
            if (resultCode == Activity.RESULT_OK){

                // Recebe os dados retornados pela Activity
                photoSelected = data.getData();

                // Obtem o ImageView
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);

                // Seta a imagem no ImageView
                imvfotoPreview.setImageURI(photoSelected);

                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
                vm.setSelectPhotoLocation(photoSelected);

            }
        }
    }
}
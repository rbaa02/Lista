package alvarenga.brandao.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

// Guarda os dados referentes a MainActivity
public class MainActivityViewModel extends ViewModel {
    ArrayList<MyItem> itens = new ArrayList<>();

    public ArrayList<MyItem> getItens(){
        return itens;
    }
}
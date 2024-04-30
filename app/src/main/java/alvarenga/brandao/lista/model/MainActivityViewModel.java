package alvarenga.brandao.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import alvarenga.brandao.lista.model.MyItem;

public class MainActivityViewModel extends ViewModel {
    // guarda a lista de itens cadastrados
    List<MyItem> itens = new ArrayList<>();

    // obter a lista de itens cadastrados
    public List<MyItem> getItens() {
        return itens;
    }
}

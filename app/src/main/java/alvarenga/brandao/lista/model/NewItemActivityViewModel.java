package alvarenga.brandao.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    // cria um item vazio para selecionar uma foto
    Uri selectPhotoLoc = null;

    // Getter - pega a foto
    public Uri getSelectPhotoLoc() {
        return selectPhotoLoc;
    }

    // Setter - seta a foto
    public void setSelectPhotoLoc(Uri selectPhotoLoc){
        this.selectPhotoLoc = selectPhotoLoc;
    }
}

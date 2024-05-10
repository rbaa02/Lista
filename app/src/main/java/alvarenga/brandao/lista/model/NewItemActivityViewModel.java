package alvarenga.brandao.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;


// Guarda os dados referente a New Item Activity
public class NewItemActivityViewModel extends ViewModel {
    Uri selectPhotoLocation = null;

    public Uri getSelectPhotoLocation(){
        return selectPhotoLocation;
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation){
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
package comproducts.company.Controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class appFounction {

    public static Bitmap getLogoFromAsset(Context mActivity, String logo) {

        AssetManager assetManager = mActivity.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open("image/" + logo + ".jpg");
        } catch (IOException e) {
            Toast.makeText(mActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(istr);

    }
}

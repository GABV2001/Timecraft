package com.abra.timecraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abra.timecraft.data.CategoyMainRepository;
import com.abra.timecraft.data.UserConfig;
import com.abra.timecraft.fragments.CategoriaMainFragment;
import com.abra.timecraft.helpers.ItemTapListener;
import com.abra.timecraft.models.UserModel;
import com.abra.timecraft.models.CategoriaMainModel;
import com.google.android.material.snackbar.Snackbar;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

    public class MainActivity extends AppCompatActivity implements ItemTapListener {

    private static final String TAG = MainActivity.class.getName();
    public static final String USER_KEY = "USER";
    public static final String EMAIL_KEY = "EMAIL";
    public static final String PASSWORD_KEY = "PASSWORD";

    private CategoyMainRepository mCategoyMainRepository;
    private List<CategoriaMainModel> mModelList;
    private ViewGroup rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

  @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void setup(){
        mCategoyMainRepository = new CategoyMainRepository(getBaseContext());
        mModelList = new ArrayList<>();
        setupViewFromData();
        rootView = findViewById(R.id.ly_root);
        setupViewFromData();
    }

    private void setupViewFromData() {
        Intent startIntent = getIntent();
        if(startIntent == null) {
            Toast.makeText(
                    this,
                    "Algo salió mal al obtener los datos :(",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }
        UserModel userModel = getUserModelFromSources(startIntent.getExtras());

  if(getSupportActionBar() != null) {
            String usuario = TextUtils.isEmpty(userModel.getUser()) ?
                    "Usuario" : userModel.getUser();
            getSupportActionBar()
                    .setTitle(getString(R.string.welcome_user_title, usuario));
        }

        if(TextUtils.isEmpty(userModel.getUser())) {
            Toast.makeText(
                    this,
                    R.string.cannot_get_email,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @NonNull
    private UserModel getUserModelFromSources(Bundle extras) {
        UserConfig userConfig = new UserConfig(getApplicationContext());
        final UserModel user = userConfig.getUser();
        if(user != null) {
            return user;
        }
        if(extras == null) {
            throw new InvalidParameterException("Extras");
        }
        return new UserModel(extras.getString(USER_KEY), extras.getString(EMAIL_KEY), extras.getString(PASSWORD_KEY));
    }

    private void loadData() {
        if(!mModelList.isEmpty()) {
            Log.d(TAG, "Ya existen valores en la lista");

            return;
        }
        if(mCategoyMainRepository == null) {
            Log.e(TAG, "mPointsRepository no debería ser null");
            return;
        }
        mModelList = mCategoyMainRepository.getAll();

        loadPointsFragment(new ArrayList<CategoriaMainModel>(mModelList));
    }

    private void loadPointsFragment(ArrayList<CategoriaMainModel> cmain) {
        FragmentTransaction frgTran = getSupportFragmentManager().beginTransaction();
        frgTran.replace(R.id.categoriasMain_ph, CategoriaMainFragment.newInstance(cmain));
        frgTran.commit();
    }


   public void onItemTap(View view, int position) {
        showMessageWithSelectedItem(position);
    }

    private void showMessageWithSelectedItem(int position) {
        if(mModelList == null) {
            Log.e(TAG, "invalid mModelList");
            return;
        }
        if(position > mModelList.size()) {
            Log.e(TAG, "invalid position");
            return;
        }

        CategoriaMainModel selectedItemModel = mModelList.get(position);
        navigateToProfile(selectedItemModel);
        //showMessageCategorySelected(selectedItemModel);
    }

    private void navigateToProfile(CategoriaMainModel categoria) {
            launchSelectedCategory(categoria);
    }

    private void launchSelectedCategory(CategoriaMainModel selectedMain) {
       Intent intent = new Intent(this, CategorySelectedActivity.class);
        intent.putExtra(CategorySelectedActivity.ARG_LIST, selectedMain);
        startActivity(intent);
    }


    private void showMessageCategorySelected(CategoriaMainModel selectedItemModel) {
        Snackbar.make(rootView,
                String.format(Locale.getDefault(),
                        "Has seleccionado %s", selectedItemModel.getTitulo()
                ),
                Snackbar.LENGTH_LONG
        ).show();
    }


}
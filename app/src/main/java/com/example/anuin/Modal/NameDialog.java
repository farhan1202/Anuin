package com.example.anuin.Modal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anuin.MainActivity;
import com.example.anuin.R;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NameDialog extends BottomSheetDialogFragment {
    @BindView(R.id.editNama)
    EditText editNama;
    //private AccountDialogListener listener;
    PrefManager prefManager;
    ApiInterface apiInterface;
    @BindView(R.id.btnSaveName)
    Button btnSaveName;
    LoginDialog loginDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.name_layout, container, false);
        ButterKnife.bind(this, view);
        prefManager = new PrefManager(view.getContext());
        apiInterface = UtilsApi.getApiService();
//        String nama = getArguments().getString("nama");
        loginDialog = new LoginDialog(view.getContext());

        componentDidMount();
        return view;
    }

    private void componentDidMount() {
        apiInterface.requestProfile(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                            editNama.setText(jsonObject1.getString("name"));
                            String username = jsonObject1.getString("username");
                            String email = jsonObject1.getString("email");
                            String phone_number = jsonObject1.getString("phone_number");
                            String member_image = jsonObject1.getString("member_image");
                            btnSaveName.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (TextUtils.isEmpty(editNama.getText().toString())){
                                        editNama.setError("Field tidak boleh kosong");
                                    }else{
                                        updateName(username, email, phone_number, member_image);
                                        loginDialog.startLoadingDialog();
                                    }
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void updateName(String username, String email, String phone_number, String member_image) {
        apiInterface.updateProfile(UtilsApi.APP_TOKEN, prefManager.getTokenUser(),
                prefManager.getId(),
                editNama.getText().toString(),
                username,
                email,
                phone_number,
                member_image).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            Toast.makeText(getContext(), "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            loginDialog.dismissLoadingDialog();
                            Intent intent1 = new Intent(getContext(), MainActivity.class);
                            intent1.putExtra("FLAGPAGE", 2);
                            startActivity(intent1);
                            getActivity().finish();
                        }else{
                            loginDialog.dismissLoadingDialog();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    loginDialog.dismissLoadingDialog();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Koneksi bermasalah", Toast.LENGTH_SHORT).show();
                loginDialog.dismissLoadingDialog();
            }
        });
    }

    /*public interface AccountDialogListener{
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AccountDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"Must Implement Interface");
        }

    }*/
}

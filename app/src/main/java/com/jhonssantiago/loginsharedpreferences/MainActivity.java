package com.jhonssantiago.loginsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private EditText txtNome, txtLogin, txtSenha;
    private RadioButton rbAzul, rbVermelho, rbVerde;
    private Button btSalvar;
    private SharedPreferences preferences;

    private String cor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txtNome = findViewById(R.id.nome);
        txtLogin = findViewById(R.id.login);
        txtSenha = findViewById(R.id.senha);
        rbAzul = findViewById(R.id.azul);
        rbVermelho = findViewById(R.id.vermelho);
        rbVerde = findViewById(R.id.verde);
        btSalvar = findViewById(R.id.salvar);

        preferences = getSharedPreferences("arquivo",Context.MODE_PRIVATE);
        verificar(preferences);

    }

    private void verificar(SharedPreferences preferences) {

        String name = preferences.getString("nome", null);
        if(name == null ){
            btSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verificarCorEscolhida();
                    //permite editar o arquivo
                    SharedPreferences.Editor editor = preferences.edit();
                    //inclui ao arquivo, uma String com "chave/valor".
                    editor.putString("nome",txtNome.getText().toString());
                    editor.putString("login",txtLogin.getText().toString());
                    editor.putString("senha",txtSenha.getText().toString());
                    editor.putString("cor", cor);
                    //faz com que a informação seja salva no arquivo
                    editor.commit();
                    Intent it = new Intent(getApplicationContext(), SegundaActivity.class);
                    startActivity(it);
                }
            });
        } else {
            Intent it = new Intent(getApplicationContext(), SegundaActivity.class);
            startActivity(it);
        }

    }

    private String verificarCorEscolhida() {
        if(rbAzul.isChecked()) {
            return cor = "AZUL";
        }
        if(rbVermelho.isChecked()) {
            return cor = "VERMELHO";
        }
        if(rbVerde.isChecked()){
            return cor = "VERDE";
        }
        return cor = "BRANCO";
    }
}
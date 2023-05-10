package com.jhonssantiago.loginsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {
    private LinearLayout layout;
    private TextView resultadotxt;
    private Button btMudarCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        layout = findViewById(R.id.teladois);
        resultadotxt = findViewById(R.id.resultado);
        btMudarCor = findViewById(R.id.mudarcor);

        btMudarCor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                SharedPreferences.Editor sharedPreferences = getSharedPreferences("arquivo",MODE_PRIVATE).edit();
                sharedPreferences.clear();
                sharedPreferences.commit();
                startActivity(it);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("arquivo",MODE_PRIVATE);
        if(sharedPreferences.contains("nome") && sharedPreferences.contains("cor")){
            String cor = sharedPreferences.getString("cor","Branca");
            setarCor(cor);//os dados nenhum e branca serão mostrados caso não encontre o valor armazenado corresponde às chaves: nome_usuario e cor_usuario. Seria um valor default.
            String logado = "Olá "+ sharedPreferences.getString("nome","nenhum");
            resultadotxt.setText(logado);
        }else{
            Toast.makeText(this,"ERRO no arquivo",Toast.LENGTH_SHORT).show();
        }

    }

    private void setarCor(String cor) {

        switch (cor){
            case "VERDE":
                layout.setBackgroundColor(getColor(R.color.green));
                break;
            case "AZUL":
                layout.setBackgroundColor(getColor(R.color.blue));
                break;
            case "VERMELHO":
                layout.setBackgroundColor(getColor(R.color.red));
                break;
            case "BRANCO":
                layout.setBackgroundColor(getColor(R.color.white));
                break;
        }
    }//
}
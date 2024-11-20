package com.example.conversor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView temperatura;
    TextView tipo;
    TextView resultado;
    SeekBar seletor;
    Button ajuda;
    ToggleButton alternar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatura = findViewById(R.id.temperatura);
        tipo = findViewById(R.id.tipo);
        resultado = findViewById(R.id.resultado);
        seletor = findViewById(R.id.seletor);
        ajuda = findViewById(R.id.ajuda);
        alternar = findViewById(R.id.alternar);

        alternar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tipo.setText("ºF");
                } else {
                    tipo.setText("ºC");
                }
            }
        });

        seletor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresso, boolean usuario) {
                temperatura.setText(String.valueOf(Math.round(progresso)));
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void calcular() {
        Double formula;
        String texto;
        Double valor = Double.parseDouble(String.valueOf(temperatura.getText()));

        if(alternar.isChecked()) {
            texto = "ºC";
            formula = (valor * 9 / 5) + 32;
        }
        else{
            texto = "ºF";
            formula = (valor - 32) * 5 / 9;
        }
        formula = Math.floor(formula* 100) / 100;
        resultado.setText(formula + texto);
    }


    public void abrirAjuda (View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Ajuda");
        dialog.setMessage("Use o SeekBar para definir a temperatura.\n" +
                "Escolha entre Celsios e Fahrenheit.\n" +
                "Clique em \"Converter\" para ver o resultado.");

        dialog.setCancelable(true);

        dialog.setPositiveButton("Entendi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.create();
        dialog.show();
    }
}
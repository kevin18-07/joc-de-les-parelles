package com.example.parelles


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.parelles.R

class MainActivity : AppCompatActivity() {

    private lateinit var modelo: Model

    private lateinit var cartas: Array<ImageButton>

    private lateinit var btnValidar: Button
    private lateinit var txtResultado: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        modelo = Model()

        cartas = arrayOf(
            findViewById(R.id.carta0),
            findViewById(R.id.carta1),
            findViewById(R.id.carta2),
            findViewById(R.id.carta3),
            findViewById(R.id.carta4),
            findViewById(R.id.carta5)
        )

        btnValidar = findViewById(R.id.btnValidar)
        txtResultado = findViewById(R.id.txtResultado)

        for (i in cartas.indices) {

            cartas[i].setOnClickListener {

                if (modelo.girarCarta(i)) {
                    actualizarVista()
                }
            }
        }

        btnValidar.setOnClickListener {

            if (!modelo.quedanDosCartas()) {

                txtResultado.text =
                    "Selecciona dues cartes!"

            } else {

                val resultado = modelo.validar()

                actualizarVista()

                if (modelo.juegoTerminado()) {

                    txtResultado.text =
                        "FELICITATS! HAS GUANYAT!"

                    btnValidar.isEnabled = false

                } else if (resultado) {

                    txtResultado.text =
                        "Correcte! Has trobat una parella!"

                } else {

                    txtResultado.text =
                        "No són iguals! Torna-ho a provar."
                }
            }
        }

        actualizarVista()
    }

    private fun actualizarVista() {

        for (i in cartas.indices) {

            if (modelo.estaVisible(i)) {

                cartas[i].setImageResource(
                    modelo.cartas[i]
                )

                cartas[i].isEnabled = false

            } else {

                cartas[i].setImageResource(
                    R.drawable.back
                )

                cartas[i].isEnabled =
                    modelo.visibles.size < 2
            }
        }
    }
}

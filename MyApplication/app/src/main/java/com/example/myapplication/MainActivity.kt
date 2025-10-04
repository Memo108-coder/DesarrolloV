package com.example.myapplication
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etSalario: EditText
    private lateinit var btnCalcular: Button
    private lateinit var btnNuevo: Button
    private lateinit var btnSalir: Button
    private lateinit var tvResultado: TextView

    private val currencyFormat: NumberFormat by lazy {
        NumberFormat.getCurrencyInstance(Locale("es", "NI"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etSalario = findViewById(R.id.etSalario)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnNuevo = findViewById(R.id.btnNuevo)
        btnSalir = findViewById(R.id.btnSalir)
        tvResultado = findViewById(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            ocultarTeclado(it)
            calcular()
        }
        btnNuevo.setOnClickListener { limpiar() }
        btnSalir.setOnClickListener { confirmarSalida() }
    }

    private fun calcular() {
        val nombre = etNombre.text.toString().trim()
        val salario = etSalario.text.toString().trim().toDoubleOrNull()

        if (nombre.isEmpty()) {
            etNombre.error = "Ingresa el nombre"
            etNombre.requestFocus()
            return
        }
        if (salario == null || salario <= 0) {
            etSalario.error = "Ingresa un salario válido"
            etSalario.requestFocus()
            return
        }

        val anual = salario * 12
        val decimoTercerMes = salario
        val deduccion = salario * 0.07
        val neto = salario - deduccion

        tvResultado.text = buildString {
            appendLine("Trabajador: $nombre")
            appendLine("Salario mensual: ${currencyFormat.format(salario)}")
            appendLine("Salario anual (x12): ${currencyFormat.format(anual)}")
            appendLine("13º mes (ejemplo): ${currencyFormat.format(decimoTercerMes)}")
            appendLine("Deducción 7% (ejemplo): ${currencyFormat.format(deduccion)}")
            appendLine("Neto (ejemplo): ${currencyFormat.format(neto)}")
        }
        Toast.makeText(this, "Cálculo realizado", Toast.LENGTH_SHORT).show()
    }

    private fun limpiar() {
        etNombre.text?.clear()
        etSalario.text?.clear()
        tvResultado.text = "Resultados aparecerán aquí..."
        etNombre.requestFocus()
    }

    private fun confirmarSalida() {
        AlertDialog.Builder(this)
            .setTitle("Salir")
            .setMessage("¿Deseas cerrar la aplicación?")
            .setPositiveButton("Sí") { _, _ -> finishAffinity() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun ocultarTeclado(view: View) {
        try {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (_: Exception) {}
    }
}
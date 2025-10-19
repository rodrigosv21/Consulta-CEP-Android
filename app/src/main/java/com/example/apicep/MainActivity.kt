package com.example.apicep

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apicep.databinding.ActivityMainBinding
import com.example.apicep.views.DadosCep
import com.example.apicep.views.ViewDadosActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBuscar.setOnClickListener {
            val cepDigitado = binding.editCep.text.toString()

            val dadosCep = DadosCep(cepDigitado)
            val isValid = dadosCep.validate()

            if (isValid) {
                val intent = Intent(this, ViewDadosActivity::class.java)
                intent.putExtra("cep", cepDigitado)
                startActivity(intent)
            } else {
                Toast.makeText(this, "CEP inválido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

package com.example.apicep.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apicep.R
import com.example.apicep.databinding.ActivityViewDadosBinding
import com.example.apicep.entity.ApiService
import com.example.apicep.entity.Endereco
import com.example.apicep.entity.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ViewDadosActivity : AppCompatActivity() {

    private val binding by lazy { ActivityViewDadosBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scroll_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.IO).launch {
            recuperar()
        }
    }

    private suspend fun recuperar() {
        val cepDigitado = intent.getStringExtra("cep") ?: ""

        try {
            val enderecoApi = RetrofitClient.retrofit.create(ApiService::class.java)
            val retorno: Response<Endereco> = enderecoApi.buscarEndereco(cepDigitado)

            if (retorno.isSuccessful) {
                val endereco = retorno.body()

                withContext(Dispatchers.Main) {
                    endereco?.let { preencherCampos(it) }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun preencherCampos(endereco: Endereco) {
        binding.apply {
            tvCep.text = endereco.cep ?: ""
            tvLogradouro.text = endereco.logradouro ?: ""
            tvComplemento.text = endereco.complemento ?: ""
            tvBairro.text = endereco.bairro ?: ""
            tvLocalidade.text = endereco.localidade ?: ""
            tvUf.text = endereco.uf ?: ""
            tvEstado.text = endereco.estado ?: ""
            tvRegiao.text = endereco.regiao ?: ""
            tvIbge.text = endereco.ibge ?: ""
            tvGia.text = endereco.gia ?: ""
            tvDdd.text = endereco.ddd ?: ""
            tvSiafi.text = endereco.siafi ?: ""
        }
    }
}



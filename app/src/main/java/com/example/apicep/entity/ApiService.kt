package com.example.apicep.entity

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/ws/{cep}/json/")
    suspend fun buscarEndereco(@Path("cep") cep: String): Response<Endereco>
}

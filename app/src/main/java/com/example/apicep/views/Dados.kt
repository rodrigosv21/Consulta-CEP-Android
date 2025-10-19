package com.example.apicep.views

class DadosCep(private val cep: String) {

    private inner class CheckCep {
        fun isValid(): Boolean {
            val cepRegex = Regex("^\\d{5}-?\\d{3}\$")
            return cep.matches(cepRegex)
        }
    }

    fun validate(): Boolean {
        val checker = CheckCep()
        return checker.isValid()
    }
}

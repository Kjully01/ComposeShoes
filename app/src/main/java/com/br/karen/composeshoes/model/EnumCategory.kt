package com.br.karen.composeshoes.model

enum class EnumCategory(val titulo: String) {
    TODOS("Todos"),
    TENIS("Tênis"),
    BOTAS("Botas"),
    CHUTEIRAS("Chuteiras"),
    SAPATENIS("Sapatênis");

    companion object {
        fun fromTitulo(titulo: String): EnumCategory? {
            return entries.find { it.titulo.equals(titulo, ignoreCase = true) }
        }
    }
}
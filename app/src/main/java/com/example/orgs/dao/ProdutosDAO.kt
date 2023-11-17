package com.example.orgs.dao

import com.example.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDAO {


    fun adicionar(produto: Produto) {
        produtos.add(produto)

    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()

    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(nome = "Salada de fruta", descricao = "laranja maca", valor = BigDecimal(19.50))
        )
    }

}
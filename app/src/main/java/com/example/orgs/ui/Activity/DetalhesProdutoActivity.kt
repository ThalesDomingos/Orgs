package com.example.orgs.ui.Activity

import android.os.Bundle
import android.os.PersistableBundle
import com.example.orgs.databinding.DetalhesDoProdutoBinding
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.extensions.formataParaMoedaBrasileira
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto


class DetalhesProdutoActivity : AppCompatActivity () {

    private val binding by lazy {
        DetalhesDoProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }
    private fun tentaCarregarProduto() {
        // tentativa de buscar o produto se ele existir,
        // caso contrário, finalizar a Activity
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheCampos(produtoCarregado)
        } ?: finish()
    }
    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            imageView2.tentaCarregarImagem(produtoCarregado.imagem)
            detalhesProdutoTitulo.text = produtoCarregado.nome
            detalhesProdutoDescricao.text = produtoCarregado.descricao
            detalhesProdutoValor.text = produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }
}
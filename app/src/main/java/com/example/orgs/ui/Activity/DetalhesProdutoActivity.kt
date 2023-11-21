package com.example.orgs.ui.Activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import com.example.orgs.databinding.DetalhesDoProdutoBinding
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.extensions.formataParaMoedaBrasileira
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetalhesProdutoActivity : AppCompatActivity () {


    private var produtoId: Long = 0L
    private  var produto: Produto? = null
    private val binding by lazy {
        DetalhesDoProdutoBinding.inflate(layoutInflater)
    }

    val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }
    private val scope = CoroutineScope(IO)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        buscaProduto()
    }

    private fun buscaProduto() {

            scope.launch {
                produto = produtoDao.buscaPorId(produtoId)
                withContext(Main){
                    produto?.let {
                        preencheCampos(it)
                    } ?: finish()
                }

            }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


            when (item.itemId){
                R.id.menu_detalhes_produto_apagar -> {
                    scope.launch {
                            produto?.let { produtoDao.remove(it) }
                            finish()
                    }

                }
                R.id.menu_detalhes_produto_editar -> {
                    Intent(this, FormularioProdutoActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO_ID, produtoId)
                    startActivity(this)
                    }
                }
            }

        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
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
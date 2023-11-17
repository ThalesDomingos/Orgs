package com.example.orgs.ui.Activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDAO
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaProdutosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)}

    private val dao = ProdutosDAO()

    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
        configuraFab()

    }

    private fun configuraFab() {
//        val fab = findViewById<FloatingActionButton>(R.id.activity_lista_produtos_fab)
        val fab = binding.activityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("MissingInflatedId")
    private fun configuraRecyclerView() {
//        setContentView(R.layout.activity_lista_produtos)
//        val recyclerView = findViewById<RecyclerView>(R.id.activity_lista_produtos_recyclerView)
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter
        setContentView(binding.root)
    }
}
package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityFormularioCadastroUsuarioBinding
import br.com.alura.orgs.extensions.toHash
import br.com.alura.orgs.model.Usuario
import kotlinx.coroutines.launch
import java.lang.Exception

class FormularioCadastroUsuarioActivity : AppCompatActivity() {
    private val dao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }

    private val binding by lazy {
        ActivityFormularioCadastroUsuarioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
    }

    private fun configuraBotaoCadastrar() {
        binding.activityFormularioCadastroBotaoCadastrar.setOnClickListener {
            val novoUsuario = criaUsuario()
            lifecycleScope.launch(){
                try {
                    dao.salva(novoUsuario)
                    finish()
                }catch (e:Exception){
                    Log.e("CadastroUsuarioo", "Configurando cadastrar")
                    Log.e("CadastroUsuarioo", "${e.printStackTrace()}")
                    Toast.makeText(this@FormularioCadastroUsuarioActivity,"Falha ao cadastrar usuario",Toast.LENGTH_SHORT).show()
                }
            }
            Log.i("CadastroUsuario", "onCreate: $novoUsuario")
            finish()
        }
    }

    private fun criaUsuario(): Usuario {
        val usuario = binding.activityFormularioCadastroUsuario.text.toString()
        val nome = binding.activityFormularioCadastroNome.text.toString()
        val senha = binding.activityFormularioCadastroSenha.text.toString().toHash()
        return Usuario(usuario, nome, senha)
    }
}
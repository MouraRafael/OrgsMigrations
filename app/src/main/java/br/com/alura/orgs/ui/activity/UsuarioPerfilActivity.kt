package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityUsuarioPerfilBinding
import br.com.alura.orgs.model.Usuario
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class UsuarioPerfilActivity : UsuarioBaseActivity() {
    private val binding by lazy {
        ActivityUsuarioPerfilBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Perfil"

        preencheCampos()

        configuraLogout()



    }

    private fun configuraLogout() {
        binding.activityUsuarioPerfilBotaoLogout.setOnClickListener {
            lifecycleScope.launch {
                deslogaUsuario()
            }
        }
    }

    private fun preencheCampos() {
        lifecycleScope.launch{
            usuario.filterNotNull().collect{usuario->
                binding.activityUsuarioPerfilUsername.text = usuario.id
                binding.activityUsuarioPerfilNome.text = usuario.nome
            }
        }
    }
}
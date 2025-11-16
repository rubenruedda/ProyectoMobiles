package view.fragment

import NoticiaAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aplicacion_infosport.databinding.FragmentNoticiasBinding
import model.Noticia
import view.NoticiaExpandida
import viewModel.NoticiaViewModel

class NoticiasFragment : Fragment() {

    private var _binding: FragmentNoticiasBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoticiaViewModel by viewModels()
    private lateinit var noticiaAdapter: NoticiaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticiasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvNoticias()
        setupSearchView()

        // Observamos la nueva lista filtrada
        viewModel.noticiasFiltradas.observe(viewLifecycleOwner) { noticias ->
            noticiaAdapter.submitList(noticias)
        }
    }

    private fun setupRvNoticias() {
        noticiaAdapter = NoticiaAdapter { noticia ->
            lanzarActivityNoticiaExpandida(noticia)
        }
        binding.rvNoticias.adapter = noticiaAdapter
    }

    private fun setupSearchView() {
        binding.searchViewNoticias.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Opcional: ocultar teclado
                binding.searchViewNoticias.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Cada vez que el texto cambia, actualiza el ViewModel
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }

    private fun lanzarActivityNoticiaExpandida(noticia: Noticia) {
        val intent = Intent(requireContext(), NoticiaExpandida::class.java)
        // Pasamos el ID para que la nueva Activity cargue el detalle
        intent.putExtra("NOTICIA_ID", noticia.id)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
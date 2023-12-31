package com.dbd.Debede.Services;

import com.dbd.Debede.Entities.Categoria;
import com.dbd.Debede.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    public Categoria obtenerCategoriaPorGenero(String genero) {
        // Realizar la consulta a la base de datos para buscar la categoría por el género
        Optional<Categoria> optionalCategoria = categoriaRepository.findByTipoCategoria(genero);

        // Si la categoría se encuentra, devolverla; de lo contrario, devolver null
        return optionalCategoria.orElse(null);
    }
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setRestriccionEdad(categoriaActualizada.getRestriccionEdad());
                    categoria.setTipoCategoria(categoriaActualizada.getTipoCategoria());
                    categoria.setUbicacionGeografica(categoriaActualizada.getUbicacionGeografica());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}

package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Articulo;
import com.tallerwebi.dominio.RepositorioArticulo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioArticuloImp implements RepositorioArticulo {
    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioArticuloImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Articulo> buscarPorTipoEntrenamiento(String tipoEntrenamiento) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Articulo where tipoEntrenamiento = :tipoEntrenamiento", Articulo.class)
                .setParameter("tipoEntrenamiento", tipoEntrenamiento)
                .list();
    }
    @Override
    public List<Articulo> todosLosArticulos(int page, int size) {
        Session session = sessionFactory.getCurrentSession();

        // Crear la consulta para obtener todos los artículos
        Query<Articulo> query = session.createQuery("from Articulo a order by rand()", Articulo.class);
        // Configurar paginación
        int firstResult = page * size;
        query.setFirstResult(firstResult);  // Establecer el índice del primer resultado
        query.setMaxResults(size);          // Establecer el número máximo de resultados
        // Ejecutar la consulta y retornar la lista paginada
        List<Articulo> articulos = query.list();
        return articulos;
    }

    @Override
    public Articulo getPorId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Articulo.class, id);
    }
    @Override
    public Long contarTotalArticulos(){
        Session session = sessionFactory.getCurrentSession();
        // Crear la consulta para contar el número total de artículos
        Long count = (Long) session.createQuery("SELECT COUNT(a) FROM Articulo a").uniqueResult();
        return count;
    }
}

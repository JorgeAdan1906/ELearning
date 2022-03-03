package Elearning.dao.impl;

import Elearning.dao.MiCuestionarioDao;
import Elearning.modelo.MiCuestionario;
import Elearning.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("MiCuestionarioDao")
public class MiCuestionarioDaoImpl implements MiCuestionarioDao{

    @Override
    public List<MiCuestionario> findAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        //Declaramos la lista donde almacenara el conjunto de datos de la tabla 
        List<MiCuestionario> lista = null;

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //crea la consulta Query
            Query<MiCuestionario> miQuery = session.createQuery("from MiCuestionario id order by id.idMiCuestionario");
            //Amacenamos los datos en la lista declarada anteriormente 
            lista = miQuery.list();
            lista.size();
            //regresa el commit
            transaccion.commit();
        } catch (HibernateException e) {
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Datos
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }
        return lista;
    }

    @Override
    public MiCuestionario create(MiCuestionario entidad) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Guardamos la transaccion
            session.save(entidad);
            transaccion.commit();

        } catch (HibernateException e) {
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Dato
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }
        return entidad;
    }

    @Override
    public MiCuestionario getMiCuestionario(Integer idMiCuestionario) {
        //Obtener la sesion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        MiCuestionario entidad = null;
        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Obtener por medio del id llamamos a la Tabla MiCuestionario y que haga de parametro el idMiCuestionario
            entidad = session.get(MiCuestionario.class, idMiCuestionario);
            transaccion.commit();

        } catch (HibernateException e) {
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Datos
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }
        //regresamos el objeto por su id 
        return entidad;
    }

    @Override
    public MiCuestionario update(MiCuestionario entidad) {
        System.out.println("entidad dao impl: " + entidad);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Actualizamos los datos 
            session.update(entidad);
            transaccion.commit();

        } catch (HibernateException e) {
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Dato
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }

        return entidad;
    }

    @Override
    public boolean delete(MiCuestionario entidad) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        boolean flag = true;

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Actualizamos los datos 
            session.delete(entidad);
            transaccion.commit();

        } catch (HibernateException e) {
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Dato
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
            flag = false;

        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }

        return flag;
    }

    @Override
    public Integer countApproved(int idUsuario, int idCurso) {
        //Obtener la sesion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        //Declaramos el entero donde almacenara el total de cuestionarios
        int count = 0;

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Crear la consulta SQLQuery
            Query miQuery;
            miQuery = session.createSQLQuery("SELECT COUNT(MiCuestionario.idMiCuestionario) AS 'Aprobados' FROM MiCuestionario INNER JOIN Cuestionario ON MiCuestionario.idCuestionario = Cuestionario.idCuestionario INNER JOIN Modulo ON Cuestionario.idModulo = Modulo.idModulo INNER JOIN Curso ON Modulo.idCurso = Curso.idCurso INNER JOIN " + 
                    "(SELECT Cuestionario.idCuestionario FROM Cuestionario INNER JOIN Modulo ON Cuestionario.idModulo = Modulo.idModulo INNER JOIN Curso ON Modulo.idCurso = Curso.idCurso WHERE Curso.idCurso = " + idCurso + ") Sub ON Sub.idCuestionario = Cuestionario.idCuestionario WHERE MiCuestionario.evaluacion = 1 AND MiCuestionario.idUsuario = " + idUsuario);
            //Almacenamos los datos
            count = (Integer)miQuery.uniqueResult();
            //Regresa el commit
            transaccion.commit();
        } catch (HibernateException e) {
            //Si la transaccion esta vacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Datos
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }
        return count;
    }

    @Override
    public List<MiCuestionario> findByUsuarioByCuestionario(int idUsuario, int idCuestionario) {
        //Obtener la sesion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        //Declaramos la lista donde almacenara el conjunto de datos de la tabla 
        List<MiCuestionario> lista = null;

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //crea la consulta Query
            Query miQuery = session.createSQLQuery("SELECT * FROM MiCuestionario m WHERE m.idUsuario =:idU AND m.idCuestionario =:idC").addEntity(MiCuestionario.class)
                    .setParameter("idU",idUsuario)
                    .setParameter("idC", idCuestionario);
            //Amacenamos los datos en la lista declarada anteriormente 
            lista = miQuery.list();
            lista.size();
            //regresa el commit
            transaccion.commit();
        } catch (HibernateException e) {
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Datos
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }
        return lista;
    }

    @Override
    public MiCuestionario getByUsuarioByCuestionario(int idUsuario, int idCuestionario) {
        //Obtener la sesion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        //Declaramos la lista donde almacenara el conjunto de datos de la tabla 
        MiCuestionario entidad = null;
        List<MiCuestionario> lista = null;
        int idMiCuest;

        try {
            System.out.println("llegaaaaa");
            //Iniciamos Transaccion
            transaccion.begin();
            //crea la consulta Query
            try {
                Query miQuery = session.createSQLQuery("SELECT * FROM MiCuestionario WHERE idUsuario='" + idUsuario + "' AND idCuestionario='" + idCuestionario + "' ").addEntity(MiCuestionario.class);
            //Amacenamos los datos en la lista declarada anteriormente 
                lista = miQuery.list();
                System.out.println("lista mi cuest dao impl: " + lista);
            } catch (Exception e) {
            }
            
            lista.size();
            //regresa el commit
            transaccion.commit();
        } catch (HibernateException e) {
            System.out.println("ERROR dao impl: " + e);
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Datos
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }
        
        for(MiCuestionario u : lista){
            entidad = getMiCuestionario(u.getIdMiCuestionario());
        }
        System.out.println("enitdad: " + entidad);
        return entidad;
    }

    @Override
    public List<MiCuestionario> findAllByCursoByUsuario(int idCurso, int idUsuario) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        List<MiCuestionario> lista = null;

        try {
            transaccion.begin();
            //crea la consulta Query
//            Query miQuery = session.createSQLQuery("SELECT Cuestionario.idCuestionario, Cuestionario.nombre, Cuestionario.idModulo FROM Cuestionario INNER JOIN Modulo ON Cuestionario.idModulo = Modulo.idModulo INNER JOIN Curso ON Modulo.idCurso = Curso.idCurso WHERE Curso.idCurso =:id").addEntity(Cuestionario.class)
//            .setParameter("id",idCurso);
            
            //CONSULTA A TALBE MI CUESTIONARIOS CON JOINS
            //consulta completa
            //Query miQuery = session.createSQLQuery("select MiCuestionario.idMiCuestionario, MiCuestionario.evaluacion, MiCuestionario.idUsuario, MiCuestionario.idCuestionario, MiCuestionario.estado, Cuestionario.idCuestionario, Cuestionario.nombre, Cuestionario.idModulo, Modulo.idModulo, Modulo.titulo, Modulo.descripcion, Modulo.url, MOdulo.idCurso, Modulo.idCuestionario,Curso.idCurso, Curso.nombre, Curso.descripcion, Curso.caratula, curso.categoria from MiCuestionario INNER JOIN Cuestionario ON MiCuestionario.idCuestionario = Cuestionario.idCuestionario INNER JOIN Modulo ON Cuestionario.idModulo = Modulo.idModulo INNER JOIN Curso ON Modulo.idCurso = Curso.idCurso WHERE Curso.idCurso=:id").addEntity(MiCuestionario.class)
            
            Query miQuery = session.createSQLQuery("select MiCuestionario.idMiCuestionario, MiCuestionario.evaluacion, MiCuestionario.idUsuario, MiCuestionario.idCuestionario, MiCuestionario.estado, MiCuestionario.calificacion, Cuestionario.idCuestionario, Cuestionario.nombre, Cuestionario.idModulo, Curso.idCurso, Curso.nombre, Curso.descripcion, Curso.caratula, curso.categoria from MiCuestionario INNER JOIN Cuestionario ON MiCuestionario.idCuestionario = Cuestionario.idCuestionario INNER JOIN Modulo ON Cuestionario.idModulo = Modulo.idModulo INNER JOIN Curso ON Modulo.idCurso = Curso.idCurso WHERE Curso.idCurso='" + idCurso + "' and MiCuestionario.idUsuario='" + idUsuario + "' ").addEntity(MiCuestionario.class);
            
            //Amacenamos los datos en la lista declarada anteriormente 
            lista = miQuery.list();
            System.out.println("lista cuestionarios: " + lista);
            lista.size();
            //regresa el commit
            transaccion.commit();
        } catch (HibernateException e) {
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Datos
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }
        return lista;
    }

    @Override
    public Boolean getEvaluacion(Integer idMiCuestionario) {
        System.out.println("llega a getEvaluacion dao");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        MiCuestionario entidad = null;
        List<MiCuestionario> lista = null;
        //int idMiCuest;
        Boolean evaluacion = false;

        try {
            transaccion.begin();
            try {
                Query miQuery = session.createSQLQuery("select evaluacion from MiCuestionario where idMiCuestionario =" + idMiCuestionario);
                //lista = miQuery.list();
                evaluacion = (Boolean)miQuery.uniqueResult();
                System.out.println("-----------------------------Evaluacion: " + evaluacion);
                transaccion.commit();
            } catch (Exception e) {
                System.out.println("error en dao impl get ev: " + e);
            }
            
        } catch (HibernateException e) {
            System.out.println("ERROR dao impl: " + e);
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            session.close();
        }
        
        return evaluacion;
    }

    
}
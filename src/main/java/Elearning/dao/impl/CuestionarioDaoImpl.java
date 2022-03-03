package Elearning.dao.impl;

import Elearning.dao.CuestionarioDao;
import Elearning.dao.ModuloDao;
import Elearning.modelo.Cuestionario;
import Elearning.modelo.Curso;
import Elearning.modelo.Modulo;
import Elearning.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;



@Repository("CuestionarioDao")
public class CuestionarioDaoImpl implements CuestionarioDao {

    @Override
    public List<Cuestionario> findAll(int idModulo) {
        //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        //Declaramos la lista donde almacenara el conjunto de datos de la tabla 
        List<Cuestionario> lista = null;

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //crea la consulta Query
            System.out.println("LLEGA A FINDALL EN CUESTIONARIODAOIMPL--------------");
            Query miQuery = session.createSQLQuery("select * from Cuestionario c where c.idModulo=:id").addEntity(Cuestionario.class)
                    .setParameter("id",idModulo);
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
    public Integer countByCurso(Integer idCurso) {
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
            miQuery = session.createSQLQuery("SELECT COUNT(Modulo.idModulo) AS 'Cuestionarios' FROM Cuestionario INNER JOIN Modulo ON Cuestionario.idModulo = Modulo.idModulo INNER JOIN Curso ON Modulo.idCurso = Curso.idCurso WHERE Curso.idCurso = " + idCurso + " GROUP BY Curso.nombre");
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
    public Cuestionario getCuestionario(Integer idCuestionario) {
        //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        Cuestionario entidad = null;
        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Obtener por medio del id llamamos a la Tabla Cuestionario y que haga de parametro el idCuestionario
            entidad = session.get(Cuestionario.class, idCuestionario);
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
        //regresamos el objeto por su id 
        return entidad;
    }

    @Override
    public Cuestionario create(Cuestionario elCuestionario, int idModulo) {
        System.out.println("El cuestionario: " + elCuestionario);
        //System.out.println("nombre: " + elCuestionario.getNombre());
        //System.out.println("idModulo: " + idModulo);
        //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Guardamos la transaccion
            Integer id = (Integer) session.save(elCuestionario);
            transaccion.commit();
            elCuestionario.setIdCuestionario(id);

        } catch (HibernateException e) {
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Dato
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }
        getIdByModuloAndName(idModulo, elCuestionario.getNombre());
        return elCuestionario;
    }

    @Override
    public Cuestionario update(Cuestionario elCuestionario) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Actualizamos los datos 
            session.update(elCuestionario);
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

        return elCuestionario;
    }
    
    @Override
    public boolean deleteMisCuestionarioByCuestionario(Cuestionario elCuestionario){
        //System.out.println("el cuestionario delete: " + elCuestionario);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        boolean flag=true;
   
        try {
            transaccion.begin();
            Query miQuery;
            miQuery = session.createSQLQuery("delete from MiCuestionario where idCuestionario="+elCuestionario.getIdCuestionario());
            int resp = miQuery.executeUpdate();
            System.out.println("resp: " + resp);
            System.out.println("SE borraron los misCuestionarios By cuestionario");
            transaccion.commit();

        } catch (HibernateException e) {
            System.out.println("Error delete misCuest by cuest: " + e);
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
            flag=false;
            
        } finally {
            session.close();
        }
        delete(elCuestionario);
        return true;
    }

    @Override
    public boolean delete(Cuestionario elCuestionario) {
        System.out.println("llega a delete");
        //deleteMisCuestionarioByCuestionario(elCuestionario);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        boolean flag=true;
   
        try {
            transaccion.begin();
            Query miQuery;
            miQuery= session.createSQLQuery("delete from Cuestionario where idCuestionario="+ elCuestionario.getIdCuestionario());
            int resp = miQuery.executeUpdate();
            System.out.println("resp: " + resp);
            System.out.println("Se borro el cuestionario");
            transaccion.commit();
            //session.delete(elCuestionario);
            
        } catch (HibernateException e) {
            System.out.println("error delete cuest: " + e);
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Dato
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
            flag=false;
            
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public Integer getIdByModulo(Integer idModulo) {
                //Obtener la sesion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        //Declaramos el entero donde almacenara el total de cuestionarios
        int idCuestionario = 0;

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Crear la consulta SQLQuery
            Query miQuery;
            miQuery = session.createSQLQuery("SELECT idCuestionario FROM Cuestionario WHERE idModulo = " + idModulo);
            //Almacenamos los datos
            idCuestionario = (Integer)miQuery.uniqueResult();
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
        return idCuestionario;
    }

    @Override
    public List<Cuestionario> findAllByCurso(int idCurso) {
         //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        //Declaramos la lista donde almacenara el conjunto de datos de la tabla 
        List<Cuestionario> lista = null;

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //crea la consulta Query
            Query miQuery = session.createSQLQuery("SELECT Cuestionario.idCuestionario, Cuestionario.nombre, Cuestionario.idModulo FROM Cuestionario INNER JOIN Modulo ON Cuestionario.idModulo = Modulo.idModulo INNER JOIN Curso ON Modulo.idCurso = Curso.idCurso WHERE Curso.idCurso =:id").addEntity(Cuestionario.class)
            .setParameter("id",idCurso);
            
            //CONSULTA A TALBE MI CUESTIONARIOS CON JOINS
//            Query miQuery = session.createSQLQuery("select MiCuestionario.idMiCuestionario, MiCuestionario.evaluacion, MiCuestionario.idUsuario, MiCuestionario.idCuestionario, MiCuestionario.estado, Cuestionario.idCuestionario, Cuestionario.nombre, Cuestionario.idModulo, Modulo.idModulo, Modulo.titulo, Modulo.descripcion, Modulo.url, MOdulo.idCurso, Modulo.idCuestionario,Curso.idCurso, Curso.nombre, Curso.descripcion, Curso.caratula, curso.categoria from MiCuestionario INNER JOIN Cuestionario ON MiCuestionario.idCuestionario = Cuestionario.idCuestionario INNER JOIN Modulo ON Cuestionario.idModulo = Modulo.idModulo INNER JOIN Curso ON Modulo.idCurso = Curso.idCurso WHERE Curso.idCurso=:id").addEntity(Cuestionario.class)
//            .setParameter("id",idCurso);
            //Amacenamos los datos en la lista declarada anteriormente 
            lista = miQuery.list();
            //System.out.println("lista cuestionarios: " + lista);
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
    public List<Cuestionario> findAllByCursoByModulo(int idCurso, int idModulo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        List<Cuestionario> lista = null;

        try {
            transaccion.begin();
            Query miQuery = session.createSQLQuery("SELECT Cuestionario.idCuestionario, Cuestionario.nombre, Cuestionario.idModulo FROM Cuestionario INNER JOIN Modulo ON Cuestionario.idModulo = Modulo.idModulo INNER JOIN Curso ON Modulo.idCurso = Curso.idCurso WHERE Curso.idCurso =:id and Cuestionario.idModulo=:idModulo").addEntity(Cuestionario.class)
            .setParameter("id",idCurso).setParameter("idModulo", idModulo);
            
            //CONSULTA A TALBE MI CUESTIONARIOS CON JOINS
//            Query miQuery = session.createSQLQuery("select MiCuestionario.idMiCuestionario, MiCuestionario.evaluacion, MiCuestionario.idUsuario, MiCuestionario.idCuestionario, MiCuestionario.estado, Cuestionario.idCuestionario, Cuestionario.nombre, Cuestionario.idModulo, Modulo.idModulo, Modulo.titulo, Modulo.descripcion, Modulo.url, MOdulo.idCurso, Modulo.idCuestionario,Curso.idCurso, Curso.nombre, Curso.descripcion, Curso.caratula, curso.categoria from MiCuestionario INNER JOIN Cuestionario ON MiCuestionario.idCuestionario = Cuestionario.idCuestionario INNER JOIN Modulo ON Cuestionario.idModulo = Modulo.idModulo INNER JOIN Curso ON Modulo.idCurso = Curso.idCurso WHERE Curso.idCurso=:id").addEntity(Cuestionario.class)
//            .setParameter("id",idCurso);
            lista = miQuery.list();
            System.out.println("lista cuestionarios bu modulo by curso: " + lista);
            lista.size();
            //regresa el commit
            transaccion.commit();
        } catch (HibernateException e) {
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
    public Integer getIdByModuloAndName(Integer idModulo, String nombre) {
        ModuloDaoImpl moduloDao = new ModuloDaoImpl();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        int idCuestionario = 0;

        try {
            transaccion.begin();
            Query miQuery;
            miQuery = session.createSQLQuery("SELECT idCuestionario FROM Cuestionario WHERE idModulo=" + idModulo);
            idCuestionario = (Integer)miQuery.uniqueResult();
            System.out.println("---------------------------idCuestionario: " + idCuestionario);
            transaccion.commit();
        } catch (HibernateException e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            session.close();
        }
        moduloDao.updateidModulo(idModulo, idCuestionario);
        return idCuestionario;    
    }

    @Override
    public List<Integer> getCuestionariosByCurso(int idCurso) {
        System.out.println("llega a getCuestionariosByCurso");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        List<Integer> lista = null;

        try {
            transaccion.begin();
            Query miQuery = session.createSQLQuery("select Cuestionario.idCuestionario from Cuestionario inner join Modulo on Cuestionario.idModulo = Modulo.idModulo where Modulo.idCurso="+ idCurso);
            //Amacenamos los datos en la lista declarada anteriormente 
            lista = miQuery.list();
            lista.size();
            //regresa el commit
            transaccion.commit();
            System.out.println("lista id cuest: " + lista);
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

}

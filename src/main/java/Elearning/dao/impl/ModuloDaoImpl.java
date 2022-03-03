package Elearning.dao.impl;

import Elearning.dao.ModuloDao;
import Elearning.modelo.Modulo;
import Elearning.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("ModuloDao")
public class ModuloDaoImpl implements ModuloDao {

    @Override
    public List<Modulo> findAll() {

        //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        //Declaramos la lista donde almacenara el conjunto de datos de la tabla 
        List<Modulo> lista = null;

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //crea la consulta Query
            Query<Modulo> miQuery = session.createQuery("from Modulo id order by id.idModulo");
            //Amacenamos los datos en la lista declarada anteriormente 
            lista = miQuery.list();
            //regresa el commit
            lista.size();
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
    public Modulo create(Modulo elModulo) {
        //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Guardamos la transaccion
            Integer id = (Integer) session.save(elModulo);
            transaccion.commit();
            elModulo.setIdModulo(id);

        } catch (HibernateException e) {
            //Si la transaccion esta bacia y ademas esta activa que regrese el estado en el que se encontraba la Base de Dato
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
        } finally {
            //Finalmente cerramos la sesion 
            session.close();
        }
        return elModulo;
    }

    @Override
    public Modulo getModulo(Integer idModulo) {
        //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        Modulo entidad = null;
        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Obtener por medio del id llamamos a la Tabla usuario y que haga de parametro el idUsuario
            entidad = session.get(Modulo.class, idModulo);
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
    public Modulo update(Modulo elModulo) {

        //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Actualizamos los datos 
            session.update(elModulo);
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

        return elModulo;

    }

    @Override
    public boolean delete(Modulo elModulo) {
        //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();
        boolean flag = true;

        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //Actualizamos los datos 
            session.delete(elModulo);
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
    public List<Modulo> findbyCurso_media(int idCurso) {
        //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();

        List<Modulo> lista = null;
        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //crea la consulta Query
            //Query <Modulo> query = session.createSQLQuery("select * from Modulo mo where mo.idCurso=:c")  
            Query <Modulo> query = session.createSQLQuery("select Modulo.idModulo,Modulo.descripcion, Modulo.url, Modulo.idCuestionario, Modulo.titulo, Modulo.idCurso, Cuestionario.idCuestionario, Cuestionario.nombre from modulo INNER JOIN Cuestionario on Modulo.idCuestionario = Cuestionario.idCuestionario where idCurso=:c")
                    .addEntity(Modulo.class)
                    .setParameter("c", idCurso);
                    
            //Amacenamos los datos en la lista declarada anteriormente 
            lista = query.list();
//            System.out.println("+++++++++++++++");
//            System.out.println("LISTA: " + lista);
//            System.out.println(query);
//            System.out.println("+++++++++++++++");
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
        System.out.println(lista);
        return lista;
    }
    
    @Override
    public List<Modulo> findbyCurso(int idCurso) {
        //Obtener la secion 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Ocupamos la transaccion en caso de error la base de datos se restaura a como estaba
        Transaction transaccion = session.getTransaction();

        List<Modulo> lista = null;
        try {
            //Iniciamos Transaccion
            transaccion.begin();
            //crea la consulta Query
            Query <Modulo> query = session.createSQLQuery("select * from Modulo mo where mo.idCurso=:c")  
            //Query <Modulo> query = session.createSQLQuery("select Modulo.idModulo,Modulo.descripcion, Modulo.url, Modulo.idCuestionario, Modulo.titulo, Modulo.idCurso, Cuestionario.idCuestionario, Cuestionario.nombre from modulo INNER JOIN Cuestionario on Modulo.idCuestionario = Cuestionario.idCuestionario where idCurso=:c")
                    .addEntity(Modulo.class)
                    .setParameter("c", idCurso);
                    
            //Amacenamos los datos en la lista declarada anteriormente 
            lista = query.list();
            System.out.println("+++++++++++++++");
            System.out.println("LISTA: " + lista);
            System.out.println(query);
            System.out.println("+++++++++++++++");
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
        System.out.println(lista);
        return lista;
    }

    @Override
    public String updateidModulo(int idModulo, int idCuestionario) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        //int idCuestionario = 0;

        try {
            transaccion.begin();
            Query miQuery;
            miQuery = session.createSQLQuery("update Modulo set idCuestionario="+idCuestionario+ " where idModulo="+idModulo);
            int res = miQuery.executeUpdate();
            System.out.println("res: " + res);
            //idCuestionario = (Integer)miQuery.uniqueResult();
            //System.out.println("---------------------------idCuestionario: " + idCuestionario);
            //session.getTransaction().commit();
            transaccion.commit();
            System.out.println("tabla modulo actualizada correctamente");
        } catch (HibernateException e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
                System.out.println("error al update: " + e);
            }
        } finally {
            session.close();
        }
        return "ok";    
    }

}

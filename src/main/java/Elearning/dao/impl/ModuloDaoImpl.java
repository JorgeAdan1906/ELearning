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
            //System.out.println("LISTA: " + lista);
            //System.out.println(query);
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

    @Override
    public boolean delete(Modulo elModulo) {
        noCheckModulo();
        noCheckCuestionario();
        noCheckPregunta();
        CuestionarioDaoImpl cuestDao = new CuestionarioDaoImpl();
        System.out.println("EL modulo titulo: " + elModulo.getTitulo());
        System.out.println("EL modulo idCuest: " + elModulo.getIdCuestionario());
        //System.out.println("idCuest: " + elModulo.getIdCuestionario());
        //cuestDao.deleteMisCuestionarioByCuestionario(elModulo.getIdCuestionario());
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        boolean flag=true;
        try {
            System.out.println("entra al try de delete modulo");
            transaccion.begin();
            Query miQuery;
            miQuery = session.createSQLQuery("delete from Modulo where idModulo="+ elModulo.getIdModulo());
            int resp = miQuery.executeUpdate();
            transaccion.commit();

        } catch (HibernateException e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
            flag=false;
            
        } finally {
            session.close();
        }
        return true;
    }
    
    
    @Override
    public boolean noCheckModulo() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        boolean flag=true;
        try {
            System.out.println("nocheck modulo");
            transaccion.begin();
            Query miQuery;
            miQuery = session.createSQLQuery(" ALTER TABLE Modulo nocheck constraint FK__Modulo__idCuesti__5AEE82B9");
            int resp = miQuery.executeUpdate();
            transaccion.commit();

        } catch (HibernateException e) {
            System.out.println("Error nocheck modulo: " + e);
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
            flag=false;
            
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean noCheckCuestionario() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        boolean flag=true;
        try {
            System.out.println("No chceck Cuest");
            transaccion.begin();
            Query miQuery;
            miQuery = session.createSQLQuery("ALTER TABLE Cuestionario nocheck constraint FK_modulo");
            int resp = miQuery.executeUpdate();
            transaccion.commit();

        } catch (HibernateException e) {
            System.out.println("Error No check cuest: " + e);
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
            flag=false;
            
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean CheckModulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean CheckCuestionario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean noCheckPregunta() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        boolean flag=true;
        try {
            System.out.println("No chceck Cuest");
            transaccion.begin();
            Query miQuery;
            miQuery = session.createSQLQuery("ALTER TABLE Preguntas nocheck constraint FK_Cuestionario");
            int resp = miQuery.executeUpdate();
            transaccion.commit();

        } catch (HibernateException e) {
            System.out.println("Error No check preg: " + e);
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
            flag=false;
            
        } finally {
            session.close();
        }
        return true;}

    @Override
    public List<Integer> findIdsbyCurso(int idCurso) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaccion = session.getTransaction();
        List<Integer> lista = null;
        List<Modulo> listaM = null;
        try {
            transaccion.begin();
            Query <Integer> query = session.createSQLQuery("select idModulo from Modulo mo where mo.idCurso=:c") 
                    .setParameter("c", idCurso);
                    
            //Amacenamos los datos en la lista declarada anteriormente 
            lista = query.list();
            //System.out.println("LISTA: " + lista);
            //System.out.println(query);
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
        return lista;}

}

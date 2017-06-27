package repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.Consultor;
import model.Empresa;
import model.Usuario;
import util.JpaUtil;

public class ConsultorRepository implements RepositoryInterface<Consultor>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Consultor add(Consultor object) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = null;

		try {
			trx = manager.getTransaction();
			trx.begin();
			manager.persist(object);
			trx.commit();
		} catch (RuntimeException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return object;
	}
	
	public List<Empresa> getEmpresas(Integer id){
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		List<Empresa> list = null;
		
		try {
			trx = manager.getTransaction();
			trx.begin();

			TypedQuery<Empresa> query = manager.createQuery("from Empresa where consultor_id = ?", Empresa.class);
			query.setParameter(1,id);
			
			list = query.getResultList();
			trx.commit();

		} catch (PersistenceException ex) {
			if (trx != null) {
				trx.rollback();
			}
			Logger.getLogger("com").info("Exception: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			manager.close();
		}

		return list;
	}
	
	public Consultor getConsultorByName(String name) {
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;
		
		Consultor cons = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			TypedQuery<Consultor> query = manager.createQuery("from Consultor where nome = ?", Consultor.class);
			query.setParameter(1, name);
			cons = query.getSingleResult();
			
			trx.commit();
		} catch (PersistenceException ex) {
			if (trx != null) {
				trx.rollback();
			}
			cons = null;
		} finally {
			manager.close();
		}

		return cons;
	}

	@Override
	public List<Consultor> getAll() {
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		List<Consultor> list = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			TypedQuery<Consultor> query = manager.createQuery("from Consultor", Consultor.class);

			list = query.getResultList();
			trx.commit();

		} catch (PersistenceException ex) {
			if (trx != null) {
				trx.rollback();
			}
			Logger.getLogger("com").info("Exception: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			manager.close();
		}

		return list;
	}

	@Override
	public Consultor getById(Integer i) {
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		Consultor st = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			st = manager.find(Consultor.class, i);
			
			trx.commit();

		} catch (PersistenceException ex) {
			if (trx != null) {
				trx.rollback();
			}
			Logger.getLogger("com").info("Exception: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return st;
	}

	@Override
	public Consultor update(Consultor t) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = null;

		try {
			trx = manager.getTransaction();
			trx.begin();
			manager.merge(t);
			trx.commit();
		} catch (RuntimeException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return t;
	}

	@Override
	public Consultor remove(Integer i) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = null;
		
		Consultor cons = null;

		try {
			trx = manager.getTransaction();
			trx.begin();
			cons = manager.find(Consultor.class, i);
			manager.remove(cons);
			trx.commit();
		} catch (RuntimeException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return cons;
	}

}

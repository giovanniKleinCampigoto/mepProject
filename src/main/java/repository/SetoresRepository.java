package repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.Setores;

import util.JpaUtil;

public class SetoresRepository implements RepositoryInterface<Setores>, Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Override
	public Setores add(Setores object) {
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

	@Override
	public List<Setores> getAll() {
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		List<Setores> list = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			TypedQuery<Setores> query = manager.createQuery("from Setores", Setores.class);

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
	public Setores getById(Integer i) {
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		Setores st = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			st = manager.find(Setores.class, i);

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
	public Setores update(Setores t) {
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
	public Setores remove(Integer i) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = null;

		Setores setores = null;

		try {
			trx = manager.getTransaction();
			trx.begin();
			setores = manager.find(Setores.class, i);
			manager.remove(setores);
			trx.commit();
		} catch (RuntimeException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return setores;
	}
}

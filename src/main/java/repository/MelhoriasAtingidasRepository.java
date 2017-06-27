package repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.MelhoriasAtingidas;
import util.JpaUtil;

public class MelhoriasAtingidasRepository implements RepositoryInterface<MelhoriasAtingidas>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public MelhoriasAtingidas add(MelhoriasAtingidas object) {
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
	public List<MelhoriasAtingidas> getAll() {
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		List<MelhoriasAtingidas> list = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			TypedQuery<MelhoriasAtingidas> query = manager.createQuery("from MelhoriasAtingidas",
					MelhoriasAtingidas.class);

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
	public MelhoriasAtingidas getById(Integer i) {
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		MelhoriasAtingidas st = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			st = manager.find(MelhoriasAtingidas.class, i);

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
	public MelhoriasAtingidas update(MelhoriasAtingidas t) {
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
	public MelhoriasAtingidas remove(Integer i) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = null;

		MelhoriasAtingidas melhorias = null;

		try {
			trx = manager.getTransaction();
			trx.begin();
			melhorias = manager.find(MelhoriasAtingidas.class, i);
			manager.remove(melhorias);
			trx.commit();
		} catch (RuntimeException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return melhorias;
	}

}

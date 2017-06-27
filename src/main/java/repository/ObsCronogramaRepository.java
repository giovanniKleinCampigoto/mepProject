package repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.ObsCronograma;
import util.JpaUtil;

public class ObsCronogramaRepository implements RepositoryInterface<ObsCronograma>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ObsCronograma add(ObsCronograma object) {
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
	public List<ObsCronograma> getAll() {
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		List<ObsCronograma> list = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			TypedQuery<ObsCronograma> query = manager.createQuery("from ObsCronograma", ObsCronograma.class);

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
	public ObsCronograma getById(Integer i) {
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		ObsCronograma st = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			st = manager.find(ObsCronograma.class, i);

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
	public ObsCronograma update(ObsCronograma t) {
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
	public ObsCronograma remove(Integer i) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = null;

		ObsCronograma obs = null;

		try {
			trx = manager.getTransaction();
			trx.begin();
			obs = manager.find(ObsCronograma.class, i);
			manager.remove(obs);
			trx.commit();
		} catch (RuntimeException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return obs;
	}

}

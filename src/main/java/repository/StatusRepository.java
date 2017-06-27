package repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.Status;
import util.JpaUtil;

public class StatusRepository implements RepositoryInterface <Status>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Status add (Status status) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = null;

		try {
			trx = manager.getTransaction();
			trx.begin();
			manager.persist(status);
			trx.commit();
		} catch (RuntimeException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return status;
	}

	@Override
	public List<Status> getAll() {

		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		List<Status> list = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			TypedQuery<Status> query = manager.createQuery("from Status", Status.class);

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
	public Status getById(Integer i) {

		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		Status st = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			st = manager.find(Status.class, i);
			
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
	public Status update(Status status) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = null;

		try {
			trx = manager.getTransaction();
			trx.begin();
			manager.merge(status);
			trx.commit();
		} catch (RuntimeException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return status;
	}

	@Override
	public Status remove(Integer i) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = null;
		
		Status status = null;

		try {
			trx = manager.getTransaction();
			trx.begin();
			status = manager.find(Status.class, i);
			manager.remove(status);
			trx.commit();
		} catch (RuntimeException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return status;
	}

}

package repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Pessoa;
import util.JpaUtil;

public class PessoaRepository implements RepositoryInterface<Pessoa>, Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Override
	public Pessoa add(Pessoa object) {
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
	public List<Pessoa> getAll() {
		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		List<Pessoa> list = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			TypedQuery<Pessoa> query = manager.createQuery("from Pessoa", Pessoa.class);

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
	public Pessoa getById(Integer i) {

		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = null;

		Pessoa st = null;

		try {
			trx = manager.getTransaction();
			trx.begin();

			st = manager.find(Pessoa.class, i);

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
	public Pessoa update(Pessoa t) {
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
	public Pessoa remove(Integer i) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = null;

		Pessoa pessoa = null;

		try {
			trx = manager.getTransaction();
			trx.begin();
			pessoa = manager.find(Pessoa.class, i);
			manager.remove(pessoa);
			trx.commit();
		} catch (RuntimeException e) {
			if (trx != null) {
				trx.rollback();
			}
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return pessoa;
	}
}

package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import model.Status;
import repository.StatusRepository;

@ManagedBean(name = "statusService")
@ApplicationScoped
public class StatusService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Status st = new Status();

	public List<Status> buscar() {
		StatusRepository sd = new StatusRepository();
		return sd.getAll();
	}

	public void addStatus() {
		StatusRepository sd = new StatusRepository();

		if (st.getId() == 0) {
			sd.add(st);
			st = new Status();
		} else {
			sd.update(st);
			st = new Status();
		}
	}

	public Status deletar(Status status) {
		StatusRepository sd = new StatusRepository();
		return sd.remove(status.getId());
	}

	public Status getSt() {
		return st;
	}

	public void setSt(Status st) {
		this.st = st;
	}
}

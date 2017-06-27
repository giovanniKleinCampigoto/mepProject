package filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.LoginBean;
import model.Usuario;
import util.SessionUtil;

@WebFilter("*.jsf")
public class AutorizacaoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = ((HttpServletRequest) req).getSession();

		if (!(session.getAttribute("Administrador") != null) && !(session.getAttribute("Consultor") != null)
				&& !request.getRequestURI().endsWith("/login.jsf")
				&& !request.getRequestURI().contains("/javax.faces.resource/")) {
			response.sendRedirect(request.getContextPath() + "/login.jsf");
		} else {
			if ((session.getAttribute("Administrador") != null) && request.getRequestURI().endsWith("/consultor.jsf")
					&& !request.getRequestURI().contains("/javax.faces.resource/")) {
				response.sendRedirect(request.getContextPath() + "/cadastro.jsf");
			}
			if ((session.getAttribute("Consultor") != null) && request.getRequestURI().endsWith("/cadastro.jsf")
					&& !request.getRequestURI().contains("/javax.faces.resource/")) {
				response.sendRedirect(request.getContextPath() + "/consultor.jsf");
			}
			chain.doFilter(req, res);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

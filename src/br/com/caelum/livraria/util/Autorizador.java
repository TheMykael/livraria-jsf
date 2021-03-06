package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 9039601385787424895L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		String nomeDaPagina = context.getViewRoot().getViewId();
		System.out.println(nomeDaPagina);
		
		if ("/login.xhtml".equals(nomeDaPagina)) {
			return;
		}
		
		Usuario usuarioLogado = (Usuario) context.getExternalContext()
				.getSessionMap()
				.get("usuarioLogado");
		
		if (usuarioLogado != null) {
			return;
		}
		
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login?faces-redirect=true");
		context.getRenderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}

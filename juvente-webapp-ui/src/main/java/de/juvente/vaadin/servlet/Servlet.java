package de.juvente.vaadin.servlet;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import de.juvente.vaadin.ui.NavigatorUI;

@WebServlet(urlPatterns = "/*", name = "Servlet", asyncSupported = true)
@VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
public class Servlet extends VaadinServlet {
	private static final long serialVersionUID = 1L;
	
}

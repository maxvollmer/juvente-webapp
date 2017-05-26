package de.juvente.vaadin.ui;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.wolfie.history.HistoryExtension;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.SingleComponentContainerViewDisplay;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.juvente.vaadin.components.common.Footer;
import de.juvente.vaadin.components.common.Header;
import de.juvente.vaadin.components.common.NavigationBar;
import de.juvente.vaadin.views.ErrorView;
import de.juvente.vaadin.views.MainView;
import de.juvente.vaadin.views.StartView;

@SuppressWarnings("serial")
@Theme("juventetheme")
public class NavigatorUI extends UI {
	private static final String ERROR_WEB_TITLE = "Error";

	private final Map<Class<? extends View>, String> viewNames = new HashMap<>();
	private final Map<Class<? extends View>, String> viewWebtitles = new HashMap<>();
	private Navigator navigator;

	@Override
	protected void init(final VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		this.setContent(layout);

		final Panel content = new Panel();

		layout.addComponent(new Header());
		layout.addComponent(new NavigationBar(this));
		layout.addComponent(content);
		layout.addComponent(new Footer());

		initNavigator(content);

		addViews();
	}

	private void addViews()
	{
		addView("", "Start", new StartView(this));
		addView("main", "Main", new MainView(this));
	}

	private void initNavigator(final Panel content ) {
		final String contextPath = VaadinServlet.getCurrent().getServletContext().getContextPath();

		final HistoryExtension history = new HistoryExtension();
		history.extend(this);
		final Navigator navigator = new Navigator(this, history.createNavigationStateManager(contextPath), new SingleComponentContainerViewDisplay(content));

		navigator.addViewChangeListener(new ViewChangeListener() {
			@Override
			public boolean beforeViewChange(final ViewChangeEvent event)
			{
				setPageTitleForView(event.getNewView());
				return true;
			}
		});

		navigator.setErrorView(new ErrorView(this));

		this.navigator = navigator;
	}

	private void setPageTitleForView(final View view) {
		final String webTitle = viewWebtitles.get(view.getClass());
		if (webTitle == null) {
			getPage().setTitle("Juvente - " + ERROR_WEB_TITLE);
		}
		else if (StringUtils.isBlank(webTitle)) {
			getPage().setTitle("Juvente");
		}
		else {
			getPage().setTitle("Juvente - " + webTitle);
		}
	}

	private void addView(final String name, final String webTitle, final View view) {
		navigator.addView(name, view);
		viewNames.put(view.getClass(), name);
		viewWebtitles.put(view.getClass(), webTitle);
	}

	public void navigateTo(final Class<? extends View> viewClass) {
		navigator.navigateTo(viewNames.get(viewClass));
	}
}
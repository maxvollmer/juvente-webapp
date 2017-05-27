package de.juvente.vaadin.ui

import com.vaadin.ui.UI
import com.vaadin.server.VaadinRequest
import com.vaadin.annotations.Theme
import com.vaadin.spring.annotation.SpringUI
import org.springframework.beans.factory.annotation.Autowired
import de.juvente.i18n.I18N
import javax.servlet.annotation.WebServlet
import com.vaadin.spring.server.SpringVaadinServlet
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.ContextLoaderListener
import javax.servlet.annotation.WebListener
import com.vaadin.spring.annotation.EnableVaadin
import java.util.Locale
import com.vaadin.ui.Panel
import de.juvente.vaadin.components.common.NavigationBar
import com.vaadin.ui.VerticalLayout
import de.juvente.vaadin.components.common.Header
import de.juvente.vaadin.components.common.Footer
import de.juvente.vaadin.views.MainView
import de.juvente.vaadin.views.ErrorView
import com.vaadin.navigator.Navigator.SingleComponentContainerViewDisplay
import org.apache.commons.lang3.StringUtils
import com.vaadin.navigator.Navigator
import com.github.wolfie.history.HistoryExtension
import com.vaadin.server.VaadinServlet
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import de.juvente.vaadin.views.StartView
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.navigator.View
import java.util.HashMap

@SpringUI(path = "/*")
@Theme("juventetheme")
class JuventeUI extends UI {
	private static final String ERROR_WEB_TITLE = "Error";

	private final val viewNames = new HashMap<Class<? extends View>, String>();
	private final val viewWebtitles = new HashMap<Class<? extends View>, String>();
	private Navigator navigator;

	@Autowired
	private I18N i18n;

	@WebServlet(urlPatterns = "/*", name = "NavigatorUIServlet", asyncSupported = true)
	public static class NavigatorUIServlet extends SpringVaadinServlet {
	}

	@WebListener
	public static class MyContextLoaderListener extends ContextLoaderListener {}

	@Configuration
	@EnableVaadin
	public static class MyConfiguration {
	}

	def static JuventeUI getCurrent() {
		val currentUI = com.vaadin.ui.UI.current
		if (currentUI instanceof JuventeUI) currentUI else null
	}

	override public void setLocale(Locale locale)
	{
		super.setLocale(locale);
		i18n.updateI18N();
	}

	override protected init(VaadinRequest request) {
		i18n.setUI(this);

		val layout = new VerticalLayout();
		layout.styleName = "juvente-container";
		setContent(layout);

		val content = new Panel();

		layout.addComponents(
			new Header(),
			new NavigationBar(),
			content,
			new Footer()
		);

		initNavigator(content);
		addViews();
	}


	private def addViews()
	{
		addView("", "Start", new StartView());
		addView("main", "Main", new MainView());
	}

	private def initNavigator(Panel content) {
		val contextPath = VaadinServlet?.current?.servletContext?.contextPath;

		val history = new HistoryExtension();
		history.extend(this);
		val navigator = new Navigator(this, history.createNavigationStateManager(contextPath), new SingleComponentContainerViewDisplay(content));

		navigator.addViewChangeListener(new ViewChangeListener() {
			override beforeViewChange(ViewChangeEvent event)
			{
				setPageTitleForView(event.getNewView());
				return true;
			}
			override afterViewChange(ViewChangeEvent event)
			{
				setPageTitleForView(event.getNewView());
				i18n.updateI18N();
			}
		});

		navigator.setErrorView(new ErrorView());

		this.navigator = navigator;
	}

	private def setPageTitleForView(View view) {
		val webTitle = viewWebtitles.get(view.getClass());
		if (webTitle === null) {
			getPage().setTitle("Juvente - " + ERROR_WEB_TITLE);
		}
		else if (StringUtils.isBlank(webTitle)) {
			getPage().setTitle("Juvente");
		}
		else {
			getPage().setTitle("Juvente - " + webTitle);
		}
	}

	private def addView(String name, String webTitle, View view) {
		navigator.addView(name, view);
		viewNames.put(view.getClass(), name);
		viewWebtitles.put(view.getClass(), webTitle);
	}

	static def navigateTo(Class<? extends View> viewClazz) {
		getCurrent()?.internalNavigateTo(viewClazz);
	}

	private def internalNavigateTo(Class<? extends View> viewClazz) {
		navigator.navigateTo(viewNames.get(viewClazz));
	}
}


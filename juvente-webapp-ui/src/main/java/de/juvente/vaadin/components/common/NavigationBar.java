package de.juvente.vaadin.components.common;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

import de.juvente.vaadin.ui.NavigatorUI;
import de.juvente.vaadin.views.MainView;
import de.juvente.vaadin.views.StartView;

@SuppressWarnings("serial")
public class NavigationBar extends HorizontalLayout {

	private final NavigatorUI navigatorUI;

	public NavigationBar(final NavigatorUI navigatorUI) {
		this.navigatorUI = navigatorUI;
		this.setStyleName("navbar");
        this.addComponents(createLink(StartView.class, "Start"));
        this.addComponents(createLink(MainView.class, "Main"));
	}

	private Component createLink(final Class<? extends View> viewClazz, final String displayName) {
        final Button button = new Button(displayName, new Button.ClickListener() {
			@Override
            public void buttonClick(final ClickEvent event) {
				navigatorUI.navigateTo(viewClazz);
            }
        });
        button.setStyleName("navlink");
		return button;
	}
}

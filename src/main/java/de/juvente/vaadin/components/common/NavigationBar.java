package de.juvente.vaadin.components.common;

import java.util.Locale;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

import de.juvente.i18n.texts.Texts;
import de.juvente.vaadin.ui.NavigatorUI;
import de.juvente.vaadin.views.MainView;
import de.juvente.vaadin.views.StartView;

@SuppressWarnings("serial")
public class NavigationBar extends HorizontalLayout {

	private final NavigatorUI navigatorUI;

	public NavigationBar(final NavigatorUI navigatorUI) {
		this.navigatorUI = navigatorUI;
		this.setStyleName("navbar");
        this.addComponents(createLink(StartView.class, Texts.NAVBAR_BUTTON_TEST));
        this.addComponents(createLink(MainView.class, "Main"));

        final Button button = new Button("LOLÜBERSETZ", new Button.ClickListener() {
			@Override
            public void buttonClick(final ClickEvent event) {
				navigatorUI.setLocale(Math.random()>0.5?Locale.ENGLISH:Locale.GERMAN);
            }
        });
        button.setStyleName("navlink");
        this.addComponents(button);
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

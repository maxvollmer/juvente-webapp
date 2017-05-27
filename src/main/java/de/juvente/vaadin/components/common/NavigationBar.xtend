package de.juvente.vaadin.components.common

import de.juvente.vaadin.views.MainView
import com.vaadin.ui.Button
import de.juvente.i18n.texts.Texts
import java.util.Locale
import de.juvente.vaadin.ui.JuventeUI
import de.juvente.vaadin.views.StartView
import com.vaadin.navigator.View
import com.vaadin.ui.HorizontalLayout

class NavigationBar extends HorizontalLayout {

	new() {
		styleName = "navbar";

		addComponents(
			createLink(StartView, Texts.NAVBAR_BUTTON_TEST),
			createLink(MainView, "Main"),
			createTemporaryLanguageTestButton
		);
	}

	def createTemporaryLanguageTestButton() {
		val button = new Button("LOLÜBERSETZ", [JuventeUI.getCurrent().setLocale(if (Math.random()>0.5) Locale.ENGLISH else Locale.GERMAN)]);
		button.styleName ="navlink";
		return button;
	}

	private def createLink(Class<? extends View> viewClazz, String displayName) {
		val button = new Button(displayName, [JuventeUI.navigateTo(viewClazz)]);
		button.styleName ="navlink";
		return button;
	}
}

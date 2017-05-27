package de.juvente.vaadin.components.common

import com.vaadin.ui.Panel
import de.juvente.vaadin.views.MainView
import org.apache.commons.lang3.StringUtils

class Header extends Panel {
	new() {
		styleName = "header";
		val button = new ButtonLink(StringUtils.EMPTY, MainView);
		button.styleName = "header-link";
		setContent(button);
	}
}

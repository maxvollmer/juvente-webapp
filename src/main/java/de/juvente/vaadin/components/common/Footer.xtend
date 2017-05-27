package de.juvente.vaadin.components.common

import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.TextField

class Footer extends VerticalLayout {
	
	new() {
		val name = new TextField();
		name.caption = "footer";
		addComponent(name);
	}

}
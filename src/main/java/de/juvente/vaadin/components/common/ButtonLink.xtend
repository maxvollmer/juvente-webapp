package de.juvente.vaadin.components.common

import com.vaadin.ui.Button
import com.vaadin.navigator.View
import de.juvente.vaadin.ui.JuventeUI

class ButtonLink extends Button {
	
	new(String label, Class<? extends View> viewClazz) {
		super(label, [JuventeUI.navigateTo(viewClazz)]);
	}
	
}

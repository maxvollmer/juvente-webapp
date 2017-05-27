package de.juvente.vaadin.components.common;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Footer extends VerticalLayout
{
	public Footer() {
        final TextField name = new TextField();
        name.setCaption("footer");
        this.addComponents(name);
	}
}

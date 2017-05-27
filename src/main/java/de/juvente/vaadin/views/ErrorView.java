package de.juvente.vaadin.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.juvente.vaadin.ui.NavigatorUI;

public class ErrorView extends VerticalLayout implements View
{
	private static final long serialVersionUID = 1L;

	public ErrorView(final NavigatorUI navigatorUI) {
        final TextField name = new TextField();
        name.setCaption("error");
        this.addComponents(name);
	}

	@Override
	public void enter(final ViewChangeEvent event)
	{
		
	}
}

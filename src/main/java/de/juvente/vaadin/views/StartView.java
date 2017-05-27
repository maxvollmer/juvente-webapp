package de.juvente.vaadin.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import de.juvente.vaadin.components.common.ButtonLink;

public class StartView extends VerticalLayout implements View
{
	private static final long serialVersionUID = 1L;

    public StartView() {
        setSizeFull();

        final ButtonLink button = new ButtonLink("Go to Main View", MainView.class);

        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        Notification.show("Welcome to the Animal Farm");
    }
}

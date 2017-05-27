package de.juvente.vaadin.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import de.juvente.vaadin.ui.NavigatorUI;

public class StartView extends VerticalLayout implements View
{
	private static final long serialVersionUID = 1L;

	private static final String NAME = "";

    public StartView(final NavigatorUI navigatorUI) {
        setSizeFull();

        final Button button = new Button("Go to Main View", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
            public void buttonClick(final ClickEvent event) {
				navigatorUI.navigateTo(MainView.class);
            }
        });

        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        Notification.show("Welcome to the Animal Farm");
    }
}

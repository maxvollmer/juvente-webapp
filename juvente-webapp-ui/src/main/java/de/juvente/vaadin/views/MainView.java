package de.juvente.vaadin.views;

import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.juvente.Person;
import de.juvente.backend.CrudService;
import de.juvente.vaadin.ui.NavigatorUI;

public class MainView extends VerticalLayout implements View
{
	private static final long serialVersionUID = 1L;

    private final CrudService<Person> service = new CrudService<>();
    private final DataProvider<Person, String> dataProvider = new CallbackDataProvider<>(
                    query -> service.findAll().stream(),
                    query -> service.findAll().size());

	public MainView(final NavigatorUI navigatorUI) {
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        final Button button = new Button("Click Me");
        button.addClickListener(e -> {
            service.save(new Person(name.getValue()));
            dataProvider.refreshAll();
        });

        final Grid<Person> grid = new Grid<>();
        grid.addColumn(Person::getName).setCaption("Name");
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        this.addComponents(name, button, grid);
        this.setSizeFull();
        this.setExpandRatio(grid, 1.0f);
	}

	@Override
	public void enter(final ViewChangeEvent event)
	{
		
	}
}

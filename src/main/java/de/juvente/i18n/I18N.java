package de.juvente.i18n;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.HasComponents.ComponentAttachDetachNotifier;
import com.vaadin.ui.HasComponents.ComponentAttachEvent;
import com.vaadin.ui.HasComponents.ComponentAttachListener;
import com.vaadin.ui.UI;

import de.juvente.backend.data.Text;
import de.juvente.backend.repositories.TextRepository;
import de.juvente.i18n.texts.Texts;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
public class I18N implements ComponentAttachListener
{
	private final Set<ComponentAttachDetachNotifier> componentsWithListener = new HashSet<>();
	private final Map<Component, String> originalCaptions = new HashMap<>();

	@Autowired
	private TextRepository repository;

	private UI ui;

	public void setUI(final UI ui)
	{
		repository.save(new Text(Texts.NAVBAR_BUTTON_TEST, "TESTDE", "TESTEN"));
		this.ui = ui;
		addComponentAttachListenerRecursively(ui);
	}

	@Override
	public void componentAttachedToContainer(final ComponentAttachEvent event)
	{
		final Component attachedComponent = event.getAttachedComponent();
		addComponentAttachListenerRecursively(attachedComponent);
		updateI18N(attachedComponent);
	}

	private void addComponentAttachListenerRecursively(final Component component) {
		if (component instanceof ComponentAttachDetachNotifier && !componentsWithListener.contains(component)) {
			((ComponentAttachDetachNotifier)component).addComponentAttachListener(this);
			componentsWithListener.add((ComponentAttachDetachNotifier)component);
		}
		if (component instanceof HasComponents) {
			for (final Component child : (HasComponents)component) {
				addComponentAttachListenerRecursively(child);
			}
		}
	}

	public void updateI18N()
	{
		updateI18N(ui);
	}

	private void updateI18N(final Component component)
	{
		translate(component);
		if (component instanceof HasComponents) {
			for (final Component child : (HasComponents)component) {
				updateI18N(child);
			}
		}
	}

	private void translate(final Component component)
	{
		final String originalCaption = getOriginalCaption(component);
		if (!StringUtils.isBlank(originalCaption)) {
			try {
				component.setCaption(translate(originalCaption));
			}
			catch(final UnsupportedOperationException e) {/*swallow*/}
		}
	}

	private String getOriginalCaption(final Component component)
	{
		if (!originalCaptions.containsKey(component)) {
			originalCaptions.put(component, component.getCaption());
		}
		return originalCaptions.get(component);
	}

	private String translate(final String key)
	{
		final Text text = repository.findByKey(key);
		if (text != null) {
			if (Locale.ENGLISH.getLanguage().equals(ui.getLocale().getLanguage())) {
				return text.getEn();
			}
			else {
				return text.getDe();
			}
		}
		return key;
	}
}

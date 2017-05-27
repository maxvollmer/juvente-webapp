package de.juvente.vaadin.i18n;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

import de.juvente.backend.data.Text;
import de.juvente.backend.repositories.TextRepository;

@SuppressWarnings("serial")
@SpringUI
public abstract class I18NUI extends UI
{
	private final Set<ComponentAttachDetachNotifier> componentsWithListener = new HashSet<>();
	private final Map<Component, String> originalCaptions = new HashMap<>();

	private final ComponentAttachListener myComponentAttachListener = new ComponentAttachListener() {
		@Override
		public void componentAttachedToContainer(final ComponentAttachEvent event)
		{
			final Component attachedComponent = event.getAttachedComponent();
			addComponentAttachListenerRecursively(attachedComponent);
			updateI18N(attachedComponent);
		}
	};

	private void addComponentAttachListenerRecursively(final Component component) {
		if (component instanceof ComponentAttachDetachNotifier && !componentsWithListener.contains(component)) {
			((ComponentAttachDetachNotifier)component).addComponentAttachListener(myComponentAttachListener);
			componentsWithListener.add((ComponentAttachDetachNotifier)component);
		}
		if (component instanceof HasComponents) {
			for (final Component child : (HasComponents)component) {
				addComponentAttachListenerRecursively(child);
			}
		}
	}

	@Override
	protected final void init(final VaadinRequest request)
	{
		this.addComponentAttachListener(myComponentAttachListener);
		initialize(request);
	}

	protected abstract void initialize(VaadinRequest request);

	@Override
	public void setLocale(final Locale locale)
	{
		super.setLocale(locale);
		updateI18N();
	}

	protected void updateI18N()
	{
		updateI18N(this);
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

	@Autowired
	private TextRepository repository;

	private String translate(final String key)
	{
		final Text text = repository.findByKey(key);
		if (text != null) {
			if (Locale.ENGLISH.getLanguage().equals(getLocale().getLanguage())) {
				return text.getEn();
			}
			else {
				return text.getDe();
			}
		}
		return key;
	}
}

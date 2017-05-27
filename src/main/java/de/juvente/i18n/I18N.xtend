package de.juvente.i18n

import com.vaadin.ui.HasComponents.ComponentAttachListener
import com.vaadin.ui.HasComponents.ComponentAttachEvent
import java.util.HashSet
import com.vaadin.ui.HasComponents.ComponentAttachDetachNotifier
import com.vaadin.ui.UI
import org.springframework.beans.factory.annotation.Autowired
import java.util.HashMap
import de.juvente.backend.repositories.TextRepository
import com.vaadin.ui.Component
import de.juvente.backend.data.Text
import de.juvente.i18n.texts.Texts
import com.vaadin.ui.HasComponents
import java.util.Locale

import static extension org.apache.commons.lang3.StringUtils.isBlank

@org.springframework.stereotype.Component
class I18N implements ComponentAttachListener {

	private final val componentsWithListener = new HashSet<ComponentAttachDetachNotifier>();
	private final val originalCaptions = new HashMap<Component, String>();

	@Autowired
	private TextRepository repository;

	private UI ui;

	def setUI(UI ui)
	{
		repository.save(new Text(Texts.NAVBAR_BUTTON_TEST, "TESTDE", "TESTEN"));
		this.ui = ui;
		addComponentAttachListenerRecursively(ui);
	}

	override componentAttachedToContainer(ComponentAttachEvent event) {
		event.attachedComponent.addComponentAttachListenerRecursively
		event.attachedComponent.updateI18N
	}

	private def void addComponentAttachListenerRecursively(Component component) {
		if (component instanceof ComponentAttachDetachNotifier) {
			if (!componentsWithListener.contains(component)) {
				component.addComponentAttachListener(this)
				componentsWithListener.add(component)
			}
		}
		if (component instanceof HasComponents) {
			for (Component child : component) {
				child.addComponentAttachListenerRecursively
			}
		}
	}

	def updateI18N()
	{
		ui.updateI18N;
	}

	private def void updateI18N(Component component)
	{
		component.translate
		if (component instanceof HasComponents) {
			for (Component child : component) {
				child.updateI18N;
			}
		}
	}

	private def translate(Component component)
	{
		if (!component.originalCaption.isBlank) {
			try {
				component.caption = component.originalCaption.translate
			}
			catch(UnsupportedOperationException e) {/*swallow*/}
		}
	}

	private def getOriginalCaption(Component component)
	{
		if (!originalCaptions.containsKey(component)) {
			originalCaptions.put(component, component.caption)
		}
		return originalCaptions.get(component)
	}

	private def translate(String key)
	{
		val text = repository.findByKey(key)
		if (text !== null) {
			if (Locale.ENGLISH.language == ui.locale.language) {
				return text.en
			}
			else {
				return text.de
			}
		}
		return key
	}
}
package org.apache.jmeter.protocol.http.modifier.gui;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.apache.jmeter.config.gui.AbstractResponseBasedModifierGui;
import org.apache.jmeter.protocol.http.modifier.URLRewritingModifier;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.gui.JLabeledTextField;
import org.apache.jorphan.gui.layout.VerticalLayout;

/**
 * @author mstover
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 */
public class URLRewritingModifierGui extends AbstractResponseBasedModifierGui {
	
	JLabeledTextField argumentName;
	JCheckBox pathExt;
	private final static String title = JMeterUtils.getResString("http_url_rewriting_modifier_title");

	/**
	 * @see JMeterGUIComponent#getStaticLabel()
	 */
	public String getStaticLabel() {
		return title;
	}
	
	public URLRewritingModifierGui()
	{
		init();
	}
	
	private void init()
	{
		this.setLayout(new VerticalLayout(5, VerticalLayout.LEFT, VerticalLayout.TOP));

		// MAIN PANEL
		JPanel mainPanel = new JPanel();
		Border margin = new EmptyBorder(10, 10, 5, 10);
		mainPanel.setBorder(margin);
		mainPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));


		// NAME
		mainPanel.add(makeTitlePanel());
		argumentName = new JLabeledTextField(JMeterUtils.getResString("session_argument_name"));
		mainPanel.add(argumentName);
		pathExt = new JCheckBox(JMeterUtils.getResString("Path_Extension_choice"));
		
		mainPanel.add(pathExt);	

		this.add(mainPanel);
	}

	/**
	 * @see JMeterGUIComponent#createTestElement()
	 */
	public TestElement createTestElement() {
		URLRewritingModifier modifier = new URLRewritingModifier();
		modifyTestElement(modifier);
		return modifier;
	}

    /**
     * Modifies a given TestElement to mirror the data in the gui components.
     * @see org.apache.jmeter.gui.JMeterGUIComponent#modifyTestElement(TestElement)
     */
    public void modifyTestElement(TestElement modifier)
    {
        this.configureTestElement(modifier);
        ((URLRewritingModifier)modifier).setArgumentName(argumentName.getText());
        ((URLRewritingModifier)modifier).setPathExtension(pathExt.isSelected());
    }
	
	public void configure(TestElement el)
	{
		argumentName.setText(((URLRewritingModifier)el).getArgumentName());
		pathExt.setSelected(((URLRewritingModifier)el).isPathExtension());
		super.configure(el);
	}

}

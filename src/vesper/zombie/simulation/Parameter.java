package vesper.zombie.simulation;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import net.miginfocom.swing.MigLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Parameter extends JPanel {
	private int value;
	private JSlider slider;
	private JTextField textField;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Parameter(String name, int startValue, int min, int max) {
		this.value = startValue;
		setLayout(new MigLayout("", "[160px][32px][200px]", "[15px]"));
		
		JLabel lblParameterName = new JLabel(name);
		add(lblParameterName, "cell 0 0,growx");
		
		this.textField = new JTextField();
		textField.setText(Integer.toString(startValue));
		add(textField, "cell 1 0,growx,aligny top");
		textField.setColumns(10);
		
		this.slider = new JSlider();
		slider.setMinimum(min);
		slider.setMaximum(max);
		slider.setValue(startValue);
		add(slider, "cell 2 0,alignx left,aligny top");
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setValue(Integer.valueOf(textField.getText()));
			}
		});
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				setValue(slider.getValue());
			}
		});

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		slider.setValue(getValue());
		textField.setText(Integer.toString(getValue()));
	}
}

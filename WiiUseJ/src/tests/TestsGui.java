package tests;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.BoxLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.ComponentOrientation;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

import wiiusej.WiimoteListener;

public class TestsGui extends JFrame implements WiimoteListener {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="194,71"
	private JPanel jMainPanel = null;
	private JTextArea ButtonsEventsTextArea = null;
	private JPanel jRightPanel = null;
	private JPanel jTopLeftPanel = null;
	private JPanel jBottomLeftPanel = null;
	private JButton jRumbleButton = null;
	private JButton jIRButton = null;
	private JButton jAcceleroButton = null;
	private JButton jSmoothingButton = null;
	private JButton jContinuousButton = null;
	private JButton jStatusButton = null;
	private JPanel jLedsButtonsPanel = null;
	private JButton jLed1Button = null;
	private JButton jLed4Button = null;
	private JButton jLed2Button = null;
	private JButton jLed3Button = null;
	private JPanel jButtonsPanel = null;
	private JPanel jStatusButtonPanel = null;
	private JPanel jRumbleButtonPanel = null;
	private JPanel jIRButtonPanel = null;
	private JPanel jAccButtonPanel = null;
	private JPanel jSmoothingButtonPanel = null;
	private JPanel jContinuousButtonPanel = null;
	private JPanel jThresHoldPanel = null;
	private JButton jThresholdButton = null;
	private JTextField jThresholdTextField = null;
	private JPanel jLeftPanel = null;
	private JPanel jThresholTextPanel = null;
	private JPanel jStatusDisplayPanel = null;
	/**
	 * This is the default constructor
	 */
	public TestsGui() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 600);
		this.setContentPane(getJMainPanel());
		this.setMaximumSize(new Dimension(800, 600));
		this.setMinimumSize(new Dimension(800, 600));
		this.setPreferredSize(new Dimension(800, 600));
		this.setTitle("WiiUseJ TestsGUI");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
		}
		return jContentPane;
	}

	/**
	 * This method initializes jMainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJMainPanel() {
		if (jMainPanel == null) {
			jMainPanel = new JPanel();
			jMainPanel.setLayout(new BoxLayout(getJMainPanel(), BoxLayout.X_AXIS));
			jMainPanel.add(getJLeftPanel(), null);			
			jMainPanel.add(getJRightPanel(), null);
		}
		return jMainPanel;
	}

	/**
	 * This method initializes ButtonsEventsTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getButtonsEventsTextArea() {
		if (ButtonsEventsTextArea == null) {
			ButtonsEventsTextArea = new JTextArea();
			ButtonsEventsTextArea.setPreferredSize(new Dimension(16, 16));
			ButtonsEventsTextArea.setText("Buttons Events :");
		}
		return ButtonsEventsTextArea;
	}

	/**
	 * This method initializes jRightPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJRightPanel() {
		if (jRightPanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = -1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = -1;
			jRightPanel = new JPanel();
			jRightPanel.setLayout(new BoxLayout(getJRightPanel(), BoxLayout.Y_AXIS));
			jRightPanel.setPreferredSize(new Dimension(200, 600));
			jRightPanel.add(getJButtonsPanel(), null);
			jRightPanel.add(getButtonsEventsTextArea(), null);
		}
		return jRightPanel;
	}

	/**
	 * This method initializes jTopLeftPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJTopLeftPanel() {
		if (jTopLeftPanel == null) {
			jTopLeftPanel = new JPanel();
			jTopLeftPanel.setLayout(new GridBagLayout());
			jTopLeftPanel.setPreferredSize(new Dimension(600, 300));
			jTopLeftPanel.setBackground(new Color(238, 211, 238));
		}
		return jTopLeftPanel;
	}

	/**
	 * This method initializes jBottomLeftPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJBottomLeftPanel() {
		if (jBottomLeftPanel == null) {
			jBottomLeftPanel = new JPanel();
			jBottomLeftPanel.setLayout(new GridBagLayout());
			jBottomLeftPanel.setPreferredSize(new Dimension(600, 300));
			jBottomLeftPanel.setBackground(new Color(122, 154, 149));
		}
		return jBottomLeftPanel;
	}

	/**
	 * This method initializes jRumbleButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJRumbleButton() {
		if (jRumbleButton == null) {
			jRumbleButton = new JButton();
			jRumbleButton.setText("Rumble");
			jRumbleButton.setHorizontalAlignment(SwingConstants.CENTER);
			jRumbleButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			jRumbleButton.setName("jRumbleButton");
			jRumbleButton.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return jRumbleButton;
	}

	/**
	 * This method initializes jIRButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJIRButton() {
		if (jIRButton == null) {
			jIRButton = new JButton();
			jIRButton.setText("Infrared");
			jIRButton.setHorizontalAlignment(SwingConstants.CENTER);
			jIRButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			jIRButton.setName("jIRButton");
			jIRButton.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return jIRButton;
	}

	/**
	 * This method initializes jAcceleroButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJAcceleroButton() {
		if (jAcceleroButton == null) {
			jAcceleroButton = new JButton();
			jAcceleroButton.setText("Accelerometer");
			jAcceleroButton.setHorizontalAlignment(SwingConstants.CENTER);
			jAcceleroButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			jAcceleroButton.setName("jAcceleroButton");
			jAcceleroButton.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return jAcceleroButton;
	}

	/**
	 * This method initializes jSmoothingButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJSmoothingButton() {
		if (jSmoothingButton == null) {
			jSmoothingButton = new JButton();
			jSmoothingButton.setText("Smoothing");
			jSmoothingButton.setHorizontalAlignment(SwingConstants.CENTER);
			jSmoothingButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			jSmoothingButton.setName("jSmoothingButton");
			jSmoothingButton.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return jSmoothingButton;
	}

	/**
	 * This method initializes jContinuousButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJContinuousButton() {
		if (jContinuousButton == null) {
			jContinuousButton = new JButton();
			jContinuousButton.setText("Continuous");
			jContinuousButton.setHorizontalAlignment(SwingConstants.CENTER);
			jContinuousButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			jContinuousButton.setName("jContinuousButton");
			jContinuousButton.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return jContinuousButton;
	}

	/**
	 * This method initializes jStatusButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJStatusButton() {
		if (jStatusButton == null) {
			jStatusButton = new JButton();
			jStatusButton.setText("Status");
			jStatusButton.setHorizontalAlignment(SwingConstants.CENTER);
			jStatusButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			jStatusButton.setName("jStatusButton");
			jStatusButton.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return jStatusButton;
	}

	/**
	 * This method initializes jLedsButtonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJLedsButtonsPanel() {
		if (jLedsButtonsPanel == null) {
			jLedsButtonsPanel = new JPanel();
			jLedsButtonsPanel.setLayout(new BoxLayout(getJLedsButtonsPanel(), BoxLayout.X_AXIS));
			jLedsButtonsPanel.setPreferredSize(new Dimension(150, 26));
			jLedsButtonsPanel.add(getJLed1Button(), null);
			jLedsButtonsPanel.add(getJLed2Button(), null);
			jLedsButtonsPanel.add(getJLed3Button(), null);
			jLedsButtonsPanel.add(getJLed4Button(), null);
		}
		return jLedsButtonsPanel;
	}

	/**
	 * This method initializes jLed1Button	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJLed1Button() {
		if (jLed1Button == null) {
			jLed1Button = new JButton();
			jLed1Button.setText("1");
			jLed1Button.setPreferredSize(new Dimension(50, 26));
			jLed1Button.setHorizontalTextPosition(SwingConstants.CENTER);
			jLed1Button.setName("jLed1Button");
			jLed1Button.setMnemonic(KeyEvent.VK_UNDEFINED);
		}
		return jLed1Button;
	}

	/**
	 * This method initializes jLed4Button	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJLed4Button() {
		if (jLed4Button == null) {
			jLed4Button = new JButton();
			jLed4Button.setText("4");
			jLed4Button.setMnemonic(KeyEvent.VK_UNDEFINED);
			jLed4Button.setHorizontalTextPosition(SwingConstants.CENTER);
			jLed4Button.setName("jLed4Button");
			jLed4Button.setPreferredSize(new Dimension(50, 26));
		}
		return jLed4Button;
	}

	/**
	 * This method initializes jLed2Button	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJLed2Button() {
		if (jLed2Button == null) {
			jLed2Button = new JButton();
			jLed2Button.setText("2");
			jLed2Button.setMnemonic(KeyEvent.VK_UNDEFINED);
			jLed2Button.setHorizontalTextPosition(SwingConstants.CENTER);
			jLed2Button.setName("jLed2Button");
			jLed2Button.setPreferredSize(new Dimension(50, 26));
		}
		return jLed2Button;
	}

	/**
	 * This method initializes jLed3Button	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJLed3Button() {
		if (jLed3Button == null) {
			jLed3Button = new JButton();
			jLed3Button.setText("3");
			jLed3Button.setMnemonic(KeyEvent.VK_UNDEFINED);
			jLed3Button.setHorizontalTextPosition(SwingConstants.CENTER);
			jLed3Button.setName("jLed3Button");
			jLed3Button.setPreferredSize(new Dimension(50, 26));
		}
		return jLed3Button;
	}

	/**
	 * This method initializes jButtonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJButtonsPanel() {
		if (jButtonsPanel == null) {
			jButtonsPanel = new JPanel();
			jButtonsPanel.setLayout(new BoxLayout(getJButtonsPanel(), BoxLayout.Y_AXIS));
			jButtonsPanel.setComponentOrientation(ComponentOrientation.UNKNOWN);
			jButtonsPanel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jButtonsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jButtonsPanel.add(getJStatusButtonPanel(), null);
			jButtonsPanel.add(getJStatusDisplayPanel(), null);
			jButtonsPanel.add(getJRumbleButtonPanel(), null);
			jButtonsPanel.add(getJIRButtonPanel(), null);
			jButtonsPanel.add(getJAccButtonPanel(), null);
			jButtonsPanel.add(getJThresHoldPanel(), null);
			jButtonsPanel.add(getJSmoothingButtonPanel(), null);
			jButtonsPanel.add(getJContinuousButtonPanel(), null);
			jButtonsPanel.add(getJLedsButtonsPanel(), null);
		}
		return jButtonsPanel;
	}

	/**
	 * This method initializes jStatusButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJStatusButtonPanel() {
		if (jStatusButtonPanel == null) {
			jStatusButtonPanel = new JPanel();
			jStatusButtonPanel.setLayout(new BoxLayout(getJStatusButtonPanel(), BoxLayout.X_AXIS));
			jStatusButtonPanel.setPreferredSize(new Dimension(150, 26));
			jStatusButtonPanel.add(getJStatusButton(), null);
		}
		return jStatusButtonPanel;
	}

	/**
	 * This method initializes jRumbleButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJRumbleButtonPanel() {
		if (jRumbleButtonPanel == null) {
			jRumbleButtonPanel = new JPanel();
			jRumbleButtonPanel.setLayout(new BoxLayout(getJRumbleButtonPanel(), BoxLayout.X_AXIS));
			jRumbleButtonPanel.add(getJRumbleButton(), null);
		}
		return jRumbleButtonPanel;
	}

	/**
	 * This method initializes jIRButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJIRButtonPanel() {
		if (jIRButtonPanel == null) {
			jIRButtonPanel = new JPanel();
			jIRButtonPanel.setLayout(new BoxLayout(getJIRButtonPanel(), BoxLayout.X_AXIS));
			jIRButtonPanel.setPreferredSize(new Dimension(150, 26));
			jIRButtonPanel.add(getJIRButton(), null);
		}
		return jIRButtonPanel;
	}

	/**
	 * This method initializes jAccButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJAccButtonPanel() {
		if (jAccButtonPanel == null) {
			jAccButtonPanel = new JPanel();
			jAccButtonPanel.setLayout(new BoxLayout(getJAccButtonPanel(), BoxLayout.X_AXIS));
			jAccButtonPanel.setPreferredSize(new Dimension(150, 26));
			jAccButtonPanel.add(getJAcceleroButton(), null);
		}
		return jAccButtonPanel;
	}

	/**
	 * This method initializes jSmoothingButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJSmoothingButtonPanel() {
		if (jSmoothingButtonPanel == null) {
			jSmoothingButtonPanel = new JPanel();
			jSmoothingButtonPanel.setLayout(new BoxLayout(getJSmoothingButtonPanel(), BoxLayout.X_AXIS));
			jSmoothingButtonPanel.setPreferredSize(new Dimension(150, 26));
			jSmoothingButtonPanel.add(getJSmoothingButton(), null);
		}
		return jSmoothingButtonPanel;
	}

	/**
	 * This method initializes jContinuousButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContinuousButtonPanel() {
		if (jContinuousButtonPanel == null) {
			jContinuousButtonPanel = new JPanel();
			jContinuousButtonPanel.setLayout(new BoxLayout(getJContinuousButtonPanel(), BoxLayout.X_AXIS));
			jContinuousButtonPanel.setPreferredSize(new Dimension(150, 26));
			jContinuousButtonPanel.add(getJContinuousButton(), null);
		}
		return jContinuousButtonPanel;
	}

	/**
	 * This method initializes jThresHoldPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJThresHoldPanel() {
		if (jThresHoldPanel == null) {
			jThresHoldPanel = new JPanel();
			jThresHoldPanel.setLayout(new BoxLayout(getJThresHoldPanel(), BoxLayout.X_AXIS));
			jThresHoldPanel.add(getJThresholdButton(), null);
			jThresHoldPanel.add(getJThresholTextPanel(), null);
		}
		return jThresHoldPanel;
	}

	/**
	 * This method initializes jThresholdButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJThresholdButton() {
		if (jThresholdButton == null) {
			jThresholdButton = new JButton();
			jThresholdButton.setHorizontalTextPosition(SwingConstants.CENTER);
			jThresholdButton.setText("Set");
			jThresholdButton.setMnemonic(KeyEvent.VK_UNDEFINED);
		}
		return jThresholdButton;
	}

	/**
	 * This method initializes jThresholdTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJThresholdTextField() {
		if (jThresholdTextField == null) {
			jThresholdTextField = new JTextField();
			jThresholdTextField.setPreferredSize(new Dimension(100, 20));
			jThresholdTextField.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return jThresholdTextField;
	}

	/**
	 * This method initializes jLeftPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJLeftPanel() {
		if (jLeftPanel == null) {
			jLeftPanel = new JPanel();
			jLeftPanel.setLayout(new BoxLayout(getJLeftPanel(), BoxLayout.Y_AXIS));
			jLeftPanel.add(getJTopLeftPanel(), null);
			jLeftPanel.add(getJBottomLeftPanel(), null);
		}
		return jLeftPanel;
	}

	/**
	 * This method initializes jThresholTextPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJThresholTextPanel() {
		if (jThresholTextPanel == null) {
			jThresholTextPanel = new JPanel();
			jThresholTextPanel.setLayout(new FlowLayout());
			jThresholTextPanel.add(getJThresholdTextField(), null);
		}
		return jThresholTextPanel;
	}

	/**
	 * This method initializes jStatusDisplayPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJStatusDisplayPanel() {
		if (jStatusDisplayPanel == null) {
			jStatusDisplayPanel = new JPanel();
			jStatusDisplayPanel.setLayout(new BoxLayout(getJStatusDisplayPanel(), BoxLayout.X_AXIS));
			jStatusDisplayPanel.setPreferredSize(new Dimension(200, 25));
		}
		return jStatusDisplayPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="190,-4"

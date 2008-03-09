/**
 * This file is part of WiiuseJ.
 *
 *  WiiuseJ is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  WiiuseJ is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with WiiuseJ.  If not, see <http://www.gnu.org/licenses/>.
 */
package wiiusej.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import wiiusej.utils.IRPanel;
import wiiusej.Wiimote;
import wiiusej.utils.AccelerationPanel;
import wiiusej.utils.GForcePanel;
import wiiusej.utils.ButtonsEventPanel;
import wiiusej.utils.OrientationPanel;
import wiiusej.wiiuseapievents.ButtonsEvent;
import wiiusej.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiuseapievents.IREvent;
import wiiusej.wiiuseapievents.MotionSensingEvent;
import wiiusej.wiiuseapievents.StatusEvent;
import wiiusej.wiiuseapievents.WiimoteListener;

/**
 * Gui class to test WiiuseJ.
 * @author  guiguito
 */
public class WiiuseJGuiTest extends javax.swing.JFrame implements WiimoteListener {

    private Wiimote wiimote;
    private Robot robot = null;
    private boolean statusMotionRequested = false;
    private boolean statusIRRequested = false;

    /** Creates new form WiiuseJGuiTest */
    public WiiuseJGuiTest(Wiimote wiimote) {
        initComponents();
        this.wiimote = wiimote;
        wiimote.addWiiMoteEventListeners((IRPanel) irViewPanel);
        wiimote.addWiiMoteEventListeners((ButtonsEventPanel) buttonsPanel);
        wiimote.addWiiMoteEventListeners((OrientationPanel) motionSensingPanel);
        wiimote.addWiiMoteEventListeners((GForcePanel) gForcePanel);
        wiimote.addWiiMoteEventListeners((AccelerationPanel) accelerationPanel);
        wiimote.addWiiMoteEventListeners(this);
        wiimote.deactivateContinuous();
        wiimote.deactivateSmoothing();
        wiimote.setScreenAspectRatio169();
        wiimote.setSensorBarBelowScreen();
        getStatusButtonMousePressed(null);
    }

    public void onButtonsEvent(ButtonsEvent arg0) {
        if (robot != null) {
            if (arg0.isButtonAPressed()) {
                robot.mousePress(InputEvent.BUTTON1_MASK);

            }
            if (arg0.isButtonBPressed()) {
                robot.mousePress(InputEvent.BUTTON2_MASK);

            }
            if (arg0.isButtonOnePressed()) {
                robot.mousePress(InputEvent.BUTTON3_MASK);

            }
            if (arg0.isButtonAJustReleased()) {
                robot.mouseRelease(InputEvent.BUTTON1_MASK);

            }
            if (arg0.isButtonBJustReleased()) {
                robot.mouseRelease(InputEvent.BUTTON2_MASK);

            }
            if (arg0.isButtonOneJustReleased()) {
                robot.mouseRelease(InputEvent.BUTTON3_MASK);

            }
            if (arg0.isButtonUpPressed()) {//mouse wheel up
                robot.mouseWheel(-1);
            }
            if (arg0.isButtonDownPressed()) {//mouse wheel down
                robot.mouseWheel(1);
            }
            
            if (arg0.isButtonTwoPressed()) {//stop mouse control
                mouseIRControlButtonMousePressed(null);
            }
        }
    }

    public void onIrEvent(IREvent arg0) {
        if (robot != null) {//if mouse control activated
            robot.mouseMove(arg0.getX(), arg0.getY());
        }
        if (statusIRRequested){
            xResolutionTextField.setText(""+arg0.getXVRes());
            yResolutionTextField.setText(""+arg0.getYVRes());
            statusIRRequested = false;
        }
    }

    public void onMotionSensingEvent(MotionSensingEvent arg0) {
        if (statusMotionRequested){//Status requested
            accelerationThresholdTextField.setText(""+arg0.getAccelerationThreshold());
            orientationThresholdTextField.setText(""+arg0.getOrientationThreshold());
            alphaSmoothingTextField.setText(""+arg0.getAlphaSmoothing());
            statusMotionRequested = false;
        }
    }

    public void onStatusEvent(StatusEvent arg0) {
        messageText.setText("Status received !");
        batteryLevelText.setText(arg0.getBatteryLevel() + " %");                
        led1Button.setEnabled(arg0.isLed1Set());
        led2Button.setEnabled(arg0.isLed2Set());
        led3Button.setEnabled(arg0.isLed3Set());
        led4Button.setEnabled(arg0.isLed4Set());
        //attachments
        int eventType = arg0.getEventType();
        if (eventType == StatusEvent.WIIUSE_CLASSIC_CTRL_INSERTED){
            expansionText.setText("Classic control connected.");
        }else
        if (eventType == StatusEvent.WIIUSE_CLASSIC_CTRL_REMOVED){
            expansionText.setText("Classic control removed.");
        }else
        if (eventType == StatusEvent.WIIUSE_NUNCHUK_INSERTED){
            expansionText.setText("Nunchuk connected.");
        }else
        if (eventType == StatusEvent.WIIUSE_NUNCHUK_REMOVED){
            expansionText.setText("Nunchuk removed.");
        }else
        if (eventType == StatusEvent.WIIUSE_GUITAR_HERO_3_CTRL_INSERTED){
            expansionText.setText("Guitar Hero 3 control connected.");
        }else
        if (eventType == StatusEvent.WIIUSE_GUITAR_HERO_3_CTRL_REMOVED){
            expansionText.setText("Guitar Hero 3 control removed.");
        }
    }

    public void onDisconnectionEvent(DisconnectionEvent arg0) {
        messageText.setText("Wiimote Disconnected !");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftPanel = new javax.swing.JPanel();
        irViewPanel = new IRPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        motionSensingPanel = new OrientationPanel();
        gForcePanel = new wiiusej.utils.GForcePanel();
        accelerationPanel = new AccelerationPanel();
        rightPanel = new javax.swing.JPanel();
        fixedWiimotePanel = new javax.swing.JPanel();
        buttonsPanel = new ButtonsEventPanel();
        controlsPanel = new javax.swing.JPanel();
        activateRumblePanel = new javax.swing.JPanel();
        toggleRumbleButton = new javax.swing.JButton();
        deactivateRumblePanel = new javax.swing.JPanel();
        toggleIRTrackingButton = new javax.swing.JButton();
        activateIRtrackingPanel = new javax.swing.JPanel();
        toggleMotionSensingTrackingButton = new javax.swing.JButton();
        deactivateIRTrackingPanel = new javax.swing.JPanel();
        toggleSmoothingButton = new javax.swing.JButton();
        activateMotionSensingTrackingPanel = new javax.swing.JPanel();
        toggleContinuousButton = new javax.swing.JButton();
        deactivateMotionSensingTrackingPanel = new javax.swing.JPanel();
        led1Button = new javax.swing.JButton();
        led2Button = new javax.swing.JButton();
        led3Button = new javax.swing.JButton();
        led4Button = new javax.swing.JButton();
        setLedsButton = new javax.swing.JButton();
        activateSmoothingPanel = new javax.swing.JPanel();
        alphaSmoothingTextField = new javax.swing.JTextField();
        alphaSmoothingButton = new javax.swing.JButton();
        deactivateSmoothingPanel = new javax.swing.JPanel();
        orientationThresholdTextField = new javax.swing.JTextField();
        orientationThresholdButton = new javax.swing.JButton();
        activateContinuousPanel = new javax.swing.JPanel();
        accelerationThresholdTextField = new javax.swing.JTextField();
        accelerationThresholdButton = new javax.swing.JButton();
        deactivateContinuousPanel = new javax.swing.JPanel();
        getStatusButton = new javax.swing.JButton();
        batteryText = new javax.swing.JLabel();
        batteryLevelText = new javax.swing.JLabel();
        ledsPanel = new javax.swing.JPanel();
        toggleSensorBarPositionButton = new javax.swing.JButton();
        alphaSmoothingPanel = new javax.swing.JPanel();
        toggleScreenAspectRatioButton = new javax.swing.JButton();
        orientationThresholdPanel = new javax.swing.JPanel();
        xLabel = new javax.swing.JLabel();
        xResolutionTextField = new javax.swing.JTextField();
        yLabel = new javax.swing.JLabel();
        yResolutionTextField = new javax.swing.JTextField();
        setVirtualResolutionButton = new javax.swing.JButton();
        accelerationThresholdPanel = new javax.swing.JPanel();
        mouseIRControlButton = new javax.swing.JButton();
        batteryPanel = new javax.swing.JPanel();
        expansionText = new javax.swing.JLabel();
        messagesPanel = new javax.swing.JPanel();
        messageLabelText = new javax.swing.JLabel();
        messageText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        irViewPanel.setBackground(new java.awt.Color(0, 0, 0));
        irViewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true), "IR View", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 0, 51)));

        javax.swing.GroupLayout irViewPanelLayout = new javax.swing.GroupLayout(irViewPanel);
        irViewPanel.setLayout(irViewPanelLayout);
        irViewPanelLayout.setHorizontalGroup(
            irViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        irViewPanelLayout.setVerticalGroup(
            irViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout motionSensingPanelLayout = new javax.swing.GroupLayout(motionSensingPanel);
        motionSensingPanel.setLayout(motionSensingPanelLayout);
        motionSensingPanelLayout.setHorizontalGroup(
            motionSensingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );
        motionSensingPanelLayout.setVerticalGroup(
            motionSensingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Orientation", motionSensingPanel);

        javax.swing.GroupLayout gForcePanelLayout = new javax.swing.GroupLayout(gForcePanel);
        gForcePanel.setLayout(gForcePanelLayout);
        gForcePanelLayout.setHorizontalGroup(
            gForcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );
        gForcePanelLayout.setVerticalGroup(
            gForcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("GForce", gForcePanel);

        javax.swing.GroupLayout accelerationPanelLayout = new javax.swing.GroupLayout(accelerationPanel);
        accelerationPanel.setLayout(accelerationPanelLayout);
        accelerationPanelLayout.setHorizontalGroup(
            accelerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );
        accelerationPanelLayout.setVerticalGroup(
            accelerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Raw Acceleration", accelerationPanel);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(irViewPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(irViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Orientation");

        rightPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rightPanel.setLayout(new javax.swing.BoxLayout(rightPanel, javax.swing.BoxLayout.LINE_AXIS));

        fixedWiimotePanel.setMaximumSize(new java.awt.Dimension(120, 32767));
        fixedWiimotePanel.setMinimumSize(new java.awt.Dimension(120, 100));
        fixedWiimotePanel.setPreferredSize(new java.awt.Dimension(120, 100));
        fixedWiimotePanel.setRequestFocusEnabled(false);
        fixedWiimotePanel.setLayout(null);

        buttonsPanel.setMaximumSize(new java.awt.Dimension(120, 484));
        buttonsPanel.setMinimumSize(new java.awt.Dimension(120, 484));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setPreferredSize(new java.awt.Dimension(120, 484));

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );

        fixedWiimotePanel.add(buttonsPanel);
        buttonsPanel.setBounds(0, 0, 120, 484);

        rightPanel.add(fixedWiimotePanel);

        controlsPanel.setMinimumSize(new java.awt.Dimension(100, 264));
        controlsPanel.setPreferredSize(new java.awt.Dimension(190, 264));
        controlsPanel.setLayout(new java.awt.GridLayout(16, 1));

        toggleRumbleButton.setText("Activate Rumble");
        toggleRumbleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                toggleRumbleButtonMousePressed(evt);
            }
        });
        activateRumblePanel.add(toggleRumbleButton);

        controlsPanel.add(activateRumblePanel);

        toggleIRTrackingButton.setText("Activate IR Tracking");
        toggleIRTrackingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                toggleIRTrackingButtonMousePressed(evt);
            }
        });
        deactivateRumblePanel.add(toggleIRTrackingButton);

        controlsPanel.add(deactivateRumblePanel);

        toggleMotionSensingTrackingButton.setText("Activate motion sensing Tracking");
        toggleMotionSensingTrackingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                toggleMotionSensingTrackingButtonMousePressed(evt);
            }
        });
        activateIRtrackingPanel.add(toggleMotionSensingTrackingButton);

        controlsPanel.add(activateIRtrackingPanel);

        toggleSmoothingButton.setText("Activate Smoothing");
        toggleSmoothingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                toggleSmoothingButtonMousePressed(evt);
            }
        });
        deactivateIRTrackingPanel.add(toggleSmoothingButton);

        controlsPanel.add(deactivateIRTrackingPanel);

        toggleContinuousButton.setText("Activate Continuous");
        toggleContinuousButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                toggleContinuousButtonMousePressed(evt);
            }
        });
        activateMotionSensingTrackingPanel.add(toggleContinuousButton);

        controlsPanel.add(activateMotionSensingTrackingPanel);

        led1Button.setText("Led1");
        led1Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                led1ButtonMousePressed(evt);
            }
        });
        deactivateMotionSensingTrackingPanel.add(led1Button);

        led2Button.setText("Led2");
        led2Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                led2ButtonMousePressed(evt);
            }
        });
        deactivateMotionSensingTrackingPanel.add(led2Button);

        led3Button.setText("Led3");
        led3Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                led3ButtonMousePressed(evt);
            }
        });
        deactivateMotionSensingTrackingPanel.add(led3Button);

        led4Button.setText("Led4");
        led4Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                led4ButtonMousePressed(evt);
            }
        });
        deactivateMotionSensingTrackingPanel.add(led4Button);

        setLedsButton.setText("Set leds");
        setLedsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setLedsButtonMousePressed(evt);
            }
        });
        deactivateMotionSensingTrackingPanel.add(setLedsButton);

        controlsPanel.add(deactivateMotionSensingTrackingPanel);

        alphaSmoothingTextField.setMinimumSize(new java.awt.Dimension(100, 20));
        alphaSmoothingTextField.setPreferredSize(new java.awt.Dimension(100, 20));
        activateSmoothingPanel.add(alphaSmoothingTextField);

        alphaSmoothingButton.setText("Set alpha smoothing");
        alphaSmoothingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                alphaSmoothingButtonMousePressed(evt);
            }
        });
        activateSmoothingPanel.add(alphaSmoothingButton);

        controlsPanel.add(activateSmoothingPanel);

        orientationThresholdTextField.setMinimumSize(new java.awt.Dimension(100, 20));
        orientationThresholdTextField.setPreferredSize(new java.awt.Dimension(100, 20));
        deactivateSmoothingPanel.add(orientationThresholdTextField);

        orientationThresholdButton.setText("Set orientation threshold");
        orientationThresholdButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                orientationThresholdButtonMousePressed(evt);
            }
        });
        deactivateSmoothingPanel.add(orientationThresholdButton);

        controlsPanel.add(deactivateSmoothingPanel);

        accelerationThresholdTextField.setPreferredSize(new java.awt.Dimension(100, 20));
        activateContinuousPanel.add(accelerationThresholdTextField);

        accelerationThresholdButton.setText("Set acceleration threshold");
        accelerationThresholdButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accelerationThresholdButtonMousePressed(evt);
            }
        });
        activateContinuousPanel.add(accelerationThresholdButton);

        controlsPanel.add(activateContinuousPanel);

        getStatusButton.setText("Get status");
        getStatusButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                getStatusButtonMousePressed(evt);
            }
        });
        deactivateContinuousPanel.add(getStatusButton);

        batteryText.setFont(new java.awt.Font("Tahoma", 0, 14));
        batteryText.setText("Battery level :");
        deactivateContinuousPanel.add(batteryText);

        batteryLevelText.setFont(new java.awt.Font("Arial", 0, 14));
        batteryLevelText.setText(" %");
        deactivateContinuousPanel.add(batteryLevelText);

        controlsPanel.add(deactivateContinuousPanel);

        toggleSensorBarPositionButton.setText("Set sensor bar above");
        toggleSensorBarPositionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                toggleSensorBarPositionButtonMousePressed(evt);
            }
        });
        ledsPanel.add(toggleSensorBarPositionButton);

        controlsPanel.add(ledsPanel);

        toggleScreenAspectRatioButton.setText("Set screen aspect ratio 4/3");
        toggleScreenAspectRatioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                toggleScreenAspectRatioButtonMousePressed(evt);
            }
        });
        alphaSmoothingPanel.add(toggleScreenAspectRatioButton);

        controlsPanel.add(alphaSmoothingPanel);

        xLabel.setText("X");
        orientationThresholdPanel.add(xLabel);

        xResolutionTextField.setMinimumSize(new java.awt.Dimension(40, 20));
        xResolutionTextField.setPreferredSize(new java.awt.Dimension(40, 20));
        orientationThresholdPanel.add(xResolutionTextField);

        yLabel.setText("Y");
        orientationThresholdPanel.add(yLabel);

        yResolutionTextField.setFocusTraversalPolicyProvider(true);
        yResolutionTextField.setMinimumSize(new java.awt.Dimension(40, 20));
        yResolutionTextField.setPreferredSize(new java.awt.Dimension(40, 20));
        orientationThresholdPanel.add(yResolutionTextField);

        setVirtualResolutionButton.setText("Set virtual resolution");
        setVirtualResolutionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setVirtualResolutionButtonMousePressed(evt);
            }
        });
        orientationThresholdPanel.add(setVirtualResolutionButton);

        controlsPanel.add(orientationThresholdPanel);

        mouseIRControlButton.setText("Start infrared mouse control");
        mouseIRControlButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mouseIRControlButtonMousePressed(evt);
            }
        });
        accelerationThresholdPanel.add(mouseIRControlButton);

        controlsPanel.add(accelerationThresholdPanel);

        expansionText.setText("No expansion connected");
        batteryPanel.add(expansionText);

        controlsPanel.add(batteryPanel);

        messageLabelText.setFont(new java.awt.Font("Tahoma", 0, 14));
        messageLabelText.setText("Message : ");
        messagesPanel.add(messageLabelText);

        messageText.setFont(new java.awt.Font("Arial", 0, 14));
        messageText.setText("None");
        messagesPanel.add(messageText);

        controlsPanel.add(messagesPanel);

        rightPanel.add(controlsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 800, 600);
    }// </editor-fold>//GEN-END:initComponents
    private void toggleRumbleButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toggleRumbleButtonMousePressed
        if (toggleRumbleButton.isEnabled()) {
            wiimote.activateRumble();
            toggleRumbleButton.setEnabled(false);
            toggleRumbleButton.setText("Deactivate Rumble");
            messageText.setText("Rumble activated");
        } else {
            wiimote.deactivateRumble();
            toggleRumbleButton.setEnabled(true);
            toggleRumbleButton.setText("Activate Rumble");
            messageText.setText("Rumble deactivated");
        }
    }//GEN-LAST:event_toggleRumbleButtonMousePressed

    private void toggleIRTrackingButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toggleIRTrackingButtonMousePressed
        if (toggleIRTrackingButton.isEnabled()) {
            wiimote.activateIRTRacking();
            toggleIRTrackingButton.setEnabled(false);
            toggleIRTrackingButton.setText("Deactivate IR Tracking");
            messageText.setText("IR Tracking activated");
        } else {
            wiimote.deactivateIRTRacking();
            toggleIRTrackingButton.setEnabled(true);
            toggleIRTrackingButton.setText("Activate IR Tracking");
            ((IRPanel) irViewPanel).onDisconnectionEvent(null);
            messageText.setText("IR Tracking deactivated");
        }
    }//GEN-LAST:event_toggleIRTrackingButtonMousePressed

    private void toggleMotionSensingTrackingButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toggleMotionSensingTrackingButtonMousePressed
        if (toggleMotionSensingTrackingButton.isEnabled()) {
            wiimote.activateMotionSensing();
            toggleMotionSensingTrackingButton.setEnabled(false);
            toggleMotionSensingTrackingButton.setText("Deactivate Motion Sensing");
            messageText.setText("Motion Sensing activated");
        } else {
            wiimote.deactivateMotionSensing();
            toggleMotionSensingTrackingButton.setEnabled(true);
            toggleMotionSensingTrackingButton.setText("Activate Motion Sensing");
            ((OrientationPanel) motionSensingPanel).onDisconnectionEvent(null);
            ((GForcePanel) gForcePanel).onDisconnectionEvent(null);
            messageText.setText("Motion Sensing deactivated");
        }
    }//GEN-LAST:event_toggleMotionSensingTrackingButtonMousePressed

    private void toggleSmoothingButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toggleSmoothingButtonMousePressed
        if (toggleSmoothingButton.isEnabled()) {
            wiimote.activateSmoothing();
            toggleSmoothingButton.setEnabled(false);
            toggleSmoothingButton.setText("Deactivate Alpha Smoothing");
            messageText.setText("Alpha Smoothing activated");
        } else {
            wiimote.deactivateSmoothing();
            toggleSmoothingButton.setEnabled(true);
            toggleSmoothingButton.setText("Activate Alpha Smoothing");
            messageText.setText("Alpha Smoothing deactivated");
        }
    }//GEN-LAST:event_toggleSmoothingButtonMousePressed

    private void toggleContinuousButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toggleContinuousButtonMousePressed
        if (toggleContinuousButton.isEnabled()) {
            wiimote.activateContinuous();
            toggleContinuousButton.setEnabled(false);
            toggleContinuousButton.setText("Deactivate Continuous");
            messageText.setText("Continuous activated");
        } else {
            wiimote.deactivateContinuous();
            toggleContinuousButton.setEnabled(true);
            toggleContinuousButton.setText("Activate Continuous");
            messageText.setText("Continuous deactivated");
        }
    }//GEN-LAST:event_toggleContinuousButtonMousePressed

    private void led1ButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_led1ButtonMousePressed
        if (led1Button.isEnabled()) {
            led1Button.setEnabled(false);
        } else {
            led1Button.setEnabled(true);
        }
    }//GEN-LAST:event_led1ButtonMousePressed

    private void led2ButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_led2ButtonMousePressed
        if (led2Button.isEnabled()) {
            led2Button.setEnabled(false);
        } else {
            led2Button.setEnabled(true);
        }
    }//GEN-LAST:event_led2ButtonMousePressed

    private void led3ButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_led3ButtonMousePressed
        if (led3Button.isEnabled()) {
            led3Button.setEnabled(false);
        } else {
            led3Button.setEnabled(true);
        }
    }//GEN-LAST:event_led3ButtonMousePressed

    private void led4ButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_led4ButtonMousePressed
        if (led4Button.isEnabled()) {
            led4Button.setEnabled(false);
        } else {
            led4Button.setEnabled(true);
        }
    }//GEN-LAST:event_led4ButtonMousePressed

    private void setLedsButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setLedsButtonMousePressed
        wiimote.setLeds(led1Button.isEnabled(), led2Button.isEnabled(),
                led3Button.isEnabled(), led4Button.isEnabled());
        messageText.setText("Leds set");
    }//GEN-LAST:event_setLedsButtonMousePressed

    private void alphaSmoothingButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alphaSmoothingButtonMousePressed
        try {
            float nb = Float.parseFloat(alphaSmoothingTextField.getText());
            wiimote.setAlphaSmoothingValue(nb);
            messageText.setText("Alpha smoothing set to " + nb);
        } catch (NumberFormatException e) {
            messageText.setText("Number is not a float, alpha smoothing not set !");
        }   
    }//GEN-LAST:event_alphaSmoothingButtonMousePressed

    private void orientationThresholdButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orientationThresholdButtonMousePressed
        try {
            float nb = Float.parseFloat(orientationThresholdTextField.getText());
            wiimote.setOrientationThreshold(nb);
            messageText.setText("Orientation threshold set to " + nb);
        } catch (NumberFormatException e) {
            messageText.setText("Number is not a float, orientation threshold not set !");
        } 
    }//GEN-LAST:event_orientationThresholdButtonMousePressed

    private void accelerationThresholdButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accelerationThresholdButtonMousePressed
        try {
            int nb = Integer.parseInt(accelerationThresholdTextField.getText());
            wiimote.setAccelerationThreshold(nb);
            messageText.setText("Acceleration threshold set to " + nb);
        } catch (NumberFormatException e) {
            messageText.setText("Number is not an integer, acceleration threshold not set !");
        }
    }//GEN-LAST:event_accelerationThresholdButtonMousePressed

    private void getStatusButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getStatusButtonMousePressed
        wiimote.getStatus();
        statusMotionRequested = true;
        statusIRRequested = true;
    }//GEN-LAST:event_getStatusButtonMousePressed

    private void toggleSensorBarPositionButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toggleSensorBarPositionButtonMousePressed
        if (toggleSensorBarPositionButton.isEnabled()) {
            wiimote.setSensorBarBelowScreen();
            toggleSensorBarPositionButton.setEnabled(false);
            toggleSensorBarPositionButton.setText("Set sensor bar below");
            messageText.setText("Sensor bar set above");
        } else {
            wiimote.setSensorBarAboveScreen();
            toggleSensorBarPositionButton.setEnabled(true);
            toggleSensorBarPositionButton.setText("Set sensor bar above");
            messageText.setText("Sensor bar set below");
        }
    }//GEN-LAST:event_toggleSensorBarPositionButtonMousePressed

    private void toggleScreenAspectRatioButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toggleScreenAspectRatioButtonMousePressed
        if (toggleScreenAspectRatioButton.isEnabled()) {
            wiimote.setScreenAspectRatio43();
            toggleScreenAspectRatioButton.setEnabled(false);
            toggleScreenAspectRatioButton.setText("Set screen aspect ratio 16/9");
            messageText.setText("creen aspect ratio to 4/3");
        } else {
            wiimote.setScreenAspectRatio169();
            toggleScreenAspectRatioButton.setEnabled(true);
            toggleScreenAspectRatioButton.setText("Set screen aspect ratio 4/3");
            messageText.setText("Screen aspect ratio to 16/9");
        }
    }//GEN-LAST:event_toggleScreenAspectRatioButtonMousePressed

    private void setVirtualResolutionButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setVirtualResolutionButtonMousePressed
        try {
            int xres = Integer.parseInt(xResolutionTextField.getText());
            int yres = Integer.parseInt(yResolutionTextField.getText());
            wiimote.setVirtualResolution(xres, yres);
            messageText.setText("Virtual resolution set to " + xres + "X" + yres);
        } catch (NumberFormatException e) {
            messageText.setText("A number in the virtual resolution is not an integer. Virtual resolution not set!");
        }
    }//GEN-LAST:event_setVirtualResolutionButtonMousePressed

    private void mouseIRControlButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseIRControlButtonMousePressed
        if (mouseIRControlButton.isEnabled()) {
            try {
                mouseIRControlButton.setEnabled(false);
                mouseIRControlButton.setText("Stop infrared mouse control");
                robot = new Robot();
                messageText.setText("Infrared mouse control started");
            } catch (AWTException ex) {
                Logger.getLogger(WiiuseJGuiTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            mouseIRControlButton.setEnabled(true);
            mouseIRControlButton.setText("Start infrared mouse control");
            robot = null;
            messageText.setText("Infrared mouse control stopped");
        }
    }//GEN-LAST:event_mouseIRControlButtonMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accelerationPanel;
    private javax.swing.JButton accelerationThresholdButton;
    private javax.swing.JPanel accelerationThresholdPanel;
    private javax.swing.JTextField accelerationThresholdTextField;
    private javax.swing.JPanel activateContinuousPanel;
    private javax.swing.JPanel activateIRtrackingPanel;
    private javax.swing.JPanel activateMotionSensingTrackingPanel;
    private javax.swing.JPanel activateRumblePanel;
    private javax.swing.JPanel activateSmoothingPanel;
    private javax.swing.JButton alphaSmoothingButton;
    private javax.swing.JPanel alphaSmoothingPanel;
    private javax.swing.JTextField alphaSmoothingTextField;
    private javax.swing.JLabel batteryLevelText;
    private javax.swing.JPanel batteryPanel;
    private javax.swing.JLabel batteryText;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JPanel deactivateContinuousPanel;
    private javax.swing.JPanel deactivateIRTrackingPanel;
    private javax.swing.JPanel deactivateMotionSensingTrackingPanel;
    private javax.swing.JPanel deactivateRumblePanel;
    private javax.swing.JPanel deactivateSmoothingPanel;
    private javax.swing.JLabel expansionText;
    private javax.swing.JPanel fixedWiimotePanel;
    private javax.swing.JPanel gForcePanel;
    private javax.swing.JButton getStatusButton;
    private javax.swing.JPanel irViewPanel;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton led1Button;
    private javax.swing.JButton led2Button;
    private javax.swing.JButton led3Button;
    private javax.swing.JButton led4Button;
    private javax.swing.JPanel ledsPanel;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel messageLabelText;
    private javax.swing.JLabel messageText;
    private javax.swing.JPanel messagesPanel;
    private javax.swing.JPanel motionSensingPanel;
    private javax.swing.JButton mouseIRControlButton;
    private javax.swing.JButton orientationThresholdButton;
    private javax.swing.JPanel orientationThresholdPanel;
    private javax.swing.JTextField orientationThresholdTextField;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JButton setLedsButton;
    private javax.swing.JButton setVirtualResolutionButton;
    private javax.swing.JButton toggleContinuousButton;
    private javax.swing.JButton toggleIRTrackingButton;
    private javax.swing.JButton toggleMotionSensingTrackingButton;
    private javax.swing.JButton toggleRumbleButton;
    private javax.swing.JButton toggleScreenAspectRatioButton;
    private javax.swing.JButton toggleSensorBarPositionButton;
    private javax.swing.JButton toggleSmoothingButton;
    private javax.swing.JLabel xLabel;
    private javax.swing.JTextField xResolutionTextField;
    private javax.swing.JLabel yLabel;
    private javax.swing.JTextField yResolutionTextField;
    // End of variables declaration//GEN-END:variables
}

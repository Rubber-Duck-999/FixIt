package team.FixIt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View
{
    private JFrame frame;
    private JPanel buttonPanel, fieldPanel;
    private JLabel loginField, passwordField;
    private JButton registerButton, loginButton;
    private Font buttonFont;

    public View()
    {
        frame = new JFrame("Simple MVC");
        buttonPanel = new JPanel();
        fieldPanel = new JPanel();

        passwordField = new JLabel("", SwingConstants.CENTER);
        loginField = new JLabel("", SwingConstants.CENTER);

        registerButton = new JButton("Register");
        loginButton    = new JButton("Login");

        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        buttonPanel.setLayout(new GridLayout(2,1));

        setButtonFont();
        setLabelFont();

        fieldPanel.setLayout(new GridLayout(1,2));
        fieldPanel.add(loginField);
        fieldPanel.add(passwordField);

        frame.add(fieldPanel, BorderLayout.NORTH);
        //frame.add(buttonPanel, BorderLayout.CENTER);

        GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        frame.setSize(640,480);
        //frame.pack();
        frame.setVisible(true);
        //device.setFullScreenWindow(frame);
    }

    private void setLabelFont()
    {
        buttonFont = new Font("Calibri", Font.BOLD, 128);
        loginField.setFont(buttonFont);
        passwordField.setFont(buttonFont);
    }

    private void setButtonFont()
    {
        buttonFont = new Font("Calibri", Font.BOLD, 40);
        registerButton.setFont(buttonFont);
        loginButton.setFont(buttonFont);
    }

    public < E > void setLogin(E v)
    {
        System.out.println(v);
        loginField.setText("" + v);
    }

    public < E > void setPassword(E v)
    {
        System.out.println(v);
        passwordField.setText("" + v);
    }


    public void addController(ActionListener listenerButtons)
    {
    }

    public void displayErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(frame, "Error: " + message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displayPassMessage(String message)
    {
        JOptionPane.showMessageDialog(frame, "Pass: " + message, "Pass", JOptionPane.INFORMATION_MESSAGE);
    }

    public static class CloseListener extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }
}
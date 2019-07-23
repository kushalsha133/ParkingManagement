package ParkingManagement;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.fazecast.jSerialComm.SerialPort;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;



public class startuppage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					startuppage frame = new startuppage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public startuppage() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit ex = new exit();
				ex.exitt();
			}
		});
		btnExit.setBounds(171, 185, 89, 23);
		contentPane.add(btnExit);
		
		
		
		
		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				 park pp =new park();
				 pp.parkk();
			}
		 });
		
		btnEnter.setBounds(171, 137, 89, 23);
		contentPane.add(btnEnter);
		
		JButton btnNewButton = new JButton("OWNER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OWNER owe=new OWNER();
				   owe.ownerpage();
			}
		});
		btnNewButton.setBounds(335, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel labell1 = new JLabel(" Welcome To Delhi Govt. Parking Service");
		labell1.setForeground(Color.RED);
		labell1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labell1.setBounds(75, 82, 267, 44);
		contentPane.add(labell1);
		
		JLabel labell2 = new JLabel("");
		labell2.setIcon(new ImageIcon("C:\\eclipse-workplace\\ParkingManagementProgram\\Photos\\car-park.jpg"));
		labell2.setBounds(0, 0, 434, 261);
		contentPane.add(labell2);
	}
}










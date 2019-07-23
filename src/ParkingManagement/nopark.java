package ParkingManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class nopark {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void noparkk() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nopark window = new nopark();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public nopark() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 208, 295);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\eclipse-workplace\\ParkingManagementProgram\\Photos\\parkfull.jpg"));
		lblNewLabel.setBounds(0, 0, 222, 263);
		frame.getContentPane().add(lblNewLabel);
	}
}

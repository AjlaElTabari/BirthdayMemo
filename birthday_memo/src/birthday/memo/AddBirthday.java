package birthday.memo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Implements logic for adding new birthday into users file
 * 
 * @author ajla.eltabari
 *
 */
public class AddBirthday extends JFrame {

	private static final long serialVersionUID = -3286174181011898013L;

	private String filename = "src/birthday/memo/birthdays.txt";
	BufferedImage bufferedImage;

	JPanel pnlMain = new JPanel();
	JPanel pnlContent = new JPanel();
	JPanel pnlPic = new ImagePanel();

	JLabel lblName = new JLabel("Name");
	JTextField tfName = new JTextField();

	JLabel lblLastname = new JLabel("Lastname");
	JTextField tfLastname = new JTextField();

	JLabel lblDay = new JLabel("Day");
	JTextField tfDay = new JTextField();

	JLabel lblMonth = new JLabel("Month");
	JTextField tfMonth = new JTextField();

	JLabel lblYear = new JLabel("Year");
	JTextField tfYear = new JTextField();

	JButton btnAdd = new JButton("Add");

	BirthdayMemo bm = new BirthdayMemo();

	public AddBirthday() {

		// btn add birthday
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String s = tfName.getText() + " " + tfLastname.getText()
							+ " " + Integer.parseInt(tfMonth.getText()) + " "
							+ Integer.parseInt(tfDay.getText()) + " "
							+ Integer.parseInt(tfYear.getText());

					DataHelper.insertBirtdhday(filename,
							DataHelper.getBirthdays(filename), s);
				} catch (NumberFormatException ex) {
					JOptionPane error = new JOptionPane(
							"Entered information is not valid. Please try again.",
							JOptionPane.ERROR_MESSAGE);
					error.createDialog("Error").setVisible(true);

					new AddBirthday();
				}

				dispose();
				bm.taTodaysEvents.setText(DataHelper.printBirthdays(filename));
			}

		});

		// main panel settings
		pnlMain.setLayout(new BorderLayout());

		// content panel settings
		pnlContent.setLayout(new GridLayout(5, 2));

		pnlPic.setPreferredSize(new Dimension(426, 150));

		pnlContent.add(lblName);
		pnlContent.add(tfName);
		pnlContent.add(lblLastname);
		pnlContent.add(tfLastname);
		pnlContent.add(lblDay);
		pnlContent.add(tfDay);
		pnlContent.add(lblMonth);
		pnlContent.add(tfMonth);
		pnlContent.add(lblYear);
		pnlContent.add(tfYear);

		pnlMain.add(pnlPic, BorderLayout.NORTH);
		pnlMain.add(pnlContent, BorderLayout.CENTER);
		pnlMain.add(btnAdd, BorderLayout.SOUTH);

		add(pnlMain);
		setTitle("Add Birthday");
		setSize(426, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	public class ImagePanel extends JPanel {

		private static final long serialVersionUID = 221227307122900452L;

		public ImagePanel() {
			try {
				bufferedImage = ImageIO.read(new File(
						"src/birthday/memo/birthdaycupcakes.jpg"));
			} catch (IOException ex) {
				JOptionPane error = new JOptionPane(
						"Could not find or open desired file.",
						JOptionPane.ERROR_MESSAGE);
				error.createDialog("Error").setVisible(true);
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bufferedImage, 0, 0, null);
		}

	}
}

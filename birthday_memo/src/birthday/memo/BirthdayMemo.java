package birthday.memo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Represents small GUI application
 * Allows user to have his own file, that contains list of birthdays
 * User can manipulate those birthdays through this application
 * @author ajla.eltabari
 *
 */
public class BirthdayMemo extends JFrame {

	private static final long serialVersionUID = -8643595850257360443L;
	private String filename = "src/birthday/memo/birthdays.txt";
	private JPanel pnlContainer = new JPanel();
	private BufferedImage bufferedImage;
	private JPanel pnlTitle = new ImagePanel();
	private JPanel pnlCenter = new JPanel();
	private JPanel pnlReports = new JPanel();
	protected JTextArea taTodaysEvents = new JTextArea();
	private JButton btnAddBirthday = new JButton("Add new birthday");
	private JComboBox<String> ddlReports = new JComboBox<>();
	private JTextArea taReportResult = new JTextArea();
	
	private JMenuBar menubar = new JMenuBar();
	private JMenu options = new JMenu("Options");
	
	private ImageIcon icon = new ImageIcon(BirthdayMemo.class.getResource("/birthday/memo/github.png"));
	private JMenuItem gitRepo = new JMenuItem("GIT Repo", icon);

	public BirthdayMemo() {
		setTitle("Birthday Memo");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		// Menu on click
				gitRepo.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Desktop.getDesktop().browse(new URI("https://github.com/AjlaElTabari/BirthdayMemo/tree/master/birthday_memo/src/birthday/memo"));
						} catch (IOException | URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				});

		// Customize title
		pnlTitle.setPreferredSize(new Dimension(800, 100));

		// Customize button
		btnAddBirthday.setBackground(new Color(171, 126, 139));
		btnAddBirthday.setOpaque(true);
		btnAddBirthday.setBorderPainted(false);
		btnAddBirthday.setFont(new Font("Monospace", Font.BOLD, 12));
		btnAddBirthday.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddBirthday();
			}

		});

		// DropDown menu Reports
		ddlReports.addItem("Select report");
		ddlReports.addItem("Todays birthdays");
		ddlReports.addItem("Birthdays by name");
		ddlReports.addItem("Birthdays by date");

		ddlReports.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ddlReports.getSelectedItem() == "Todays birthdays") {
					DateFormat day = new SimpleDateFormat("dd");
					DateFormat month = new SimpleDateFormat("MM");
				    //get current date time with Date()
				    Date date = new Date();
					taReportResult.setText(DataHelper.todaysBirthdays(Integer.parseInt(day.format(date)), Integer.parseInt(month.format(date)),
							filename));
				} else if (ddlReports.getSelectedItem() == "Birthdays by name") {
					JFrame jfName = new JFrame("SearchByName");
					JPanel pnlName = new JPanel();
					JLabel lblName = new JLabel("Name");
					JTextField tfName = new JTextField();

					pnlName.setLayout(new GridLayout(1, 2));

					pnlName.add(lblName);
					pnlName.add(tfName);
					jfName.add(pnlName);
					jfName.setTitle("Birthday Memo");
					jfName.setSize(200, 100);
					jfName.setLocationRelativeTo(null);
					jfName.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jfName.setVisible(true);

					tfName.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							taReportResult.setText(DataHelper.birthdaysByName(
									tfName.getText(), filename));
							jfName.dispose();
						}

					});
				} else if (ddlReports.getSelectedItem() == "Birthdays by date") {
					JFrame jfDate = new JFrame("SearchByName");
					JPanel pnlDate = new JPanel();
					JLabel lblDay = new JLabel("Day");
					JTextField tfDay = new JTextField();
					JLabel lblMonth = new JLabel("Month");
					JTextField tfMonth = new JTextField();

					pnlDate.setLayout(new GridLayout(2, 2));

					pnlDate.add(lblDay);
					pnlDate.add(tfDay);
					pnlDate.add(lblMonth);
					pnlDate.add(tfMonth);
					jfDate.add(pnlDate);
					jfDate.setTitle("Birthday Memo");
					jfDate.setSize(300, 200);
					jfDate.setLocationRelativeTo(null);
					jfDate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jfDate.setVisible(true);

					tfMonth.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								taReportResult.setText(DataHelper
										.birthdaysByDate(tfDay.getText(),
												tfMonth.getText(), filename));
							} catch (NumberFormatException ex) {
								JOptionPane error = new JOptionPane(
										"Entered information is not valid.",
										JOptionPane.ERROR_MESSAGE);
								error.createDialog("Error").setVisible(true);
							}
							jfDate.dispose();
						}

					});
				}
			}

		});

		// Menu
		options.add(gitRepo);
		menubar.add(options);
		
		setJMenuBar(menubar);
		
		// Borders for panels
		pnlCenter.setBorder(BorderFactory.createTitledBorder(""));
		pnlReports.setBorder(BorderFactory.createTitledBorder(""));

		// Panels layouts
		pnlContainer.setLayout(new BorderLayout());
		pnlContainer.setPreferredSize(new Dimension(300, 400));
		pnlCenter.setLayout(new GridLayout(1, 2));
		pnlReports.setLayout(new BorderLayout());

		// Logic
		taTodaysEvents.setText(DataHelper.printBirthdays(filename));

		// Adding components to the panels and frame
		pnlContainer.add(pnlCenter, BorderLayout.CENTER);
		pnlContainer.add(pnlTitle, BorderLayout.NORTH);
		pnlContainer.add(btnAddBirthday, BorderLayout.SOUTH);

		pnlCenter.add(taTodaysEvents);
		pnlCenter.add(pnlReports);

		pnlReports.add(ddlReports, BorderLayout.NORTH);
		pnlReports.add(taReportResult, BorderLayout.CENTER);

		// pnlTitle.add(lblTitle);
		add(pnlContainer);
		setVisible(true);
	}

	public class ImagePanel extends JPanel {

		private static final long serialVersionUID = 221227307122900452L;

		public ImagePanel() {
			try {
				bufferedImage = ImageIO.read(new File(
						"src/birthday/memo/birthday.png"));
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

	public static void main(String[] args) {
		new BirthdayMemo();
	}
}

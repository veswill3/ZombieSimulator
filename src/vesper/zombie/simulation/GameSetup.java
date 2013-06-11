package vesper.zombie.simulation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameSetup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Parameter startCitizens;
	private Parameter startPolice;
	private Parameter policeAmmo;
	private Parameter policeAccuracy;
	private Parameter startZombie;
	private Parameter zombieDetection;
	private Parameter humanStamina;
	private Parameter zombieStamina;
	private Parameter boardHeight;
	private Parameter boardWidth;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSetup frame = new GameSetup();
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
	public GameSetup() {
		setTitle("Zombie Survival - Simulation Setup");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][]"));
		
		this.startCitizens = new Parameter("Citizen Population", 600, 1, 1000);
		contentPane.add(startCitizens, "cell 0 0,grow");
		
		this.startPolice = new Parameter("Police Population", 30, 0, 200);
		contentPane.add(startPolice, "cell 0 1,grow");
		
		this.policeAmmo = new Parameter("Police Ammo", 10, 0, 100);
		contentPane.add(policeAmmo, "cell 0 2,grow");
		
		this.policeAccuracy = new Parameter("Police Accuracy", 70, 0, 100);
		contentPane.add(policeAccuracy, "cell 0 3,grow");
		
		this.startZombie = new Parameter("Zombie Population", 3, 1, 50);
		contentPane.add(startZombie, "cell 0 4,grow");
		
		this.zombieDetection = new Parameter("Zombie Smell Radius", 20, 0, 500);
		contentPane.add(zombieDetection, "cell 0 5,grow");
		
		this.humanStamina = new Parameter("Human Stamina", 10, 0, 100);
		contentPane.add(humanStamina, "cell 0 6,grow");
		
		this.zombieStamina = new Parameter("Zombie Stamina", 25, 0, 100);
		contentPane.add(zombieStamina, "cell 0 7,grow");
		
		this.boardHeight = new Parameter("Board Height", 500, 100, 1000);
		contentPane.add(boardHeight, "cell 0 8,grow");
		
		this.boardWidth = new Parameter("Board Width", 800, 100, 1000);
		contentPane.add(boardWidth, "cell 0 9,grow");
		
		JButton cmdDefault = new JButton("Defaults");
		cmdDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startCitizens.setValue(600);
				startPolice.setValue(30);
				policeAmmo.setValue(10);
				policeAccuracy.setValue(70);
				startZombie.setValue(3);
				zombieDetection.setValue(20);
				humanStamina.setValue(15);
				zombieStamina.setValue(25);
				boardHeight.setValue(500);
				boardWidth.setValue(800);
			}
		});
		contentPane.add(cmdDefault, "flowx,cell 0 10");
		
		JButton cmdStart = new JButton("Start Simulation");
		cmdStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ZombieSimulator.startGame(startCitizens.getValue(), startPolice.getValue(), policeAmmo.getValue(), policeAccuracy.getValue(),
						startZombie.getValue(), zombieDetection.getValue(), humanStamina.getValue(), zombieStamina.getValue(), boardHeight.getValue(), boardWidth.getValue());
			}
		});
		contentPane.add(cmdStart, "cell 0 10,growx");

	}
}

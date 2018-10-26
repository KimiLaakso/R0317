import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.Font;

public class tietoja {

	private JFrame frmTietoja;

	/**
	 * Launch the application.
	 */
	public static void UusiIkkuna() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					tietoja window = new tietoja();
					window.frmTietoja.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tietoja() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTietoja = new JFrame();
		frmTietoja.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 12));
		frmTietoja.setTitle("Tietoja");
		frmTietoja.setBounds(100, 100, 204, 82);
		frmTietoja.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmTietoja.getContentPane().setLayout(null);
		
		JTextPane txtpnTehnytKimLaakso = new JTextPane();
		txtpnTehnytKimLaakso.setText("Tehnyt Kim Laakso");
		txtpnTehnytKimLaakso.setBounds(10, 11, 155, 21);
		frmTietoja.getContentPane().add(txtpnTehnytKimLaakso);
	}
}

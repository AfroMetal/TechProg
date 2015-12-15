package pokertexasholdem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConnectionWindow extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private JTextField tfName;
    private JTextField tfAdress;
    private JTextField tfPort;
    private JButton btnConnect;
    
    private Client client;
    
    /**
     * Create the application.
     */
    public ConnectionWindow( Client client ) {
        
        super();
        this.client = client;
        
        setTitle("Connection");
        setBounds(100, 100, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        
        JLabel lblNick = new JLabel("Enter your nickname:");
        lblNick.setFont(new Font("Cambria Math", Font.PLAIN, 14));
        lblNick.setBounds(49, 11, 180, 30);
        getContentPane().add(lblNick);
        
        tfName = new JTextField();
        tfName.setBounds(49, 46, 186, 25);
        getContentPane().add(tfName);
        
        JLabel lblAdress = new JLabel("Server adress:");
        lblAdress.setFont(new Font("Cambria Math", Font.PLAIN, 14));
        lblAdress.setBounds(49, 84, 180, 30);
        getContentPane().add(lblAdress);
        
        tfAdress = new JTextField();
        tfAdress.setBounds(49, 117, 186, 25);
        getContentPane().add(tfAdress);
        
        JLabel lblPort = new JLabel("Port number:");
        lblPort.setFont(new Font("Cambria Math", Font.PLAIN, 14));
        lblPort.setBounds(49, 157, 180, 30);
        getContentPane().add(lblPort);
        
        tfPort = new JTextField();
        tfPort.setBounds(49, 188, 186, 25);
        getContentPane().add(tfPort);
        
        btnConnect = new JButton("Connect");
        btnConnect.setFont(new Font("Cambria Math", Font.PLAIN, 18));
        btnConnect.setBounds(79, 221, 125, 35);
        getContentPane().add(btnConnect);
        btnConnect.addActionListener(this);
        
        setVisible(true);
    }
    
    public void setTitleText(String string) {
        setTitle(string);
        
    }
    
    public void setBtnConnectEnabled(boolean state) {
        btnConnect.setEnabled(state);
    }
    
    public void setBtnConnectText(String text) {
        btnConnect.setText(text);
    }
    
    @Override
    public void actionPerformed(ActionEvent connect) {
        String adress = tfAdress.getText();
        String port = tfPort.getText();
        String name = tfName.getText();
        
        if (connect.getSource() == btnConnect) {
            client.connect(adress, port, name);
        }
        
    }
}

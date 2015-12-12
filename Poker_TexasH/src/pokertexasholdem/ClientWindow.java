package pokertexasholdem;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import java.awt.Rectangle;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.Cursor;

public class ClientWindow {
    
    private JFrame frame;
    private Map<String, JLabel> mapLblDealer= new HashMap<String, JLabel>();
    private Map<String, JLabel> mapLblName= new HashMap<String, JLabel>();
    private Map<String, JLabel> mapLblMoney= new HashMap<String, JLabel>();
    private Map<String, JLabel> mapLblAction= new HashMap<String, JLabel>();
    private Map<String, JLabel> mapLblBet= new HashMap<String, JLabel>();
    private Map<String, JLabel> mapLblCardPlayer= new HashMap<String, JLabel>();
    private Map<String, JLabel> mapLblCardCommunity= new HashMap<String, JLabel>();
    private JFormattedTextField spinnerTextField;
    private JLabel lblCard1, lblCard2;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientWindow window = new ClientWindow();
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
    public ClientWindow() {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(35, 70, 35));
        frame.getContentPane().setBounds(new Rectangle(0, 0, 1000, 700));
        frame.setResizable(false);
        frame.setBounds(100, 100, 1010, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JPanel playersDataPanel = new JPanel();
        playersDataPanel.setBackground(new Color(35, 70, 35));
        playersDataPanel.setBounds(10, 10, 984, 135);
        frame.getContentPane().add(playersDataPanel);
        playersDataPanel.setLayout(new GridLayout(6, 10, 10, 0));
        
        JLabel lblDealerPlayer1 = new JLabel("");
        lblDealerPlayer1.setFont(new Font("Cambria Math", Font.BOLD, 16));
        lblDealerPlayer1.setForeground(new Color(204, 51, 51));
        lblDealerPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblDealer.put("Player1", lblDealerPlayer1);
        playersDataPanel.add(lblDealerPlayer1);
        
        JLabel lblDealerPlayer2 = new JLabel("");
        lblDealerPlayer2.setFont(new Font("Cambria Math", Font.BOLD, 16));
        lblDealerPlayer2.setForeground(new Color(204, 51, 51));
        lblDealerPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblDealer.put("Player2", lblDealerPlayer2);
        playersDataPanel.add(lblDealerPlayer2);
        
        JLabel lblDealerPlayer3 = new JLabel("");
        lblDealerPlayer3.setFont(new Font("Cambria Math", Font.BOLD, 16));
        lblDealerPlayer3.setForeground(new Color(204, 51, 51));
        lblDealerPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblDealer.put("Player3", lblDealerPlayer3);
        playersDataPanel.add(lblDealerPlayer3);
        
        JLabel lblDealerPlayer4 = new JLabel("");
        lblDealerPlayer4.setFont(new Font("Cambria Math", Font.BOLD, 16));
        lblDealerPlayer4.setForeground(new Color(204, 51, 51));
        lblDealerPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblDealer.put("Player4", lblDealerPlayer4);
        playersDataPanel.add(lblDealerPlayer4);
        
        JLabel lblDealerPlayer5 = new JLabel("");
        lblDealerPlayer5.setFont(new Font("Cambria Math", Font.BOLD, 16));
        lblDealerPlayer5.setForeground(new Color(204, 51, 51));
        lblDealerPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblDealer.put("Player5", lblDealerPlayer5);
        playersDataPanel.add(lblDealerPlayer5);
        
        JLabel lblDealerPlayer6 = new JLabel("");
        lblDealerPlayer6.setFont(new Font("Cambria Math", Font.BOLD, 16));
        lblDealerPlayer6.setForeground(new Color(204, 51, 51));
        lblDealerPlayer6.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblDealer.put("Player6", lblDealerPlayer6);
        playersDataPanel.add(lblDealerPlayer6);
        
        JLabel lblDealerPlayer7 = new JLabel("");
        lblDealerPlayer7.setFont(new Font("Cambria Math", Font.BOLD, 16));
        lblDealerPlayer7.setForeground(new Color(204, 51, 51));
        lblDealerPlayer7.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblDealer.put("Player7", lblDealerPlayer7);
        playersDataPanel.add(lblDealerPlayer7);
        
        JLabel lblDealerPlayer8 = new JLabel("");
        lblDealerPlayer8.setFont(new Font("Cambria Math", Font.BOLD, 16));
        lblDealerPlayer8.setForeground(new Color(204, 51, 51));
        lblDealerPlayer8.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblDealer.put("Player8", lblDealerPlayer8);
        playersDataPanel.add(lblDealerPlayer8);
        
        JLabel lblDealerPlayer9 = new JLabel("");
        lblDealerPlayer9.setFont(new Font("Cambria Math", Font.BOLD, 16));
        lblDealerPlayer9.setForeground(new Color(204, 51, 51));
        lblDealerPlayer9.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblDealer.put("Player9", lblDealerPlayer9);
        playersDataPanel.add(lblDealerPlayer9);
        
        JLabel lblDealerPlayer10 = new JLabel("");
        lblDealerPlayer10.setFont(new Font("Cambria Math", Font.BOLD, 16));
        lblDealerPlayer10.setForeground(new Color(204, 51, 51));
        lblDealerPlayer10.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblDealer.put("Player10", lblDealerPlayer10);
        playersDataPanel.add(lblDealerPlayer10);
        
        JLabel lblNamePlayer1 = new JLabel("");
        lblNamePlayer1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNamePlayer1.setForeground(new Color(255, 255, 204));
        lblNamePlayer1.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblNamePlayer1.setBackground(new Color(102, 153, 102));
        lblNamePlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblName.put("Player1", lblNamePlayer1);
        playersDataPanel.add(lblNamePlayer1);
        
        JLabel lblNamePlayer2 = new JLabel("");
        lblNamePlayer2.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNamePlayer2.setForeground(new Color(255, 255, 204));
        lblNamePlayer2.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblNamePlayer2.setBackground(new Color(102, 153, 102));
        lblNamePlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblName.put("Player2", lblNamePlayer2);
        playersDataPanel.add(lblNamePlayer2);
        
        JLabel lblNamePlayer3 = new JLabel("");
        lblNamePlayer3.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNamePlayer3.setForeground(new Color(255, 255, 204));
        lblNamePlayer3.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblNamePlayer3.setBackground(new Color(102, 153, 102));
        lblNamePlayer3.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblName.put("Player3", lblNamePlayer3);
        playersDataPanel.add(lblNamePlayer3);
        
        JLabel lblNamePlayer4 = new JLabel("");
        lblNamePlayer4.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNamePlayer4.setForeground(new Color(255, 255, 204));
        lblNamePlayer4.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblNamePlayer4.setBackground(new Color(102, 153, 102));
        lblNamePlayer4.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblName.put("Player4", lblNamePlayer4);
        playersDataPanel.add(lblNamePlayer4);

        JLabel lblNamePlayer5 = new JLabel("");
        lblNamePlayer5.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNamePlayer5.setForeground(new Color(255, 255, 204));
        lblNamePlayer5.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblNamePlayer5.setBackground(new Color(102, 153, 102));
        lblNamePlayer5.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblName.put("Player5", lblNamePlayer5);
        playersDataPanel.add(lblNamePlayer5);
        
        JLabel lblNamePlayer6 = new JLabel("");
        lblNamePlayer6.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNamePlayer6.setForeground(new Color(255, 255, 204));
        lblNamePlayer6.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblNamePlayer6.setBackground(new Color(102, 153, 102));
        lblNamePlayer6.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblName.put("Player6", lblNamePlayer6);
        playersDataPanel.add(lblNamePlayer6);
        
        JLabel lblNamePlayer7 = new JLabel("");
        lblNamePlayer7.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNamePlayer7.setForeground(new Color(255, 255, 204));
        lblNamePlayer7.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblNamePlayer7.setBackground(new Color(102, 153, 102));
        lblNamePlayer7.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblName.put("Player7", lblNamePlayer7);
        playersDataPanel.add(lblNamePlayer7);
        
        JLabel lblNamePlayer8 = new JLabel("");
        lblNamePlayer8.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNamePlayer8.setForeground(new Color(255, 255, 204));
        lblNamePlayer8.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblNamePlayer8.setBackground(new Color(102, 153, 102));
        lblNamePlayer8.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblName.put("Player8", lblNamePlayer8);
        playersDataPanel.add(lblNamePlayer8);
        
        JLabel lblNamePlayer9 = new JLabel("");
        lblNamePlayer9.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNamePlayer9.setForeground(new Color(255, 255, 204));
        lblNamePlayer9.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblNamePlayer9.setBackground(new Color(102, 153, 102));
        lblNamePlayer9.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblName.put("Player9", lblNamePlayer9);
        playersDataPanel.add(lblNamePlayer9);
        
        JLabel lblNamePlayer10 = new JLabel("");
        lblNamePlayer10.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNamePlayer10.setForeground(new Color(255, 255, 204));
        lblNamePlayer10.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblNamePlayer10.setBackground(new Color(102, 153, 102));
        lblNamePlayer10.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblName.put("Player10", lblNamePlayer10);
        playersDataPanel.add(lblNamePlayer10);
        
        JLabel lblMoneyPlayer1 = new JLabel("");
        lblMoneyPlayer1.setVerticalAlignment(SwingConstants.TOP);
        lblMoneyPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoneyPlayer1.setForeground(new Color(255, 255, 204));
        lblMoneyPlayer1.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblMoneyPlayer1.setBackground(new Color(102, 153, 102));
        mapLblMoney.put("Player1", lblMoneyPlayer1);
        playersDataPanel.add(lblMoneyPlayer1);
        
        JLabel lblMoneyPlayer2 = new JLabel("");
        lblMoneyPlayer2.setVerticalAlignment(SwingConstants.TOP);
        lblMoneyPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoneyPlayer2.setForeground(new Color(255, 255, 204));
        lblMoneyPlayer2.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblMoneyPlayer2.setBackground(new Color(102, 153, 102));
        mapLblMoney.put("Player2", lblMoneyPlayer2);
        playersDataPanel.add(lblMoneyPlayer2);
        
        JLabel lblMoneyPlayer3 = new JLabel("");
        lblMoneyPlayer3.setVerticalAlignment(SwingConstants.TOP);
        lblMoneyPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoneyPlayer3.setForeground(new Color(255, 255, 204));
        lblMoneyPlayer3.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblMoneyPlayer3.setBackground(new Color(102, 153, 102));
        mapLblMoney.put("Player3", lblMoneyPlayer3);
        playersDataPanel.add(lblMoneyPlayer3);
        
        JLabel lblMoneyPlayer4 = new JLabel("");
        lblMoneyPlayer4.setVerticalAlignment(SwingConstants.TOP);
        lblMoneyPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoneyPlayer4.setForeground(new Color(255, 255, 204));
        lblMoneyPlayer4.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblMoneyPlayer4.setBackground(new Color(102, 153, 102));
        mapLblMoney.put("Player4", lblMoneyPlayer4);
        playersDataPanel.add(lblMoneyPlayer4);
        
        JLabel lblMoneyPlayer5 = new JLabel("");
        lblMoneyPlayer5.setVerticalAlignment(SwingConstants.TOP);
        lblMoneyPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoneyPlayer5.setForeground(new Color(255, 255, 204));
        lblMoneyPlayer5.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblMoneyPlayer5.setBackground(new Color(102, 153, 102));
        mapLblMoney.put("Player5", lblMoneyPlayer5);
        playersDataPanel.add(lblMoneyPlayer5);
        
        JLabel lblMoneyPlayer6 = new JLabel("");
        lblMoneyPlayer6.setVerticalAlignment(SwingConstants.TOP);
        lblMoneyPlayer6.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoneyPlayer6.setForeground(new Color(255, 255, 204));
        lblMoneyPlayer6.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblMoneyPlayer6.setBackground(new Color(102, 153, 102));
        mapLblMoney.put("Player6", lblMoneyPlayer6);
        playersDataPanel.add(lblMoneyPlayer6);
        
        JLabel lblMoneyPlayer7 = new JLabel("");
        lblMoneyPlayer7.setVerticalAlignment(SwingConstants.TOP);
        lblMoneyPlayer7.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoneyPlayer7.setForeground(new Color(255, 255, 204));
        lblMoneyPlayer7.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblMoneyPlayer7.setBackground(new Color(102, 153, 102));
        mapLblMoney.put("Player7", lblMoneyPlayer7);
        playersDataPanel.add(lblMoneyPlayer7);
        
        JLabel lblMoneyPlayer8 = new JLabel("");
        lblMoneyPlayer8.setVerticalAlignment(SwingConstants.TOP);
        lblMoneyPlayer8.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoneyPlayer8.setForeground(new Color(255, 255, 204));
        lblMoneyPlayer8.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblMoneyPlayer8.setBackground(new Color(102, 153, 102));
        mapLblMoney.put("Player8", lblMoneyPlayer8);
        playersDataPanel.add(lblMoneyPlayer8);
        
        JLabel lblMoneyPlayer9 = new JLabel("");
        lblMoneyPlayer9.setVerticalAlignment(SwingConstants.TOP);
        lblMoneyPlayer9.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoneyPlayer9.setForeground(new Color(255, 255, 204));
        lblMoneyPlayer9.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblMoneyPlayer9.setBackground(new Color(102, 153, 102));
        mapLblMoney.put("Player9", lblMoneyPlayer9);
        playersDataPanel.add(lblMoneyPlayer9);
        
        JLabel lblMoneyPlayer10 = new JLabel("");
        lblMoneyPlayer10.setVerticalAlignment(SwingConstants.TOP);
        lblMoneyPlayer10.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoneyPlayer10.setForeground(new Color(255, 255, 204));
        lblMoneyPlayer10.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblMoneyPlayer10.setBackground(new Color(102, 153, 102));
        mapLblMoney.put("Player10", lblMoneyPlayer10);
        playersDataPanel.add(lblMoneyPlayer10);
        
        JLabel lblActionPlayer1 = new JLabel("");
        lblActionPlayer1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblActionPlayer1.setForeground(new Color(255, 255, 153));
        lblActionPlayer1.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblActionPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblAction.put("Player1", lblActionPlayer1);
        playersDataPanel.add(lblActionPlayer1);
        
        JLabel lblActionPlayer2 = new JLabel("");
        lblActionPlayer2.setVerticalAlignment(SwingConstants.BOTTOM);
        lblActionPlayer2.setForeground(new Color(255, 255, 153));
        lblActionPlayer2.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblActionPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblAction.put("Player2", lblActionPlayer2);
        playersDataPanel.add(lblActionPlayer2);
        
        JLabel lblActionPlayer3 = new JLabel("");
        lblActionPlayer3.setVerticalAlignment(SwingConstants.BOTTOM);
        lblActionPlayer3.setForeground(new Color(255, 255, 153));
        lblActionPlayer3.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblActionPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblAction.put("Player3", lblActionPlayer3);
        playersDataPanel.add(lblActionPlayer3);
        
        JLabel lblActionPlayer4 = new JLabel("");
        lblActionPlayer4.setVerticalAlignment(SwingConstants.BOTTOM);
        lblActionPlayer4.setForeground(new Color(255, 255, 153));
        lblActionPlayer4.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblActionPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblAction.put("Player4", lblActionPlayer4);
        playersDataPanel.add(lblActionPlayer4);
        
        JLabel lblActionPlayer5 = new JLabel("");
        lblActionPlayer5.setVerticalAlignment(SwingConstants.BOTTOM);
        lblActionPlayer5.setForeground(new Color(255, 255, 153));
        lblActionPlayer5.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblActionPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblAction.put("Player5", lblActionPlayer5);
        playersDataPanel.add(lblActionPlayer5);
        
        JLabel lblActionPlayer6 = new JLabel("");
        lblActionPlayer6.setVerticalAlignment(SwingConstants.BOTTOM);
        lblActionPlayer6.setForeground(new Color(255, 255, 153));
        lblActionPlayer6.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblActionPlayer6.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblAction.put("Player6", lblActionPlayer6);
        playersDataPanel.add(lblActionPlayer6);
        
        JLabel lblActionPlayer7 = new JLabel("");
        lblActionPlayer7.setVerticalAlignment(SwingConstants.BOTTOM);
        lblActionPlayer7.setForeground(new Color(255, 255, 153));
        lblActionPlayer7.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblActionPlayer7.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblAction.put("Player7", lblActionPlayer7);
        playersDataPanel.add(lblActionPlayer7);
        
        JLabel lblActionPlayer8 = new JLabel("");
        lblActionPlayer8.setVerticalAlignment(SwingConstants.BOTTOM);
        lblActionPlayer8.setForeground(new Color(255, 255, 153));
        lblActionPlayer8.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblActionPlayer8.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblAction.put("Player8", lblActionPlayer8);
        playersDataPanel.add(lblActionPlayer8);
        
        JLabel lblActionPlayer9 = new JLabel("");
        lblActionPlayer9.setVerticalAlignment(SwingConstants.BOTTOM);
        lblActionPlayer9.setForeground(new Color(255, 255, 153));
        lblActionPlayer9.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblActionPlayer9.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblAction.put("Player9", lblActionPlayer9);
        playersDataPanel.add(lblActionPlayer9);
        
        JLabel lblActionPlayer10 = new JLabel("");
        lblActionPlayer10.setVerticalAlignment(SwingConstants.BOTTOM);
        lblActionPlayer10.setForeground(new Color(255, 255, 153));
        lblActionPlayer10.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblActionPlayer10.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblAction.put("Player10", lblActionPlayer10);
        playersDataPanel.add(lblActionPlayer10);
        
        JLabel lblBetPlayer1 = new JLabel("");
        lblBetPlayer1.setVerticalAlignment(SwingConstants.TOP);
        lblBetPlayer1.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblBetPlayer1.setForeground(new Color(255, 255, 153));
        lblBetPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblBet.put("Player1", lblBetPlayer1);
        playersDataPanel.add(lblBetPlayer1);
        
        JLabel lblBetPlayer2 = new JLabel("");
        lblBetPlayer2.setVerticalAlignment(SwingConstants.TOP);
        lblBetPlayer2.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblBetPlayer2.setForeground(new Color(255, 255, 153));
        lblBetPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblBet.put("Player2", lblBetPlayer2);
        playersDataPanel.add(lblBetPlayer2);
        
        JLabel lblBetPlayer3 = new JLabel("");
        lblBetPlayer3.setVerticalAlignment(SwingConstants.TOP);
        lblBetPlayer3.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblBetPlayer3.setForeground(new Color(255, 255, 153));
        lblBetPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblBet.put("Player3", lblBetPlayer3);
        playersDataPanel.add(lblBetPlayer3);
        
        JLabel lblBetPlayer4 = new JLabel("");
        lblBetPlayer4.setVerticalAlignment(SwingConstants.TOP);
        lblBetPlayer4.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblBetPlayer4.setForeground(new Color(255, 255, 153));
        lblBetPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblBet.put("Player4", lblBetPlayer4);
        playersDataPanel.add(lblBetPlayer4);
        
        JLabel lblBetPlayer5 = new JLabel("");
        lblBetPlayer5.setVerticalAlignment(SwingConstants.TOP);
        lblBetPlayer5.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblBetPlayer5.setForeground(new Color(255, 255, 153));
        lblBetPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblBet.put("Player5", lblBetPlayer5);
        playersDataPanel.add(lblBetPlayer5);
        
        JLabel lblBetPlayer6 = new JLabel("");
        lblBetPlayer6.setVerticalAlignment(SwingConstants.TOP);
        lblBetPlayer6.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblBetPlayer6.setForeground(new Color(255, 255, 153));
        lblBetPlayer6.setHorizontalAlignment(SwingConstants.CENTER);
        playersDataPanel.add(lblBetPlayer6);
        
        JLabel lblBetPlayer7 = new JLabel("");
        lblBetPlayer7.setVerticalAlignment(SwingConstants.TOP);
        lblBetPlayer7.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblBetPlayer7.setForeground(new Color(255, 255, 153));
        lblBetPlayer7.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblBet.put("Player7", lblBetPlayer7);
        playersDataPanel.add(lblBetPlayer7);
        
        JLabel lblBetPlayer8 = new JLabel("");
        lblBetPlayer8.setVerticalAlignment(SwingConstants.TOP);
        lblBetPlayer8.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblBetPlayer8.setForeground(new Color(255, 255, 153));
        lblBetPlayer8.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblBet.put("Player8", lblBetPlayer8);
        playersDataPanel.add(lblBetPlayer8);
        
        JLabel lblBetPlayer9 = new JLabel("");
        lblBetPlayer9.setVerticalAlignment(SwingConstants.TOP);
        lblBetPlayer9.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblBetPlayer9.setForeground(new Color(255, 255, 153));
        lblBetPlayer9.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblBet.put("Player9", lblBetPlayer9);
        playersDataPanel.add(lblBetPlayer9);
        
        JLabel lblBetPlayer10 = new JLabel("");
        lblBetPlayer10.setVerticalAlignment(SwingConstants.TOP);
        lblBetPlayer10.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        lblBetPlayer10.setForeground(new Color(255, 255, 153));
        lblBetPlayer10.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblBet.put("Player10", lblBetPlayer10);
        playersDataPanel.add(lblBetPlayer10);
        
        JPanel cardsPlayer1 = new JPanel();
        cardsPlayer1.setBackground(new Color(35, 70, 35));
        playersDataPanel.add(cardsPlayer1);
        cardsPlayer1.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblCard1Player1 = new JLabel("");
        lblCard1Player1.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard1Player1.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card1Player1", lblCard1Player1);
        cardsPlayer1.add(lblCard1Player1);
        
        JLabel lblCard2Player1 = new JLabel("");
        lblCard2Player1.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard2Player1.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card2Player1", lblCard2Player1);
        cardsPlayer1.add(lblCard2Player1);
        
        JPanel cardsPlayer2 = new JPanel();
        cardsPlayer2.setBackground(new Color(35, 70, 35));
        playersDataPanel.add(cardsPlayer2);
        cardsPlayer2.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblCard1Player2 = new JLabel("");
        lblCard1Player2.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard1Player2.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card1Player2", lblCard1Player2);
        cardsPlayer2.add(lblCard1Player2);
        
        JLabel lblCard2Player2 = new JLabel("");
        lblCard2Player2.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard2Player2.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card2Player2", lblCard2Player2);
        cardsPlayer2.add(lblCard2Player2);
        
        JPanel cardsPlayer3 = new JPanel();
        cardsPlayer3.setBackground(new Color(35, 70, 35));
        playersDataPanel.add(cardsPlayer3);
        cardsPlayer3.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblCard1Player3 = new JLabel("");
        lblCard1Player3.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard1Player3.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card1Player3", lblCard1Player3);
        cardsPlayer3.add(lblCard1Player3);
        
        JLabel lblCard2Player3 = new JLabel("");
        lblCard2Player3.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard2Player3.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card2Player3", lblCard2Player3);
        cardsPlayer3.add(lblCard2Player3);
        
        JPanel cardsPlayer4 = new JPanel();
        cardsPlayer4.setBackground(new Color(35, 70, 35));
        playersDataPanel.add(cardsPlayer4);
        cardsPlayer4.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblCard1Player4 = new JLabel("");
        lblCard1Player4.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard1Player4.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card1Player4", lblCard1Player4);
        cardsPlayer4.add(lblCard1Player4);
        
        JLabel lblCard2Player4 = new JLabel("");
        lblCard2Player4.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard2Player4.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card2Player4", lblCard2Player4);
        cardsPlayer4.add(lblCard2Player4);
        
        JPanel cardsPlayer5 = new JPanel();
        cardsPlayer5.setBackground(new Color(35, 70, 35));
        playersDataPanel.add(cardsPlayer5);
        cardsPlayer5.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblCard1Player5 = new JLabel("");
        lblCard1Player5.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard1Player5.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card1Player5", lblCard1Player5);
        cardsPlayer5.add(lblCard1Player5);
        
        JLabel lblCard2Player5 = new JLabel("");
        lblCard2Player5.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard2Player5.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card2Player5", lblCard2Player5);
        cardsPlayer5.add(lblCard2Player5);
        
        JPanel cardsPlayer6 = new JPanel();
        cardsPlayer6.setBackground(new Color(35, 70, 35));
        playersDataPanel.add(cardsPlayer6);
        cardsPlayer6.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblCard1Player6 = new JLabel("");
        lblCard1Player6.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard1Player6.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card1Player6", lblCard1Player6);
        cardsPlayer6.add(lblCard1Player6);
        
        JLabel lblCard2Player6 = new JLabel("");
        lblCard2Player6.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard2Player6.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card2Player6", lblCard2Player6);
        cardsPlayer6.add(lblCard2Player6);
        
        JPanel cardsPlayer7 = new JPanel();
        cardsPlayer7.setBackground(new Color(35, 70, 35));
        playersDataPanel.add(cardsPlayer7);
        cardsPlayer7.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblCard1Player7 = new JLabel("");
        lblCard1Player7.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard1Player7.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card1Player7", lblCard1Player7);
        cardsPlayer7.add(lblCard1Player7);
        
        JLabel lblCard2Player7 = new JLabel("");
        lblCard2Player7.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard2Player7.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card2Player7", lblCard2Player7);
        cardsPlayer7.add(lblCard2Player7);
        
        JPanel cardsPlayer8 = new JPanel();
        cardsPlayer8.setBackground(new Color(35, 70, 35));
        playersDataPanel.add(cardsPlayer8);
        cardsPlayer8.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblCard1Player8 = new JLabel("");
        lblCard1Player8.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard1Player8.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card1Player8", lblCard1Player8);
        cardsPlayer8.add(lblCard1Player8);
        
        JLabel lblCard2Player8 = new JLabel("");
        lblCard2Player8.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard2Player8.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card2Player8", lblCard2Player8);
        cardsPlayer8.add(lblCard2Player8);
        
        JPanel cardsPlayer9 = new JPanel();
        cardsPlayer9.setBackground(new Color(35, 70, 35));
        playersDataPanel.add(cardsPlayer9);
        cardsPlayer9.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblCard1Player9 = new JLabel("");
        lblCard1Player9.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard1Player9.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card1Player9", lblCard1Player9);
        cardsPlayer9.add(lblCard1Player9);
        
        JLabel lblCard2Player9 = new JLabel("");
        lblCard2Player9.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard2Player9.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card2Player9", lblCard2Player9);
        cardsPlayer9.add(lblCard2Player9);
        
        JPanel cardsPlayer10 = new JPanel();
        cardsPlayer10.setBackground(new Color(35, 70, 35));
        playersDataPanel.add(cardsPlayer10);
        cardsPlayer10.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblCard1Player10 = new JLabel("");
        lblCard1Player10.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard1Player10.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card1Player10", lblCard1Player10);
        cardsPlayer10.add(lblCard1Player10);
        
        JLabel lblCard2Player10 = new JLabel("");
        lblCard2Player10.setFont(new Font("Cambria Math", Font.BOLD, 20));
        lblCard2Player10.setHorizontalAlignment(SwingConstants.CENTER);
        mapLblCardPlayer.put("Card2Player10", lblCard2Player10);
        cardsPlayer10.add(lblCard2Player10);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(35, 70, 35));
        infoPanel.setBounds(10, 200, 984, 50);
        frame.getContentPane().add(infoPanel);
        
        JLabel lblInfo = new JLabel("");
        lblInfo.setForeground(new Color(180, 255, 180));
        lblInfo.setFont(new Font("Cambria Math", Font.PLAIN, 22));
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(lblInfo, BorderLayout.CENTER);
                
        JPanel potValuePanel = new JPanel();
        potValuePanel.setBackground(new Color(35, 70, 35));
        potValuePanel.setBounds(10, 260, 984, 50);
        frame.getContentPane().add(potValuePanel);
        potValuePanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblPotText = new JLabel("Pot: $");
        lblPotText.setForeground(new Color(153, 255, 51));
        lblPotText.setFont(new Font("Cambria Math", Font.PLAIN, 35));
        lblPotText.setHorizontalAlignment(SwingConstants.RIGHT);
        potValuePanel.add(lblPotText);
        
        JLabel lblPotValue = new JLabel("0");
        lblPotValue.setHorizontalAlignment(SwingConstants.LEFT);
        lblPotValue.setForeground(new Color(153, 255, 51));
        lblPotValue.setFont(new Font("Cambria Math", Font.PLAIN, 35));
        potValuePanel.add(lblPotValue);
        
        JPanel communityCardsPanel = new JPanel();
        communityCardsPanel.setBackground(new Color(35, 70, 35));
        communityCardsPanel.setBounds(107, 320, 790, 200);
        frame.getContentPane().add(communityCardsPanel);
        communityCardsPanel.setLayout(new GridLayout(0, 5, 10, 0));
        
        JLabel lblFlopCard1 = new JLabel("");
        lblFlopCard1.setFont(new Font("SansSerif", Font.BOLD, 60));
        lblFlopCard1.setHorizontalAlignment(SwingConstants.CENTER);
        lblFlopCard1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        lblFlopCard1.setBackground(new Color(255, 255, 255));
        lblFlopCard1.setOpaque(false);
        mapLblCardCommunity.put("Card1Flop", lblFlopCard1);
        communityCardsPanel.add(lblFlopCard1);
        
        JLabel lblFlopCard2 = new JLabel("");
        lblFlopCard2.setFont(new Font("SansSerif", Font.BOLD, 60));
        lblFlopCard2.setHorizontalAlignment(SwingConstants.CENTER);
        lblFlopCard2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        lblFlopCard2.setBackground(new Color(255, 255, 255));
        lblFlopCard2.setOpaque(false);
        mapLblCardCommunity.put("Card2Flop", lblFlopCard2);
        communityCardsPanel.add(lblFlopCard2);
        
        JLabel lblFlopCard3 = new JLabel("");
        lblFlopCard3.setFont(new Font("SansSerif", Font.BOLD, 60));
        lblFlopCard3.setHorizontalAlignment(SwingConstants.CENTER);
        lblFlopCard3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        lblFlopCard3.setBackground(new Color(255, 255, 255));
        lblFlopCard3.setOpaque(false);
        mapLblCardCommunity.put("Card3Flop", lblFlopCard3);
        communityCardsPanel.add(lblFlopCard3);
        
        JLabel lblTurnCard = new JLabel("");
        lblTurnCard.setFont(new Font("SansSerif", Font.BOLD, 60));
        lblTurnCard.setHorizontalAlignment(SwingConstants.CENTER);
        lblTurnCard.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        lblTurnCard.setBackground(new Color(255, 255, 255));
        lblTurnCard.setOpaque(false);
        mapLblCardCommunity.put("Card1Turn", lblTurnCard);
        communityCardsPanel.add(lblTurnCard);
        
        JLabel lblRiverCard = new JLabel("");
        lblRiverCard.setFont(new Font("SansSerif", Font.BOLD, 60));
        lblRiverCard.setHorizontalAlignment(SwingConstants.CENTER);
        lblRiverCard.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        lblRiverCard.setBackground(new Color(255, 255, 255));
        lblRiverCard.setOpaque(false);
        mapLblCardCommunity.put("Card1River", lblRiverCard);
        communityCardsPanel.add(lblRiverCard);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(30, 70, 30));
        buttonsPanel.setBounds(10, 650, 700, 60);
        frame.getContentPane().add(buttonsPanel);
        buttonsPanel.setLayout(new GridLayout(0, 6, 20, 0));
        
        JButton btnCheck = new JButton("Check");
        btnCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnCheck.setForeground(new Color(30, 70, 30));
        btnCheck.setBackground(new Color(255, 255, 204));
        btnCheck.setFont(new Font("Cambria Math", Font.PLAIN, 22));
        buttonsPanel.add(btnCheck);
        
        JButton btnBet = new JButton("Bet");
        btnBet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnBet.setForeground(new Color(30, 70, 30));
        btnBet.setBackground(new Color(255, 255, 204));
        btnBet.setFont(new Font("Cambria Math", Font.PLAIN, 22));
        buttonsPanel.add(btnBet);
        
        JButton btnRaise = new JButton("Raise");
        btnRaise.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRaise.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnRaise.setForeground(new Color(30, 70, 30));
        btnRaise.setBackground(new Color(255, 255, 204));
        btnRaise.setFont(new Font("Cambria Math", Font.PLAIN, 22));
        buttonsPanel.add(btnRaise);
        
        JButton btnCall = new JButton("Call");
        btnCall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnCall.setForeground(new Color(30, 70, 30));
        btnCall.setBackground(new Color(255, 255, 204));
        btnCall.setFont(new Font("Cambria Math", Font.PLAIN, 22));
        buttonsPanel.add(btnCall);
        
        JButton btnFold = new JButton("Fold");
        btnFold.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnFold.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnFold.setForeground(new Color(30, 70, 30));
        btnFold.setBackground(new Color(255, 255, 204));
        btnFold.setFont(new Font("Cambria Math", Font.PLAIN, 22));
        buttonsPanel.add(btnFold);
        
        JButton btnAllIn = new JButton("All-in");
        btnAllIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAllIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnAllIn.setForeground(new Color(30, 70, 30));
        btnAllIn.setBackground(new Color(255, 255, 204));
        btnAllIn.setFont(new Font("Cambria Math", Font.PLAIN, 22));
        buttonsPanel.add(btnAllIn);
        
        JLabel lblBetRaiseDollar = new JLabel("$");
        lblBetRaiseDollar.setOpaque(true);
        lblBetRaiseDollar.setBackground(new Color(255, 255, 255));
        lblBetRaiseDollar.setFont(new Font("Cambria Math", Font.PLAIN, 28));
        lblBetRaiseDollar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBetRaiseDollar.setBounds(130, 589, 34, 50);
        frame.getContentPane().add(lblBetRaiseDollar);
        
        JSpinner spinnerBetRaise = new JSpinner();
        spinnerBetRaise.setVerifyInputWhenFocusTarget(false);
        spinnerBetRaise.setRequestFocusEnabled(false);
        spinnerBetRaise.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        spinnerBetRaise.setEditor(new JSpinner.DefaultEditor(spinnerBetRaise));
        spinnerTextField = ((DefaultEditor)spinnerBetRaise.getEditor()).getTextField();
        spinnerTextField.setBackground(new Color(255, 255, 255));
        spinnerTextField.setEditable(false);
        spinnerTextField.setHorizontalAlignment(JTextField.RIGHT);
        spinnerBetRaise.setFocusTraversalKeysEnabled(false);
        spinnerBetRaise.setFocusable(false);
        spinnerBetRaise.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        spinnerBetRaise.setToolTipText("set Bet or Raise value");
        spinnerBetRaise.setBorder(null);
        spinnerBetRaise.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(5)));
        spinnerBetRaise.setFont(new Font("Cambria Math", Font.PLAIN, 28));
        spinnerBetRaise.setBounds(161, 589, 189, 50);
        frame.getContentPane().add(spinnerBetRaise);
        
        JPanel playerCardsPanel = new JPanel();
        playerCardsPanel.setBackground(new Color(35, 70, 35));
        playerCardsPanel.setBounds(740, 570, 250, 150);
        frame.getContentPane().add(playerCardsPanel);
        playerCardsPanel.setLayout(new GridLayout(0, 2, 10, 0));
        
        lblCard1 = new JLabel("");
        lblCard1.setForeground(new Color(0, 0, 0));
        lblCard1.setFont(new Font("SansSerif", Font.BOLD, 50));
        lblCard1.setHorizontalAlignment(SwingConstants.CENTER);
        lblCard1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        lblCard1.setBackground(new Color(255, 255, 255));
        lblCard1.setOpaque(true);
        playerCardsPanel.add(lblCard1);
        
        lblCard2 = new JLabel("");
        lblCard2.setForeground(new Color(0, 0, 0));
        lblCard2.setFont(new Font("SansSerif", Font.BOLD, 50));
        lblCard2.setHorizontalAlignment(SwingConstants.CENTER);
        lblCard2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        lblCard2.setBackground(new Color(255, 255, 255));
        lblCard2.setOpaque(true);
        playerCardsPanel.add(lblCard2);
        
    }

    private String getBid() {
        String bid = spinnerTextField.getText();
        spinnerTextField.setText(null);
        return bid;
    }
    
    private void setDealer(String playerAndIndex) {
        JLabel label = mapLblDealer.get(playerAndIndex);
        for(JLabel lbl : mapLblDealer.values()) {
            if(lbl != label) {
                lbl.setText("");
            }
            else {
                lbl.setText("Dealer");
            }
        }
    }
    
    private void setName(String playerAndIndex, String name) {
        JLabel label = mapLblName.get(playerAndIndex);
        label.setText(name);
    }
    
    private void setMoney(String playerAndIndex, String money) {
        JLabel label = mapLblMoney.get(playerAndIndex);
        label.setText("$" + money);
    }
    
    private void setAction(String playerAndIndex, String lastAction) {
        JLabel label = mapLblAction.get(playerAndIndex);
        label.setText(lastAction);
    }
    
    private void setBet(String playerAndIndex, int bet){
        JLabel label = mapLblBet.get(playerAndIndex);
        label.setText("$" + bet);
    }
    
    private void setCardPlayer(String cardAndIndexPlayerAndIndex, String rankSuit) {
        // ♠♣♥♦
        JLabel label = mapLblCardPlayer.get(cardAndIndexPlayerAndIndex);
        label.setText(rankSuit);
        if(rankSuit.endsWith("♦") || rankSuit.endsWith("♥")) {
            label.setForeground(new Color(180, 20, 20));
        }
        else {
            label.setForeground(new Color(20, 20, 20));
        }
    }
    
    private void setCardCommunity(String cardAndIndexDealName, String rankSuit) {
        // ♠♣♥♦
        JLabel label = mapLblCardCommunity.get(cardAndIndexDealName);
        label.setText(rankSuit);
        if(rankSuit.endsWith("♦") || rankSuit.endsWith("♥")) {
            label.setForeground(new Color(180, 20, 20));
        }
        else {
            label.setForeground(new Color(20, 20, 20));
        }
        if(rankSuit != "") {
            label.setOpaque(true);
        }
        else {
            label.setOpaque(false);
        }
    }
    
    private void setCards(String card1RankSuit, String card2RankSuit) {
        if(card1RankSuit.endsWith("♦") || card1RankSuit.endsWith("♥")) {
            lblCard1.setForeground(new Color(180, 20, 20));
        }
        else {
            lblCard1.setForeground(new Color(20, 20, 20));
        }
        if(card2RankSuit.endsWith("♦") || card2RankSuit.endsWith("♥")) {
            lblCard2.setForeground(new Color(180, 20, 20));
        }
        else {
            lblCard2.setForeground(new Color(20, 20, 20));
        }
        lblCard1.setText(card1RankSuit);
        lblCard2.setText(card2RankSuit);
    }
}

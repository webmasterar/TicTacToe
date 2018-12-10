package ttt;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The classic TicTacToe game
 * 
 * @author Ahmad Retha
 * @license MIT
 * @date 29/11/2018
 */
public class TicTacToe extends JFrame implements ActionListener {

    private int dimension;
    private boolean turn;
    private short turnsTaken;
    private int draws, oWins, xWins;
    private JLabel drawsLbl, xWinsLbl, oWinsLbl;
    private JButton[][] board;
    
    public TicTacToe() {
        //init
        super("TicTacToe");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        this.dimension = 300;
        this.turnsTaken = 0;
        this.draws = 0;
        this.oWins = 0;
        this.xWins = 0;
        this.turn = true;
        //board
        JPanel boardPnl = new JPanel(new GridLayout(3, 3));
        boardPnl.setPreferredSize(new Dimension(this.dimension, this.dimension));
        this.add(boardPnl, BorderLayout.CENTER);
        this.board = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = new JButton();
                this.board[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                this.board[i][j].addActionListener(this);
                boardPnl.add(this.board[i][j]);
            }
        }
        //scores
        JPanel scoresPnl = new JPanel(new GridLayout(1, 6));
        this.add(scoresPnl, BorderLayout.SOUTH);
        JLabel dLbl = new JLabel("Draws:");
        dLbl.setHorizontalAlignment(JLabel.CENTER);
        scoresPnl.add(dLbl);
        this.drawsLbl = new JLabel(String.valueOf(draws));
        this.drawsLbl.setHorizontalAlignment(JLabel.CENTER);
        scoresPnl.add(this.drawsLbl);
        JLabel xLbl = new JLabel("X wins:");
        xLbl.setHorizontalAlignment(JLabel.CENTER);
        scoresPnl.add(xLbl);
        this.xWinsLbl = new JLabel(String.valueOf(xWins));
        this.xWinsLbl.setHorizontalAlignment(JLabel.CENTER);
        scoresPnl.add(this.xWinsLbl);
        JLabel oLbl = new JLabel("O wins:");
        oLbl.setHorizontalAlignment(JLabel.CENTER);
        scoresPnl.add(oLbl);
        this.oWinsLbl = new JLabel(String.valueOf(oWins));
        this.oWinsLbl.setHorizontalAlignment(JLabel.CENTER);
        scoresPnl.add(this.oWinsLbl);
        //display
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if (b.getText().equals("")) {
            b.setText((this.turn) ? "X" : "O");
            this.turn = !this.turn;
            if (this.checkForWinner()) {
                this.gameOver();
            } else {
                this.turnsTaken++;
                if (this.turnsTaken == 9) {
                    this.draws += 1;
                    this.drawsLbl.setText(String.valueOf(this.draws));
                    this.gameOver();
                }
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j].setText("");
            }
        }
        this.turnsTaken = 0;
    }

    private boolean checkForWinner() {
        String winner = "";
        //row by row
        for (int i = 0; i < 3; i++) {
            String t = this.board[i][0].getText();
            if (!t.equals("") && this.board[i][1].getText().equals(t) && this.board[i][2].getText().equals(t)) {
                winner = t;
            }
        }
        //col by col
        for (int j = 0; j < 3; j++) {
            String t = this.board[0][j].getText();
            if (!t.equals("") && this.board[1][j].getText().equals(t) && this.board[2][j].getText().equals(t)) {
                winner = t;
            }
        }
        //diagonals
        String c = this.board[1][1].getText();
        if (!c.equals("")) {
            if ((c.equals(this.board[0][0].getText()) && c.equals(this.board[2][2].getText())) ||
                (c.equals(this.board[0][2].getText()) && c.equals(this.board[2][0].getText()))
            ) {
                winner = c;
            }
        }
        //declare winner
        if (!winner.equals("")) {
            JOptionPane.showMessageDialog(this, "The winner is " + winner + "!");
            if (winner.equals("X")) {
                this.xWins += 1;
                this.xWinsLbl.setText(String.valueOf(this.xWins));
            } else {
                this.oWins += 1;
                this.oWinsLbl.setText(String.valueOf(this.oWins));
            }
            return true;
        }
        return false;
    }

    private void gameOver() {
        int reset = JOptionPane.showConfirmDialog(this, "Game Over!\nPlay again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (reset == 0) {
            this.reset();
        } else {
            this.dispose();
            System.exit(0);
        }
    }
}

package ttt;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    private JButton[][] board;
    
    public TicTacToe() {
        super("TicTacToe");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new GridLayout(3, 3));
        this.dimension = 300;
        this.turnsTaken = 0;
        this.turn = true;
        this.board = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = new JButton();
                this.board[i][j].setFont(new Font("Arial", Font.PLAIN, 36));
                this.board[i][j].addActionListener(this);
                this.add(this.board[i][j]);
            }
        }
        super.setSize(this.dimension, this.dimension);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
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
        String tl = this.board[0][0].getText();
        if (!tl.equals("") && this.board[1][1].getText().equals(tl) && this.board[2][2].getText().equals(tl)) {
            winner = tl;
        }
        String tr = this.board[0][2].getText();
        if (!tr.equals("") && this.board[1][1].getText().equals(tr) && this.board[2][0].getText().equals(tr)) {
            winner = tr;
        }
        //declare winner
        if (!winner.equals("")) {
            JOptionPane.showMessageDialog(this, "The winner is " + winner + "!");
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

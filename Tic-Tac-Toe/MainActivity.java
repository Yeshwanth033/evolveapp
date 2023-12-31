import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private TextView currentPlayerTextView;
    private Button resetButton;

    private boolean isPlayerOneTurn = true;
    private String currentPlayer = "X";
    private String[][] board = new String[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        currentPlayerTextView = findViewById(R.id.currentPlayerTextView);
        resetButton = findViewById(R.id.resetButton);

        initializeGame();
    }

    private void initializeGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }

        currentPlayer = "X";
        isPlayerOneTurn = true;

        updateUI();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof Button) {
                final Button button = (Button) child;
                final int row = GridLayout.spec(i / 3).getLine();
                final int col = GridLayout.spec(i % 3).getLine();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onGridItemClick(row, col, button);
                    }
                });
            }
        }
    }

    private void onGridItemClick(int row, int col, Button button) {
        if (board[row][col].isEmpty()) {
            board[row][col] = currentPlayer;
            checkWinner(row, col);
            switchPlayer();
            updateUI();
        }
    }

    private void switchPlayer() {
        isPlayerOneTurn = !isPlayerOneTurn;
        currentPlayer = isPlayerOneTurn ? "X" : "O";
    }

    private void checkWinner(int row, int col) {
        // TODO: Implement winner-checking logic
        // Update UI or show a dialog if there's a winner or a tie
    }

    private void updateUI() {
        currentPlayerTextView.setText("Current Player: " + currentPlayer);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                int row = GridLayout.spec(i / 3).getLine();
                int col = GridLayout.spec(i % 3).getLine();
                button.setText(board[row][col]);
            }
        }
    }

    private void showWinnerDialog(String winner) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");
        builder.setMessage(winner.equals("Tie") ? "It's a tie!" : "Player " + winner + " wins!");
        builder.setPositiveButton("Play Again", (dialogInterface, i) -> resetGame());
        builder.create().show();
    }

    private void resetGame() {
        initializeGame();
    }
}

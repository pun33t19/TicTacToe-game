package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons=new Button[3][3];
    private boolean player1turn=true;

    private int roundcount;

    private int player1count;
    private int player2count;

    private TextView textviewplayer1 ;
    private TextView textviewplayer2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewplayer1=findViewById(R.id.TextViewp1);
        textviewplayer2=findViewById(R.id.TextViewp2);

        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonid="btn_"+i+j;
                int resID=getResources().getIdentifier(buttonid,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button resetbutton;
        resetbutton=findViewById(R.id.btn_reset);
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
        if(player1turn){
            ((Button) v).setText("X");
        }
        else
            ((Button)v).setText("O");
        roundcount++;

        if(checkwin()){
            if (player1turn){
                player1win();
            }
            else{
                player2win();
            }
        }
        else if(roundcount==9){
            draw();
        }
        else{
            player1turn=!player1turn;
        }
    }

    private boolean checkwin(){
        String[][] field=new String[3][3];
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for (int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1]) && field
            [i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }
        for (int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i]) && field
                    [0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }
        if(field[0][0].equals(field[1][1]) && field
                [0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }
        if(field[0][2].equals(field[1][1]) && field
                [0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }
        return false;


    }

    private void player1win(){
        player1count++;
        Toast.makeText(this, "Player 1 wins!!", Toast.LENGTH_SHORT).show();
        updateplayerscore();
        resetgame();
    }


    private void player2win(){
        player2count++;
        Toast.makeText(this, "Player 2 wins!!", Toast.LENGTH_SHORT).show();
        updateplayerscore();
        resetgame();}

    private void draw(){
        Toast.makeText(this, "draw!!", Toast.LENGTH_SHORT).show();
        resetgame();
    }

    private void updateplayerscore(){
        textviewplayer1.setText("Player 1:"+player1count);
        textviewplayer2.setText("Player 2:"+player2count);
    }

    private void resetgame(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundcount=0;
        player1turn=true;
    }
    private void newGame(){
        player1count=0;
        player2count=0;
        updateplayerscore();
        resetgame();
    }

}
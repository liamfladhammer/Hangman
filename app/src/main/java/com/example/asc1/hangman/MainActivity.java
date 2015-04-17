package com.example.asc1.hangman;

        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import java.util.ArrayList;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    EditText textInput;
    TextView numTriesLeft,theWord, gameOver, wordTriesLeft;
    ImageView man;
    ArrayList<String> text=setWords();
    String actual;
    int lives=6;
    String converted=getWord();
    Button theButton;
    boolean letterFound;


    OnClickListener buttonListener=new OnClickListener(){
        public void onClick(View v){
            try {
                String s=textInput.getText().toString();
                for(char c:s.toCharArray()) {
                    play(c);
                    numTriesLeft.setText("" + lives);
                    theWord.setText(converted);
                    textInput.setText("");
                }
            }
            catch(IndexOutOfBoundsException e){

            }
       }
   };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInput=(EditText)findViewById(R.id.editText);

        numTriesLeft=(TextView)findViewById(R.id.numTries);
        numTriesLeft.setText(""+lives);

        theWord=(TextView)findViewById(R.id.theWord);
        theWord.setText(new String(converted));

        gameOver=(TextView)findViewById(R.id.gameOver);

        wordTriesLeft=(TextView)findViewById(R.id.triesLeft);

        theButton=(Button)findViewById(R.id.submit);
        theButton.setOnClickListener(buttonListener);

        man=(ImageView)findViewById(R.id.man);
        letterFound=false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   public String convert(String s){
        String dashes=new String();
        for(int i=0;i<s.length();i++){
            dashes+="-";
        }
        return dashes;
    }

    public ArrayList<String> setWords(){
        ArrayList<String> words=new ArrayList<String>();
        words.add("acres");
        words.add("adult");
        words.add("advice");
        return words;
    }

    public String getWord(){
        int wordIndex=(int)(Math.random()*3);
        actual=text.get(wordIndex);
        return convert(actual);
    }

    public void play(char c) {
        char[] convertedArray=converted.toCharArray();
        letterFound=false;
        if(lives>0) {
            for (int i = 0; i < actual.length(); i++) {
                if (actual.charAt(i) == c) {
                    convertedArray[i] = c;
                    letterFound=true;
                }
            }
            if(!letterFound){
                lives--;
                numTriesLeft.setText(lives+"");
                if(lives==5)
                    man.setImageResource(R.drawable.man1);
                if(lives==4)
                    man.setImageResource(R.drawable.man2);
                if(lives==3)
                    man.setImageResource(R.drawable.man3);
                if(lives==2)
                    man.setImageResource(R.drawable.man4);
                if(lives==1)
                    man.setImageResource(R.drawable.man5);
                if(lives==0) {
                    man.setImageResource(R.drawable.man6);
                    gameOver.setVisibility(View.VISIBLE);
                    theButton.setVisibility(View.INVISIBLE);
                    numTriesLeft.setVisibility(View.INVISIBLE);
                    wordTriesLeft.setVisibility(View.INVISIBLE);
                    textInput.setVisibility(View.INVISIBLE);
                }
            }
            converted=new String(convertedArray);
            if(!converted.contains("-")) {
                gameOver.setText("YOU WIN");
                gameOver.setVisibility(View.VISIBLE);
                theButton.setVisibility(View.INVISIBLE);
                numTriesLeft.setVisibility(View.INVISIBLE);
                wordTriesLeft.setVisibility(View.INVISIBLE);
                textInput.setVisibility(View.INVISIBLE);
            }
        }
    }


}






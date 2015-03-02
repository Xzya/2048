package app.android.xzya.ro.twothousandfourtyeight;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private final int n = 4;
        private TextView score;
        private View view;
        public static Button[] gridIDs;
        private LinearLayout grid;

        private Game game;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.test, container, false);

            gridIDs = new Button[n*n];
            score = (TextView)rootView.findViewById(R.id.score);
            view = (View)rootView.findViewById(R.id.view1);
            gridIDs[0] = (Button)rootView.findViewById(R.id.g00);
            gridIDs[1] = (Button)rootView.findViewById(R.id.g01);
            gridIDs[2] = (Button)rootView.findViewById(R.id.g02);
            gridIDs[3] = (Button)rootView.findViewById(R.id.g03);
            gridIDs[4] = (Button)rootView.findViewById(R.id.g10);
            gridIDs[5] = (Button)rootView.findViewById(R.id.g11);
            gridIDs[6] = (Button)rootView.findViewById(R.id.g12);
            gridIDs[7] = (Button)rootView.findViewById(R.id.g13);
            gridIDs[8] = (Button)rootView.findViewById(R.id.g20);
            gridIDs[9] = (Button)rootView.findViewById(R.id.g21);
            gridIDs[10] = (Button)rootView.findViewById(R.id.g22);
            gridIDs[11] = (Button)rootView.findViewById(R.id.g23);
            gridIDs[12] = (Button)rootView.findViewById(R.id.g30);
            gridIDs[13] = (Button)rootView.findViewById(R.id.g31);
            gridIDs[14] = (Button)rootView.findViewById(R.id.g32);
            gridIDs[15] = (Button)rootView.findViewById(R.id.g33);
//            grid = (LinearLayout)rootView.findViewById(R.id.grid);

            view.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
                public void onSwipeTop() {
                    //				Toast.makeText(GameActivity.this, "top", Toast.LENGTH_SHORT).show();
                    game.up();
                    refreshGrid();
                }
                public void onSwipeRight() {
                    //				Toast.makeText(GameActivity.this, "right", Toast.LENGTH_SHORT).show();
                    game.right();
                    refreshGrid();
                }
                public void onSwipeLeft() {
                    //				Toast.makeText(GameActivity.this, "left", Toast.LENGTH_SHORT).show();
                    game.left();
                    refreshGrid();
                }
                public void onSwipeBottom() {
                    //				Toast.makeText(GameActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                    game.down();
                    refreshGrid();
                }
            });

            game = new Game();
            refreshGrid();




            return rootView;
        }


        public void refreshGrid(){

//            TransitionManager.beginDelayedTransition(grid);
//
//            RelativeLayout.LayoutParams positionRules = new RelativeLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
//            );

//            gridIDs[0].setBackgroundColor(Color.BLACK);
//
//            float originalX = gridIDs[0].getX(), newX = gridIDs[1].getX(), originalY = gridIDs[0].getY(), newY = gridIDs[1].getY();
//
//            TranslateAnimation animation = new TranslateAnimation(originalX-10, newX-10, originalY-10, newY-10);
//            animation.setDuration(300);
//
//            gridIDs[0].startAnimation(animation);

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){

                }
            }
            score.setText("Score:\n" + String.valueOf(game.getScore()));

            if (game.isGameOver()){
                new AlertDialog.Builder(getActivity())
                        .setTitle("Game Over")
                        .setMessage("Do you want to start over?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                game = new Game();
                                refreshGrid();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                getActivity().finish();
                            }
                        })
                        .show();
            }
        }


    }

    private static class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context ctx){
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                        }
                        result = true;
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                    }
                    result = true;

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public void onSwipeRight() {
        }

        public void onSwipeLeft() {
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }

        //		@Override
        //		public boolean onTouch(View v, MotionEvent event) {
        //			// TODO Auto-generated method stub
        //			return false;
        //		}
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }
}

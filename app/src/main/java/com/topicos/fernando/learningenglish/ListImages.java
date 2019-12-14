package com.topicos.fernando.learningenglish;

public class ListImages {

    public static MyImage[] getBodyImages(){
        return new MyImage[]{
                new MyImage(R.drawable.b_arm, "arm"),
                new MyImage(R.drawable.b_back, "back"),
                new MyImage(R.drawable.b_ear, "ear"),
                new MyImage(R.drawable.b_elbow, "elbow"),
                new MyImage(R.drawable.b_eye, "eye"),
                new MyImage(R.drawable.b_fingers, "fingers"),
                new MyImage(R.drawable.b_foot, "foot"),
                new MyImage(R.drawable.b_hair, "hair"),
                new MyImage(R.drawable.b_hand, "hand"),
                new MyImage(R.drawable.b_knee, "knee"),
                new MyImage(R.drawable.b_nose, "nose"),
                new MyImage(R.drawable.b_stomach, "stomach"),
                new MyImage(R.drawable.b_teeth, "teeth"),
                new MyImage(R.drawable.b_tongue, "tonge"),
        };
    }

    public static MyImage[] getFruitImages(){
        return new MyImage[]{
                new MyImage(R.drawable.f_apple, "apple"),
                new MyImage(R.drawable.f_cantaloupe, "cantaloupe"),
                new MyImage(R.drawable.f_lemon, "lemon"),
                new MyImage(R.drawable.f_mango, "mango"),
                new MyImage(R.drawable.f_orange, "orange"),
                new MyImage(R.drawable.f_papaya, "papaya"),
                new MyImage(R.drawable.f_pineapple, "pineapple"),
                new MyImage(R.drawable.f_pear, "pineapple"),
                new MyImage(R.drawable.f_peach, "peach"),
                new MyImage(R.drawable.f_watermelon, "watermelon")
        };
    }

}

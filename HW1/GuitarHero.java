import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    public static void main(String[] args) {
        /*  Use the following 37 keys to represent the keyboard, from lowest note to highest note.
         * This keyboard arrangement imitates a piano keyboard:
         * The “white keys” are on the qwerty and zxcv rows
         * and the “black keys” on the 12345 and asdf rows of the keyboard.*/
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[keyboard.length()];

        for (int i = 0; i < keyboard.length(); i++) {
            double frequency = 440.0 * Math.pow(2, (i-24.0) / 12.0);
            strings[i] = new GuitarString(frequency);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                /* check if the key pressed is on the keyboard; if so, pluck the string */
                if (keyboard.contains(String.valueOf(key))) {
                    strings[keyboard.indexOf(key)].pluck();
                    ;
                }
            }


            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString string : strings){
                sample += string.sample();
                /* advance the simulation of each guitar string by one step */
                string.tic();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

        }
    }
}

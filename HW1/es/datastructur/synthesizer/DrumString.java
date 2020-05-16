package es.datastructur.synthesizer;

public class DrumString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 1.0; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a drum string of the given frequency.  */
    public DrumString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int)Math.round(SR / frequency));
        for (int i = 0; i < buffer.capacity(); i++){
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the drum string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < buffer.capacity(); i++){
            buffer.dequeue();
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        Double item = buffer.dequeue();
        /* Flipping the sign of a new value with probability 0.5 before enqueueing it in tic()
         * will produce a drum sound. */
        buffer.enqueue(Math.signum(Math.random())*(item + buffer.peek()) / 2 * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}

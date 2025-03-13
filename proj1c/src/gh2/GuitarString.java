package gh2;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import java.lang.Math;
import javax.sound.midi.*;
// TODO: maybe more imports

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    // TODO: uncomment the following line once you're ready to start this portion
     private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new LinkedListDeque61B<>();
        int shouldbesize = (int) Math.round(SR / frequency);
        for (int i = 1; i <= shouldbesize;i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        Deque61B<Double> buffer2 = new LinkedListDeque61B<>();
        int size = buffer.size();
        for (int i = 0; i < size; i++){
            double randomNoise = Math.random() - 0.5;
            buffer2.addLast(randomNoise);
        }
        buffer = buffer2;
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
        double newDouble;
        double front = buffer.removeFirst();
        double next = buffer.get(1);
        newDouble = (front + next) * (0.5) * DECAY;
        buffer.addLast(newDouble);
        }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.get(1);
    }
}

    // TODO: Remove all comments that say TODO when you're done.

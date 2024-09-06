package no.uib.inf101.tetris.midi;

import java.io.InputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

/**
 * Play the Tetris music. Sample usage:
 * <code>
 * TetrisSong music = new TetrisSong();
 * music.run(); 
 * </code>
 */
public class TetrisSong implements Runnable {
    private static final String TETRISMUSIC = "pirates.midi";
    private Sequencer sequencer;

    @Override
    public void run() {
        InputStream song = TetrisSong.class.getClassLoader().getResourceAsStream(TETRISMUSIC);
        this.doPlayMidi(song, true);
    }

    private void doPlayMidi(final InputStream is, final boolean loop) {
        try {
            this.doStopMidiSounds();
            (this.sequencer = MidiSystem.getSequencer()).setSequence(MidiSystem.getSequence(is));
            if (loop) {
                this.sequencer.setLoopCount(-1);
            }
            this.sequencer.open();
            this.sequencer.start();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }

    public void doStopMidiSounds() {
        try {
            if (this.sequencer == null || !this.sequencer.isRunning()) {
                return;
            }
            this.sequencer.stop();
            this.sequencer.close();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
        this.sequencer = null;
    }

    public void doPauseMidiSounds() {
        try {
            if (this.sequencer == null || !this.sequencer.isRunning()) {
                return;
            }
            this.sequencer.stop();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }
    
    public void doUnpauseMidiSounds() {
        try {
            if (this.sequencer == null) {
                return;
            }
            this.sequencer.start();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }

    private void midiError(final String msg) {
        System.err.println("Midi error: " + msg);
        this.sequencer = null;
    }
}
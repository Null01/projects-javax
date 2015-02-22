package jfx_lab03_hilos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author andresfelipegarciaduran
 */
public class ProccessAccess extends Thread {

    private final List<Thread> processExecute;

    public ProccessAccess(Thread... proccess) {
        processExecute = new ArrayList<>();
        processExecute.addAll(Arrays.asList(proccess));
    }

    @Override
    public synchronized void run() {
        int index = 0, timer = 0;
        switch_step(index);
        while (true) {
            ++timer;
            if (timer - 1 == ITags.TimeProcess) {
                index = (index + 1) % processExecute.size();
                switch_step(index);
                timer = 0;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }

        }
    }

    private void switch_step(int i) {
        Main.output.appendText(processExecute.get(i).getName() + "\n");
        switch (i) {
            case 0:
                Main.fadeTransitionRedLight.play();
                Main.fadeTransitionGreenLight.stop();
                break;
            case 1:
                Main.fadeTransitionGreenLight.play();
                Main.fadeTransitionRedLight.stop();
                break;
        }
        String[] splits = processExecute.get(i).getName().split(" ");
        int next_thread = Integer.parseInt(splits[1]) + 1;
        processExecute.get(i).setName(splits[0] + " " + next_thread);
    }
}

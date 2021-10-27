package club.deltapvp.deltacore.api.utilities;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task {

    /**
     * Run a task on the main thread
     *
     * @param runnable
     */
    public void run(Runnable runnable) {
        runDelayed(runnable, 0);
    }

    /**
     * Runs a delayed task in Minecraft Ticks
     *
     * @param runnable
     * @param ticks
     */
    public void runDelayed(Runnable runnable, int ticks) {
        runDelayed(runnable, ticks * 50L);
    }

    /**
     * Runs a delayed task in {@link java.util.concurrent.TimeUnit#MILLISECONDS}
     *
     * @param runnable
     * @param mills
     */
    @SneakyThrows
    public void runDelayed(Runnable runnable, long mills) {
        TaskRunnable taskRunnable = new TaskRunnable(runnable, mills, 0);
        taskRunnable.run();
    }

    /**
     * Run a repeating task in Minecraft Ticks
     *
     * @param runnable
     * @param ticks
     */
    public TaskRunnable runRepeating(Runnable runnable, int ticks) {
        return runRepeating(runnable, ticks * 50L);
    }

    /**
     * Run a repeating task in Minecraft Ticks
     *
     * @param runnable
     * @param mills
     */
    public TaskRunnable runRepeating(Runnable runnable, long mills) {
        TaskRunnable taskRunnable = new TaskRunnable(runnable, 0, mills);
        taskRunnable.run();

        return taskRunnable;
    }

    /**
     * Run a task asynchronously
     *
     * @param runnable
     */
    public void runAsync(Runnable runnable) {
        runDelayedAsync(runnable, 0);
    }

    /**
     * Run a delayed asynchronous task in Minecraft Ticks
     *
     * @param runnable
     * @param ticks
     */
    public void runDelayedAsync(Runnable runnable, int ticks) {
        runDelayedAsync(runnable, ticks * 50L);
    }

    /**
     * Run a delayed asynchronous task in {@link java.util.concurrent.TimeUnit#MILLISECONDS}
     *
     * @param runnable
     * @param mills
     */
    @SneakyThrows
    public void runDelayedAsync(Runnable runnable, long mills) {
        TaskRunnable taskRunnable = new TaskRunnable(runnable, mills, 0);
        Thread thread = new Thread(taskRunnable);
        thread.start();
    }

    /**
     * Run an asynchronous repeating task in Minecraft Ticks
     *
     * @param runnable
     * @param ticks
     */
    public TaskRunnable runRepeatingAsync(Runnable runnable, int ticks) {
        return runRepeatingAsync(runnable, ticks * 50L);
    }

    /**
     * Run an asynchronous repeating task in {@link java.util.concurrent.TimeUnit#MILLISECONDS}
     *
     * @param runnable
     * @param mills
     */
    public TaskRunnable runRepeatingAsync(Runnable runnable, long mills) {
        TaskRunnable taskRunnable = new TaskRunnable(runnable, 0, mills);
        Thread thread = new Thread(taskRunnable);
        thread.start();

        return taskRunnable;
    }


    private class TaskRunnable implements Runnable {

        private final Runnable runnable;
        private final long delay;
        private final long repeating;
        private boolean stopped = false;

        public TaskRunnable(Runnable runnable, long delayInMills, long repeatingInMills) {
            this.runnable = runnable;
            this.delay = delayInMills;
            this.repeating = repeatingInMills;
        }

        @SneakyThrows
        @Override
        public void run() {
            if (delay != 0)
                Thread.sleep(delay);

            if (repeating == 0) {
                runnable.run();
            } else {
                while (!stopped) {
                    runnable.run();
                    Thread.sleep(repeating);
                }
            }
        }

        public void cancel() {
            this.stopped = true;
        }
    }
}

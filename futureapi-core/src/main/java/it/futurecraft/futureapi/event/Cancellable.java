package it.futurecraft.futureapi.event;

/**
 * A cancellable event.
 */
public interface Cancellable {
    /**
     * Cancel the event.
     */
    void cancel();

    /**
     * Check whether the event has been cancelled or not.
     *
     * @return <code>true</code> If the event has been cancelled.
     */
    boolean isCancelled();
}

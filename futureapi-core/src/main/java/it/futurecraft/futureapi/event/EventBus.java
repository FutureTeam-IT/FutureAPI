package it.futurecraft.futureapi.event;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

/**
 * The event bus.
 */
public interface EventBus {
    /**
     * Create a new instance of the event bus.
     *
     * @return The event bus instance.
     */
    static EventBus create() {
        return new EventBusImpl();
    }

    /**
     * Subscribe a consumer function to the event.
     *
     * @param event   The event to subscribe to.
     * @param handler The event handler.
     * @param <E>     The event type.
     * @return The subscription to the event.
     */
    <E extends FutureEvent> Subscription<E> subscribe(@NotNull Class<E> event, @NotNull Consumer<? super E> handler);

    /**
     * Subscribe a consumer function to the event.
     * Specify the priority of the handler.
     *
     * @param event    The event to subscribe to.
     * @param handler  The event handler.
     * @param priority The handler priority.
     * @param <E>      The event type.
     * @return The subscription to the event.
     * @since v0.2.3
     */
    <E extends FutureEvent> Subscription<E> subscribe(@NotNull Class<E> event, @NotNull Consumer<? super E> handler, @NotNull FutureEvent.Priority priority);

    /**
     * Dispatch an event.
     *
     * @param event The event to dispatch.
     * @param <E>   The event type.
     */
    <E extends FutureEvent> void dispatch(@NotNull E event);

    /**
     * Check whether the event has subscribers.
     *
     * @param <E>   The event type.
     * @param event The event to check.
     * @return <code>true</code> If the event has subscribers.
     */
    <E extends FutureEvent> boolean hasSubscribers(@NotNull Class<E> event);

    /**
     * Get subscriptions list for the event.
     *
     * @param event The event to check.
     * @param <E>   The event type.
     * @return The subscription list.
     */
    <E extends FutureEvent> List<Subscription<? super E>> getSubscriptions(@NotNull Class<E> event);
}

package it.futurecraft.api.event;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @since v0.2.2
 */
final class EventSubImpl implements EventBus {
    private final Map<Class<? extends FutureEvent>, List<Subscription<? super FutureEvent>>> subscriptions;

    public EventSubImpl() {
        this.subscriptions = new HashMap<>();
    }

    private static <T extends R, R> List<R> cast(List<T> list) {
        return (List<R>) list;
    }

    @Override
    public <E extends FutureEvent> Subscription<E> subscribe(@NotNull Class<E> event, @NotNull Consumer<? super E> handler) {
        List<Subscription<? super E>> subscriptions = cast(this.subscriptions.computeIfAbsent(event, key -> new ArrayList<>()));

        Subscription<E> subscription = new SubscriptionImpl<>(event, handler);
        subscriptions.add(subscription);

        return subscription;
    }

    @Override
    public <E extends FutureEvent> void dispatch(@NotNull E event) {
        List<Subscription<? super E>> subscriptions = cast(this.subscriptions.computeIfAbsent(event.getClass(), key -> new ArrayList<>()));

        for (final Subscription<? super E> subscription : subscriptions) {
            subscription.getHandler().accept(event);
        }
    }

    @Override
    public <E extends FutureEvent> boolean hasSubscribers(@NotNull Class<E> event) {
        return this.subscriptions.containsKey(event) && this.subscriptions.get(event).size() > 0;
    }

    @Override
    public <E extends FutureEvent> List<Subscription<? super E>> getSubscriptions(@NotNull Class<E> event) {
        return cast(this.subscriptions.computeIfAbsent(event, key -> new ArrayList<>()));
    }
}

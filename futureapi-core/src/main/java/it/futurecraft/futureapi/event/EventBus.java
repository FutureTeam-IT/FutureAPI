
/*
 * futureapi Copyright (C) 2022 FutureTeam-IT
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

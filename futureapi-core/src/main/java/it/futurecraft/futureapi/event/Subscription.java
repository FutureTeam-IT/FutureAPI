
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

import java.util.function.Consumer;

/**
 * Represent a subscription to event dispatch.
 *
 * @param <E> The event.
 */
public interface Subscription<E extends FutureEvent> {
    /**
     * Get the event handler from the subscription.
     *
     * @return The consumer object.
     */
    Consumer<? super E> getHandler();

    /**
     * Get the event class from the subscription.
     *
     * @return The event class.
     */
    Class<E> getEvent();

    /**
     * Get the priority of the handler.
     *
     * @return The priority value.
     */
    FutureEvent.Priority getPriority();

    /**
     * Unsubscribe from the event listening.
     */
    void unsubscribe();
}

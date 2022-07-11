
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

package it.futurecraft.futureapi.util;

/**
 * A pair of two objects.
 *
 * @param <F> The first object.
 * @param <S> The second object.
 */
public final class Pair<F, S> {
    private F fist;
    private S second;

    private Pair(F fist, S second) {
        this.fist = fist;
        this.second = second;
    }

    /**
     * Create a new pair.
     *
     * @param fist The first object.
     * @param second The second object.
     * @return The pair.
     */
    public static <F, S> Pair<F, S> of(F fist, S second) {
        return new Pair<>(fist, second);
    }

    /**
     * Get the fist element of the pair.
     *
     * @return The fist element.
     */
    public F getFist() {
        return fist;
    }

    /**
     * Change the value of the fist element of the pair.
     *
     * @param value The new value for the fist element.
     */
    public void setFist(F value) {
        this.fist = value;
    }

    /**
     * Get the second element of the pair.
     *
     * @return The second element.
     */
    public S getSecond() {
        return second;
    }

    /**
     * Change the value of the second element of the pair.
     *
     * @param value The new value for the second element.
     */
    public void setSecond(S value) {
        this.second = value;
    }
}

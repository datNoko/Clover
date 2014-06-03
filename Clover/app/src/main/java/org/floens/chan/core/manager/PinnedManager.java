/*
 * Clover - 4chan browser https://github.com/Floens/Clover/
 * Copyright (C) 2014  Floens
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.floens.chan.core.manager;

import android.content.Context;

import org.floens.chan.ChanApplication;
import org.floens.chan.core.model.Loadable;
import org.floens.chan.core.model.Pin;

import java.util.ArrayList;
import java.util.List;

public class PinnedManager {
    private final List<PinListener> listeners = new ArrayList<>();
    private final List<Pin> pins;

    public PinnedManager(Context context) {
        pins = ChanApplication.getDatabaseManager().getPinned();
    }

    public void addPinListener(PinListener l) {
        listeners.add(l);
    }

    public void removePinListener(PinListener l) {
        listeners.remove(l);
    }

    /**
     * Look for a pin that has an loadable that is equal to the supplied
     * loadable.
     *
     * @param other
     * @return The pin whose loadable is equal to the supplied loadable, or null
     * if no pin was found.
     */
    public Pin findPinByLoadable(Loadable other) {
        for (Pin pin : pins) {
            if (pin.loadable.equals(other)) {
                return pin;
            }
        }

        return null;
    }

    public List<Pin> getPins() {
        return pins;
    }

    public List<Pin> getWatchingPins() {
        List<Pin> l = new ArrayList<>();

        for (Pin p : pins) {
            if (p.watching)
                l.add(p);
        }

        return l;
    }

    /**
     * Add a pin
     *
     * @param pin
     * @return true if it was added, false if it wasn't (duplicated)
     */
    public boolean add(Pin pin) {
        // No duplicates
        for (Pin e : pins) {
            if (e.loadable.equals(pin.loadable)) {
                return false;
            }
        }

        pins.add(pin);
        ChanApplication.getDatabaseManager().addPin(pin);

        onPinsChanged();

        return true;
    }

    /**
     * Remove a pin
     *
     * @param pin
     */
    public void remove(Pin pin) {
        pins.remove(pin);
        ChanApplication.getDatabaseManager().removePin(pin);
        pin.destroyWatcher();

        onPinsChanged();
    }

    /**
     * Update the pin in the database
     *
     * @param pin
     */
    public void update(Pin pin) {
        ChanApplication.getDatabaseManager().updatePin(pin);

        onPinsChanged();
    }

    /**
     * Updates all the pins to the database. This will run in a new thread
     * because it can be an expensive operation. (this will be an huge headache
     * later on when we get concurrent problems)
     */
    public void updateAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ChanApplication.getDatabaseManager().updatePins(pins);
            }
        }).start();
    }

    public void onPinViewed(Pin pin) {
        if (pin.getPinWatcher() != null) {
            pin.getPinWatcher().onViewed();
        }

        onPinsChanged();
    }

    public void onPinsChanged() {
        for (PinListener l : listeners) {
            l.onPinsChanged();
        }
    }

    public static interface PinListener {
        public void onPinsChanged();
    }
}
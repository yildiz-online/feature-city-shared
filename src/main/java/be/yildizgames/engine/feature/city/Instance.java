/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.engine.feature.city;

/**
 * Maximum number of instances for a given entity.
 * Immutable class.
 *
 * @author Grégory Van den Borre
 */
public final class Instance {

    /**
     * Unique instance, maximum is 1.
     */
    public static final Instance UNIQUE = new Instance(1);

    /**
     * No limit.
     */
    public static final Instance NO_LIMIT = new Instance(Integer.MAX_VALUE);

    /**
     * Number of allowed instances.
     */
    public final int number;

    /**
     * Full constructor.
     *
     * @param instanceNumber Number of allowed instances.
     */
    public Instance(final int instanceNumber) {
        super();
        if(instanceNumber <=0) {
            throw new IllegalArgumentException("Instance must be at greater than 0.");
        }
        this.number = instanceNumber;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Instance instance = (Instance) o;

        return number == instance.number;
    }

    @Override
    public final int hashCode() {
        return number;
    }
}

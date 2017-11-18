/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2017 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
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

package be.yildizgames.engine.feature.city.building;

import be.yildiz.common.collections.Maps;

import java.util.Map;

/**
 * Simple wrapper class for an entity type. An Entity type is composed of a type
 * and a name, both must be unique in the application instance.
 * Immutable class.
 * @author Grégory Van den Borre
 *
 * specfield type : int : positive not null value, 2 different types cannot
 * have the same type value.
 * specfield name : String : not null value, 2 different types cannot have the
 * same name.
 */
public final class BuildingType {

    /**
     * Map the type to its index value.
     */
    private static final Map<Integer, BuildingType> MAP = Maps.newMap();

    /**
     * Constant for the world type.
     */
    public static final BuildingType WORLD = new BuildingType(0, "world");

    /**
     * Index value.
     */
    public final int type;

    /**
     * Entity type name.
     */
    public final String name;

    /**
     * Full constructor.
     *
     * @param value Entity type index, must be unique.
     * @param name  Type name, must be unique.
     */
    //@requires value >=0 && value != null && !BuildingType.MAP.containsKey(value);
    //@requires name != null && !name.empty && !BuildingType.MAP.containsValue(name);
    //@ensures BuildingType.MAP.get(value) == this;
    //@ensures this.type = value;
    //@ensures this.name = name;
    private BuildingType(final int value, final String name) {
        super();
        this.name = name;
        this.type = value;
        BuildingType.MAP.putIfAbsent(value, this);
        assert this.invariant();
    }

    public static BuildingType register(int value, String name) {
        if(BuildingType.MAP.containsKey(value)) {
            throw new IllegalArgumentException("Value already registered.");
        }
        return new BuildingType(value, name);
    }

    /**
     * Retrieve a type from its index.
     *
     * @param index Entity index value.
     * @return The Type matching the index value.
     * @throws NullPointerException if there is no value matching the index.
     */
    public static BuildingType valueOf(final int index) {
        assert BuildingType.MAP.containsKey(index) : "Entity type " + index + " not registered";
        return BuildingType.MAP.get(index);
    }

    @Override
    public String toString() {
        return this.name;
    }

    private boolean invariant() {
        assert this.type >= 0 : "Type must be positive";
        assert this.name != null : "Name must not be null";
        assert BuildingType.MAP.get(this.type) == this : "This object is not registered";
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BuildingType that = (BuildingType) o;

        return type == that.type && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + name.hashCode();
        return result;
    }
}

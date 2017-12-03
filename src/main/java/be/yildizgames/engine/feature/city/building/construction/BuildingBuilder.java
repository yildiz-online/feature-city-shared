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

package be.yildizgames.engine.feature.city.building.construction;

import be.yildiz.common.id.EntityId;
import be.yildiz.common.id.PlayerId;
import be.yildiz.common.vector.Point3D;
import be.yildizgames.engine.feature.city.CityId;
import be.yildizgames.engine.feature.city.building.Building;

/**
 * A building able to create new entities.
 * <p>
 * Immutable class.
 *
 * @param <B> Building implementation type.
 *
 * @author Grégory Van den Borre
 */
public abstract class BuildingBuilder<B extends Building> {

    /**
     * Builder unique id.
     */
    private final EntityId builderId;

    /**
     * Position where the newly build entities will be located once built.
     */
    private final Point3D buildPosition;

    /**
     * Associated building.
     */
    private final B building;

    /**
     * Player owning this building.
     */
    private final PlayerId owner;

    /**
     * Instantiate a new BuildingBuilder.
     *
     * @param builderId     Builder unique id.
     * @param owner         Player owning this building.
     * @param buildPosition Position in the world.
     * @param building      Associated building.
     */
    protected BuildingBuilder(final EntityId builderId, final PlayerId owner, final Point3D buildPosition, final B building) {
        super();
        assert builderId != null;
        assert owner != null;
        assert buildPosition != null;
        assert building != null;
        this.builderId = builderId;
        this.buildPosition = buildPosition;
        this.building = building;
        this.owner = owner;
    }

    public CityId getCity() {
        return this.building.getCity();
    }

    public EntityId getBuilderId() {
        return builderId;
    }

    public Point3D getBuildPosition() {
        return buildPosition;
    }

    public PlayerId getOwner() {
        return owner;
    }
}

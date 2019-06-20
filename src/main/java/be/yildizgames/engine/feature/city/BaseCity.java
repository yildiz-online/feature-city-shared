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

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.model.PlayerId;
import be.yildizgames.engine.feature.city.building.Building;
import be.yildizgames.engine.feature.city.building.BuildingData;
import be.yildizgames.engine.feature.city.building.BuildingPosition;
import be.yildizgames.engine.feature.city.building.BuildingType;
import be.yildizgames.engine.feature.resource.ResourceValue;
import be.yildizgames.engine.feature.resource.ResourcesProducer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A BaseCity.
 * @param <T> Building implementation.
 * @param <D> BuildingData implementation.
 *
 * @author Grégory Van den Borre
 */
public class BaseCity<T extends Building, D extends BuildingData> implements City<T, D> {

    /**
     * Buildings positions in the world.
     */
    private final Point3D[] positionOffset;

    /**
     * Associated entity.
     */
    private final CityId id;

    /**
     * List of buildings.
     */
    private final List<T> buildings;

    /**
     * Resource production for this city.
     */
    private final ResourcesProducer producer;

    /**
     * Data for buildings in the city.
     */
    private final Map<BuildingType, D> data;

    private final PlayerId owner;

    private final Point3D position;

    /**
     * Create a new BaseCity.
     *
     * @param id The entity representing this city in the world.
     * @param initialResource The resources available in the city when creating it.
     * @param positionOffset Building positions.
     * @param data List of building types and their data available.
     */
    protected BaseCity(final CityId id, final PlayerId owner, final Point3D position, final ResourceValue initialResource, final Point3D[] positionOffset, Map<BuildingType, D> data) {
        super();
        this.id = id;
        this.position = position;
        this.owner = owner;
        this.data = data;
        this.buildings = new ArrayList<>();
        this.positionOffset = Arrays.copyOf(positionOffset, positionOffset.length);
        for (int i = 0; i < this.positionOffset.length; i++) {
            this.positionOffset[i] = this.positionOffset[i].add(position);
        }
        this.producer = new ResourcesProducer(EntityId.valueOf(id.value), System.currentTimeMillis(), initialResource);
    }

    @Override
    public final D getByType(final BuildingType type) {
        return this.data.get(type);
    }

    @Override
    public final String getName() {
        return "City";
    }

    @Override
    public final boolean hasNegativeProductionRatio() {
        return this.producer.hasNegativeRatio();
    }

    @Override
    public final Point3D getBuildingPosition(final BuildingPosition position) {
        return positionOffset[position.value];
    }

    @Override
    public final int getAllocatedStaff() {
        int total = 0;
        for (T b : this.buildings) {
            total += b.getOldStaff().value;
        }
        return total;
    }

    @Override
    public final T getBuilding(final BuildingPosition position) {
        return this.buildings.get(position.value);
    }

    @Override
    public final void createConstruction(final T building) {
        if (this.buildings.size() > building.getBuildingPosition().value) {
            this.buildings.remove(building.getBuildingPosition().value);
        }
        this.buildings.add(building.getBuildingPosition().value, building);
    }

    @Override
    public final Set<BuildingType> getAllowedType() {
        Set<BuildingType> l = this.data.keySet();
        for (Building b : this.buildings) {
            l.remove(b.getType());
        }
        return l;
    }

    @Override
    public final List<D> getAllType() {
        return new ArrayList<>(this.data.values());
    }

    @Override
    public final int getMaximumBuildings() {
        return this.positionOffset.length;
    }

    @Override
    public final CityId getId() {
        return this.id;
    }

    @Override
    public final PlayerId getOwner() {
        return this.owner;
    }

    @Override
    public final Point3D getPosition() {
        return this.position;
    }

    @Override
    public final void initializeProducer() {
        this.getProducer().setInitialised();
    }

    @Override
    public final List<T> getBuildings() {
        return buildings;
    }

    @Override
    public final ResourcesProducer getProducer() {
        return producer;
    }
}

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

import be.yildiz.common.util.Checker;
import be.yildizgames.engine.feature.city.CityId;
import be.yildizgames.engine.feature.city.building.staff.Staff;
import be.yildizgames.engine.feature.entity.Level;
import be.yildizgames.engine.feature.resource.ResourceValue;
import be.yildizgames.engine.feature.resource.bonus.BonusResources;

import java.time.Duration;

/**
 * Class containing data for a building(city, level, position, type).
 *
 * @author Grégory Van den Borre
 */
public final class BaseBuilding implements Building {

    /**
     * BaseCity containing this building.
     */
    private final CityId city;

    /**
     * Building type.
     */
    private final BuildingData data;

    /**
     * Position of this building in the city.
     */
    private final BuildingPosition buildingPosition;

    /**
     * Building current level.
     */
    private Level level;

    /**
     * Staff currently affected to this building.
     */
    private Staff staff;

    /**
     * Staff currently affected to this building while it is upgraded.
     */
    private Staff oldStaff;

    /**
     * Create a new building.
     *
     * @param city             Id of the city containing this building.
     * @param data             Associated building type data.
     * @param buildingPosition Position of the building in the city.
     * @param level            Current building level.
     * @param staff            Current staff allocated.
     * @throws NullPointerException     If any parameter is null.
     * @throws IllegalArgumentException If level is above max level, if staff is negative or if staff if above max for the current level.
     */
    public BaseBuilding(final CityId city, final BuildingData data, final BuildingPosition buildingPosition, final Level level, final Staff staff) {
        super();
        assert city != null;
        assert data != null;
        assert buildingPosition != null;
        assert level != null;
        this.city = city;
        this.data = data;
        this.buildingPosition = buildingPosition;
        this.setLevel(level);
        this.setStaff(staff);
        this.setOldStaff();
    }

    @Override
    public void setLevel(final Level buildingLevel) {
        assert buildingLevel != null;
        if (buildingLevel.value < 0 || buildingLevel.value > this.data.getMaxLevel().value) {
            throw new AssertionError("Wrong level for " + this + " trying to set level " + buildingLevel);
        }
        this.level = buildingLevel;
    }

    @Override
    public void setStaff(final Staff staff) {
        Checker.exceptionNotPositive(staff.value);
        if (staff.value > this.getMaxPopulation(this.level).value) {
            throw new AssertionError("Staff too high for this level.");
        }
        this.staff = staff;
    }


    @Override
    public boolean isMaxLevel() {
        return this.level.value == this.data.getMaxLevel().value;
    }

    @Override
    public void setOldStaff() {
        this.oldStaff = this.staff;
    }

    @Override
    public Duration getTimeToBuild(final Level level) {
        return this.data.getTimeToBuild(level);
    }

    @Override
    public boolean exists() {
        return this.level.isNotZero();
    }

    @Override
    public BuildingType getType() {
        return this.data.getType();
    }

    @Override
    public Staff getMaxPopulation(final Level level) {
        return this.data.getMaxPopulation(level);
    }

    @Override
    public BonusResources getLevelBonus() {
        return this.data.getLevelBonus(this.level);
    }

    @Override
    public BonusResources getStaffBonus() {
        return this.data.getStaffBonus(this.oldStaff);
    }

    @Override
    public CityId getCity() {
        return city;
    }

    @Override
    public BuildingPosition getBuildingPosition() {
        return buildingPosition;
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public Staff getStaff() {
        return staff;
    }

    @Override
    public Staff getOldStaff() {
        return oldStaff;
    }

    /**
     * Hash computed on base Id, position and type, level not used.
     *
     * @return the computed hash code.
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.city.hashCode();
        result = PRIME * result + this.buildingPosition.hashCode();
        result = PRIME * result + this.data.hashCode();
        return result;
    }

    /**
     * 2 building of different levels are considered equals.
     *
     * @param obj Other object to check.
     * @return <code>true</code> If the 2 objects are considered equals.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseBuilding)) {
            return false;
        }
        BaseBuilding other = (BaseBuilding) obj;
        return this.city.equals(other.city) && this.buildingPosition == other.buildingPosition && this.data.equals(other.data);
    }

    @Override
    public String toString() {
        return "Building: " + this.data.getType().name + ", level: " + this.level + ", base: " + this.city + ", position: " + this.buildingPosition;
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public boolean isBuilder() {
        return this.data.isBuilder();
    }

    @Override
    public Duration getNextLevelTimeToBuild() {
        if (this.isMaxLevel()) {
            throw new AssertionError("Already at max level.");
        }
        return this.data.getTimeToBuild(this.level.add(1));
    }

    @Override
    public ResourceValue getNextLevelPrice() {
        if (this.isMaxLevel()) {
            throw new AssertionError("Already at max level.");
        }
        return this.data.getPrice(this.level.add(1));
    }
}

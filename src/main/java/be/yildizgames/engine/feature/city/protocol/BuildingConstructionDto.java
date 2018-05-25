/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
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

package be.yildizgames.engine.feature.city.protocol;

import be.yildizgames.engine.feature.city.CityId;
import be.yildizgames.engine.feature.city.Level;
import be.yildizgames.engine.feature.city.building.Building;
import be.yildizgames.engine.feature.city.building.BuildingPosition;
import be.yildizgames.engine.feature.city.building.BuildingType;
import be.yildizgames.engine.feature.city.building.staff.Staff;

import java.time.Duration;

/**
 * @author Grégory Van den Borre
 */
public class BuildingConstructionDto {

    /**
     * Id of the base containing the building.
     */
    public final CityId cityId;

    /**
     * Type of the building.
     */
    public final BuildingType type;

    /**
     * Level of the building.
     */
    public final Level level;

    /**
     * Position of the building in the base.
     */
    public final BuildingPosition position;

    /**
     * Staff allocated to the building.
     */
    public final Staff staff;

    /**
     * Time left before the building is completed completed.
     */
    public final Duration time;

    public BuildingConstructionDto(CityId cityId, BuildingType type, Level level, BuildingPosition position, Staff staff, Duration time) {
        super();
        this.cityId = cityId;
        this.type = type;
        this.level = level;
        this.position = position;
        this.staff = staff;
        this.time = time;
    }

    public BuildingConstructionDto(CityId cityId, BuildingType type, Level level, BuildingPosition position, Staff staff) {
        this(cityId, type, level, position, staff, Duration.ZERO);
    }

    public BuildingConstructionDto(Building b, Duration time) {
        this(b.getCity(), b.getType(), b.getLevel(), b.getBuildingPosition(), b.getStaff(), time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BuildingConstructionDto that = (BuildingConstructionDto) o;

        return time == that.time && cityId.equals(that.cityId) && type.equals(that.type) && level.equals(that.level) && position.equals(that.position) && staff.equals(that.staff);
    }

    @Override
    public int hashCode() {
        int result = cityId.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + level.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + staff.hashCode();
        return result;
    }
}

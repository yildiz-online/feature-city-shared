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

package be.yildizgames.engine.feature.city.protocol;

import be.yildiz.common.id.EntityId;
import be.yildizgames.engine.feature.city.building.Building;
import be.yildizgames.engine.feature.city.building.BuildingPosition;
import be.yildizgames.engine.feature.city.building.BuildingType;
import be.yildizgames.engine.feature.city.building.Level;
import be.yildizgames.engine.feature.city.building.staff.Staff;

/**
 * @author Grégory Van den Borre
 */
public class BuildingConstructionDto {

    /**
     * Id of the base containing the building.
     */
    public final EntityId cityId;

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
     * Time when the building will be completed.
     */
    public final long time;

    public BuildingConstructionDto(EntityId cityId, BuildingType type, Level level, BuildingPosition position, Staff staff, long time) {
        super();
        this.cityId = cityId;
        this.type = type;
        this.level = level;
        this.position = position;
        this.staff = staff;
        this.time = time;
    }

    public BuildingConstructionDto(EntityId cityId, BuildingType type, Level level, BuildingPosition position, Staff staff) {
        this(cityId, type, level, position, staff, 0);
    }

    public BuildingConstructionDto(Building b, long time) {
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

        if (time != that.time) {
            return false;
        }
        if (!cityId.equals(that.cityId)) {
            return false;
        }
        if (!type.equals(that.type)) {
            return false;
        }
        if (!level.equals(that.level)) {
            return false;
        }
        if (!position.equals(that.position)) {
            return false;
        }
        return staff.equals(that.staff);
    }

    @Override
    public int hashCode() {
        int result = cityId.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + level.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + staff.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }
}
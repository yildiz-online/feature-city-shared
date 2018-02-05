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

import be.yildizgames.common.model.EntityId;
import be.yildizgames.engine.feature.city.building.BuildingPosition;
import be.yildizgames.engine.feature.city.building.staff.Staff;

/**
 * @author Grégory Van den Borre
 */
public class StaffAllocationDto {

    /**
     * Id of the city containing the building where staff will be allocated.
     */
    public final EntityId cityId;

    /**
     * Building position in the city.
     */
    public final BuildingPosition position;

    /**
     * Number of staff to allocate.
     */
    public final Staff staff;

    public StaffAllocationDto(EntityId cityId, BuildingPosition position, Staff staff) {
        this.cityId = cityId;
        this.position = position;
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StaffAllocationDto that = (StaffAllocationDto) o;

        return cityId.equals(that.cityId) && position.equals(that.position) && staff.equals(that.staff);
    }

    @Override
    public int hashCode() {
        int result = cityId.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + staff.hashCode();
        return result;
    }
}

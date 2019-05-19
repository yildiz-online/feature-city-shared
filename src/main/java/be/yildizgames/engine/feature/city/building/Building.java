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

package be.yildizgames.engine.feature.city.building;

import be.yildizgames.engine.feature.city.CityId;
import be.yildizgames.engine.feature.city.Level;
import be.yildizgames.engine.feature.city.building.staff.Staff;
import be.yildizgames.engine.feature.resource.ResourceValue;
import be.yildizgames.engine.feature.resource.bonus.BonusResources;

import java.time.Duration;

/**
 * A building is a game element being part of a city, a building has a type, a level, and have staff in it.
 * <p>
 * Mutable class.
 *
 * @author Grégory Van den Borre
 */
//@specfield type:BuildingType:Building unique type, mutable value, but can only change from empty building to another type.
//@specfield level:Level:Building level, between 0 and the building max level.
//@specfield staff:Staff:Staff assigned in the building, between 0 and the building max staff.
//@specfield city:EntityId:Id of the city holding this building, immutable value.
//@specfield position:BuildingPosition:Position of the building in the city.
//@invariant city != null
//@invariant position != null
//@invariant 0 <= position <= max building in the holding city
//@invariant type != null
//@invariant level != null
//@invariant 0 <= level <= maxLevel
//@invariant staff != null
//@invariant 0 <= staff <= maxStaff
public interface Building {

    /**
     * @return The id of the city containing this building.
     */
    //@Ensures result != null.
    CityId getCity();

    /**
     * Staff currently assigned.
     *
     * @return The current staff.
     */
    Staff getStaff();

    /**
     * Set the number of affected staff.
     *
     * @param staff Staff to set
     */
    void setStaff(Staff staff);

    /**
     * @return The position of the building in the holding city.
     */
    //@Ensures The position is contained in the city.
    BuildingPosition getBuildingPosition();

    /**
     * Set the new staff assigned while a new assignation is being done to prevent to prevent using the newly assigned staff in another building.
     */
    void setOldStaff();

    /**
     * Get all the affected staff including the one currently in assignation.
     *
     * @return The value of the staff before being assigned.
     */
    Staff getOldStaff();

    /**
     * @return The building level.
     */
    //@Ensures result != null
    Level getLevel();

    /**
     * Update the level of this building.
     *
     * @param buildingLevel New level.
     */
    void setLevel(Level buildingLevel);

    /**
     * @return The building type.
     */
    //@Ensures result != null
    BuildingType getType();

    /**
     * @return The bonus to apply with the level of the building.
     */
    //@Ensures result != null
    BonusResources getLevelBonus();

    /**
     * @return The bonus to apply with the number of staff assigned in the building.
     */
    //@Ensures result != null
    BonusResources getStaffBonus();

    /**
     * Get the max population for a given level.
     *
     * @param level Level to check.
     * @return The max population for the given level.
     */
    Staff getMaxPopulation(Level level);

    /**
     * @return The number of resources required to reach the next level.
     * @throws IllegalArgumentException if the building is at max level.
     */
    ResourceValue getNextLevelPrice();

    /**
     * @return The time required to reach the next level.
     * @throws IllegalArgumentException if the building is at max level.
     */
    Duration getNextLevelTimeToBuild();

    /**
     * @return <code>true</code> if this building has not been built.
     */
    boolean isEmpty();

    /**
     * @return <code>true</code> if the current level is the maximum level.
     */
    boolean isMaxLevel();

    /**
     * @return <code>true</code> if this building has the ability to build entities, <code>false</code> otherwise.
     */
    boolean isBuilder();

    /**
     * Provide the time to build this building at a given level.
     *
     * @param level Building level.
     * @return The time to build this building.
     * @throws NullPointerException if level is null.
     */
    //@Ensures result != null
    Duration getTimeToBuild(final Level level);

    boolean exists();

}

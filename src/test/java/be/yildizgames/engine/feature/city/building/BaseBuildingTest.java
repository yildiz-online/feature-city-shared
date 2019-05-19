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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Grégory Van den Borre
 */
public class BaseBuildingTest {

    public static final CityId OK_CITY = CityId.valueOf(6);

    public static final Staff OK_STAFF = Staff.valueOf(2);

    public static final Level OK_LEVEL = Level.valueOf(5);

    public static final BuildingPosition OK_POSITION = BuildingPosition.valueOf(2);

    private static final Level MAX_LEVEL = Level.valueOf(32);

    private static final BuildingType TYPE = BuildingType.register(10, "test");

    private static final ResourceValue NEXT_LEVEL_PRICE = new ResourceValue(new float[]{10, 10, 10});

    private static final Duration NEXT_LEVEL_TIME = Duration.ofSeconds(10);

    public static final BuildingData OK_DATA = getData();

    private static BuildingData getData() {
        return new BuildingData() {
            @Override
            public BuildingType getType() {
                return TYPE;
            }

            @Override
            public ResourceValue getPrice() {
                return NEXT_LEVEL_PRICE;
            }

            @Override
            public Duration getTimeToBuild() {
                return NEXT_LEVEL_TIME;
            }

            @Override
            public BonusResources getStaffBonus(Staff staffAllocated) {
                return null;
            }

            @Override
            public BonusResources getLevelBonus(Level level) {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean isBuilder() {
                return false;
            }

            @Override
            public boolean hasRatioBonus() {
                return false;
            }

            @Override
            public ResourceValue getPrice(Level level) {
                return this.getPrice();
            }

            @Override
            public Duration getTimeToBuild(Level level) {
                return this.getTimeToBuild();
            }

            @Override
            public Staff getMaxPopulation(Level level) {
                return Staff.valueOf(20);
            }

            @Override
            public Level getMaxLevel() {
                return MAX_LEVEL;
            }

            @Override
            public boolean isBuildable() {
                return true;
            }
        };
    }


    @Test
    public void testConstructor() {
        Building b = new BaseBuilding(OK_CITY, OK_DATA, OK_POSITION, OK_LEVEL, OK_STAFF);
        assertEquals(OK_CITY, b.getCity());
        assertEquals(OK_POSITION, b.getBuildingPosition());
        assertEquals(OK_LEVEL, b.getLevel());
        assertEquals(OK_STAFF, b.getStaff());
        assertEquals(OK_STAFF, b.getOldStaff());
    }

    @Test
    public void testConstructorNullCity() {
        Assertions.assertThrows(AssertionError.class, () -> new BaseBuilding(null, OK_DATA, OK_POSITION, OK_LEVEL, OK_STAFF));
    }

    @Test
    public void testConstructorNullData() {
        Assertions.assertThrows(AssertionError.class, () -> new BaseBuilding(OK_CITY, null, OK_POSITION, OK_LEVEL, OK_STAFF));
    }

    @Test
    public void testConstructorNullPosition() {
        Assertions.assertThrows(AssertionError.class, () -> new BaseBuilding(OK_CITY, OK_DATA, null, OK_LEVEL, OK_STAFF));
    }

    @Test
    public void testConstructorNullLevel() {
        Assertions.assertThrows(AssertionError.class, () -> new BaseBuilding(OK_CITY, OK_DATA, OK_POSITION, null, OK_STAFF));
    }

    @Test
    public void testConstructorNegativeStaff() {
        Assertions.assertThrows(AssertionError.class, () -> new BaseBuilding(OK_CITY, OK_DATA, OK_POSITION, OK_LEVEL, Staff.valueOf(-1)));
    }

    @Test
    public void testConstructorLevelTooHigh() {
        Assertions.assertThrows(AssertionError.class, () -> new BaseBuilding(OK_CITY, OK_DATA, OK_POSITION, MAX_LEVEL.add(1), OK_STAFF));
    }

    @Test
    public void testConstructorAssignedStaffTooHigh() {
        Assertions.assertThrows(AssertionError.class, () -> new BaseBuilding(OK_CITY, OK_DATA, OK_POSITION, OK_LEVEL, Staff.valueOf(35)));
    }

    @Test
    public void testSetStaff() {
        Building b = givenABuilding();
        b.setStaff(Staff.valueOf(10));
        assertEquals(OK_STAFF, b.getOldStaff());
        assertEquals(Staff.valueOf(10), b.getStaff());
    }

    @Test
    public void testTooHighStaff() {
        Building b = givenABuilding();
        Assertions.assertThrows(AssertionError.class, () -> b.setStaff(Staff.valueOf(35)));
    }

    @Test
    public void testNegativeStaff() {
        Building b = new BaseBuilding(OK_CITY, OK_DATA, OK_POSITION, OK_LEVEL, OK_STAFF);
        Assertions.assertThrows(AssertionError.class, () -> b.setStaff(Staff.valueOf(-1)));
    }

    @Test
    public void testSetLevel() {
        Building b = givenABuilding();
        b.setLevel(Level.valueOf(14));
        assertEquals(Level.valueOf(14), b.getLevel());
    }

    @Test
    public void testSetLevelNull() {
        Building b = givenABuilding();
        Assertions.assertThrows(AssertionError.class, () -> b.setLevel(null));
    }

    @Test
    public void testSetLevelTooHigh() {
        Building b = givenABuilding();
        Assertions.assertThrows(AssertionError.class, () -> b.setLevel(MAX_LEVEL.add(1)));
    }

    @Test
    public void testGetNextLevelPrice() {
        Building b = givenABuilding();
        assertEquals(NEXT_LEVEL_PRICE, b.getNextLevelPrice());
    }

    @Test
    public void testGetNextLevelPriceLevelMax() {
        Building b = givenABuilding();
        b.setLevel(MAX_LEVEL);
        Assertions.assertThrows(AssertionError.class, b::getNextLevelPrice);
    }

    @Test
    public void testGetNextLevelTime() {
        Building b = givenABuilding();
        assertEquals(NEXT_LEVEL_TIME, b.getNextLevelTimeToBuild());
    }

    @Test
    public void testGetNextLevelTimeLevelMax() {
        Building b = givenABuilding();
        b.setLevel(MAX_LEVEL);
        Assertions.assertThrows(AssertionError.class, b::getNextLevelTimeToBuild);
    }

    private Building givenABuilding() {
        return new BaseBuilding(OK_CITY, OK_DATA, OK_POSITION, OK_LEVEL, OK_STAFF);
    }

}

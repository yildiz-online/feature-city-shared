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

package be.yildizgames.engine.feature.city.building;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.model.PlayerId;
import be.yildizgames.engine.feature.city.building.construction.BuildingBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Grégory Van den Borre
 */
class BuildingBuilderTest {

    @Test
    void testBuildingBuilder() {
        Building building = new BaseBuilding(BaseBuildingTest.OK_CITY, BaseBuildingTest.OK_DATA, BaseBuildingTest.OK_POSITION, BaseBuildingTest.OK_LEVEL, BaseBuildingTest.OK_STAFF);
        new DummyBuildingBuilder(building);
        
        assertThrows(AssertionError.class, () -> new DummyBuildingBuilder(null, PlayerId.WORLD, Point3D.valueOf(10, 15, 20), building));
        
        assertThrows(AssertionError.class, () -> new DummyBuildingBuilder(EntityId.valueOf(10L), PlayerId.WORLD, null, building));
        
        assertThrows(AssertionError.class, () -> new DummyBuildingBuilder(EntityId.valueOf(10L), PlayerId.WORLD, Point3D.valueOf(10, 15, 20), null));
    }

    @Test
    void testRemoveFromQueue() {
    }

    @Test
    void testAddInQueue() {
    }

    @Test
    void testGetBuilderId() {
        Building building = new BaseBuilding(BaseBuildingTest.OK_CITY, BaseBuildingTest.OK_DATA, BaseBuildingTest.OK_POSITION, BaseBuildingTest.OK_LEVEL, BaseBuildingTest.OK_STAFF);
        BuildingBuilder<Building> b = new DummyBuildingBuilder(building);
        Assertions.assertEquals(EntityId.valueOf(10L), b.getBuilderId());
    }

    @Test
    void testGetOwner() {
    }

    @Test
    void testGetBuildPosition() {
    }

    @Test
    void testGetQueue() {
    }

    @Test
    void testSetQueue() {
    }

    private static final class DummyBuildingBuilder extends BuildingBuilder<Building> {

        /**
         * Instantiate a new BuildingBuilder.
         *
         * @param building Associated building.
         */
        DummyBuildingBuilder(Building building) {
            super(EntityId.valueOf(10L), PlayerId.WORLD, Point3D.valueOf(10, 15, 20), building);
        }

        DummyBuildingBuilder(EntityId builderId, PlayerId world, Point3D xyz, Building building) {
            super(builderId, world, xyz, building);
        }
    }

}

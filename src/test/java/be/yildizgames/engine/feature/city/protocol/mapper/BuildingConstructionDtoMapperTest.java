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

package be.yildizgames.engine.feature.city.protocol.mapper;

import be.yildizgames.common.model.Level;
import be.yildizgames.engine.feature.city.CityId;
import be.yildizgames.engine.feature.city.building.BuildingPosition;
import be.yildizgames.engine.feature.city.building.BuildingType;
import be.yildizgames.engine.feature.city.building.staff.Staff;
import be.yildizgames.engine.feature.city.protocol.BuildingConstructionDto;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author Grégory Van den Borre
 */
public class BuildingConstructionDtoMapperTest extends BaseMapperTest<BuildingConstructionDto> {

    @BeforeAll
    public static void init() {
         BuildingType.register(143, "test");
    }

    public BuildingConstructionDtoMapperTest() {
        super(new BuildingConstructionDtoMapper(),
                new BuildingConstructionDto(
                        CityId.valueOf(3),
                        BuildingType.valueOf(143),
                        Level.valueOf(1),
                        BuildingPosition.valueOf(5),
                        Staff.valueOf(2)
                ));
    }

}
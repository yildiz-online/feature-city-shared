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

package be.yildizgames.engine.feature.city.protocol.mapper;

import be.yildizgames.common.mapping.LongMapper;
import be.yildizgames.common.mapping.MappingException;
import be.yildizgames.common.mapping.ObjectMapper;
import be.yildizgames.common.mapping.Separator;
import be.yildizgames.engine.feature.city.protocol.BuildingConstructionDto;

import java.time.Duration;

/**
 * @author Grégory Van den Borre
 */
public class BuildingConstructionDtoMapper implements ObjectMapper<BuildingConstructionDto> {

    @Override
    public BuildingConstructionDto from(String s) throws MappingException {
        assert s != null;
        String[] v = s.split(Separator.VAR_SEPARATOR);
        try {
            return new BuildingConstructionDto(
                    CityIdMapper.getInstance().from(v[0]),
                    BuildingTypeMapper.getInstance().from(v[1]),
                    LevelMapper.getInstance().from(v[2]),
                    BuildingPositionMapper.getInstance().from(v[3]),
                    StaffMapper.getInstance().from(v[4]),
                    Duration.ofMillis(LongMapper.getInstance().from(v[5]))
            );
        } catch (IndexOutOfBoundsException e) {
            throw new MappingException(e);
        }
    }

    @Override
    public String to(BuildingConstructionDto dto) {
        assert dto != null;
        return CityIdMapper.getInstance().to(dto.cityId)
                + Separator.VAR_SEPARATOR
                + BuildingTypeMapper.getInstance().to(dto.type)
                + Separator.VAR_SEPARATOR
                + LevelMapper.getInstance().to(dto.level)
                + Separator.VAR_SEPARATOR
                + BuildingPositionMapper.getInstance().to(dto.position)
                + Separator.VAR_SEPARATOR
                + StaffMapper.getInstance().to(dto.staff)
                + Separator.VAR_SEPARATOR
                + LongMapper.getInstance().to(dto.time.toMillis());
    }
}

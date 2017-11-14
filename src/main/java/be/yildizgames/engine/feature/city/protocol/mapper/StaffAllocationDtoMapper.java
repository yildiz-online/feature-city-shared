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


package be.yildizgames.engine.feature.city.protocol.mapper;

import be.yildizgames.common.mapping.EntityIdMapper;
import be.yildizgames.common.mapping.MappingException;
import be.yildizgames.common.mapping.ObjectMapper;
import be.yildizgames.common.mapping.Separator;
import be.yildizgames.engine.feature.city.protocol.StaffAllocationDto;

/**
 * @author Grégory Van den Borre
 */
public class StaffAllocationDtoMapper implements ObjectMapper<StaffAllocationDto> {

    private static final StaffAllocationDtoMapper INSTANCE = new StaffAllocationDtoMapper();

    private StaffAllocationDtoMapper() {
        super();
    }

    public static StaffAllocationDtoMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public StaffAllocationDto from(String s) throws MappingException {
        assert s != null;
        String[] v = s.split(Separator.OBJECTS_SEPARATOR);
        try {
            return new StaffAllocationDto(EntityIdMapper.getInstance().from(v[0]),
                    BuildingPositionMapper.getInstance().from(v[1]),
                    StaffMapper.getInstance().from(v[2])
                    );
        } catch (IndexOutOfBoundsException e) {
            throw new MappingException(e);
        }
    }

    @Override
    public String to(StaffAllocationDto dto) {
        assert dto != null;
        return EntityIdMapper.getInstance().to(dto.cityId)
                + Separator.OBJECTS_SEPARATOR
                + BuildingPositionMapper.getInstance().to(dto.position)
                + Separator.OBJECTS_SEPARATOR
                + StaffMapper.getInstance().to(dto.staff);
    }
}

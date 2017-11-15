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

import be.yildiz.common.Instance;
import be.yildiz.common.Level;
import be.yildiz.common.util.Checker;
import be.yildizgames.engine.feature.city.building.staff.Staff;
import be.yildizgames.engine.feature.resource.ResourceValue;
import be.yildizgames.engine.feature.resource.bonus.BonusResources;

import java.time.Duration;

/**
 * Base implementation for the BuildingData
 *
 * @author Grégory Van den Borre
 */
public abstract class GameBuildingData implements BuildingData {

    /**
     * Object type.
     */
    private final BuildingType type;

    /**
     * Number of instance of this type allowed to be built.
     */
    private final Instance maxInstances;

    /**
     * Required level to build this object(it can be player or building level
     * depending on the context).
     */
    private final Level requiredLevel;

    /**
     * Data for every possible level of this building.
     */
    private final LevelData[] levels;

    /**
     * Factory building the bonus associated to this building.
     */
    private final BonusFactory bonusFactory;

    /**
     * Special case if this building type is considered as empty or not.
     */
    private final boolean empty;

    /**
     * Is this building type capable of creating entities.
     */
    private final boolean builder;

    private final boolean buildable;

    /**
     * Full constructor.
     *
     * @param type         Entity type.
     * @param bonusFactory Associated bonus factory.
     * @param instance     Number of building of that type allowed.
     * @param buildable    <code>true</code>if this type of building can be built.
     */
    //@requires all parameters not being null.
    //@requires level data size at least 1.
    //@requires type to be unique for every instance.
    //@effect Create a new instance, add it to the static list.
    //@modifies this.
    protected GameBuildingData(final BuildingType type, final BonusFactory bonusFactory, final Instance instance, final boolean buildable) {
        this(type, bonusFactory, instance, false, false, buildable);
    }

    protected GameBuildingData(final BuildingType type, final BonusFactory bonusFactory, final Instance instance, final boolean empty, final boolean builder, final boolean buildable) {
        super();
        this.type = type;
        this.maxInstances = instance;
        this.requiredLevel = Level.ZERO;
        this.levels = this.generateLevelData();
        this.bonusFactory = bonusFactory;
        this.empty = empty;
        this.builder = builder;
        this.buildable = buildable;
    }

    @Override
    public boolean hasRatioBonus() {
        return this.bonusFactory.hasRatioBonus();
    }

    @Override
    public BonusResources getLevelBonus(final Level level) {
        return this.bonusFactory.getLevelBonus(level);
    }

    @Override
    public BonusResources getStaffBonus(final Staff staff) {
        return this.bonusFactory.getStaffBonus(staff);
    }

    public BuildingType getType() {
        return type;
    }

    public Instance getMaxInstances() {
        return maxInstances;
    }

    public Level getRequiredLevel() {
        return requiredLevel;
    }

    /**
     * Retrieve the data matching a given level.
     *
     * @param level Level of the building.
     * @return The price and time to build data of the building at the given level.
     */
    private LevelData getForLevel(final Level level) {
        assert Checker.inArrayRange(level.value, levels);
        if(this.empty) {
            return this.levels[0];
        }
        return this.levels[level.value - 1];
    }

    @Override
    public final ResourceValue getPrice() {
        return this.getForLevel(Level.ONE).getPrice();
    }

    @Override
    public final ResourceValue getPrice(final Level level) {
        return this.getForLevel(level).getPrice();
    }

    @Override
    public final Duration getTimeToBuild() {
        return this.getForLevel(Level.ONE).getTimeToBuild();
    }

    @Override
    public final Duration getTimeToBuild(final Level level) {
        return this.getForLevel(level).getTimeToBuild();
    }

    @Override
    public final Staff getMaxPopulation(final Level level) {
        return this.getForLevel(level).getMaxPopulation();
    }

    @Override
    public final Level getMaxLevel() {
        return Level.valueOf(this.levels.length);
    }

    @Override
    public final boolean isBuildable() {
        return buildable;
    }

    @Override
    public final boolean isEmpty() {
        return this.empty;
    }

    @Override
    public final boolean isBuilder() {
        return this.builder;
    }

    protected abstract LevelData[] generateLevelData();

    @Override
    public final String toString() {
        return this.getType().toString();
    }
}

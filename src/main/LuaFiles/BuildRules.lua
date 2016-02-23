--
-- Created by IntelliJ IDEA.
-- User: userhp
-- Date: 15/02/2016
-- Time: 14:30
-- To change this template use File | Settings | File Templates.
--

--Can be changed as wanted
local amountOfHousesNeededForHotel = 4

function houseCheck(amountOfHousesInPropertyA, amountOfHousesInPropertyB)
    return amountOfHousesInPropertyA == amountOfHousesInPropertyB or amountOfHousesInPropertyA == amountOfHousesInPropertyB - 1
end

--DO not edit
function getAmountOfHousesNeededForHotel()
    return amountOfHousesNeededForHotel
end




--DO NOT CHANGE NAME OR PARAMETERS of this method, also must return boolean
function canBuildHouse(property, player, board, properties)
    allowedToBuildHouse = true;
    group = property:getGroup()
    amountOfPropertiesRequired = board:amountOfSpacesInGroup(group)
    amountOfPropertiesOwned = player:ownsSpacesOfGroup(group)
    if amountOfPropertiesOwned < amountOfPropertiesRequired then
        allowedToBuildHouse = false
    else

        housesOnProperty = property:getHouses()
        for k, v in pairs(properties) do
            if not houseCheck(housesOnProperty, v:getHouses()) then
                allowedToBuildHouse = false
            end -- end if
        end
    end
    return allowedToBuildHouse
end

function allPropertiesHaveSameAmountOfHouses(property, properties)
    enoughHouses = true
    housesOnProperty = property:getHouses()
    for k, v in pairs(properties) do
        if not v:getHouses() == housesOnProperty then
            enoughHouses = false
        end -- end if
    end
    return enoughHouses
end

--DO NOT CHANGE NAME OR PARAMETERS of this method, also must return boolean
function canBuildHotel(property, player, board, properties)
    allowedToBuildHotel = true
    if not property:getHouses() == amountOfHousesNeededForHotel
            and not allPropertiesHaveSameAmountOfHouses(property, properties)
            and not canBuildHouse(property, player, board, properties) then
        allowedToBuildHotel = false;
    end
    return allowedToBuildHotel
end


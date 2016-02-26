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

function ownsAllOfGroup(board, player, property)
    group = property:getGroup()
    amountOfPropertiesRequired = board:amountOfSpacesInGroup(group)
    amountOfPropertiesOwned = player:ownsSpacesOfGroup(group)
    return amountOfPropertiesOwned == amountOfPropertiesRequired
end



--DO NOT CHANGE NAME OR PARAMETERS of this method, also must return boolean
function canBuildHouse(property, player, board, properties)
    allowedToBuildHouse = false;

    if ownsAllOfGroup(board, player, property) then

        housesOnProperty = property:getHouses()
        for k, v in pairs(properties) do
            if houseCheck(housesOnProperty, v:getHouses()) then
                allowedToBuildHouse = true
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
    allowedToBuildHotel = false
    if property:getHouses() == amountOfHousesNeededForHotel
            and allPropertiesHaveSameAmountOfHouses(property, properties)
            and ownsAllOfGroup(board, player, property)
            and property:getHotels() == 0 then
        allowedToBuildHotel = true;
    end
    return allowedToBuildHotel
end


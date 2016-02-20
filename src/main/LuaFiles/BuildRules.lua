--
-- Created by IntelliJ IDEA.
-- User: userhp
-- Date: 15/02/2016
-- Time: 14:30
-- To change this template use File | Settings | File Templates.
--

--Can be changed as wanted
function houseCheck(amountOfHousesInPropertyA, amountOfHousesInPropertyB)
    return not amountOfHousesInPropertyA == amountOfHousesInPropertyB
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
            if houseCheck(v:getHouses(), housesOnProperty) then
                allowedToBuildHouse = false
            end -- end if
        end
    end
    return allowedToBuildHouse
end




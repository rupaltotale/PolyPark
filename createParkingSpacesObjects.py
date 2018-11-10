import random
def createPermitObjects( filename):
    string = "["
    parkingLots = ["A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2", "E1", "E2"]
    permits = ["ABC", "XYZ", "EFG", "MNO", "PQR"]
    days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
    x1 = 0
    x2 = 100
    y1 = 0
    y2 = 100
    for i in range(len(parkingLots)):
        permitsArr = []
        hoursArr = "{"
        # Generate random permits
        for j in range(random.randint(1, len(permits))):
            permit = permits[random.randint(0, len(permits)-1)]
            if permit not in permitsArr:
                permitsArr.append(permit)
        # Generate random hours
        for k in range(len(days)):
            hours = [random.randint(0, 12), random.randint(12, 24)]
            hoursArr += '"' + days[k] + '"' + ": "+ str(hours) + ", "
        hoursArr += "}"
        # Generate random loc
        loc = [random.uniform(x1,x2), random.uniform(y1,y2)]
        obj = '{\n\t"ParkingLot": "' + parkingLots[i] + '", \n\t' \
        + '"Permits" : ' + str(permitsArr) + ', \n\t' \
        + '"Hours" : ' + str(hoursArr) + ',\n\t' \
        + '"Location" : ' + str(loc) + '\n},\n' 
        string += obj
    string = string[:-2] + "]"
    string = string.replace("'", '"')
    fh = open(filename, 'w')
    fh.write(string)
    return string
print(createPermitObjects("permitParkingSpaces.json"))
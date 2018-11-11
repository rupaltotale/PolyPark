import random as rand

class NoPermitLot:
    def __init__(self, lotName, hours, location, hourlyRate):
        self.lotName = lotName
        self.hours = hours
        self.location = location
        self.hourlyRate = hourlyRate

def createRandomLots(lotNameList):
    lotsList = []
    hoursDict = {"Monday" : [7.0,17.0],\
                 "Tuesday" : [7.0,17.0],\
                 "Wednesday" : [7.0,17.0],\
                 "Thursday" : [7.0,17.0],\
                 "Friday" : [7.0,17.0],\
                 "Saturday" : [7.0,17.0],\
                 "Sunday" : [7.0,17.0]}
    for lotName in lotNameList:
        lot = NoPermitLot(lotName, hoursDict, [0, 0], 8.50)
        lotsList.append(lot);
    return lotsList

def main():
    f = open("noPermitLots.json", "w")
    lotsNameList = ["A1", "C3", "C4", "C5", "South Perimeter",\
                    "College Avenue", "Grand Avenue Structure", "Grand Avenue",\
                    "H2", "H4", "H10", "Dairy",\
                    "Poultry", "Mountain Lane", "Ornamental Horticulture",\
                    "Meat Processing", "Village Drive", "K1"]
    lotsList = createRandomLots(lotsNameList)
    f.write("[")
    for i in range(len(lotsList)):
        stringDict = str(lotsList[i].hours)
        stringDict = stringDict.replace("'", '"')
        if i == len(lotsList) - 1:
            f.write('{\n"ParkingLot": "%s",\n"Hours" :%s,\n"Location" : %s,\n"HourlyRate" : %.2f\n}' % (lotsList[i].lotName, stringDict, lotsList[i].location, lotsList[i].hourlyRate))
        else:
            f.write('{\n"ParkingLot": "%s",\n"Hours" :%s,\n"Location" : %s,\n"HourlyRate" : %.2f\n},' % (lotsList[i].lotName, stringDict, lotsList[i].location, lotsList[i].hourlyRate))
    f.write("]")
    f.close()

if __name__ == '__main__':
    main()

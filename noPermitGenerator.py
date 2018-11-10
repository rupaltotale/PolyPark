import random as rand

class NoPermitLot:
    def __init__(self, lotName, hours, location, hourlyRate):
        self.lotName = lotName
        self.hours = hours
        self.location = location
        self.hourlyRate = hourlyRate

def createRandomLots(lotNameList):
    lotsList = []
    hoursDict = {"Monday" : [7,5],\
                 "Tuesday" : [7,5],\
                 "Wednesday" : [7,5],\
                 "Thursday" : [7,5],\
                 "Friday" : [7,5],\
                 "Saturday" : [7,5],\
                 "Sunday" : [7,5]}
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
    for lot in lotsList:
        stringDict = str(lot.hours)
        stringDict = stringDict.replace("'", '"')
        f.write('{\n"ParkingLot" : "%s",\n"Hours" :%s,\n"Location" : %s,\n"HourlyRate" : %.2f\n}' % (lot.lotName, stringDict, lot.location, lot.hourlyRate))
    f.close()

if __name__ == '__main__':
    main()

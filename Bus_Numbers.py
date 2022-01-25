import sys

#Helper
def mergeSort(array, start, end):
    if start < end:
        pivot = (start+end)//2
        mergeSort(array, start, pivot)
        mergeSort(array, pivot+1, end)
        merge(array, start, pivot, end)

def merge(array, start, pivot, end):
    firstHalf = []
    for x in range(start, pivot+1):
        firstHalf.append(array[x])
        
    secondHalf = []
    for x in range(pivot+1, end+1):
        secondHalf.append(array[x])
    
    firstHalf.append(1001)
    secondHalf.append(1001)
    
    i=0
    j=0
    
    for x in range(start, end+1):
        if firstHalf[i] < secondHalf[j]:
            array[x] = firstHalf[i]
            i=i+1
        else:
            array[x] = secondHalf[j]
            j=j+1

#get input
inputs = sys.stdin.readlines()
numBus = int(inputs[0])
busLines = inputs[1].strip().split(" ")
busLines = [int(bus) for bus in busLines]
mergeSort(busLines, 0, numBus-1)
busLines = [[bus, bus] for bus in busLines]

x=1
while x < len(busLines):
    if busLines[x][0] - busLines[x-1][1] == 1:
        busLines[x-1][1] = busLines[x][0]
        busLines.pop(x)
    else:
        x=x+1

returnSTR = ""
for lines in busLines:
    if lines[1]-lines[0] > 1:
        returnSTR += (str(lines[0])+"-"+str(lines[1])+" ")
    elif lines[1]-lines[0] ==1:
        returnSTR += (str(lines[0])+" "+str(lines[1])+" ")
    else:
        returnSTR += (str(lines[0]) + " ")
    
returnSTR.strip()
print(returnSTR)

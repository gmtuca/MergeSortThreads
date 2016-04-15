import sys
import matplotlib.pyplot as plt
#import statistics

def mean(data):
    """Return the sample arithmetic mean of data."""
    n = len(data)
    if n < 1:
        raise ValueError('mean requires at least one data point')
    return sum(data)/n # in Python 2 use sum(data)/float(n)

def _ss(data):
    """Return sum of square deviations of sequence data."""
    c = mean(data)
    ss = sum((x-c)**2 for x in data)
    return ss

def stdev(data):
    """Calculates the population standard deviation."""
    n = len(data)
    if n < 2:
        raise ValueError('variance requires at least two data points')
    ss = _ss(data)
    pvar = ss/n # the population variance
    return pvar**0.5

def plotandsave(l,stats):
    plt.errorbar(stats["threads"], stats["averages"], yerr=stats["stdevs"], ecolor='r', label="{:,d}".format(l))
    plt.legend(loc='upper right')
    plt.xlabel('Number of threads')
    plt.ylabel('Performance (op/sec)')
    plt.title('Merge Sort with Threads - Performance Plot')
    plt.savefig('graphs/{}.png'.format(l))
    plt.show()

statsArray = []

print("length,threads,avg,stdev")

#stats array has format: length,threads,performance

import csv
with open(sys.argv[1], 'rb') as f:
    reader = csv.reader(f)

    l = 0
    statsArray = []

    for row in reader:
    	if not row: #reached separation
            avg = mean(list(float(r[2]) for r in statsArray))
            std = stdev(list(float(r[2]) for r in statsArray))

            stats["threads"].append(int(statsArray[0][1]))
            stats["averages"].append(avg)
            stats["stdevs"].append(std)

            print("{},{},{},{}".format(statsArray[0][0], statsArray[0][1], avg, std))

            del statsArray[:]
    	else:
            if int(row[0]) != l:
                if l is not 0:
                        plotandsave(l,stats)
                l = int(row[0])
                stats = {"threads" : [], "averages" : [], "stdevs" : []}

            statsArray.append(row)

    plotandsave(l,stats)



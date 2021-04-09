from sys import stdin as si
from sys import stdout as so
from datetime import datetime

weekday = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"]


x, y = map(int, si.readline().split())
dt = datetime(2007, x, y, 0, 0, 0, 0)
so.write("%s\n" % weekday[dt.weekday()])

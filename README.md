
# citygamefinder

Our project team loves watching sports. Home AND Away games. 

If you plan on traveling anytime in the near future, you can use citygamefinder rest service to find professional sports games near you.

### A potential Implmentation of this rest service could be
![Screen1](/images/Screen1.PNG)

The following sports will be searchable while their season is currently underway:
* MLB
* NBA
* NFL
* NHL

Via these URI's:
* http://localhost:8080/citygamefinder/sports
  - "You want to see what sports our service supports currently"
* http://localhost:8080/citygamefinder/sports/nfl
  - "You want the full schedule for NFL"
* http://localhost:8080/citygamefinder/sports/53718/100
  - "You want the games of all sports within 100 miles of 53718"
* http://localhost:8080/citygamefinder/sports/nfl/53718/100
  - "You want the NFL games within miles 100 of 53718"
* http://localhost:8080/citygamefinder/sports/nfl/53718/100/12-01-17
  - "You want the NFL games within 100 miles of 53718 on or after 12-01-17"
* http://localhost:8080/citygamefinder/sports/nfl/53718/100/12-01-17/12-25-17
  - "You want the NFL games within 100 miles of 53718 between the dates of 12-01-17 & 12-25-17"

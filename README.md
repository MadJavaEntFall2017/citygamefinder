# citygamefinder

Our project team loves watching sports. Home AND Away games. 

If you plan on traveling anytime in the near future, you can use citygamefinder rest service to find professional sports games near you.

The following sports will be searchable while their season is currently underway:
* MLB
* NBA
* NFL
* NHL

Via these URI's: (These links work)
* http://13.59.5.68:8080/citygamefinder/sports
  - "You want to see what sports our service supports currently"
* http://13.59.5.68:8080/citygamefinder/sports/nfl
  - "You want the full schedule for NFL"
* http://13.59.5.68:8080/citygamefinder/sports/53718/150
  - "You want the games of all sports within 150 miles of 53718"
* http://13.59.5.68:8080/citygamefinder/sports/nfl/53718/150
  - "You want the NFL games within miles 150 of 53718"
* http://13.59.5.68:8080/citygamefinder/sports/nfl/53718/150/2017-12-01
  - "You want the NFL games within 150 miles of 53718 on or after 2017-12-01"
* http://13.59.5.68:8080/citygamefinder/sports/nfl/53718/150/2017-12-01/2017-12-25
  - "You want the NFL games within 150 miles of 53718 between the dates of 2017-12-01 & 2017-12-25"
  
  ### A potential Implmentation of this rest service could be
![demo](/images/demo.PNG)

# live demo

http://13.59.5.68:8080/citygamefinder/





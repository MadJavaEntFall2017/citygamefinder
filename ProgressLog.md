# Progress Log

## Week 1 (10/22/2017 - 10/28/2017)

#### Progress

For our first week, most of our time was spent figuring out what our project was going to be. We discussed various possible ideas, most of which revolved around sports in some way. After doing some investigating into available apis, we came to the conclusion that we would like to have an api that can be used by sites to find if there are any sporting events taking place near a location between two date ranges. We like to travel and would be nice to see if there are any sports games being played for the 4 major sports near where we are visiting.

We were able to do some searching and discovered that there is an api available called mysportsfeeds that has the data required to find sporting events between dates and had home team information. We then found a site that would be able to find all cities within a given radius of a given zip code called zipcode api.

After hashing out what our application would do, we created the repository and an initial Project Plan for our idea.

#### Obstacles

The first obstacles we hit were really just trying to figure out a good idea and some apis which would help with that idea.

#### Findings

We didn't have any findings this week since it was really just initial project planning

#### Learning Points

Some learning points that we took away from this week would be to start early. Coming up with an idea on short notice isn't easy. Luckily, we all are pretty creative so we came up with something pretty quickly.

## Week 2 (10/29/2017 - 11/04/2017)

#### Progress

This week, we made progress in getting the mysportsfeeds api in place within our project structure. We created some of the sites that could be called directly to call the apis which seemed to be returning the JSON we required. We then began working on the shell of a website to return the data so that it was easier to test with and will eventually morph into our test site.

After this was completed, we worked on getting all the various URIs setup and working so that our api can be called in various ways to return data that might be interesting (ie, all games for a specific sport for the current season or a all game for a specific sport within a certain range of a city).

#### Obstacles

The mysportsfeeds api isn't call in the normal way that we are used to. Took a bit to figure out how to properly call it within our service using the api key they gave us. After getting that to work, the only other obstacles was trying to figure out what URIs we wanted to offer to best give our users a good experience

#### Findings

A finding we had was that sometimes apis aren't setup the same way. This added some difficulty to figure out how to call apis that used different authorization methods.

#### Learning Points

We learned that figuring out uris before starting to code is important. We found that sometimes having to rework things based on how the uris were setup could be time consuming.

## Week 3 (11/05/2017 - 11/11/2017)

#### Progress

We made progress on getting the apis to best work together especially after having a few issues with city names with sports teams also existing in other states. This caused us some issues that we had to work through. We decided we had to create a database that would hold all possible sports venues and their zip code which we would read against to better figure out the distance between a given city and a sports city.

After that initial issue, we were able to get our service working pretty well. 

#### Obstacles

As stated in the progress, we ran into an issue because we hadn't thought about cities with the same name existing in multiple states. This caused us a major headache which we resolved by creating a database with all sports stadiums and their zip codes that we match against. We were able to fix this and it appears that our service is now correctly returning games within a radius of a given city.

#### Findings

A finding we had was that we can't just assume that a city that has a sports team will be the only city that exists in the country. Luckily, we were pretty quick thinkers and got a solution created and working in no time.

#### Learning Points

A learning point from this was to better hash out ideas early. The issue we ran into could have really caused a headache for us if we weren't lucky enough to find a quick solution.

## Week 4 (11/12/2017 - 11/15/2017 (Due Date))

#### Progress

We worked on getting our error processing working correctly which turned out to be way harder than expected. After a few nights of working together, we were able to get our site correctly displaying our error page with a snippet of information about what went wrong.

Also, during this time, we touched up all of our tests and javadoc to get prepared for the presentation

#### Obstacles

An obstacle that we couldn't figure out was how to properly get our error page working for when our service returns an error. After a lot of digging, we finally figured out how to best pass the error codes from our service so a potential user could handle them

#### Findings

No findings this week

#### Learning Points

We learned to not do all javadoc and official JUnit testing till the end. This created a lot of work of trying to document everything and get it all tested.
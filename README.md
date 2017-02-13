# Cell Society (Cellular Automata)
Mike Liu, Bilva Sanaba, Justin Yang  

Jan 26 - Feb 12, 2017  
Mike ~ 40 hours  
Bilva ~30 hours  
Justin ~ 30 hours  

## Roles
* Mike - Designed the structure of the project and class hierarchy, integrated all parts of the project together, implemented
all front-end components and controller, designed all abstract classes except those in util packages, 
* Bilva - Created simulation models for the Wa-Tor and Segregation, as well as back-end grid functionality for identifying
neighbors.
* Justin - Worked on XML parsing, abstract data classes for XML, and saving to XML file

Resources
http://www.cs.duke.edu/courses/compsci308/spring17/
http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/
http://nifty.stanford.edu/2011/scott-wator-world/
https://en.wikipedia.org/wiki/Conway's_Game_of_Life
http://stackoverflow.com/

To Start
Program is run through the main class, and no other classes contain main. 
Files Used to Test
All xml files used to load simulations are stored in the data package.
Resource Files
Resource files for GUI Text and CSS are included in a resource package.
Using the Program
The program starts by running the main class which opens a popup screen. 
On this screen press load and pick an xml file to load a simulation. Load can also be pressed at any time to change simulation. 
Press play to start the simulation, pause to pause, and move the slider to change the frames for second. 
Either while playing or while paused, press step to update the simulation one frame per click. 
At any time, press options for a screen to popup to  change various parameters for any simulation type. Exit out of options screen after changing values. 
At any time, press +/- to zoom in/out of the simulation. A scrollable bar will appear if zoomed in far enough.
Cell shape, Grid Type, and Neighbor Pattern can all be changed dynamically with the buttons at the bottom right.

Extra Features

Impressions
Personally, I found this project very interesting and a good way to introduce coding in a group. The end result is something we are all proud of and we are impressed with the project for the amount of time we had. 
It seemed as if a big issue during this project was simply with effective ways to split up work. It seems challenging to have everyone work on overall design, but it was also a lot for one person in our group to have to deal with most major design resolutions. In the future, a part of class dealing specifically with effective ways to divide up parts could be useful. 
Also, while the purpose of the project was to initially design code well in order to easily make the extensions, it may be more useful to give less time for Sprint 2 and more time for Sprint 3, so that groups will be forced to quickly create something with only good design in mind, and then have more time to understand where they went wrong design wise and then make changes. It seemed as if we were able to understand what we could have done different by the deadline but also recognized that we did not have enough time to restructure. 

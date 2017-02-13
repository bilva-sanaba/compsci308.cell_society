# Cell Society (Cellular Automata)
Mike Liu, Bilva Sanaba, Justin Yang  

Jan 26 - Feb 12, 2017  
Mike ~ 40 hours  
Bilva ~30 hours  
Justin ~ 30 hours  

## Roles
* Mike - Designed the structure of the project and class hierarchy, integrated all parts of the project together, designed all
abstract classes except those in util packages and implemented controller, all front-end components, model inputs, model
managers, cell states, cell generators, the bulk of grid and neighbor finder and Game of Life model, and improved util classes.
* Bilva - Created simulation models for the Wa-Tor and Segregation, as well as back-end grid functionality for identifying
neighbors.
* Justin - Worked on XML parsing, abstract data classes for XML, and saving to XML file.

## Resources
http://www.cs.duke.edu/courses/compsci308/spring17/  
http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/  
http://nifty.stanford.edu/2011/scott-wator-world/  
https://en.wikipedia.org/wiki/Conway's_Game_of_Life  
http://stackoverflow.com/

## Get Started
Program is run through the main class, and no other classes contain main. 

## Testing
XML configuration files used to load simulations are stored in the data package. Debugging was performed by directly running the
program. No unit test was written for the project.

## Resource Files
Resource files for GUI Text and CSS are included in the resource package. XML configuration files are stored in the data package.

## Using the Program
* After the program starts, press load and pick an XML file to load a simulation. Load can also be pressed at any time to change
simulation.
* Cell configuration in XML can be one of three types: specific locations, number of cells by type and probability by type
* Press play to start the simulation, pause to pause, and move the slider to change the animation speed.
* Pressing step will pause the simulation if it is running, and step through the simulation one cycle per click.
* Either while playing or while paused, press step to update the simulation one frame per click.
* Press options for a screen to popup to change various parameters for the current simulation.
* Press save to save the current simulation configuration to an XML file.
* Press +/- to zoom in/out of the simulation.
* Click on a cell to change its state
* If grid is larger than the display region, scroll to navigate the whole grid.
* Cell shape, grid type, and neighbor pattern can all be changed dynamically with the choice boxes at the bottom right.

## Bugs
None detected

## Extra Features
Allow user to dynamically change cell shape, grid type and neighbor pattern during the simulation 

## Impressions
This project was very interesting and a good way to introduce coding in a group. The end result is something we
are all proud of and we are impressed with the project for the amount of time we had. 
It seemed as if a big issue during this project was simply with effective ways to split up work. It seems challenging to have
everyone work on overall design, but it was also a lot for one person in our group to have to deal with most major design
resolutions. In the future, a part of class dealing specifically with effective ways to divide up parts could be useful. 
Also, while the purpose of the project was to initially design code well in order to easily make the extensions, it may be more
useful to give less time for Sprint 2 and more time for Sprint 3, so that groups will be forced to quickly create something with
only good design in mind, and then have more time to understand where the design should be made more extensible.
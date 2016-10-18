# Boondoggle V2

_it's 3:30am so this is a little rough but I wanted to get some of my thoughts out there._

## How-to

1. Open a console and navigate to where your AndroidStudioProjects folder is. In my case it's C:\Users\Eric\AndroidStudioProjects\

2. Do a git clone of the project so the folder structure ends up like C:\Users\Eric\AndroidStudioProjects\COMP3004-Group-Project-Boondoggle

```
git pull https://github.com/Graeme-A-Fraser/COMP3004-Group-Project-Boondoggle.git
```

3. Switch to the branch I've been working on

```
git checkout v2
```

4. Now open android studio and press file -> open

5. You should be able to open COMP3004-Group-Project-Boondoggle directly

6. Now test it out by pressing run

## What's done?

* Rough prototype
* Game loop, frame limiting and skipping
* Model, View, Controller Heirarchy
* Different views (currently just an overall view and the build view)
* Grid (my task woo)
* Buidling towers - very primitive just shows a box on the grid

## Todo

* Refactor/Cleanup code - a little messy at the moment but that's what prototypes are supposed to be
* Need to discuss design decisions (how many towers, creeps, what the building section should look like)
* Adding images/sprites
* Creep spawning system, pathfinding, dank memes
* So much more

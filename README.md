# COMP3004-Group-Project:Boondoggle
Group project for Carleton COMP3004 fall 2016

project name: Boondoggle

members:
Graeme Fraser 1009539793 graeme.fraser.ca@gmail.com
Telidy Ng 100923829 TelidyNg@cmail.carleton.ca
Eric McCarthy 100944658 EricMcCarthy@cmail.carleton.ca
Ryan David 100946431 RyanDavid@cmail.carleton.ca

## Installation

1. Open a console and navigate to where your AndroidStudioProjects folder is. In my case it's C:\Users\Eric\AndroidStudioProjects\

2. Do a git clone of the project so the folder structure ends up like C:\Users\Eric\AndroidStudioProjects\COMP3004-Group-Project-Boondoggle
	```
	git pull https://github.com/Graeme-A-Fraser/COMP3004-Group-Project-Boondoggle.git
	```

3. Now open android studio and press file -> open

4. You should be able to open COMP3004-Group-Project-Boondoggle directly

5. Now test it out by pressing run

## Contributing

_please follow the below when adding new features or making big changes to the code base_

1. Please create a new branch!!
	```
	git checkout -b new_feature_name
	```

2. Edit/Add your files

3. Add changes, commit, and push them
	```
	git add -A
	git commit -m "detailed info about feature / changes"
	git push --set-upstream origin new_feature_name
	```

4. Then if you're confident in your feature being complete, merge it into master.
	* checkout master
		```
		git checkout master
		```
	* merge your feature
		```
		git merge new_feature_name
		```
5. Now push it to the repository and delete your old branch.
	```
	git push
	git push origin --delete new_feature_name
	```


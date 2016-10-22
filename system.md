## Model

* Battleground
* Bullet
* Castle
* Creep
* Player
* Tower

## View

* Renderer
* BattlegroundRenderer
* BuildRenderer
* TowerBuildRenderer
* Recruit Renderer

## Controller

* GameEngine
* BattlegroundController
* BuildController


## Etc

* need to think of a way to spawn minions
* need to finish creating multiple towers, how should we implement?
* creeps need pathing

* GameEngine needs access to everything, pass models down to lower controllers
	* should be one instance of Battleground, Player

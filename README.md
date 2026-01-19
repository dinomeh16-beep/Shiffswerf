# Shiffswerf
Shipyard Strategy Game RequirementsModel a shipping company with a shipyard. The shipping company owns ships that sail for the company and thereby earn money. Unfortunately, the ships rust during operation and sink eventually if you do not repaint them fresh in time or scrap them. If a ship sinks during operation, the loss (salvage costs) is particularly large.
In Detail: In your shipyard, you can build ships:
Cargo ships cost 30 million EUR construction costs.
Tankers cost 70 million EUR.
Passenger ships cost 150 million EUR.
The construction of a ship takes one month. Every ship that you build gets a unique number that identifies it. Used numbers should not be reused.
In every month, a sailing ship brings profit:
Cargo ships bring 0.2 million EUR per month.
Tankers bring 0.5 million EUR per month.
Passenger ships bring 1 million EUR per month.
Every ship has a Rust Factor (proportion of rusted steel in the ship’s skin).
After the construction of the ship, 100% of the ship’s skin is intact.
Thereafter, this proportion decreases every month by a factor of 0.92.
If less than 25% of the ship’s skin is intact, then the ship sinks.
If a ship sinks, the salvage costs and compensation claims cost five times the new price of the ship.
To avoid this, one can:
Scrap the ship. This happens in the shipyard and costs one-tenth (1/10) of the ship's price.
Repaint the ship. This also happens in the shipyard and costs 1 million EUR for a Cargo ship, 3 million EUR for a Tanker, 5 million EUR for a Passenger ship.
  Afterwards, the ship’s skin is intact again to a value of: $(100\% - (5\% \times \text{number of already performed painting processes}))$.
Game Implementation
Implement a strategy game in which a player manages the shipyard month by month.
The game begins with 500 million EUR starting capital in the cash register of the shipyard.
In every month, all existing ships sail and bring their profit into the cash register.
If a ship sinks, the salvage costs are deducted from the cash register and the ship disappears from the inventory.
Every month the player can decide. The possible actions are:
Build a Cargo ship
Build a Tanker
Build a Passenger ship
Repaint a ship (identified by its ship number)
Scrap a shipDo nothing
The game is lost if the cash balance becomes negative or any other rule (see above) is violated. The goal is to drive the cash balance as high as possible.
Summary of Logic for Coding
To help you translate this into code, here is a quick data table based on the text:
Ship Type,Build Cost (M),Monthly Profit (M),Paint Cost (M),Sink Cost (M),Scrap Cost (M)
Cargo,30,0.2,1,150,3
Tanker,70,0.5,3,350,7
Passenger,150,1.0,5,750,15
Health Formula:$$NewHealth = CurrentHealth \times 0.92$$
Repair Formula:$$MaxHealth = 100 - (5 \times TimesPainted)$$

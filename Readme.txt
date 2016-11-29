
This raffle program has been put together primarily for handling raffle drawings on Flight Rising, 
originally for the creative raffle (and perhaps others) in Fire flight, but I don't mind the 
program being passed around to other people who find it useful. I'm happy to fix bugs and 
add settings people might find useful.

-Gracie88

------------------------------------------------------------

Raffle .csv File Setup
------------------------------
	A .csv is a comma separated (values) file. You can export from Sheets or most other spreadsheet programs
	directly into a .csv format. (This would be under Download As in Sheets.) This format has each row on a 
	new line with commas separating each "cell" of the spreadsheet. For example:
		20151, Gray, 9
		1124, Mom, 5000
		33392, Fletcher, 10000
	
	You'll run into trouble if your data contains any commas inside the cells, including "ignored" columns.

	The raffle program needs 3 columns to function and will ignore all others.
	
	The columns options are given to you as A, B, C, etc. (If your data is in columns beyond G,
	please just delete some unnecessary columns I mean come on.) So those labels would be the column
	labels in Sheets or Excel. If that's confusing, just think of it like 1st column, 2nd column, etc.
	
	Player Name: This would be the username of the player who has the ticket. Used
		only for outputting who won with an @ in front for easy copy/paste.
	
	Player ID: The FR ID number of the player who has the ticket. This column is
		what gets used to identify individual players, i.e. if two rows have the 
		same ID number but different usernames (PlayerName vs Playernmae), they'll
		still be considered the same player. It shouldn't matter if the ID includes
		any letters or special characters, though those characters will be taken into
		account when comparing the IDs for checking identity.
	
	Number of Tickets: This is how many tickets were awarded to the player at that time.
		This column MUST be a number without any special characters or letters in the cell.
		So if they sent in a dragon and got 100 tickets, 100 should be in this column. If
		they later bought 24 tickets with treasure, 24 should be here. If you have a unique 
		row for every single ticket (i.e. you would enter the player's name in 24 rows
		rather than having one row that says 24 tickets), you can create this column and put
		1 as the entry for every single ticket.
	
	The ticket number of each ticket is determined by the program as it creates the tickets for
	each player's entries. It's based purely on reading from the top to the bottom and how many
	tickets each row counts for. If your spreadsheet has specific ranges of ticket numbers assigned
	to specific players, then this program doesn't support taking that into account. 
	
	You may want to delete the winning ticket numbers before posting since they're sort of meaningless.
	I left them in just in case since it is kind of interesting.

------------------------------------------------------------

Seeding Best Practices
------------------------------
	-Do NOT just pick a number. I cannot stress that enough. Use random.org or similar to generate a random seed.
	-The seed can range from 0 to 9,223,372,036,854,775,807.
	-Save the seed number on your computer somewhere in case the raffle results need to be validated.
	-I would caution against making the seed public without good cause.

On Seeding a Random Generator
------------------------------
	I want to explain this concept briefly just to be really clear. Most random number generators, 
	including the one used here, are actually pseudorandom. A computer can't actually generate true 
	randomness. However, the pseudorandom numbers are unpredictable and evenly spread enough that 
	for all normal applications, it's plenty random to work. No one will be able to predict the 
	drawing, and every ticket will have an equal chance.
	
	random.org will allegedly generate truly random numbers. It's not that I doubt them, but their
	formula and process are a mystery to us.
	
	"Seeding" a pseudorandom generator can be thought of as telling it what state to begin its 
	generator in. If I seed it with "1", a specific pseudorandom sequence of numbers will come
	out; if I seed it with "2", a completely different sequence of numbers will come out. By 
	using random.org to generate a truly random seed number (between 0 and the highest possible 
	number random.org will give us so we have the biggest possible range of seeds), we get a
	truly random sequence of pseudorandom numbers.
	
	If I run the program again with exactly the same raffle file and seed number, I will get the exact 
	same sequence of winners as before - so if the raffle winners are lost or someone is questioning
	the validity of the sequence, it's repeatable as long as you've saved the seed somewhere. That also
	means a third party should be able to validate the results by using exactly the same inputs as well.
	
	In the language this program is in (Java), if a seed is not given for its random number generator,
	the default behavior is to just pick a random seed based on the system clock. Seeding is going
	to happen anyway, so might as well take advantage of that by picking it ourselves.
	
	https://en.wikipedia.org/wiki/Random_seed
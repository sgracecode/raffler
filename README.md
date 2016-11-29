# raffler
I developed this program quickly as a simple tool for doing a raffle drawing for a game community's creative raffle. My primary concerns were assuredness of a fair drawing and ability to be used by someone who isn't a programmer.

## Instructions for users
The README_Visual_Instructions.pdf file is a copy of the Google Doc I shared publicly with the other volunteers handling the raffle. I wanted any of us to be able to do or validate the drawing using this tool.

## Pre-existing process
Because this tool was embedded deeply in the exact needs of this community event, much of the design and rapid development was shaped by the processes already in place. Raffles (and general management of mass resource commitments or sales) happened frequently, and almost always they were tracked with Google Sheets with time-saving and data-validating formulas. This creative raffle had that in place as well. When picking raffle winners, a volunteer for the event would take the spreadsheet, assign ticket numbers to each participant, and use random.org to generate a number matching a random ticket number. This by-hand process was time consuming and prone to transposition errors; a few months before I created this tool, a typing error on a major raffle event caused a community scandal when the volunteer put in the range on random.org one factor too small, effectively eliminating the chance of 90% of the tickets from being drawn. 

So, I wanted to automate the ticket drawing process in a way that worked conveniently with Google Sheets, but that controlled the handling of random number generation. I wanted to use the seeding of Java's pseudorandom generator to be sure that we could validate our results if participants were questioning the fairness of the raffle.

I was also able to tailor the output in a format that would paste conveniently into the forums our community uses to communicate.

## The spreadsheet used
I was the main developer of the specific spreadsheet we used to handle the raffle (based on previous spreadsheets from other raffles in the community), and it can setill be found live here: https://docs.google.com/spreadsheets/d/1WONPfn_KxXiWdpboNIkjxR75L7MOlykpyGNGc4cArGU/edit#gid=0
